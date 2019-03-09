package bot.bricolo.granite.entities.events;

import bot.bricolo.granite.AndesitePlayer;

public class PlayerPauseEvent extends PlayerEvent {
    public PlayerPauseEvent(AndesitePlayer player) {
        super(player);
    }
}
