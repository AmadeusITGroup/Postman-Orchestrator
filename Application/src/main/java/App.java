import org.apache.commons.cli.*;
import postman.orchestrator.common.FolderStructure;
import postman.orchestrator.common.TestDescriptor;
import postman.orchestrator.common.services.CollectionService;
import postman.orchestrator.common.services.OrchestratorService;
import postman.orchestrator.models.*;

import java.nio.file.Paths;
import java.util.List;

/**
 * This class describes the available options for the PostmanOrchestrator command line
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
class OrchesratorOptions extends Options {

  public String getSourceFolderOption() { return "folder"; }
  public String getScenarioOption() { return "scenario"; }
  public String getOutputOption() { return "output"; }
  public String getHelpOption() { return "help"; }

  public OrchesratorOptions() {

    this.addOption(Option.builder("f").longOpt(getSourceFolderOption())
            .required(true)
            .hasArg().argName("folderPath")
            .desc("Source folder for collection list")
            .build());

    this.addOption(Option.builder("s").longOpt(getScenarioOption())
            .hasArg().argName("scenarioName")
            .desc("The scenario name to refer in the orchestrator")
            .build());

    this.addOption(Option.builder("o").longOpt(getOutputOption())
            .hasArg().argName("output")
            .desc("The output file name")
            .build());

    this.addOption(Option.builder("h").longOpt(getOutputOption())
            .hasArg().argName("help")
            .desc("Show command line help, including a list of options, and sample use cases.")
            .build());
  }
}

/**
 * This class is the command line parser for PostmanOrchestrator.
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
class OrchesratorCmdLine extends Options {

  private CommandLineParser parser = new DefaultParser();
  private HelpFormatter formatter = new HelpFormatter();
  private CommandLine cmd;
  private OrchesratorOptions options = new OrchesratorOptions();

  public String getSourceFolder() {
    return this.cmd.getOptionValue(options.getSourceFolderOption());
  }

  public Boolean hasScenarioOption() {
    return this.cmd.hasOption(options.getScenarioOption());
  }

  public String getScenario() {
    return this.cmd.getOptionValue(options.getScenarioOption());
  }

  public Boolean hasOutputOption() {
    return this.cmd.hasOption(options.getOutputOption());
  }

  public String getOutput() {
    return this.cmd.getOptionValue(options.getOutputOption());
  }

  public Boolean hasHelpOption() {
    return this.cmd.hasOption(options.getHelpOption());
  }

  public String getHelp() {
    return this.cmd.getOptionValue(options.getHelpOption());
  }

  public OrchesratorCmdLine() {
  }

  public boolean parseLines(String args[]) {
    try {
      this.cmd = this.parser.parse(this.options, args);

      if(this.hasHelpOption()){
        formatter.printHelp("utility-name", options);
        return false;
      }

      return true;
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      formatter.printHelp("utility-name", options);
      return false;
    }
  }
}

/**
 * The main application for the PostmanOrchestrator.
 * This part is responsible of the orchestration workflow using all services.
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class App {


  public static void main(String args[]) {

    OrchesratorCmdLine cmdLines = new OrchesratorCmdLine();
    if (cmdLines.parseLines(args)) {
      List<TestDescriptor> tests = null;
      CollectionService collectionService = new CollectionService();
      OrchestratorService orchService = new OrchestratorService();

      String collectionName = "Combined Collection";

      String rootPath = cmdLines.getSourceFolder();

      if (cmdLines.hasScenarioOption() && orchService.doesOrchestratorExists(rootPath)) {
        tests = orchService.getOrchestratorTests(rootPath, cmdLines.getScenario());
        collectionName = cmdLines.getScenario();
      }

      if(cmdLines.hasOutputOption()) {
        collectionName = cmdLines.getOutput();
      }

      if(tests == null) {
        tests = collectionService.findCollectionFiles(Paths.get(rootPath));
      }

      String filename = collectionName.replace(" ", "_").concat(".json");
      FolderStructure folders = collectionService.getStructure(rootPath, tests);
      PostmanCollection collection = collectionService.combineCollections(collectionName, folders);
      collectionService.saveCollection(Paths.get(rootPath, filename).toString(), collection);

    } else {
      System.exit(1);
    }
  }
}
