package bot.bricolo.granite.entities.events;

import bot.bricolo.granite.AndesitePlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

@SuppressWarnings("WeakerAccess")
public abstract class EventAdapter implements IEventListener {
    public void onPlayerPause(AndesitePlayer player) {
        // memes
    }

    public void onPlayerResume(AndesitePlayer player) {
        // memes
    }

    public void onTrackStart(AndesitePlayer player, AudioTrack track) {
        // memes
    }

    public void onTrackEnd(AndesitePlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        // memes
    }

    public void onTrackException(AndesitePlayer player, AudioTrack track, Exception exception) {
        // memes
    }

    public void onTrackStuck(AndesitePlayer player, AudioTrack track, long thresholdMs) {
        // memes
    }

    @Override
    public void onEvent(PlayerEvent event) {
        if (event instanceof PlayerPauseEvent) {
            onPlayerPause(event.getPlayer());
        } else if (event instanceof PlayerResumeEvent) {
            onPlayerResume(event.getPlayer());
        } else if (event instanceof TrackStartEvent) {
            onTrackStart(event.getPlayer(), ((TrackStartEvent) event).getTrack());
        } else if (event instanceof TrackEndEvent) {
            onTrackEnd(event.getPlayer(), ((TrackEndEvent) event).getTrack(), ((TrackEndEvent) event).getReason());
        } else if (event instanceof TrackExceptionEvent) {
            onTrackException(event.getPlayer(), ((TrackExceptionEvent) event).getTrack(), ((TrackExceptionEvent) event).getException());
        } else if (event instanceof TrackStuckEvent) {
            onTrackStuck(event.getPlayer(), ((TrackStuckEvent) event).getTrack(), ((TrackStuckEvent) event).getThresholdMs());
        }
    }
}
