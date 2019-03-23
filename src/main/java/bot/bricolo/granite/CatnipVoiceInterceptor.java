package bot.bricolo.granite;

import com.mewna.catnip.entity.user.VoiceState;
import com.mewna.catnip.entity.voice.VoiceServerUpdate;

public class CatnipVoiceInterceptor {
    private final Granite granite;

    public CatnipVoiceInterceptor(Granite granite) {
        this.granite = granite;
    }

    public void onVoiceServerUpdate(VoiceServerUpdate update) {
        AndesitePlayer player = granite.getPlayer(update.guildId());
        if (player != null) {
            player.onVoiceServerUpdate(update);
        }
    }

    public void onVoiceStateUpdate(VoiceState update) {
        // yes
    }
}
