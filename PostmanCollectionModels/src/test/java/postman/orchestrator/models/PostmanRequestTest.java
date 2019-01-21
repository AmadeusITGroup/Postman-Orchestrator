package postman.orchestrator.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PostmanRequestTest {
  
  @Test
  void testSerializationConstructor() {
    PostmanRequest request = new PostmanRequest();

    assertNull(request.getMethod());

    assertNotNull(request.getPostmanHeader());
    assertTrue(request.getPostmanHeader().isEmpty());

    assertNull(request.getBody());
    assertNull(request.getDescription());
    assertNull(request.getUrl());
  }

  @Test
  void testFullyParametrizedConstructor() {
    String method = "GET";
    String description = "This is a description";
    PostmanRequest request = new PostmanRequest(method, null, null, null, description);

    assertEquals(method, request.getMethod());
    assertEquals(description, request.getDescription());
    assertNull(request.getPostmanHeader());
    assertNull(request.getBody());
    assertNull(request.getUrl());
  }

  @Test
  void testOverrideValues() {
    String method = "GET";
    String description = "This is a description";

    PostmanRequest request = new PostmanRequest(method, null, null, null, description);

    assertEquals(method, request.getMethod());
    assertEquals(description, request.getDescription());
    assertNull(request.getPostmanHeader());
    assertNull(request.getBody());
    assertNull(request.getUrl());

    String method2 = "POST";
    String description2 = "This is an updated description";

    PostmanHeader header = new PostmanHeader("key", "value");
    PostmanRequestBody body = new PostmanRequestBody("Test mode", "");
    PostmanUrl url = new PostmanUrl();

    request.setMethod(method2);
    request.setDescription(description2);
    request.setBody(body);
    request.setPostmanHeader(Arrays.asList(header));
    request.setUrl(url);

    assertEquals(method2, request.getMethod());
    assertEquals(description2, request.getDescription());

    assertNotNull(request.getPostmanHeader());
    assertFalse(request.getPostmanHeader().isEmpty());
    assertEquals("key", request.getPostmanHeader().get(0).getKey());

    assertNotNull(request.getBody());
    assertEquals("Test mode", request.getBody().getMode());

    assertNotNull(request.getUrl());
  }
}