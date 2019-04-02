package bot.bricolo.granite.andesite;

import bot.bricolo.granite.Granite;
import bot.bricolo.granite.GraniteVersion;
import bot.bricolo.granite.Utils;
import bot.bricolo.granite.entities.AbstractSocket;
import bot.bricolo.granite.entities.Region;
import bot.bricolo.granite.exceptions.AudioTrackEncodingException;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node extends AbstractSocket {
    private final URI uri;
    private final Map<String, String> headers;
    private final List<Region> regions;
    private final String name;

    private Node(Granite granite, URI uri, Map<String, String> headers, List<Region> regions, String name) {
        super(granite, uri, new Draft_6455(), headers, 10);
        this.uri = uri;
        this.headers = headers;
        this.regions = regions;
        this.name = name;
        this.connect();
    }

    //********//
    // Events //
    //********//
    @Override
    public void onOpen(ServerHandshake handshakeData) {
        granite.LOG.info("Handshake with node " + name + " completed (" + handshakeData.getHttpStatus() + " " + handshakeData.getHttpStatusMessage() + ")");
        // send(new EventBuffer(30));
    }

    public void onMessage(String message) {
        JSONObject payload = new JSONObject(message);
        String op = payload.getString("op");
        switch (op) {
            case "connection-id":
                setResumeId(payload.getString("id"));
                break;
            case "player-update":
                Player player = granite.getPlayer(payload.getString("guildId"));
                if (player != null) player.playerUpdate(payload);
                break;
            case "event":
                player = granite.getPlayer(payload.getString("guildId"));
                if (player != null) {
                    try {
                        player.handleEvent(payload);
                    } catch (AudioTrackEncodingException e) {
                        granite.LOG.error("Failed to handle event " + payload.getString("type"), e);
                    }
                }
                break;
            default:
                granite.LOG.warn("Received an invalid OP: " + op);
                break;
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        granite.LOG.warn("Connection with node " + name + " closed (" + code + " " + reason + "; Was remote: " + remote + ")");
        granite.LOG.warn("Trying to reconnect in 5s...");
        Utils.setTimeout(this::connect, 5000);
    }

    @Override
    public void onError(Exception ex) {
        granite.LOG.error("An exception occurred in node " + name, ex);
    }

    //*********//
    // Getters //
    //*********//
    public String getHost() {
        return uri.getHost();
    }

    public int getPort() {
        return uri.getPort();
    }

    public String getUserId() {
        return headers.get("User-Id");
    }

    public String getPassword() {
        return headers.get("Authorization");
    }

    //********************//
    // Pseudo-constructor //
    //********************//
    public static Node makeNode(Granite granite, String host, int port, String password, String userId, List<Region> regions, String name) throws URISyntaxException {
        URI uri = new URI("ws://" + host + ":" + port + "/websocket");
        Map<String, String> headers = new HashMap<>();

        headers.put("Authorization", password);
        headers.put("User-Id", userId);
        headers.put("User-Agent", "Granite v" + GraniteVersion.VERSION + " (https://github.com/BricoloDuDimanche/Granite)");
        return new Node(granite, uri, headers, regions, name);
    }
}
