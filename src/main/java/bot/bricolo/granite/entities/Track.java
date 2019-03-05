package bot.bricolo.granite.entities;

import org.json.JSONObject;

public class Track {
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
        this.lavaClass = jsonObject.getString("class");
        this.title = jsonObject.getString("title");
        this.author = jsonObject.getString("author");
        this.length = jsonObject.getInt("length");
        this.identifier = jsonObject.getString("identifier");
        this.uri = jsonObject.getString("uri");
        this.isStream = jsonObject.getBoolean("isStream");
        this.isSeekable = jsonObject.getBoolean("isSeekable");
        this.position = jsonObject.getInt("position");
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
