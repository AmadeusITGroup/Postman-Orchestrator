package postman.orchestrator.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This class describes the test descriptor object.
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class TestDescriptor {

  @SerializedName("collection")
  @Expose
  public String collectionName;

  @SerializedName("folders")
  @Expose
  public List<String> foldersToLoad;

  /**
   * No args constructor for use in serialization
   *
   */
  public TestDescriptor() {
    this.foldersToLoad = null;
  }

  /**
   * @param collectionName
   *
   */
  public TestDescriptor(String collectionName) {
    this();
    this.collectionName = collectionName;
  }

  /**
   *
   * @param collectionName
   * @param foldersToLoad
   */
  public TestDescriptor(String collectionName, List<String> foldersToLoad) {
    this(collectionName);
    this.foldersToLoad = foldersToLoad;
  }

  public String getCollectionName() {
    return collectionName;
  }

  public void setCollectionName(String collectionName) {
    this.collectionName = collectionName;
  }

  public List<String> getFoldersToLoad() {
    return foldersToLoad;
  }

  public void setFoldersToLoad(List<String> foldersToLoad) {
    this.foldersToLoad = foldersToLoad;
  }

  public Boolean loadFullCollection() { return this.foldersToLoad == null || this.foldersToLoad.isEmpty(); }

  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof TestDescriptor))
      return false;

    TestDescriptor desc = (TestDescriptor)obj;
    return desc.getCollectionName().equalsIgnoreCase(this.collectionName);
  }

  @Override
  public int hashCode() {
    try{
      return this.collectionName.hashCode();
    }catch (final Exception e) {
      e.printStackTrace();
      return -1;
    }
  }
}
