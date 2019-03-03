package bot.bricolo.granite;

import java.util.ArrayList;
import java.util.List;

class Granite {
    private final String userId;
    private final List<AndesiteNode> nodes = new ArrayList<>();

    Granite(String userId) {
        this.userId = userId;
    }

    public void addNode(AndesiteNode node) {
        nodes.add(node);
    }
}
