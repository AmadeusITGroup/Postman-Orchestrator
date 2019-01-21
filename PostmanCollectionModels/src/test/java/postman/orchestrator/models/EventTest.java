package postman.orchestrator.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
  
  @Test
  void testSerializationConstructor() {
    Event event = new Event();

    assertNull(event.getListen());
    assertNull(event.getScript());
  }

  @Test
  void testFullyParametrizedConstructor() {
    String listen = "pre-request";
    Script script = new Script();
    Event event = new Event(listen, script);

    assertEquals(listen, event.getListen());
    assertNotNull(event.getScript());
  }

  @Test
  void testOverrideValues() {
    String listen = "pre-request";
    Script script = new Script();
    Event event = new Event(listen, script);

    event.setListen("test");
    script = new Script();
    script.setId("1");
    event.setScript(script);

    assertEquals("test", event.getListen());
    assertNotNull(event.getScript());
    assertEquals("1", event.getScript().getId());
  }
}