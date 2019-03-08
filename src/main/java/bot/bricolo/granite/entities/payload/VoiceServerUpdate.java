package bot.bricolo.granite.entities.payload;

import bot.bricolo.granite.entities.IJsonSerializable;
import net.dv8tion.jda.api.hooks.VoiceDispatchInterceptor;
import org.json.JSONObject;

public class VoiceServerUpdate implements IJsonSerializable {
    private final VoiceDispatchInterceptor.VoiceServerUpdate update;

    public VoiceServerUpdate(VoiceDispatchInterceptor.VoiceServerUpdate update) {
        this.update = update;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("op", "voice-server-update")
                .put("sessionId", update.getSessionId())
                .put("guildId", update.getGuildId())
                .put("event", update.getJSON().getJSONObject("d"));
    }
}
