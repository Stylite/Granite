package bot.bricolo.granite;

import bot.bricolo.granite.entities.AbstractSocket;
import bot.bricolo.granite.entities.payload.EventBuffer;
import net.dv8tion.jda.api.Region;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AndesiteNode extends AbstractSocket {
    private final URI uri;
    private final Map<String, String> headers;
    private final List<Region> regions;
    private final String name;

    private AndesiteNode(Granite granite, URI uri, Map<String, String> headers, List<Region> regions, String name) {
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
        send(new EventBuffer(30));
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    public void onMessage(String message) {
        JSONObject payload = new JSONObject(message);
        String op = payload.getString("op");
        switch (op) {
            case "connection-id":
                setResumeId(payload.getString("id"));
                break;
            default:
                granite.LOG.warn("Received an invalid OP: " + op);
                break;
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        granite.LOG.warn("Connection with node " + name + " closed (" + code + " " + reason + "; Was remote: " + remote + ")");
    }

    @Override
    public void onError(Exception ex) {
        granite.LOG.error("An exception occurred in node " + name, ex);
    }

    //*********//
    // Getters //
    //*********//
    String getHost() {
        return uri.getHost();
    }

    int getPort() {
        return uri.getPort();
    }

    String getUserId() {
        return headers.get("User-Id");
    }

    String getPassword() {
        return headers.get("Authorization");
    }

    //********************//
    // Pseudo-constructor //
    //********************//
    static AndesiteNode makeNode(Granite granite, String host, int port, String password, String userId, List<Region> regions, String name) throws URISyntaxException {
        URI uri = new URI("ws://" + host + ":" + port + "/websocket");
        Map<String, String> headers = new HashMap<>();

        headers.put("Authorization", password);
        headers.put("User-Id", userId);
        headers.put("User-Agent", "Granite v" + GraniteVersion.VERSION + " (https://github.com/BricoloDuDimanche/Granite)");
        return new AndesiteNode(granite, uri, headers, regions, name);
    }
}
