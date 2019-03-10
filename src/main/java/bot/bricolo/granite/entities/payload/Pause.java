package bot.bricolo.granite.entities.payload;

import bot.bricolo.granite.entities.IJsonSerializable;
import org.json.JSONObject;

public class Pause implements IJsonSerializable {
    private final String guildId;
    private final Boolean pause;

    public Pause(String guildId, Boolean pause) {
        this.guildId = guildId;
        this.pause = pause;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("op", "pause")
                .put("pause", pause)
                .put("guildId", guildId);
    }
}
