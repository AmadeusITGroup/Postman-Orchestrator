package postman.orchestrator.common;

import postman.orchestrator.models.PostmanCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * This class describes a folder structure object that will be used during the collection retrieve process
 * This object will also help to keep the folder imbrication while generating the combined Postman collection
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class FolderStructure {
  private List<FolderStructure> folders;
  private List<PostmanCollection> collections;

  private String name;

  public FolderStructure(String name)
  {
    this.name = name;
    this.folders = new ArrayList<>();
    this.collections = new ArrayList<>();
  }

  public List<FolderStructure> getFolders() { return this.folders; }
  public List<PostmanCollection> getCollections() { return this.collections; }
  public String getName() { return this.name; }

  private void addOrUpdate(FolderStructure struct) {
    FolderStructure match = getCorrespondingStructure(struct);

    if(match == null) {
      this.folders.add(struct);
    } else {
      match.combine(struct);
    }
  }

  public void combine(FolderStructure struct) {
    if(struct == null) return;

    for (FolderStructure f : struct.getFolders()) {
      this.addOrUpdate(f);
    }

    this.collections.addAll(struct.getCollections());
  }

  private FolderStructure getCorrespondingStructure(FolderStructure struct) {
    return this.folders.stream()
            .filter(f -> f.getName().equalsIgnoreCase(struct.getName()))
            .findFirst()
            .orElse(null);
  }
}
