package org.kong.edge.framework.models;

import java.util.ArrayList;
import java.util.Date;

public class DataplaneGroup{
    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public ArrayList<String> getHostnames() {
        return hostnames;
    }

    public void setHostnames(ArrayList<String> hostnames) {
        this.hostnames = hostnames;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String region;
    private String state;
    private ArrayList<String> hostnames;
    private Date created_at;
    private Date updated_at;
}