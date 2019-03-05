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
        return new JSONObject()
                .put("timeout", timeout);
    }
}
