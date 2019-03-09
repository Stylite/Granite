package bot.bricolo.granite.entities.payload;

import bot.bricolo.granite.entities.IJsonSerializable;
import org.json.JSONObject;

public class Stop implements IJsonSerializable {
    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("op", "pause");
    }
}
