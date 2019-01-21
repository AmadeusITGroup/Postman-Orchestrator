package postman.orchestrator.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an inheritance of {@link PostmanCollectionItem} in order to specify the
 * request object behavior.
 * In Postman schema there is no difference between a folder and an item
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class RequestItem extends PostmanCollectionItem {

  @SerializedName("request")
  @Expose
  private PostmanRequest request;

  @SerializedName("response")
  @Expose
  private List<Object> response = new ArrayList<Object>();

  /**
   * No args constructor for use in serialization
   *
   */
  public RequestItem() {
    super();
  }

  /**
   *
   * @param response
   * @param event
   * @param description
   * @param request
   * @param name
   */
  public RequestItem(String name, String description, List<Event> event, PostmanRequest request, List<Object> response) {
    super(name, description, event);
    this.request = request;
    this.response = response;
  }

  public PostmanRequest getRequest() {
    return request;
  }

  public void setRequest(PostmanRequest request) {
    this.request = request;
  }

  public List<Object> getResponse() {
    return response;
  }

  public void setResponse(List<Object> response) {
    this.response = response;
  }

  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof RequestItem))
      return false;

    RequestItem item = (RequestItem)obj;
    return item.getName().equalsIgnoreCase(this.getName());
  }
}
