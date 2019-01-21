package postman.orchestrator.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class describes the orchestrator item.
 * It represents a base element of the orchestrator file. It contains the list of all collections that you want
 * to combine for your scenario. It can also be a combination of other scenarios
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class OrchestratorItem {
  @SerializedName("scenarios")
  @Expose
  private List<String> scenarios = new ArrayList<>();

  @SerializedName("collections")
  @Expose
  private List<String> collectionList = new ArrayList<>();

  @SerializedName("tests")
  @Expose
  private List<TestDescriptor> testList = new ArrayList<>();

  /**
   * No args constructor for use in serialization
   *
   */
  public OrchestratorItem() {
  }

  /**
   *
   * @param scenarios
   * @param collectionList
   */
  public OrchestratorItem(List<String> scenarios, List<String> collectionList, List<TestDescriptor> testList) {
    super();
    this.scenarios = scenarios;
    this.collectionList = collectionList;
    this.testList = testList;
  }

  /**
   *
   * @param collectionList
   */
  public OrchestratorItem(List<String> collectionList) {
    super();
    this.collectionList = collectionList;
  }

  public List<String> getScenarios() {
    return scenarios;
  }

  public void setScenarios(List<String> scenarios) {
    this.scenarios = scenarios;
  }

  public List<String> getCollectionList() {
    return collectionList;
  }

  public List<TestDescriptor> getFullCollectionList() {
    List<TestDescriptor> fullList = this.collectionList.stream().filter(StringUtils::isNotBlank)
            .map(TestDescriptor::new).collect(Collectors.toList());
    fullList.addAll(this.testList);
    return fullList;
  }

  public void setCollectionList(List<String> collectionList) {
    this.collectionList = collectionList;
  }
}
