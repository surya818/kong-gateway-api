package org.kong.edge.framework.models;

import java.util.ArrayList;

public class Config {
    private String control_plane_endpoint;
    private String telemetry_endpoint;

    public boolean isCloud_gateway() {
        return cloud_gateway;
    }

    public void setCloud_gateway(boolean cloud_gateway) {
        this.cloud_gateway = cloud_gateway;
    }

    public String getControl_plane_endpoint() {
        return control_plane_endpoint;
    }

    public void setControl_plane_endpoint(String control_plane_endpoint) {
        this.control_plane_endpoint = control_plane_endpoint;
    }

    public String getTelemetry_endpoint() {
        return telemetry_endpoint;
    }

    public void setTelemetry_endpoint(String telemetry_endpoint) {
        this.telemetry_endpoint = telemetry_endpoint;
    }

    public String getCluster_type() {
        return cluster_type;
    }

    public void setCluster_type(String cluster_type) {
        this.cluster_type = cluster_type;
    }

    public String getAuth_type() {
        return auth_type;
    }

    public void setAuth_type(String auth_type) {
        this.auth_type = auth_type;
    }

    public ArrayList<Object> getProxy_urls() {
        return proxy_urls;
    }

    public void setProxy_urls(ArrayList<Object> proxy_urls) {
        this.proxy_urls = proxy_urls;
    }

    private String cluster_type;
    private String auth_type;
    private boolean cloud_gateway;
    private ArrayList<Object> proxy_urls;
}
