package bot.bricolo.granite.entities.events;

import bot.bricolo.granite.AndesitePlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

@SuppressWarnings("WeakerAccess")
public class TrackExceptionEvent extends PlayerEvent {

    private AudioTrack track;
    private Exception exception;

    public TrackExceptionEvent(AndesitePlayer player, AudioTrack track, Exception exception) {
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
