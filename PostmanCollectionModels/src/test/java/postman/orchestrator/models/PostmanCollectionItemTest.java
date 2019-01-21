package postman.orchestrator.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PostmanCollectionItemTest {

  @Test
  void testSerializationConstructor() {
    PostmanCollectionItem item = new PostmanCollectionItem();

    assertNull(item.getDescription());
    assertNotNull(item.getEvent());
    assertTrue(item.getEvent().isEmpty());
    assertNull(item.getName());
  }

  @Test
  void testFullyParametrizedConstructor() {
    String name = "Item name";
    String description = "This is a description";

    PostmanCollectionItem item = new PostmanCollectionItem(name, description, null);

    assertEquals(name, item.getName());
    assertEquals(description, item.getDescription());
    assertNull(item.getEvent());
  }

  @Test
  void testOverrideValues() {
    String name = "Item name";
    String description = "This is a description";
    String description2 = "This is an updated description";

    PostmanCollectionItem item = new PostmanCollectionItem(name, description, null);

    item.setDescription(description2);
    item.setEvent(Arrays.asList(new Event()));

    assertEquals(name, item.getName());
    assertEquals(description2, item.getDescription());
    assertNotNull(item.getEvent());
    assertFalse(item.getEvent().isEmpty());
  }
}