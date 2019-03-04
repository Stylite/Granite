package bot.bricolo.granite.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public abstract class SocketClient {
    private final NioEventLoopGroup group;
    private Channel channel;

    public SocketClient() {
        this.group = new NioEventLoopGroup();
    }

    protected void buildChannel() {
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new HeadersHandler(this.getPassword(), this.getUserId()))
                .handler(new SocketInitializer());

        ChannelFuture channelFuture = b.connect(this.getHost() + "/websocket", this.getPort());
        channelFuture.addListener((ChannelFutureListener) future -> this.channel = future.channel());
    }

    public void shutdown() {
        group.shutdownGracefully();
    }

    public abstract String getHost();

    public abstract int getPort();

    public abstract String getPassword();

    public abstract String getUserId();
}
