package postman.orchestrator.common;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OrchestratorItemTest {
  @Test
  void testBaseConstructor() {
    OrchestratorItem item = new OrchestratorItem();

    assertNotNull(item.getScenarios());
    assertTrue(item.getScenarios().isEmpty());
    assertNotNull(item.getCollectionList());
    assertTrue(item.getCollectionList().isEmpty());
    assertNotNull(item.getFullCollectionList());
    assertTrue(item.getFullCollectionList().isEmpty());
  }

  @Test
  void testFullConstructor() {
    OrchestratorItem item = new OrchestratorItem(Arrays.asList("Scenario 1"),
            Arrays.asList("Collection 1"),
            Arrays.asList(new TestDescriptor("Collection 2")));

    assertNotNull(item.getScenarios());
    assertFalse(item.getScenarios().isEmpty());
    assertNotNull(item.getCollectionList());
    assertFalse(item.getCollectionList().isEmpty());
    assertNotNull(item.getFullCollectionList());
    assertFalse(item.getFullCollectionList().isEmpty());
    assertEquals(2, item.getFullCollectionList().size());
  }

  @Test
  void testOverrideValues() {
    OrchestratorItem item = new OrchestratorItem(Arrays.asList("Collection 1"));

    assertNotNull(item.getScenarios());
    assertTrue(item.getScenarios().isEmpty());
    assertNotNull(item.getCollectionList());
    assertFalse(item.getCollectionList().isEmpty());
    assertNotNull(item.getFullCollectionList());
    assertFalse(item.getFullCollectionList().isEmpty());
    assertEquals(1, item.getFullCollectionList().size());

    item.setScenarios(Arrays.asList("Scenario 1", "Scenario 2"));
    assertNotNull(item.getScenarios());
    assertFalse(item.getScenarios().isEmpty());
    assertEquals(2, item.getScenarios().size());

    item.setCollectionList(Arrays.asList("Collection 2", "Collection 3"));
    assertNotNull(item.getFullCollectionList());
    assertFalse(item.getFullCollectionList().isEmpty());
    assertEquals(2, item.getFullCollectionList().size());
    assertEquals(2, item.getCollectionList().size());
    assertFalse(item.getCollectionList().stream().anyMatch(x -> x == "Collection 1"));
  }
}