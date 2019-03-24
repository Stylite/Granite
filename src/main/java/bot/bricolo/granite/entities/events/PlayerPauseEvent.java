package bot.bricolo.granite.entities.events;

import bot.bricolo.granite.andesite.Player;

public class PlayerPauseEvent extends PlayerEvent {
    public PlayerPauseEvent(Player player) {
        super(player);
    }
}
