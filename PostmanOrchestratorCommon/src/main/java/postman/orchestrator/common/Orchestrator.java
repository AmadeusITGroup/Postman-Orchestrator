package postman.orchestrator.common;

import java.util.*;

/**
 * This class describes the orchestrator file.
 * It represents the mapping between scenario name and the list of collections we want to combine
 * to generate the scenario
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class Orchestrator extends HashMap<String, OrchestratorItem> {

  public List<TestDescriptor> getTestList(String scenario) {
    HashSet<TestDescriptor> collectionList = new HashSet<>();
    getCollectionList(scenario, collectionList);

    return new ArrayList<>(collectionList);
  }

  public Boolean hasBuild(String scenario) {
    return this.containsKey(scenario);
  }

  private void getCollectionList(String scenario, HashSet<TestDescriptor> collectionList) {
    if(!this.containsKey(scenario)) return;

    OrchestratorItem item = this.get(scenario);

    item.getScenarios().stream().forEachOrdered(parent -> getCollectionList(parent, collectionList));
    collectionList.addAll(item.getFullCollectionList());
  }
}
