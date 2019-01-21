package postman.orchestrator.common;

import org.junit.jupiter.api.Test;
import postman.orchestrator.models.PostmanCollection;

import static org.junit.jupiter.api.Assertions.*;

class FolderStructureTest {
  @Test
  void testConstructor() {
    String name = "Structure test";
    FolderStructure struc = new FolderStructure(name);

    assertEquals(name, struc.getName());
    assertNotNull(struc.getCollections());
    assertTrue(struc.getCollections().isEmpty());
    assertNotNull(struc.getFolders());
    assertTrue(struc.getFolders().isEmpty());
  }

  @Test
  void testCombine() {
    String name = "Root";
    FolderStructure struc = new FolderStructure(name);

    FolderStructure toAdd = new FolderStructure("Level 1");
    PostmanCollection collection1 = new PostmanCollection("Collection 1");
    toAdd.getCollections().add(collection1);

    assertFalse(toAdd.getCollections().isEmpty());

    struc.getFolders().add(toAdd);
    assertTrue(struc.getCollections().isEmpty());
    assertFalse(struc.getFolders().isEmpty());

    assertEquals("Level 1", struc.getFolders().get(0).getName());

    PostmanCollection collection3 = new PostmanCollection("Collection 3");
    FolderStructure toCombine = new FolderStructure("Will be combined");
    toCombine.getCollections().add(collection3);
    struc.combine(toCombine);

    assertFalse(struc.getFolders().isEmpty());
    assertEquals(1, struc.getFolders().size());
    assertEquals(1,
            struc.getFolders().get(0).getCollections().size());
    assertFalse(struc.getCollections().isEmpty());
    assertEquals("Collection 3",
            struc.getCollections().get(0).getInfo().getName());


    FolderStructure otherStrucToCombine = new FolderStructure(name);
    FolderStructure toAddBis = new FolderStructure("Level 1");
    FolderStructure toAdd2 = new FolderStructure("Level 2");
    PostmanCollection collection2 = new PostmanCollection("Collection 2");
    toAddBis.getCollections().add(collection2);

    otherStrucToCombine.getFolders().add(toAddBis);
    otherStrucToCombine.getFolders().add(toAdd2);

    assertFalse(otherStrucToCombine.getFolders().isEmpty());
    assertEquals(2,
            otherStrucToCombine.getFolders().size());
    assertEquals(1,
            otherStrucToCombine.getFolders().get(0).getCollections().size());

    struc.combine(otherStrucToCombine);
    assertFalse(struc.getFolders().isEmpty());
    assertEquals(2, struc.getFolders().size());
    assertEquals(2,
            struc.getFolders().get(0).getCollections().size());
    assertEquals("Collection 1",
            struc.getFolders().get(0).getCollections().get(0).getInfo().getName());
    assertEquals("Collection 2",
            struc.getFolders().get(0).getCollections().get(1).getInfo().getName());
  }
}