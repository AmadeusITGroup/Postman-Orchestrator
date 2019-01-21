
package postman.orchestrator.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This class describes the Postman event object
 * It maps all required json properties to read and write Postman event
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class Event {

    @SerializedName("listen")
    @Expose
    private String listen;
    @SerializedName("script")
    @Expose
    private Script script;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Event() {
    }

    /**
     * 
     * @param script
     * @param listen
     */
    public Event(String listen, Script script) {
        super();
        this.listen = listen;
        this.script = script;
    }

    public String getListen() {
        return listen;
    }

    public void setListen(String listen) {
        this.listen = listen;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

}
