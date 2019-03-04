package bot.bricolo.granite;

import bot.bricolo.granite.network.SocketClient;
import net.dv8tion.jda.api.Region;

import java.util.Arrays;
import java.util.List;

public class AndesiteNode extends SocketClient {
    private final String host;
    private final int port;
    private final String password;
    private final String userId;
    private final List<Region> regions;
    private final String name;

    AndesiteNode(String host, int port, String password, String userId) {
        this(host, port, password, userId, Arrays.asList(Region.values()));
    }

    AndesiteNode(String host, int port, String password, String userId, String name) {
        this(host, port, password, userId, Arrays.asList(Region.values()), name);
    }

    AndesiteNode(String host, int port, String password, String userId, List<Region> regions) {
        this(host, port, password, userId, regions, "AndesiteNode@" + host + ":" + port);
    }

    AndesiteNode(String host, int port, String password, String userId, List<Region> regions, String name) {
        super();
        this.host = host;
        this.port = port;
        this.password = password;
        this.userId = userId;
        this.regions = regions;
        this.name = name;
        this.buildChannel();
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
