package bot.bricolo.granite.entities.events;

import bot.bricolo.granite.andesite.Player;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class TrackExceptionEvent extends PlayerEvent {

    private AudioTrack track;
    private Exception exception;

    public TrackExceptionEvent(Player player, AudioTrack track, Exception exception) {
        super(player);
        this.track = track;
        this.exception = exception;
    }

    public AudioTrack getTrack() {
        return track;
    }

    public Exception getException() {
        return exception;
    }
}
