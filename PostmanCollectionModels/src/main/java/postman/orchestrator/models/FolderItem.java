package postman.orchestrator.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an inheritance of {@link PostmanCollectionItem} in order to specify the
 * folder behavior.
 * In Postman schema there is no difference between a folder and an item
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class FolderItem extends PostmanCollectionItem {

  @SerializedName("item")
  @Expose
  private List<PostmanCollectionItem> items = new ArrayList<PostmanCollectionItem>();

  @SerializedName("_postman_isSubFolder")
  @Expose
  private boolean postmanIsSubFolder;

  /**
   * No args constructor for use in serialization
   *
   */
  public FolderItem() {
    super();
  }

  /**
   *
   * @param event
   * @param description
   * @param items
   * @param name
   */
  public FolderItem(String name, String description, List<PostmanCollectionItem> items, List<Event> event,
                    boolean postmanIsSubFolder) {
    super(name, description, event);
    this.items = items;
    this.postmanIsSubFolder = postmanIsSubFolder;
  }

  public FolderItem(PostmanCollection collection)
  {
    super(collection.getInfo().getName(), collection.getInfo().getDescription(), collection.getEvents());

    this.items.addAll(collection.getItems());
  }

  public List<PostmanCollectionItem> getItems() {
    return items;
  }

  public void setItems(List<PostmanCollectionItem> items) {
    this.items = items;
  }

  public boolean isPostmanIsSubFolder() {
    return postmanIsSubFolder;
  }

  public void setPostmanIsSubFolder(boolean postmanIsSubFolder) {
    this.postmanIsSubFolder = postmanIsSubFolder;
  }

  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof FolderItem))
      return false;

    FolderItem item = (FolderItem)obj;
    return item.getName().equalsIgnoreCase(this.getName());
  }
}
