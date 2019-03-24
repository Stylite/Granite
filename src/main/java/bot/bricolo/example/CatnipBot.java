package bot.bricolo.example;

import bot.bricolo.granite.andesite.Player;
import bot.bricolo.granite.CatnipVoiceInterceptor;
import bot.bricolo.granite.Granite;
import bot.bricolo.granite.exceptions.NoNodeAvailableException;
import com.mewna.catnip.Catnip;
import com.mewna.catnip.CatnipOptions;
import com.mewna.catnip.entity.channel.VoiceChannel;
import com.mewna.catnip.entity.guild.Guild;
import com.mewna.catnip.entity.message.Message;
import com.mewna.catnip.entity.user.User;
import com.mewna.catnip.entity.user.VoiceState;
import com.mewna.catnip.shard.DiscordEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// Please note that reusing this code implies that you comply with the GNU Affero General Public License v3.0
// the project is licensed under. Not complying with it is illegal and may result in a DMCA takedown.
// This code is made for educational purposes only and should not be used in a production bot.
public class CatnipBot {
    private static final Granite granite = new Granite();

    private static final String USER_ID = System.getenv("USER_ID");
    private static final String TOKEN = System.getenv("BOT_TOKEN");

    public static void main(final String[] args) throws Exception {
        // ==> 1st step: Add a node
        // Here we only passed required arguments (Host, Port, Password and UserID)
        // You can pass a list of regions (pre-made buckets available in Regions) that will be used to route
        // guild traffic to specific nodes and improve latency, as well as a friendly name for the node that will
        // be used in error messages. By default, Granite will use this node for all regions and give the name
        // "AndesiteNode@<HOST>:<PORT>". If you don't have a password set for your node, just put an empty string.
        // You can add as much nodes as you want, and Granite will handle load balancing for you.
        granite.addNode("127.0.0.1", 5000, "you-shall-not-pass", USER_ID);

        // ==> 2nd step: Configure and initialize Catnip
        CatnipOptions opts = new CatnipOptions(TOKEN);
        Catnip catnip = Catnip.catnip(opts).connect();
        catnip.on(DiscordEvent.MESSAGE_CREATE, CatnipBot::handleMessage);

        // You need to add these 3 lines to forward events and make Granite work.
        CatnipVoiceInterceptor voiceInterceptor = new CatnipVoiceInterceptor(granite);
        catnip.on(DiscordEvent.VOICE_SERVER_UPDATE, voiceInterceptor::onVoiceServerUpdate);
        catnip.on(DiscordEvent.VOICE_STATE_UPDATE, voiceInterceptor::onVoiceStateUpdate);
    }

    // Let's create a basic music bot using Granite. We'll only provide play, pause, seek, volume and stop in this
    // example, and we'll not use a proper command handler. This is something you should do if you're creating a bot.
    private static void handleMessage(Message message) {
        // First, we prevent loops by ignoring the event if the message have been sent by another bot.
        if (message.author().bot()) return;

        // Then we only respond to command issued in guilds, as DMs have no voice channels
        if (!message.channel().isGuild()) return;
        Guild guild = message.guild();
        assert guild != null;

        // We'll get a player for the guild, or create one if it does not exist.
        Player player = granite.getOrCreatePlayer(guild);

        // Let's parse the command
        String[] args = message.content().split(" ");
        try {
            switch (args[0]) {
                case "%play":
                    handlePlay(message, args, player);
                    break;
                case "%pause":
                    player.pause(true);
                    break;
                case "%resume":
                    player.pause(false);
                    break;
                case "%seek":
                    player.seek(player.state.getPosition() + Integer.valueOf(args[1]) * 1000);
                    break;
                case "%volume":
                    player.volume(Math.min(100, Math.max(0, Integer.valueOf(args[1]))));
                    break;
                case "%stop":
                    guild.closeVoiceConnection();
                    player.stop();
                    break;
            }
        } catch (NoNodeAvailableException e) {
            // This should not happen often, but may occur. Player will always try to find an available node, and
            // will get one assigned as soon as one is ready to handle players.
            message.channel().sendMessage("No music node is available right now!");
        }
    }

    // Here is the handling of the play command. I separated it because it's the biggest block of the command handler.
    private static void handlePlay(Message message, String[] args, Player player) {
        // null memes
        Guild guild = message.guild();
        assert guild != null;
        User self = message.catnip().selfUser();
        assert self != null;

        // ==> Step 1: Check if the user is in a voice channel, and if the bot can connect to it.
        VoiceState voiceState = message.catnip().cache().voiceState(guild.id(), message.author().id());
        if (voiceState == null || voiceState.channel() == null) {
            message.channel().sendMessage("You're not in a voice channel");
            return;
        }

        VoiceState selfVoiceState = message.catnip().cache().voiceState(guild.id(), self.id());
        VoiceChannel voiceChannel = voiceState.channel();
        if (selfVoiceState != null && selfVoiceState.channel() != null) {
            // We're already in a voice channel, so we just check if the command issuer is in the same channel.
            if (!Objects.equals(selfVoiceState.channelId(), voiceState.channelId())) {
                message.channel().sendMessage("You're not in the same voice channel");
                return;
            }
        } else {
            // We're connecting to a voice channel
            assert voiceChannel != null;
            voiceChannel.openVoiceConnection();
        }

        // ==> Step 2: Fetch the audio track.
        List<String> search = new ArrayList<>(Arrays.asList(args));
        search.remove(0);

        granite.getTrack(String.join(" ", search)).thenAccept(track -> {
            // ==> Step 3: Play the track.
            message.channel().sendMessage("Found track `" + track.getTitle() + "` by `" + track.getAuthor() + "`");
            try {
                player.play(track);
            } catch (NoNodeAvailableException e) {
                message.channel().sendMessage("No music node is available right now!");
            }
        });
    }
}
