package bot.bricolo.granite;

import net.dv8tion.jda.api.hooks.VoiceDispatchInterceptor;

import javax.annotation.Nonnull;

public class VoiceInterceptor implements VoiceDispatchInterceptor {
    private final Granite granite;

    VoiceInterceptor(Granite granite) {
        this.granite = granite;
    }

    @Override
    public void onVoiceServerUpdate(@Nonnull VoiceServerUpdate update) {
        AndesitePlayer player = granite.getPlayer(update.getGuildIdLong());
        if (player != null) {
            player.onVoiceServerUpdate(update);
        }
    }

    @Override
    public boolean onVoiceStateUpdate(@Nonnull VoiceStateUpdate update) {
        return true;
    }
}
