package bot.bricolo.granite.entities.payload.filters;

import bot.bricolo.granite.entities.IJsonSerializable;
import org.json.JSONObject;

import java.util.List;

public class Equalizer implements IJsonSerializable {
    private final List<Band> bands;

    public Equalizer(List<Band> bands) {
        this.bands = bands;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("bands", bands);
    }

    public class Band implements IJsonSerializable {
        private final int band;
        private final float gain;

        public Band(int band, float gain) {
            if (band < 0 || band > 14 || gain < -25.0 || gain > 1.0) {
                throw new IllegalArgumentException("Band must be an int between 0 and 14 and gain must be a float between -25.0 and 1.0");
            }

            this.band = band;
            this.gain = gain;
        }

        @Override
        public JSONObject toJson() {
            return new JSONObject()
                    .put("band", this.band)
                    .put("gain", this.gain);
        }
    }
}
