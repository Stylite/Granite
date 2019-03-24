package bot.bricolo.granite.entities.payload;

import bot.bricolo.granite.entities.IJsonSerializable;
import bot.bricolo.granite.entities.payload.filters.*;
import org.json.JSONObject;

public class Filter implements IJsonSerializable {
    private final String guildId;
    private IJsonSerializable equalizer;
    private IJsonSerializable karaoke;
    private IJsonSerializable timescale;
    private IJsonSerializable tremolo;
    private IJsonSerializable vibrato;

    public Filter(String guildId, Equalizer equalizer, Karaoke karaoke, Timescale timescale, Tremolo tremolo, Vibrato vibrato) {
        this.guildId = guildId;
        this.equalizer = equalizer;
        this.karaoke = karaoke;
        this.timescale = timescale;
        this.tremolo = tremolo;
        this.vibrato = vibrato;
    }

    public Filter resetEqualizer() {
        this.equalizer = new ResetFilter();
        return this;
    }

    public Filter resetKaraoke() {
        this.karaoke = new ResetFilter();
        return this;
    }

    public Filter resetTimescale() {
        this.timescale = new ResetFilter();
        return this;
    }

    public Filter resetTremolo() {
        this.tremolo = new ResetFilter();
        return this;
    }

    public Filter resetVibrato() {
        this.vibrato = new ResetFilter();
        return this;
    }

    @Override
    public JSONObject toJson() {
        JSONObject payload = new JSONObject()
                .put("op", "filter")
                .put("guildId", guildId);

        if (equalizer != null) payload.put("equalizer", equalizer.toJson());
        if (karaoke != null) payload.put("karaoke", karaoke.toJson());
        if (timescale != null) payload.put("timescale", timescale.toJson());
        if (tremolo != null) payload.put("tremolo", tremolo.toJson());
        if (vibrato != null) payload.put("vibrato", vibrato.toJson());

        return payload;
    }
}
