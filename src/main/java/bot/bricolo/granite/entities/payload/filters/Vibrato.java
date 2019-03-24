package bot.bricolo.granite.entities.payload.filters;

import bot.bricolo.granite.entities.IJsonSerializable;
import org.json.JSONObject;

public class Vibrato implements IJsonSerializable {
    private final float frequency;
    private final float depth;

    public Vibrato(float frequency, float depth) {
        if (frequency <= 0 || frequency > 14 || depth <= 0 || depth > 1) {
            throw new IllegalArgumentException("frequency must be  between 0 (excluded) and 14 (included) and depth between 0 (excluded) and 1 (included)");
        }

        this.frequency = frequency;
        this.depth = depth;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("frequency", frequency)
                .put("depth", depth);
    }
}
