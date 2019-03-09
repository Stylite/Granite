package bot.bricolo.granite.entities.events;

import bot.bricolo.granite.AndesitePlayer;

public class PlayerResumeEvent extends PlayerEvent {
    public PlayerResumeEvent(AndesitePlayer player) {
        super(player);
    }
}
