
package postman.orchestrator.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class describes the Postman header object
 * It maps all required json properties to read and write header
 * In Postman the header object could be part of the request and/or the response
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class PostmanHeader {

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
    public PostmanHeader() {
    }

    /**
     *
     * @param value
     * @param key
     */
    public PostmanHeader(String key, String value) {
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
