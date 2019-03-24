package bot.bricolo.granite.entities.events;

import bot.bricolo.granite.andesite.Player;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

@SuppressWarnings("WeakerAccess")
public class TrackStuckEvent extends PlayerEvent {

    private AudioTrack track;
    private long thresholdMs;

    public TrackStuckEvent(Player player, AudioTrack track, long thresholdMs) {
        super(player);
        this.track = track;
        this.thresholdMs = thresholdMs;
    }

    public AudioTrack getTrack() {
        return track;
    }

    public long getThresholdMs() {
        return thresholdMs;
    }
}
