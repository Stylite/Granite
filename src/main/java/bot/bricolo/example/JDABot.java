package bot.bricolo.example;

import bot.bricolo.granite.AndesitePlayer;
import bot.bricolo.granite.Granite;
import bot.bricolo.granite.JDAVoiceInterceptor;
import bot.bricolo.granite.exceptions.NoNodeAvailableException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Please note that reusing this code implies that you comply with the GNU Affero General Public License v3.0
// the project is licensed under. Not complying with it is illegal and may result in a DMCA takedown.
// This code is made for educational purposes only and should not be used in a production bot.
public class JDABot extends ListenerAdapter {
    private static final Granite granite = new Granite();

    private static final String USER_ID = System.getenv("USER_ID");
    private static final String TOKEN = System.getenv("BOT_TOKEN");

    public static void main(final String[] args) throws Exception {
        // ==> 1st step: Add a node
        // Here we only passed required arguments (Host, Port, Password and UserID)
        // You can pass a list of regions (pre-made buckets available in Region) that will be used to route
        // guild traffic to specific nodes and improve latency, as well as a friendly name for the node that will
        // be used in error messages. By default, Granite will use this node for all regions and give the name
        // "AndesiteNode@<HOST>:<PORT>". If you don't have a password set for your node, just put an empty string.
        // You can add as much nodes as you want, and Granite will handle load balancing for you.
        granite.addNode("127.0.0.1", 5000, "you-shall-not-pass", USER_ID);

        // ==> 2nd step: Initialize JDA
        JDA jda = new JDABuilder()
                .setToken(TOKEN)
                .addEventListeners(new JDABot())
                // You need to add the following line to forward events and make Granite work.
                .setVoiceDispatchInterceptor(new JDAVoiceInterceptor(granite))
                .build();
    }

    // Let's create a basic music bot using Granite. We'll only provide play, pause, seek, volume and stop in this
    // example, and we'll not use a proper command handler. This is something you should do if you're creating a bot.
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // First, we prevent loops by ignoring the event if the message have been sent by another bot.
        if (event.getAuthor().isBot()) return;

        // Then we only respond to command issued in guilds, as DMs have no voice channels
        if (event.getChannel().getType() != ChannelType.TEXT) return;

        // We'll get a player for the guild, or create one if it does not exist.
        AndesitePlayer player = granite.getOrCreatePlayer(event.getGuild());

        // Let's parse the command
        String[] message = event.getMessage().getContentStripped().split(" ");
        try {
            switch (message[0]) {
                case "%play":
                    handlePlay(event, message, player);
                    break;
                case "%pause":
                    player.pause(true);
                    break;
                case "%resume":
                    player.pause(false);
                    break;
                case "%seek":
                    break;
                case "%volume":
                    break;
                case "%stop":
                    event.getGuild().getAudioManager().closeAudioConnection();
                    player.stop();
                    break;
            }
        } catch (NoNodeAvailableException e) {
            // This should not happen often, but may occur. Player will always try to find an available node, and
            // will get one assigned as soon as one is ready to handle players.
            event.getChannel().sendMessage("No music node is available right now!").queue();
        }
    }

    // Here is the handling of the play command. I separated it because it's the biggest block of the command handler.
    private void handlePlay(MessageReceivedEvent event, String[] args, AndesitePlayer player) {
        // ==> Step 1: Check if the user is in a voice channel, and if the bot can connect to it.
        if (!event.getMember().getVoiceState().inVoiceChannel()) {
            event.getChannel().sendMessage("You're not in a voice channel").queue();
            return;
        }

        VoiceChannel voiceChannel = event.getMember().getVoiceState().getChannel();
        if (event.getGuild().getSelfMember().getVoiceState().inVoiceChannel()) {
            // We're already in a voice channel, so we just check if the command issuer is in the same channel.
            if (!event.getGuild().getSelfMember().getVoiceState().getChannel().getId().equals(voiceChannel.getId())) {
                event.getChannel().sendMessage("You're not in the same voice channel").queue();
                return;
            }
        } else {
            // We're connecting to a voice channel and checking if we can do so.
            if (!event.getGuild().getSelfMember().hasPermission(voiceChannel, Permission.VOICE_CONNECT, Permission.VOICE_SPEAK)) {
                event.getChannel().sendMessage("I don't have enough permissions").queue();
                return;
            }

            event.getGuild().getAudioManager().openAudioConnection(voiceChannel);
        }

        // ==> Step 2: Fetch the audio track.
        List<String> search = new ArrayList<>(Arrays.asList(args));
        search.remove(0);

        granite.getTrack(String.join(" ", search)).thenAccept(track -> {
            // ==> Step 3: Play the track.
            event.getChannel().sendMessage("Found track `" + track.getTitle() + "` by `" + track.getAuthor() + "`").queue();
            try {
                player.play(track);
            } catch (NoNodeAvailableException e) {
                event.getChannel().sendMessage("No music node is available right now!").queue();
            }
        });
    }
}
