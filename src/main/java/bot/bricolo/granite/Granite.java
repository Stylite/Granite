package bot.bricolo.granite;

import bot.bricolo.granite.andesite.Node;
import bot.bricolo.granite.andesite.Player;
import bot.bricolo.granite.entities.Region;
import bot.bricolo.granite.entities.Track;
import net.dv8tion.jda.api.entities.Guild;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("WeakerAccess")
public class Granite {
    public final Logger LOG = LoggerFactory.getLogger(Granite.class);

    private final OkHttpClient httpClient;
    private final List<Node> nodes = new ArrayList<>();
    private final Map<String, Player> players = new HashMap<>();

    public Granite() {
        httpClient = new OkHttpClient();
    }

    //*****************//
    // Node management //
    //*****************//
    public void addNode(String host, int port, String password, String userId) throws URISyntaxException {
        addNode(host, port, password, userId, Arrays.asList(Region.values()));
    }

    public void addNode(String host, int port, String password, String userId, String name) throws URISyntaxException {
        addNode(host, port, password, userId, Arrays.asList(Region.values()), name);
    }

    public void addNode(String host, int port, String password, String userId, List<Region> regions) throws URISyntaxException {
        addNode(host, port, password, userId, regions, "AndesiteNode@" + host + ":" + port);
    }

    public void addNode(String host, int port, String password, String userId, List<Region> regions, String name) throws URISyntaxException {
        nodes.add(Node.makeNode(this, host, port, password, userId, regions, name));
    }

    //*******************//
    // Player management //
    //*******************//
    @Nullable
    public Player getPlayer(Guild guild) {
        return getPlayer(guild.getId());
    }

    @Nullable
    public Player getPlayer(com.mewna.catnip.entity.guild.Guild guild) {
        return getPlayer(guild.id());
    }

    @Nullable
    public Player getPlayer(String guildId) {
        return players.get(guildId);
    }

    public Player createPlayer(Guild guild) {
        return createPlayer(guild.getId());
    }

    public Player createPlayer(com.mewna.catnip.entity.guild.Guild guild) {
        return createPlayer(guild.id());
    }

    public Player createPlayer(String guildId) {
        return players.computeIfAbsent(guildId, Function -> new Player(this, guildId));
    }

    public void removePlayer(Guild guild) {
        removePlayer(guild.getId());
    }

    public void removePlayer(com.mewna.catnip.entity.guild.Guild guild) {
        removePlayer(guild.id());
    }

    public void removePlayer(String guildId) {
        players.remove(guildId);
    }

    //*******//
    // Utils //
    //*******//
    public CompletableFuture<Track> getTrack(String search) {
        CompletableFuture<Track> completableFuture = new CompletableFuture<>();
        String identifier = search;
        try {
            new URI(search);
        } catch (URISyntaxException e) {
            identifier = "ytsearch:" + search;
        }

        search(identifier).thenAccept(trackList -> {
            if (trackList.size() == 0) return;
            try {
                completableFuture.complete(trackList.get(0));
            } catch (IndexOutOfBoundsException e1) {
                completableFuture.complete(null);
            }
        });
        return completableFuture;
    }

    public CompletableFuture<List<Track>> search(String search) {
        CompletableFuture<List<Track>> completableFuture = new CompletableFuture<>();

        int index = ThreadLocalRandom.current().nextInt(nodes.size());
        Node node = nodes.get(index);

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(node.getHost())
                .port(node.getPort())
                .addPathSegment("loadtracks")
                .addQueryParameter("identifier", search)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", node.getPassword())
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@Nonnull Call call, @Nonnull IOException e) {
                completableFuture.completeExceptionally(e);
            }

            @Override
            public void onResponse(@Nonnull Call call, @Nonnull Response response) throws IOException {
                ResponseBody body = response.body();
                if (body == null) {
                    completableFuture.completeExceptionally(new IOException("No response body"));
                    return;
                }

                List<Track> tracks = new ArrayList<>();
                JSONObject payload = new JSONObject(body.string());
                JSONArray jsonTracks = payload.getJSONArray("tracks");
                jsonTracks.forEach((track) -> tracks.add(new Track((JSONObject) track)));
                completableFuture.complete(tracks);
            }
        });

        return completableFuture;
    }

    //****************//
    // Internal Utils //
    //****************//
    public List<Node> getAvailableNodes() {
        List<Node> availableNodes = new ArrayList<>();
        nodes.forEach((node) -> {
            if (node.isOpen()) {
                availableNodes.add(node);
            }
        });
        return availableNodes;
    }
}
