package bot.bricolo.granite.entities.payload;

import bot.bricolo.granite.entities.IJsonSerializable;
import org.json.JSONObject;

public class Play implements IJsonSerializable {
    private final String track;
    private final boolean noReplace;

    public Play(String track) {
        this(track, true);
    }

    public Play(String track, boolean noReplace) {
        this.track = track;
        this.noReplace = noReplace;
    }

    @Override
    public JSONObject toJson() {
        JSONObject payload = new JSONObject();
        payload.append("track", track);
        payload.append("noReplace", noReplace);
        return payload;
    }
}
