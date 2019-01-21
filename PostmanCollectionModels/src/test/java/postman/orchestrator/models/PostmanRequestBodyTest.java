package postman.orchestrator.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostmanRequestBodyTest {
  @Test
  void testSerializationConstructor() {
    PostmanRequestBody body = new PostmanRequestBody();

    assertNull(body.getMode());
    assertNull(body.getRaw());
  }

  @Test
  void testFullyParametrizedConstructor() {
    String mode = "Test mode";
    String raw = "Raw value";
    PostmanRequestBody body = new PostmanRequestBody(mode, raw);

    assertEquals(mode, body.getMode());
    assertEquals(raw, body.getRaw());
  }

  @Test
  void testOverrideValues() {
    String mode = "Test mode";
    String raw = "Raw value";
    PostmanRequestBody body = new PostmanRequestBody(mode, raw);

    assertEquals(mode, body.getMode());
    assertEquals(raw, body.getRaw());

    mode = "Updated test key";
    raw = "{\"testProperty\": \"test value\", \"emptyArray\": []}";
    body.setMode(mode);
    body.setRaw(raw);

    assertEquals(mode, body.getMode());
    assertEquals(raw, body.getRaw());
  }
}