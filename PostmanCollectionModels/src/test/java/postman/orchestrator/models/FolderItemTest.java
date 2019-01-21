package postman.orchestrator.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FolderItemTest {
  @Test
  void testSerializationConstructor() {
    FolderItem item = new FolderItem();

    assertNull(item.getDescription());
    assertNotNull(item.getEvent());
    assertTrue(item.getEvent().isEmpty());
    assertNull(item.getName());
    assertNotNull(item.getItems());
    assertTrue(item.getItems().isEmpty());

    assertFalse(item.isPostmanIsSubFolder());
  }

  @Test
  void testFullyParametrizedConstructor() {
    String name = "Item name";
    String description = "This is a description";

    FolderItem item = new FolderItem(name, description, null, null, true);

    assertEquals(name, item.getName());
    assertEquals(description, item.getDescription());
    assertNull(item.getEvent());
    assertNull(item.getItems());
    assertTrue(item.isPostmanIsSubFolder());
  }

  @Test
  void testOverrideValues() {
    String name = "Item name";
    String description = "This is a description";

    FolderItem item = new FolderItem(name, description, null, null, false);

    assertEquals(name, item.getName());
    assertEquals(description, item.getDescription());
    assertNull(item.getEvent());
    assertNull(item.getItems());
    assertFalse(item.isPostmanIsSubFolder());

    name = "New Folder name";
    description = "This is an updated description";

    item.setName(name);
    item.setDescription(description);
    item.setEvent(Arrays.asList(new Event()));
    item.setItems(Arrays.asList(new PostmanCollectionItem()));
    item.setPostmanIsSubFolder(true);

    assertEquals(name, item.getName());
    assertEquals(description, item.getDescription());
    assertNotNull(item.getEvent());
    assertFalse(item.getEvent().isEmpty());
    assertNotNull(item.getItems());
    assertFalse(item.getItems().isEmpty());
    assertTrue(item.isPostmanIsSubFolder());
  }

  @Test
  void testEquality() {
    String folder1 = "Folder 1";
    String folder2 = "Folder 2";
    String description = "This is a description";

    FolderItem item1 = new FolderItem(folder1, description, null, null, true);
    FolderItem item2 = new FolderItem(folder2, description, null, null, true);
    FolderItem item3 = new FolderItem(folder1, description, null, null, true);

    assertFalse(item1.equals(null));
    assertFalse(item1.equals(folder2));

    assertFalse(item1.equals(item2));
    assertTrue(item1.equals(item3));
  }
}