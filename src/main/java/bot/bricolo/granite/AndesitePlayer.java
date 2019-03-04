package bot.bricolo.granite;

import java.util.ArrayList;
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
    private void assignNode() {
        List<AndesiteNode> nodes = new ArrayList<>();
        granite.nodes.forEach((node) -> {
            if (node.connected) {
                nodes.add(node);
            }
        });

        int index = ThreadLocalRandom.current().nextInt(nodes.size());
        this.node = nodes.get(index);
    }
}
