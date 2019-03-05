package bot.bricolo.granite.entities;

import bot.bricolo.granite.Utils;
import bot.bricolo.granite.exceptions.AudioTrackEncodingException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import org.json.JSONObject;

public class Track {
    private final String track;
    private final String lavaClass;
    private final String title;
    private final String author;
    private final int length;
    private final String uri;
    private final String identifier;
    private final boolean isStream;
    private final boolean isSeekable;
    private final int position;

    public Track(JSONObject jsonObject) {
        JSONObject info = jsonObject.getJSONObject("info");

        this.track = jsonObject.getString("track");
        this.lavaClass = info.getString("class");
        this.title = info.getString("title");
        this.author = info.getString("author");
        this.length = info.getInt("length");
        this.identifier = info.getString("identifier");
        this.uri = info.getString("uri");
        this.isStream = info.getBoolean("isStream");
        this.isSeekable = info.getBoolean("isSeekable");
        this.position = info.getInt("position");
    }

    public String getTrack() {
        return track;
    }

    public AudioTrack getAudioTrack() throws AudioTrackEncodingException {
        return Utils.toAudioTrack(track);
    }

    public String getLavaClass() {
        return lavaClass;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getLength() {
        return length;
    }

    public String getUri() {
        return uri;
    }

    public String getIdentifier() {
        return identifier;
    }

    public boolean isStream() {
        return isStream;
    }

    public boolean isSeekable() {
        return isSeekable;
    }

    public int getPosition() {
        return position;
    }
}
