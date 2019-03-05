package bot.bricolo.granite;

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
