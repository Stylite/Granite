package bot.bricolo.granite;

import bot.bricolo.granite.exceptions.AudioTrackEncodingException;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.bandcamp.BandcampAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.http.HttpAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.soundcloud.SoundCloudAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.twitch.TwitchStreamAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.vimeo.VimeoAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.io.MessageInput;
import com.sedmelluq.discord.lavaplayer.tools.io.MessageOutput;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.iharder.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class Utils {
    private static final AudioPlayerManager PLAYER_MANAGER;

    static {
        // This is used to decode and encode tracks for Andesite
        PLAYER_MANAGER = new DefaultAudioPlayerManager();
        PLAYER_MANAGER.registerSourceManager(new YoutubeAudioSourceManager());
        PLAYER_MANAGER.registerSourceManager(new BandcampAudioSourceManager());
        PLAYER_MANAGER.registerSourceManager(new SoundCloudAudioSourceManager());
        PLAYER_MANAGER.registerSourceManager(new TwitchStreamAudioSourceManager());
        PLAYER_MANAGER.registerSourceManager(new VimeoAudioSourceManager());
        PLAYER_MANAGER.registerSourceManager(new HttpAudioSourceManager());
    }

    public static AudioTrack toAudioTrack(String base64) throws AudioTrackEncodingException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.decode(base64));
            return PLAYER_MANAGER.decodeTrack(new MessageInput(byteArrayInputStream)).decodedTrack;
        } catch (IOException e) {
            throw new AudioTrackEncodingException("Unable to decode audio track", e);
        }
    }

    public static String toMessage(AudioTrack track) throws AudioTrackEncodingException {
        // Track
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        try {
            PLAYER_MANAGER.encodeTrack(new MessageOutput(byteArrayInputStream), track);
        } catch (IOException e) {
            throw new AudioTrackEncodingException("Unable to encode audio track", e);
        }

        return Base64.encodeBytes(byteArrayInputStream.toByteArray());
    }

    @SuppressWarnings("SameParameterValue")
    static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception ignored) {
            }
        }).start();
    }
}
