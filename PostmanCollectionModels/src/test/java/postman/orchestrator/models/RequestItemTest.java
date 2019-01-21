package postman.orchestrator.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RequestItemTest {
  @Test
  void testSerializationConstructor() {
    RequestItem item = new RequestItem();

    assertNull(item.getDescription());
    assertNotNull(item.getEvent());
    assertTrue(item.getEvent().isEmpty());
    assertNull(item.getName());
    assertNull(item.getRequest());
    assertNotNull(item.getResponse());
    assertTrue(item.getResponse().isEmpty());
  }

  @Test
  void testFullyParametrizedConstructor() {
    String name = "Item name";
    String description = "This is a description";

    RequestItem item = new RequestItem(name, description, null, null, null);

    assertEquals(name, item.getName());
    assertEquals(description, item.getDescription());
    assertNull(item.getEvent());
    assertNull(item.getRequest());
    assertNull(item.getResponse());
  }

  @Test
  void testOverrideValues() {
    String name = "Item name";
    String description = "This is a description";

    RequestItem item = new RequestItem(name, description, null, null, null);

    assertEquals(name, item.getName());
    assertEquals(description, item.getDescription());
    assertNull(item.getEvent());
    assertNull(item.getRequest());
    assertNull(item.getResponse());

    name = "New Request name";
    description = "This is an updated description";

    item.setName(name);
    item.setDescription(description);
    item.setEvent(Arrays.asList(new Event()));

    PostmanRequest request = new PostmanRequest("GET", null, null, null, description);
    item.setRequest(request);

    item.setResponse(Arrays.asList(10));

    assertEquals(name, item.getName());
    assertEquals(description, item.getDescription());
    assertNotNull(item.getEvent());
    assertFalse(item.getEvent().isEmpty());
    assertNotNull(item.getRequest());
    assertEquals("GET", item.getRequest().getMethod());

    assertNotNull(item.getResponse());
    assertFalse(item.getResponse().isEmpty());
    assertEquals(10, item.getResponse().get(0));
  }

  @Test
  void testEquality() {
    String request1 = "Request 1";
    String request2 = "Request 2";
    String description = "This is a description";

    RequestItem item1 = new RequestItem(request1, description, null, null, null);
    RequestItem item2 = new RequestItem(request2, description, null, null, null);
    RequestItem item3 = new RequestItem(request1, description, null, null, null);

    assertFalse(item1.equals(null));
    assertFalse(item1.equals(request2));

    assertFalse(item1.equals(item2));
    assertTrue(item1.equals(item3));
  }
}