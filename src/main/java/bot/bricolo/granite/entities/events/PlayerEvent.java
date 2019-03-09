package bot.bricolo.granite.entities.events;

import bot.bricolo.granite.AndesitePlayer;

public abstract class PlayerEvent {
    private final AndesitePlayer player;

    public PlayerEvent(AndesitePlayer player) {
        this.player = player;
    }

    public AndesitePlayer getPlayer() {
        return player;
    }
}
