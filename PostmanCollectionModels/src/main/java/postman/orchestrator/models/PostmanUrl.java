package postman.orchestrator.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * This class describes the Postman url object
 * It maps all required json properties to read and write url object
 * In Postman, the url object describes the used url for your request as part
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class PostmanUrl {

  @SerializedName("raw")
  @Expose
  private String raw;
  @SerializedName("protocol")
  @Expose
  private String protocol;
  @SerializedName("host")
  @Expose
  private List<String> host = new ArrayList<String>();
  @SerializedName("path")
  @Expose
  private List<String> path = new ArrayList<String>();
  @SerializedName("query")
  @Expose
  private List<QueryParam> queryParameters = new ArrayList<QueryParam>();

  /**
   * No args constructor for use in serialization
   *
   */
  public PostmanUrl() {
  }

  /**
   *
   * @param raw
   * @param protocol
   * @param host
   * @param query
   * @param path
   */
  public PostmanUrl(String raw, String protocol, List<String> host, List<String> path, List<QueryParam> query) {
    super();
    this.raw = raw;
    this.protocol = protocol;
    this.host = host;
    this.path = path;
    this.queryParameters = query;
  }

  public String getRaw() {
    return raw;
  }

  public void setRaw(String raw) {
    this.raw = raw;
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public List<String> getHost() {
    return host;
  }

  public void setHost(List<String> host) {
    this.host = host;
  }

  public List<String> getPath() {
    return path;
  }

  public void setPath(List<String> path) {
    this.path = path;
  }

  public List<QueryParam> getQueryParameters() {
    return queryParameters;
  }

  public void setQueryParameters(List<QueryParam> query) {
    this.queryParameters = query;
  }
}
