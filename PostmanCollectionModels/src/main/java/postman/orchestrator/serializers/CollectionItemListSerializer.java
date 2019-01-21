package postman.orchestrator.serializers;

import com.google.gson.*;
import postman.orchestrator.models.FolderItem;
import postman.orchestrator.models.PostmanCollectionItem;
import postman.orchestrator.models.RequestItem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A specific implementation of the {@link JsonDeserializer} and the {@link JsonSerializer}
 * to save and get the right list of Postman object since in Postman schema there is no distinction
 * between folder and request item
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class CollectionItemListSerializer implements JsonDeserializer<List<PostmanCollectionItem>>,
        JsonSerializer<List<PostmanCollectionItem>>
{
  @Override
  public List<PostmanCollectionItem> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
          throws JsonParseException
  {
    ArrayList<PostmanCollectionItem> list = new ArrayList<>();
    JsonArray ja = json.getAsJsonArray();

    for (JsonElement je : ja) {
      if (je.getAsJsonObject().get("item") != null) {
        list.add(context.deserialize(je, FolderItem.class));
      } else {
        list.add(context.deserialize(je, RequestItem.class));
      }
    }
    return list;
  }

  @Override
  public JsonElement serialize(List<PostmanCollectionItem> items, Type type, JsonSerializationContext context) {
    JsonArray array = new JsonArray();
    for (PostmanCollectionItem item : items) {
      if(item instanceof FolderItem) {
        array.add(context.serialize(item, FolderItem.class));
      }
      else if(item instanceof RequestItem) {
        array.add(context.serialize(item, RequestItem.class));
      }
    }
    return array;
  }
}
