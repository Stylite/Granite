package bot.bricolo.granite.entities.payload;

import bot.bricolo.granite.entities.IJsonSerializable;
import org.json.JSONObject;

public class Volume implements IJsonSerializable {
    private final String guildId;
    private final int volume;

    public Volume(String guildId, int volume) {
        this.guildId = guildId;
        this.volume = volume;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("op", "volume")
                .put("volume", volume)
                .put("guildId", guildId);
    }
}
