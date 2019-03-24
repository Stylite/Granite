package bot.bricolo.granite.entities.payload.filters;

import bot.bricolo.granite.entities.IJsonSerializable;
import org.json.JSONObject;

public class Timescale implements IJsonSerializable {
    private final float speed;
    private final float pitch;
    private final float rate;

    public Timescale() {
        speed = 1.0f;
        pitch = 1.0f;
        rate = 1.0f;
    }

    public Timescale(float speed, float pitch, float rate) {
        if (speed <= 0 || pitch <= 0 || rate <= 0) {
            throw new IllegalArgumentException("speed, pitch and rate must be floats and > 0");
        }

        this.speed = speed;
        this.pitch = pitch;
        this.rate = rate;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("speed", speed)
                .put("pitch", pitch)
                .put("rate", rate);
    }
}
