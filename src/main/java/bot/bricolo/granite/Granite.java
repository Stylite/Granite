package bot.bricolo.granite;

import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.Guild;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@SuppressWarnings("WeakerAccess")
public class Granite {
    private final Logger LOG = LoggerFactory.getLogger(Granite.class);
    final List<AndesiteNode> nodes = new ArrayList<>();
    final Map<Long, AndesitePlayer> players = new HashMap<>();

    public void addNode(String host, int port, String password, String userId) {
        nodes.add(new AndesiteNode(host, port, password, userId, Arrays.asList(Region.values())));
    }

    public void addNode(String host, int port, String password, String userId, String name) {
        nodes.add(new AndesiteNode(host, port, password, userId, Arrays.asList(Region.values()), name));
    }

    public void addNode(String host, int port, String password, String userId, List<Region> regions) {
        nodes.add(new AndesiteNode(host, port, password, userId, regions, "AndesiteNode@" + host + ":" + port));
    }

    public void addNode(String host, int port, String password, String userId, List<Region> regions, String name) {
        nodes.add(new AndesiteNode(host, port, password, userId, regions, name));
    }

    public AndesitePlayer getPlayer(Guild guild) {
        return getPlayer(guild.getIdLong());
    }

    public AndesitePlayer getPlayer(Long guildId) {
        return players.computeIfAbsent(guildId, Function -> new AndesitePlayer(this, guildId));
    }

    public void removePlayer(Guild guild) {
        removePlayer(guild.getIdLong());
    }

    public void removePlayer(Long guildId) {
        players.remove(guildId);
    }
}
