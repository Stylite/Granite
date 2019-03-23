package bot.bricolo.granite.entities;

import bot.bricolo.granite.Granite;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Map;

public abstract class AbstractSocket {
    protected final Granite granite;
    private DisposableSocket socket;
    private final URI serverUri;
    private final Draft draft;
    private final Map<String, String> headers;
    private final int connectTimeout;
    private final AbstractSocket instance = this; // For use in inner class
    private boolean isUsed = false;

    public AbstractSocket(Granite granite, URI serverUri, Draft draft, Map<String, String> headers, int connectTimeout) {
        this.granite = granite;
        this.serverUri = serverUri;
        this.draft = draft;
        this.headers = headers;
        this.connectTimeout = connectTimeout;
    }

    // Connecting
    protected void connect() {
        if (socket == null || isUsed) socket = new DisposableSocket(serverUri, draft, headers, connectTimeout);
        socket.connect();
        isUsed = true;
    }

    // Connecting
    public void send(IJsonSerializable json) {
        send(json.toJson().toString());
    }

    private void send(String text) {
        System.out.println("~> " + text);
        if (socket != null && socket.isOpen()) {
            granite.LOG.debug("~> " + text);
            socket.send(text);
        }
    }

    // Closing
    public void close() {
        if (socket != null)
            socket.close();
    }

    public void close(int code) {
        if (socket != null)
            socket.close(code);
    }

    public void close(int code, String reason) {
        if (socket != null)
            socket.close(code, reason);
    }

    // Getters
    public URI getServerUri() {
        return this.serverUri;
    }

    public InetSocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    public boolean isOpen() {
        return socket != null && socket.isOpen();
    }

    public boolean isConnecting() {
        return socket != null && socket.getReadyState() == ReadyState.NOT_YET_CONNECTED;
    }

    public boolean isClosed() {
        return socket == null || socket.isClosed();
    }

    public boolean isClosing() {
        return socket != null && socket.isClosing();
    }

    // Set Resume-Id
    protected void setResumeId(String id) {
        headers.put("Resume-Id", id);
    }

    // Abstractions
    public abstract void onOpen(ServerHandshake handshakeData);

    public abstract void onMessage(String message);

    public abstract void onClose(int code, String reason, boolean remote);

    public abstract void onError(Exception ex);

    // Inner class
    private class DisposableSocket extends WebSocketClient {
        DisposableSocket(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout) {
            super(serverUri, protocolDraft, httpHeaders, connectTimeout);
            isUsed = false;
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            instance.onOpen(handshakedata);
        }

        @Override
        public void onMessage(String message) {
            System.out.println("<~ " + message);
            granite.LOG.debug("<~ " + message);
            instance.onMessage(message);
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            System.out.println("big fat rip " + code + " " + reason + " " + remote);
            instance.onClose(code, reason, remote);
        }

        @Override
        public void onError(Exception ex) {
            instance.onError(ex);
        }
    }
}
