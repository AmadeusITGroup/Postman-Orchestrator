package postman.orchestrator.serializers;

import com.google.gson.*;
import postman.orchestrator.models.FolderItem;
import postman.orchestrator.models.PostmanCollectionItem;
import postman.orchestrator.models.RequestItem;

import java.lang.reflect.Type;

/**
 * A specific implementation of the {@link JsonDeserializer} get the
 * right Postman object since in Postman schema there is no distinction between folder and request item
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class CollectionItemSerializer implements JsonDeserializer<PostmanCollectionItem> {

  @Override
  public PostmanCollectionItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
          throws JsonParseException {
    JsonObject jo = json.getAsJsonObject();
    Gson g = new Gson();

    if (jo.get("item") != null) {
      return g.fromJson(jo, FolderItem.class);
    } else {
      return g.fromJson(jo, RequestItem.class);
    }
  }
}
