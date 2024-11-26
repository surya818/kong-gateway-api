package org.kong.edge.framework.models;

import java.util.ArrayList;
import java.util.Date;

public class ConfigItem{
    private String kind;
    private String id;
    private String control_plane_id;
    private String control_plane_geo;

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getControl_plane_id() {
        return control_plane_id;
    }

    public void setControl_plane_id(String control_plane_id) {
        this.control_plane_id = control_plane_id;
    }

    public String getControl_plane_geo() {
        return control_plane_geo;
    }

    public void setControl_plane_geo(String control_plane_geo) {
        this.control_plane_geo = control_plane_geo;
    }

    public ArrayList<DataplaneGroupConfig> getDataplane_group_config() {
        return dataplane_group_config;
    }

    public void setDataplane_group_config(ArrayList<DataplaneGroupConfig> dataplane_group_config) {
        this.dataplane_group_config = dataplane_group_config;
    }

    public ArrayList<DataplaneGroup> getDataplane_groups() {
        return dataplane_groups;
    }

    public void setDataplane_groups(ArrayList<DataplaneGroup> dataplane_groups) {
        this.dataplane_groups = dataplane_groups;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getEntity_version() {
        return entity_version;
    }

    public void setEntity_version(int entity_version) {
        this.entity_version = entity_version;
    }

    private ArrayList<DataplaneGroupConfig> dataplane_group_config;
    private ArrayList<DataplaneGroup> dataplane_groups;
    private Date created_at;
    private Date updated_at;
    private int entity_version;
}
