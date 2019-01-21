package postman.orchestrator.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ScriptTest {
  
  @Test
  void testSerializationConstructor() {
    Script script = new Script();

    assertNull(script.getId());
    assertNull(script.getType());

    assertNotNull(script.getExec());
    assertTrue(script.getExec().isEmpty());
  }

  @Test
  void testFullyParametrizedConstructor() {
    String id = "Script id";
    String type = "javascript";
    Script script = new Script(id, type, null);

    assertEquals(id, script.getId());
    assertEquals(type, script.getType());

    assertNull(script.getExec());
  }

  @Test
  void testOverrideValues() {
    String id = "Script id";
    String type = "javascript";
    Script script = new Script(id, type, null);

    assertEquals(id, script.getId());
    assertEquals(type, script.getType());

    assertNull(script.getExec());

    id = "Updated script id";
    type = "cURL";

    script.setId(id);
    script.setType(type);
    script.setExec(Arrays.asList("/** Comment in test **/"));

    assertEquals(id, script.getId());
    assertEquals(type, script.getType());

    assertNotNull(script.getExec());
    assertFalse(script.getExec().isEmpty());
    assertEquals("/** Comment in test **/", script.getExec().get(0));
  }
}