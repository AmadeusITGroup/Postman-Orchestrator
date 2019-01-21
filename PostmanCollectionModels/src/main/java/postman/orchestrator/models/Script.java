
package postman.orchestrator.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class describes the Postman script object, either pre-request or test script
 * It maps all required json properties to read and write a script
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class Script {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("exec")
    @Expose
    private List<String> exec = new ArrayList<String>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Script() {
    }

    /**
     *
     * @param id
     * @param exec
     * @param type
     */
    public Script(String id, String type, List<String> exec) {
        super();
        this.id = id;
        this.type = type;
        this.exec = exec;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getExec() {
        return exec;
    }

    public void setExec(List<String> exec) {
        this.exec = exec;
    }

}
