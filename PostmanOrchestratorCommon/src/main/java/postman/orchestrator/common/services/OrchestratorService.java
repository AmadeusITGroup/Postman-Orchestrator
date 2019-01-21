package postman.orchestrator.common.services;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import postman.orchestrator.common.Orchestrator;
import postman.orchestrator.common.TestDescriptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * This service is used to deserialize orchestrator file and provide the list of collections
 * the user want to use for combination based on the scenario name.
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class OrchestratorService {

  private static final String ORCHESTRATOR_FILE = "orchestrator.json";

  public List<TestDescriptor> getOrchestratorTests(String rootPath, String build) {
    Gson gson = new Gson();
    String orchestratorContent = getOrchestratorContent(rootPath);

    if(StringUtils.isNotBlank(orchestratorContent)) {
      Orchestrator orch = gson.fromJson(orchestratorContent, Orchestrator.class);
      if(orch.hasBuild(build)) {
        return orch.getTestList(build);
      }
    }

    return null;
  }

  public Boolean doesOrchestratorExists(String rootPath) {
    return Files.exists(Paths.get(rootPath, ORCHESTRATOR_FILE));
  }

  private static String getOrchestratorContent(String rootPath) {
    try {
      return new String(Files.readAllBytes(Paths.get(rootPath, ORCHESTRATOR_FILE)));
    }catch (final IOException ioe) {
      ioe.printStackTrace();
      return "";
    }
  }
}
