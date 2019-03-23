package bot.bricolo.granite;

import net.dv8tion.jda.api.hooks.VoiceDispatchInterceptor;

import javax.annotation.Nonnull;

public class JDAVoiceInterceptor implements VoiceDispatchInterceptor {
    private final Granite granite;

    public JDAVoiceInterceptor(Granite granite) {
        this.granite = granite;
    }

    @Override
    public void onVoiceServerUpdate(@Nonnull VoiceServerUpdate update) {
        AndesitePlayer player = granite.getPlayer(update.getGuildId());
        if (player != null) {
            player.onVoiceServerUpdate(update);
        }
    }

    @Override
    public boolean onVoiceStateUpdate(@Nonnull VoiceStateUpdate update) {
        return true;
    }
}
