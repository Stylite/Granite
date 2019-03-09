package bot.bricolo.granite.entities.payload;

import bot.bricolo.granite.entities.IJsonSerializable;
import org.json.JSONObject;

public class Pause implements IJsonSerializable {
    private final Boolean pause;

    public Pause(Boolean pause) {
        this.pause = pause;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("op", "pause")
                .put("pause", pause);
    }
}
