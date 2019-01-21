package postman.orchestrator.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class describes the Postman query param object
 * It maps all required json properties to read and write a query param object
 * In Postman, the query param is a key value object that describes the parameter used in your request url
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class QueryParam {

  @SerializedName("key")
  @Expose
  private String key;
  @SerializedName("value")
  @Expose
  private String value;

  /**
   * No args constructor for use in serialization
   *
   */
  public QueryParam() {
  }

  /**
   *
   * @param value
   * @param key
   */
  public QueryParam(String key, String value) {
    super();
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
