package bot.bricolo.granite;

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

    private void assignNode() {
        int index = ThreadLocalRandom.current().nextInt(granite.nodes.size());
        this.node = granite.nodes.get(index);
    }
}
