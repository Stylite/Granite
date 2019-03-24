package bot.bricolo.granite.entities.payload.filters;

import bot.bricolo.granite.entities.IJsonSerializable;
import org.json.JSONObject;

import java.util.List;

public class Karaoke implements IJsonSerializable {
    private final float level;
    private final float monoLevel;
    private final float filterBand;
    private final float filterWidth;

    public Karaoke(float level, float monoLevel, float filterBand, float filterWidth) {
        this.level = level;
        this.monoLevel = monoLevel;
        this.filterBand = filterBand;
        this.filterWidth = filterWidth;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("level", level)
                .put("monoLevel", monoLevel)
                .put("filterBand", filterBand)
                .put("filterWidth", filterWidth);
    }
}
