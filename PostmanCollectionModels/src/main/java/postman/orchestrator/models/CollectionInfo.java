package postman.orchestrator.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

/**
 * This class describes the Postman collection information
 * It maps all required json properties to read and write collection information
 * @author Anthony Todisco
 * @version 1.0
 * @since PostmanOrchestrator 1.0
 */
public class CollectionInfo {

    private transient String SCHEMA = "https://schema.getpostman.com/json/collection/v2.1.0/collection.json";

    @SerializedName("_postman_id")
    @Expose
    private String postmanId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("schema")
    @Expose
    private String schema;
    @SerializedName("description")
    @Expose
    private String description;

    /**
     * No args constructor for use in serialization
     *
     */
    public CollectionInfo() {
    }

    /**
     *
     * @param schema
     * @param postmanId
     * @param name
     * @param description
     */
    public CollectionInfo(String postmanId, String name, String description, String schema) {
        super();
        this.postmanId = postmanId;
        this.name = name;
        this.schema = schema;
        this.description = description;
    }

    CollectionInfo(String name) {
        super();
        String seed = "AMADEUS-DAPI" + new Date().getTime();
        this.postmanId = java.util.UUID.nameUUIDFromBytes(seed.getBytes()).toString();
        this.name = name;
        this.schema = SCHEMA;
    }

    public String getPostmanId() {
        return postmanId;
    }

    public void setPostmanId(String postmanId) {
        this.postmanId = postmanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

}
