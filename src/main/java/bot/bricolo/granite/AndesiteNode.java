package bot.bricolo.granite;

import bot.bricolo.granite.entities.EventBufferPayload;
import bot.bricolo.granite.entities.IJsonSerializable;
import bot.bricolo.granite.network.HeadersHandler;
import bot.bricolo.granite.network.SocketInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.dv8tion.jda.api.Region;
import org.json.JSONObject;

import java.util.List;

public class AndesiteNode {
    private final Granite granite;
    private final String host;
    private final int port;
    private final String password;
    private final String userId;
    private final List<Region> regions;
    private final String name;

    private final NioEventLoopGroup group;
    private final Bootstrap bootstrap;

    public String connectionId;
    boolean connected = false;
    private Channel channel;

    AndesiteNode(Granite granite, String host, int port, String password, String userId, List<Region> regions, String name) {
        this.granite = granite;
        this.host = host;
        this.port = port;
        this.password = password;
        this.userId = userId;
        this.regions = regions;
        this.name = name;

        this.group = new NioEventLoopGroup();
        this.bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new HeadersHandler(this))
                .handler(new SocketInitializer(this));
    }

    //***********************//
    // Connection Management //
    //***********************//
    private void connect() {
        ChannelFuture channelFuture = bootstrap.connect(host + "/websocket", port);
        channelFuture.addListener((ChannelFutureListener) future -> {
            connected = true;
            channel = future.channel();
            send(new EventBufferPayload(30));
        });
    }

    private void disconnect() {
        channel.close();
        channel = null;
        connected = false;
    }

    public void shutdown() {
        group.shutdownGracefully();
    }

    //*********//
    // Sending //
    //*********//
    private void send(IJsonSerializable json) {
        send(json.toJson());
    }

    private void send(JSONObject json) {
        send(json.toString());
    }

    private void send(String json) {
        if (connected) {
            channel.writeAndFlush(json);
        }
    }

    //********//
    // Events //
    //********//
    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    public void onMessage(String message) {
        JSONObject payload = new JSONObject(message);
        String op = payload.getString("op");
        switch (op) {
            case "connection-id":
                connectionId = payload.getString("id");
                break;
            default:
                granite.LOG.warn("Received an invalid OP: " + op);
                break;
        }
    }

    public void onNetworkError(Throwable error) {
        granite.LOG.error("A network error occurred in the node " + name, error);
        this.disconnect();
        granite.LOG.warn("Reconnecting in 5 seconds");
        Utils.setTimeout(this::connect, 5000);
    }

    //*********//
    // Getters //
    //*********//
    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
