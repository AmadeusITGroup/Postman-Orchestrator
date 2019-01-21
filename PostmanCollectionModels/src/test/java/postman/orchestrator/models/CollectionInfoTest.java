package postman.orchestrator.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionInfoTest {

    @Test
    void testSerializationConstructor() {
        CollectionInfo info = new CollectionInfo();

        assertNull(info.getName());
        assertNull(info.getDescription());
        assertNull(info.getPostmanId());
        assertNull(info.getSchema());
    }

  @Test
  void testFullyParametrizedConstructor() {
    String postmanId = "ID1";
    String name = "Collection name";
    String schema = "Collection schema";
    String description = "This is a description";
    CollectionInfo info = new CollectionInfo(postmanId, name, description, schema);

    assertEquals(name, info.getName());
    assertEquals(description, info.getDescription());
    assertEquals(postmanId, info.getPostmanId());
    assertEquals(schema, info.getSchema());
  }

  @Test
  void testBaseConstructor() {
    String name = "Collection name";
    String schema = "https://schema.getpostman.com/json/collection/v2.1.0/collection.json";
    CollectionInfo info = new CollectionInfo(name);

    assertEquals(name, info.getName());
    assertNull(info.getDescription());
    assertNotNull(info.getPostmanId());
    assertEquals(schema, info.getSchema());
  }

  @Test
  void testOverrideValues() {
    String postmanId = "ID1";
    String name = "Collection name";
    String name2 = "Collection name 2";
    String schema = "Collection schema";
    String description = "This is a description";
    CollectionInfo info = new CollectionInfo(name);

    info.setName(name2);
    info.setDescription(description);
    info.setPostmanId(postmanId);
    info.setSchema(schema);

    assertEquals(name2, info.getName());
    assertEquals(description, info.getDescription());
    assertEquals(postmanId, info.getPostmanId());
    assertEquals(schema, info.getSchema());
  }
}