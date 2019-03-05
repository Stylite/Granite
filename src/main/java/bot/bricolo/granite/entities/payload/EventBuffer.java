package bot.bricolo.granite.entities.payload;

import bot.bricolo.granite.entities.IJsonSerializable;
import org.json.JSONObject;

public class EventBuffer implements IJsonSerializable {
    private final int timeout;

    public EventBuffer(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public JSONObject toJson() {
        JSONObject payload = new JSONObject();
        payload.append("timeout", timeout);
        return payload;
    }
}
