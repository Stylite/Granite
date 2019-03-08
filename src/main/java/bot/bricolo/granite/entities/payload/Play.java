package bot.bricolo.granite.entities.payload;

import bot.bricolo.granite.entities.IJsonSerializable;
import org.json.JSONObject;

public class Play implements IJsonSerializable {
    private final String guildId;
    private final String track;
    private final boolean noReplace;

    public Play(String guildId, String track) {
        this(guildId, track, false);
    }

    public Play(String guildId, String track, boolean noReplace) {
        this.guildId = guildId;
        this.track = track;
        this.noReplace = noReplace;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("op", "play")
                .put("guildId", guildId)
                .put("track", track)
                .put("noReplace", noReplace);
    }
}
