package bot.bricolo.granite.entities.payload;

import bot.bricolo.granite.entities.IJsonSerializable;
import com.mewna.catnip.entity.user.User;
import com.mewna.catnip.entity.user.VoiceState;
import net.dv8tion.jda.api.hooks.VoiceDispatchInterceptor;
import net.dv8tion.jda.api.utils.data.DataObject;
import org.json.JSONObject;

public class VoiceServerUpdate implements IJsonSerializable {
    private final String sessionId;
    private final String guildId;
    private final DataObject event;

    public VoiceServerUpdate(VoiceDispatchInterceptor.VoiceServerUpdate update) {
        this.sessionId = update.getSessionId();
        this.guildId = update.getGuildId();
        this.event = update.toData().getObject("d");
    }

   /* public VoiceServerUpdate(com.mewna.catnip.entity.voice.VoiceServerUpdate update) {
        User self = update.catnip().selfUser();
        assert self != null;
        VoiceState voiceState = update.catnip().cache().voiceState(update.guildId(), self.id());
        assert voiceState != null;

        this.sessionId = voiceState.sessionId();
        this.guildId = update.guildId();
        //this.event = new JSONObject(update.toJson().getJsonObject("d").encode());
    } */

    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("op", "voice-server-update")
                .put("sessionId", sessionId)
                .put("guildId", guildId)
                .put("event", event);
    }
}
