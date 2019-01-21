package postman.orchestrator.serializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.skyscreamer.jsonassert.JSONAssert;
import postman.orchestrator.models.*;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CollectionSerializerTest {

  private static Gson gson;
  private static String collectionString;

  @BeforeAll
  static void initialize() {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(new TypeToken<List<PostmanCollectionItem>>(){}.getType(),
                                new CollectionItemListSerializer());
    builder.registerTypeAdapter(PostmanCollectionItem.class, new CollectionItemSerializer());
    gson = builder.create();

    collectionString = "{\"info\":{\"_postman_id\":" +
            "\"e7ee298b-bb1e-4100-bbe8-a3bcbc7e591e\",\"name\":" +
            "\"Test collection\",\"schema\":\"https://schema.getpostman.com/json/collection/v2.1.0/collection." +
            "json\"},\"item\":[{\"_postman_isSubFolder\":false, \"name\":\"Folder Test\",\"item\":[{\"name\":" +
            "\"Request 1\",\"event\":[{\"listen\"" +
            ":\"prerequest\",\"script\":{\"id\":\"f12c0b00-148a-4889-ab8a-857bed1dbf5d\",\"exec\":" +
            "[\"/** This is a comment in pre-request **/\"],\"type\":\"text/javascript\"}},{\"listen\":\"test\"," +
            "\"script\":{\"id\":\"978a6a82-1f86-4bc0-a0bd-63c674c6fbd3\",\"exec\":[\"/** This is a comment in Test " +
            "**/\"],\"type\":\"text/javascript\"}}],\"request\":{\"method\":\"GET\",\"header\":[],\"body\":" +
            "{\"mode\":\"raw\",\"raw\":\"\"},\"url\":{\"raw\":\"\"},\"description\":\"This is a description for " +
            "request 1\"},\"response\":[]}]},{\"name\":\"Request 2\",\"event\":[{\"listen\":\"test\",\"script\"" +
            ":{\"id\":\"2b1f661e-5883-40d8-8ee9-0eb1d6b98676\",\"exec\":[\"/** This is a comment in Test for " +
            "Request 2 **/\"],\"type\":\"text/javascript\"}}],\"request\":{\"method\":\"GET\",\"header\":[]," +
            "\"body\":{\"mode\":\"raw\",\"raw\":\"\"},\"url\":{\"raw\":\"\"}},\"response\":[]}]}";
  }

  @Test
  void testDeserialization() {
    PostmanCollection collection = gson.fromJson(collectionString, PostmanCollection.class);

    assertNotNull(collection);

    assertNotNull(collection.getInfo());
    assertEquals("Test collection", collection.getInfo().getName());

    assertNotNull(collection.getItems());
    assertFalse(collection.getItems().isEmpty());

    List<PostmanCollectionItem> folders = collection.getItems().stream()
                          .filter(item -> item instanceof FolderItem)
                          .collect(Collectors.toList());

    assertNotNull(folders);
    assertFalse(folders.isEmpty());

    FolderItem folder = (FolderItem) folders.get(0);
    assertNotNull(folder);
    assertEquals("Folder Test", folder.getName());
    assertNotNull(folder.getItems());
    assertFalse(folder.getItems().isEmpty());

    RequestItem request1 = (RequestItem)folder.getItems().get(0);
    assertNotNull(request1);
    assertEquals("Request 1", request1.getName());
    assertNotNull(request1.getEvent());
    assertFalse(request1.getEvent().isEmpty());

    List<PostmanCollectionItem> requests = collection.getItems().stream()
            .filter(item -> item instanceof RequestItem)
            .collect(Collectors.toList());

    assertNotNull(requests);
    assertFalse(requests.isEmpty());

    RequestItem request2 = (RequestItem)requests.get(0);
    assertNotNull(request2);
    assertEquals("Request 2", request2.getName());
    assertNotNull(request2.getEvent());
    assertFalse(request2.getEvent().isEmpty());
  }

  @Test
  void testSerialization() {
    PostmanCollection collection = new PostmanCollection("Test collection");
    collection.getInfo().setPostmanId("e7ee298b-bb1e-4100-bbe8-a3bcbc7e591e");

    FolderItem folder1 = new FolderItem();
    folder1.setName("Folder Test");

    RequestItem requestItem1 = new RequestItem();
    Script prereqScript = new Script("f12c0b00-148a-4889-ab8a-857bed1dbf5d","text/javascript",
                                    Arrays.asList("/** This is a comment in pre-request **/"));
    Script testScript = new Script("978a6a82-1f86-4bc0-a0bd-63c674c6fbd3","text/javascript",
                                    Arrays.asList("/** This is a comment in Test **/"));
    Event prereq = new Event("prerequest", prereqScript);
    Event test = new Event("test", testScript);

    requestItem1.setName("Request 1");
    requestItem1.getEvent().add(prereq);
    requestItem1.getEvent().add(test);

    PostmanRequest request = new PostmanRequest();
    request.setMethod("GET");
    request.setBody(new PostmanRequestBody("raw", ""));
    request.setUrl(new PostmanUrl("", null, null, null, null));
    request.setDescription("This is a description for request 1");
    requestItem1.setRequest(request);

    folder1.getItems().add(requestItem1);
    collection.getItems().add(folder1);

    RequestItem requestItem2 = new RequestItem();
    Script testScript2 = new Script("2b1f661e-5883-40d8-8ee9-0eb1d6b98676","text/javascript",
            Arrays.asList("/** This is a comment in Test for Request 2 **/"));
    Event test2 = new Event("test", testScript2);

    requestItem2.setName("Request 2");
    requestItem2.getEvent().add(test2);

    PostmanRequest request2 = new PostmanRequest();
    request2.setMethod("GET");
    request2.setBody(new PostmanRequestBody("raw", ""));
    request2.setUrl(new PostmanUrl("", null, null, null, null));
    requestItem2.setRequest(request2);

    collection.getItems().add(requestItem2);
    collection.setEvents(null);

    String result = gson.toJson(collection);
    try {
      JSONAssert.assertEquals(collectionString, result, false);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}