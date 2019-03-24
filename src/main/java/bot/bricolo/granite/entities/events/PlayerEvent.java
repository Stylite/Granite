package bot.bricolo.granite.entities.events;

import bot.bricolo.granite.andesite.Player;

public abstract class PlayerEvent {
    private final Player player;

    public PlayerEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
