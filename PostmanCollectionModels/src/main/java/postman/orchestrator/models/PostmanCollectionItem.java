
package postman.orchestrator.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class describes the Postman collection item object
 * It maps all required json properties to read and write Postman item
 * In Postman, an item could either be a folder or a request. This class is a base descriptor for both
 * A specific implementation has been defined to manage folder behavior {@link FolderItem} and
 * request behavior {@link RequestItem}
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class PostmanCollectionItem {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

	  @SerializedName("event")
    @Expose
    private List<Event> event = new ArrayList<Event>();

    /**
     * No args constructor for use in serialization
     *
     */
    public PostmanCollectionItem() {
    }

    /**
     *
     * @param event
     * @param description
     * @param name
     */
    public PostmanCollectionItem(String name, String description, List<Event> event) {
        super();
        this.name = name;
        this.description = description;
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }
}
