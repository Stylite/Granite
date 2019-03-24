package bot.bricolo.granite.entities.events;

import bot.bricolo.granite.andesite.Player;

public class PlayerResumeEvent extends PlayerEvent {
    public PlayerResumeEvent(Player player) {
        super(player);
    }
}
