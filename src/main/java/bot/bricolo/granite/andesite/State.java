package bot.bricolo.granite.andesite;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class State {
    // General
    private final String guildId;

    private long nodeTime;
    private AudioTrack track;
    private int position;
    private boolean paused;
    private int volume;

    // Filters
    private int timescaleSpeed;
    private int timescalePitch;
    private int timescaleRate;
    private boolean timescaleEnabled;

    private int volumeVolume;
    private boolean volumeEnabled;

    private int karaokeLevel;
    private int karaokeMonoLevel;
    private int karaokeFilterBand;
    private int karaokeFilterWidth;
    private boolean karaokeEnabled;

    private int tremoloFrequency;
    private float tremoloDepth;
    private boolean tremoloEnabled;

    private int vibratoFrequency;
    private float vibratoDepth;
    private boolean vibratoEnabled;

    private List<Float> equalizerBands;
    private boolean equalizerEnabled;

    // Mixer

    // Frames
    private int packetLoss;
    private int packetsSent;

    State(String guildId) {
        this.guildId = guildId;
    }

    public long getNodeTime() {
        return nodeTime;
    }

    public AudioTrack getTrack() {
        return track;
    }

    public int getPosition() {
        return position;
    }

    public boolean isPaused() {
        return paused;
    }

    public int getVolume() {
        return volume;
    }

    public int getTimescaleSpeed() {
        return timescaleSpeed;
    }

    public int getTimescalePitch() {
        return timescalePitch;
    }

    public int getTimescaleRate() {
        return timescaleRate;
    }

    public boolean isTimescaleEnabled() {
        return timescaleEnabled;
    }

    public int getVolumeVolume() {
        return volumeVolume;
    }

    public boolean isVolumeEnabled() {
        return volumeEnabled;
    }

    public int getKaraokeLevel() {
        return karaokeLevel;
    }

    public int getKaraokeMonoLevel() {
        return karaokeMonoLevel;
    }

    public int getKaraokeFilterBand() {
        return karaokeFilterBand;
    }

    public int getKaraokeFilterWidth() {
        return karaokeFilterWidth;
    }

    public boolean isKaraokeEnabled() {
        return karaokeEnabled;
    }

    public int getTremoloFrequency() {
        return tremoloFrequency;
    }

    public float getTremoloDepth() {
        return tremoloDepth;
    }

    public boolean isTremoloEnabled() {
        return tremoloEnabled;
    }

    public int getVibratoFrequency() {
        return vibratoFrequency;
    }

    public float getVibratoDepth() {
        return vibratoDepth;
    }

    public boolean isVibratoEnabled() {
        return vibratoEnabled;
    }

    public List<Float> getEqualizerBands() {
        return equalizerBands;
    }

    public boolean isEqualizerEnabled() {
        return equalizerEnabled;
    }

    public int getPacketLoss() {
        return packetLoss;
    }

    public int getPacketsSent() {
        return packetsSent;
    }

    public void setTrack(AudioTrack track) {
        this.track = track;
    }

    // Payloads
    void decodePayload(JSONObject payload) {
        nodeTime = payload.getLong("time");
        position = payload.getInt("position");
        paused = payload.getBoolean("paused");
        volume = payload.getInt("volume");

        JSONObject filters = payload.getJSONObject("filters");
        JSONObject timescale = filters.getJSONObject("timescale");
        JSONObject volume = filters.getJSONObject("volume");
        JSONObject karaoke = filters.getJSONObject("karaoke");
        JSONObject tremolo = filters.getJSONObject("tremolo");
        JSONObject vibrato = filters.getJSONObject("vibrato");
        JSONObject equalizer = filters.getJSONObject("equalizer");

        timescaleSpeed = timescale.getInt("speed");
        timescalePitch = timescale.getInt("pitch");
        timescaleRate = timescale.getInt("rate");
        timescaleEnabled = timescale.getBoolean("enabled");

        volumeVolume = volume.getInt("volume");
        volumeEnabled = volume.getBoolean("enabled");

        karaokeLevel = karaoke.getInt("level");
        karaokeMonoLevel = karaoke.getInt("monoLevel");
        karaokeFilterBand = karaoke.getInt("filterBand");
        karaokeFilterWidth = karaoke.getInt("filterWidth");
        karaokeEnabled = karaoke.getBoolean("enabled");

        tremoloFrequency = tremolo.getInt("frequency");
        tremoloDepth = tremolo.getBigDecimal("depth").floatValue();
        tremoloEnabled = tremolo.getBoolean("enabled");

        vibratoFrequency = vibrato.getInt("frequency");
        vibratoDepth = vibrato.getBigDecimal("depth").floatValue();
        vibratoEnabled = vibrato.getBoolean("enabled");

        List<Float> bands = new ArrayList<>();
        equalizer.getJSONArray("bands").forEach(band -> bands.add(new BigDecimal(band.toString()).floatValue()));
        equalizerBands = bands;
        equalizerEnabled = equalizer.getBoolean("enabled");

        JSONObject frame = payload.getJSONObject("frame");

        packetLoss = frame.getInt("loss");
        packetsSent = frame.getInt("success");
    }

    // Used to build payload required to resume playing on another node
    void /* List<IJsonSerializable> */ buildPayloads() {
        // Filter packets
        // State packets
        // Play track
    }
}
