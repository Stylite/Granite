package bot.bricolo.granite;

import bot.bricolo.granite.entities.Track;
import bot.bricolo.granite.entities.payload.Play;
import bot.bricolo.granite.exceptions.AudioTrackEncodingException;
import bot.bricolo.granite.exceptions.NoNodeAvailableException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class AndesitePlayer {
    private final Granite granite;
    private final Long guildId;
    private AndesiteNode node;

    AndesitePlayer(Granite granite, Long guildId) {
        this.granite = granite;
        this.guildId = guildId;
        this.assignNode();
    }

    //******************//
    // Player functions //
    //******************//
    public void play(Track track) throws NoNodeAvailableException {
        play(track.getTrack());
    }

    public void play(AudioTrack track) throws NoNodeAvailableException, AudioTrackEncodingException {
        play(Utils.toMessage(track));
    }

    private void play(String track) throws NoNodeAvailableException {
        if (node == null || !node.connected) {
            throw new NoNodeAvailableException();
        }

        node.send(new Play(track, true));
    }

    //****************//
    // Event handling //
    //****************//

    //****************//
    // Voice handling //
    //****************//

    //***********//
    // Internals //
    //***********//
    String getNodeId() {
        return node.connectionId;
    }

    private void assignNode() {
        List<AndesiteNode> nodes = granite.getAvailableNodes();
        if (nodes.size() == 0) {
            granite.LOG.warn("No Andesite node available right now (Guild " + guildId + "). Trying to assign a node again in 5 seconds");
            Utils.setTimeout(this::assignNode, 5000);
            return;
        }
        int index = ThreadLocalRandom.current().nextInt(nodes.size());
        this.node = nodes.get(index);
    }
}
