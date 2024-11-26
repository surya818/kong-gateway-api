package org.kong.edge.framework.models;

public class CreateControlPlaneRequest
{
    private String name;
    private String description;

    public String getCluster_type() {
        return cluster_type;
    }

    public void setCluster_type(String cluster_type) {
        this.cluster_type = cluster_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuth_type() {
        return auth_type;
    }

    public void setAuth_type(String auth_type) {
        this.auth_type = auth_type;
    }

    public boolean isCloud_gateway() {
        return cloud_gateway;
    }

    public void setCloud_gateway(boolean cloud_gateway) {
        this.cloud_gateway = cloud_gateway;
    }

    public Labels getLabels() {
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    private String cluster_type;
    private String auth_type;
    private boolean cloud_gateway;
    private Labels labels;
}

