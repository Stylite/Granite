package bot.bricolo.granite.entities;

import org.json.JSONObject;

public class EventBufferPayload implements IJsonSerializable {
    private final int timeout;

    public EventBufferPayload(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public JSONObject toJson() {
        JSONObject payload = new JSONObject();
        payload.append("timeout", timeout);
        return payload;
    }
}
