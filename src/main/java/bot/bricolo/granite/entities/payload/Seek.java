package bot.bricolo.granite.entities.payload;

import bot.bricolo.granite.entities.IJsonSerializable;
import org.json.JSONObject;

public class Seek implements IJsonSerializable {
    private final String guildId;
    private final int position;

    public Seek(String guildId, int position) {
        this.guildId = guildId;
        this.position = position;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("op", "seek")
                .put("position", position)
                .put("guildId", guildId);
    }
}
