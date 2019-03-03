package bot.bricolo.granite;

import java.util.Arrays;
import java.util.List;

class AndesiteNode {
    private final String address;
    private final int port;
    private final String password;
    private final List<Region> regions;
    private final String name;

    AndesiteNode(String address, int port, String password) {
        this(address, port, password, Arrays.asList(Region.values()));
    }

    AndesiteNode(String address, int port, String password, String name) {
        this(address, port, password, Arrays.asList(Region.values()), name);
    }

    AndesiteNode(String address, int port, String password, List<Region> regions) {
        this(address, port, password, regions, "AndesiteNode@" + address + ":" + port);
    }

    AndesiteNode(String address, int port, String password, List<Region> regions, String name) {
        this.address = address;
        this.port = port;
        this.password = password;
        this.regions = regions;
        this.name = name;
    }
}
