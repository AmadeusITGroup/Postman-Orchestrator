
package postman.orchestrator.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class describes the Postman request object
 * It maps all required json properties to read and write request object
 * In Postman the request object describes all the elements required to send your request: url, headers, method,
 * parameters....
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class PostmanRequest {

    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("header")
    @Expose
    private List<PostmanHeader> header = new ArrayList<PostmanHeader>();
    @SerializedName("body")
    @Expose
    private PostmanRequestBody body;
    @SerializedName("url")
    @Expose
    private PostmanUrl url;
    @SerializedName("description")
    @Expose
    private String description;

    /**
     * No args constructor for use in serialization
     *
     */
    public PostmanRequest() {
    }

    /**
     *
     * @param body
     * @param description
     * @param method
     * @param url
     * @param header
     */
    public PostmanRequest(String method, List<PostmanHeader> header, PostmanRequestBody body, PostmanUrl url, String description) {
        super();
        this.method = method;
        this.header = header;
        this.body = body;
        this.url = url;
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<PostmanHeader> getPostmanHeader() {
        return header;
    }

    public void setPostmanHeader(List<PostmanHeader> header) {
        this.header = header;
    }

    public PostmanRequestBody getBody() {
        return body;
    }

    public void setBody(PostmanRequestBody body) {
        this.body = body;
    }

    public PostmanUrl getUrl() {
        return url;
    }

    public void setUrl(PostmanUrl url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
