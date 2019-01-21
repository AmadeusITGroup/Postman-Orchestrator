package postman.orchestrator.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PostmanCollectionTest {
  @Test
  void testSerializationConstructor() {
    PostmanCollection collection = new PostmanCollection();

    assertNull(collection.getInfo());
    assertNotNull(collection.getItems());
    assertTrue(collection.getItems().isEmpty());
    assertNotNull(collection.getEvents());
    assertTrue(collection.getEvents().isEmpty());
  }

  @Test
  void testFullyParametrizedConstructor() {
    String name = "Collection name";
    CollectionInfo info = new CollectionInfo(name);

    PostmanCollection collection = new PostmanCollection(info, null, null);

    assertNotNull(collection.getInfo());
    assertEquals(name, collection.getInfo().getName());
    assertNull(collection.getItems());
    assertNull(collection.getEvents());
  }

  @Test
  void testNameParametrizedConstructor() {
    String name = "Collection name";

    PostmanCollection collection = new PostmanCollection(name);

    assertNotNull(collection.getInfo());
    assertEquals(name, collection.getInfo().getName());
    assertNotNull(collection.getItems());
    assertTrue(collection.getItems().isEmpty());
    assertNotNull(collection.getEvents());
    assertTrue(collection.getEvents().isEmpty());
  }

  @Test
  void testOverrideValue() {
    String name = "Collection name";

    PostmanCollection collection = new PostmanCollection(name);

    assertNotNull(collection.getInfo());
    assertEquals(name, collection.getInfo().getName());
    assertNotNull(collection.getItems());
    assertTrue(collection.getItems().isEmpty());
    assertNotNull(collection.getEvents());
    assertTrue(collection.getEvents().isEmpty());

    CollectionInfo info = new CollectionInfo("V2 Collection");
    collection.setInfo(info);
    assertNotNull(collection.getInfo());
    assertEquals("V2 Collection", collection.getInfo().getName());

    collection.setItems(null);
    assertNull(collection.getItems());
  }

  @Test
  void testConversionToFolder() {
    String name = "Collection name";
    String description = "This is a description";

    PostmanCollection collection = new PostmanCollection(name);
    collection.getInfo().setDescription(description);
    collection.setEvents(Arrays.asList(new Event()));

    String requestName = "Item name";
    PostmanRequest request = new PostmanRequest("GET", null, null, null, description);

    RequestItem item = new RequestItem(requestName, description,
            Arrays.asList(new Event()), request, null);

    collection.getItems().add(item);

    FolderItem folder = new FolderItem(collection);
    assertEquals(name, folder.getName());
    assertEquals(description, folder.getDescription());
    assertNotNull(folder.getEvent());
    assertFalse(folder.getEvent().isEmpty());
    assertNotNull(folder.getItems());
    assertFalse(folder.getItems().isEmpty());
    assertEquals(requestName, folder.getItems().get(0).getName());
    assertFalse(folder.isPostmanIsSubFolder());
  }
}