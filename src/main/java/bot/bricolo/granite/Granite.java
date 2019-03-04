package bot.bricolo.granite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class Granite {
    private final Logger LOG = LoggerFactory.getLogger(Granite.class);
    private final List<AndesiteNode> nodes = new ArrayList<>();

    public void addNode(AndesiteNode node) {
        nodes.add(node);
    }
}
