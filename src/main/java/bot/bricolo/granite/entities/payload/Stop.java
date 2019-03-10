package bot.bricolo.granite.entities.payload;

import bot.bricolo.granite.entities.IJsonSerializable;
import org.json.JSONObject;

public class Stop implements IJsonSerializable {
    private final String guildId;

    public Stop(String guildId) {
        this.guildId = guildId;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("op", "stop")
                .put("guildId", guildId);
    }
}
