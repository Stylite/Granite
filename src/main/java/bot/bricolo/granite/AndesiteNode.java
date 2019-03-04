package bot.bricolo.granite;

import bot.bricolo.granite.network.SocketClient;
import io.netty.channel.Channel;
import net.dv8tion.jda.api.Region;

import java.util.Arrays;
import java.util.List;

public class AndesiteNode {
    private final String host;
    private final int port;
    private final String password;
    private final String userId;
    private final List<Region> regions;
    private final String name;

    private final SocketClient socket;
    private Channel channel;

    public AndesiteNode(String host, int port, String password, String userId) {
        this(host, port, password, userId, Arrays.asList(Region.values()));
    }

    public AndesiteNode(String host, int port, String password, String userId, String name) {
        this(host, port, password, userId, Arrays.asList(Region.values()), name);
    }

    public AndesiteNode(String host, int port, String password, String userId, List<Region> regions) {
        this(host, port, password, userId, regions, "AndesiteNode@" + host + ":" + port);
    }

    public AndesiteNode(String host, int port, String password, String userId, List<Region> regions, String name) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.userId = userId;
        this.regions = regions;
        this.name = name;

        this.socket = new SocketClient(this);
        this.socket.buildChannel().thenAccept(channel -> this.channel = channel);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }
}
