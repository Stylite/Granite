package bot.bricolo.granite;

import bot.bricolo.granite.andesite.Player;
import com.mewna.catnip.entity.user.VoiceState;
import com.mewna.catnip.entity.voice.VoiceServerUpdate;

public class CatnipVoiceInterceptor {
    private final Granite granite;

    public CatnipVoiceInterceptor(Granite granite) {
        this.granite = granite;
    }

    public void onVoiceServerUpdate(VoiceServerUpdate update) {
        Player player = granite.getPlayer(update.guildId());
        if (player != null) {
            player.onVoiceServerUpdate(update);
        }
    }

    public void onVoiceStateUpdate(VoiceState update) {
        // yes
    }
}
