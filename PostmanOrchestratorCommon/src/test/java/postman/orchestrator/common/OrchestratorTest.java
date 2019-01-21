package postman.orchestrator.common;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrchestratorTest {
  @Test
  void testGetTestList() {
    Orchestrator orch = new Orchestrator();

    OrchestratorItem item = new OrchestratorItem(Arrays.asList("Collection 1"));
    orch.put("Scenario 1", item);

    List<TestDescriptor> desc = orch.getTestList("Scenario 1");
    assertFalse(desc.isEmpty());
    assertEquals(1, desc.size());
    assertTrue(orch.hasBuild("Scenario 1"));

    desc = orch.getTestList("No Scenario");
    assertTrue(desc.isEmpty());
    assertFalse(orch.hasBuild("No Scenario"));
  }
}