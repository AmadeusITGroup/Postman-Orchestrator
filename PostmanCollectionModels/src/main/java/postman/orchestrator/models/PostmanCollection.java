
package postman.orchestrator.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class describes the Postman collection object
 * It maps all required json properties to read and write Postman collection
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class PostmanCollection {

    @SerializedName("info")
    @Expose
    private CollectionInfo info;
    @SerializedName("item")
    @Expose
    private List<PostmanCollectionItem> items = new ArrayList<PostmanCollectionItem>();
    @SerializedName("event")
    @Expose
    private List<Event> events = new ArrayList<Event>();

    /**
     * No args constructor for use in serialization
     *
     */
    public PostmanCollection() {
    }

    /**
     *
     * @param events
     * @param items
     * @param info
     */
    public PostmanCollection(CollectionInfo info, List<PostmanCollectionItem> items, List<Event> events) {
        super();
        this.info = info;
        this.items = items;
        this.events = events;
    }

    public PostmanCollection(String name) {
        super();
        this.info = new CollectionInfo(name);
    }

    public CollectionInfo getInfo() {
        return info;
    }

    public void setInfo(CollectionInfo info) {
        this.info = info;
    }

    public List<PostmanCollectionItem> getItems() {
        return items;
    }

    public void setItems(List<PostmanCollectionItem> items) {
        this.items = items;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
