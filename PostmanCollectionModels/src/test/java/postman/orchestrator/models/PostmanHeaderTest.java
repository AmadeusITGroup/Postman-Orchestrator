package postman.orchestrator.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostmanHeaderTest {
  @Test
  void testSerializationConstructor() {
    PostmanHeader header = new PostmanHeader();

    assertNull(header.getKey());
    assertNull(header.getValue());
  }

  @Test
  void testFullyParametrizedConstructor() {
    String key = "Header key";
    String value = "Header value";

    PostmanHeader header = new PostmanHeader(key, value);

    assertEquals(key, header.getKey());
    assertEquals(value, header.getValue());
  }

  @Test
  void testOverrideValues() {
    String key = "Header key";
    String value = "Header value";

    String keyOv = "Overridden header key";
    String valueOv = "Overridden header value";

    PostmanHeader header = new PostmanHeader(key, value);

    header.setKey(keyOv);
    header.setValue(valueOv);

    assertEquals(keyOv, header.getKey());
    assertEquals(valueOv, header.getValue());
  }
}