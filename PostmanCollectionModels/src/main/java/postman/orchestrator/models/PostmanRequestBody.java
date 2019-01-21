
package postman.orchestrator.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class describes the Postman request body object
 * It maps all required json properties to read and write request body object
 * In Postman the request body object describes the body part in case of POST request.
 * It contains the mode (either raw or file) and in case mode=raw, the raw data (generally a json string)
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class PostmanRequestBody {

    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("raw")
    @Expose
    private String raw;

    /**
     * No args constructor for use in serialization
     *
     */
    public PostmanRequestBody() {
    }

    /**
     *
     * @param raw
     * @param mode
     */
    public PostmanRequestBody(String mode, String raw) {
        super();
        this.mode = mode;
        this.raw = raw;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

}
