package org.kong.edge.framework.models;

import java.util.ArrayList;

public class RouteResponse  {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    private String name;

    public ArrayList<String> getProtocols() {
        return protocols;
    }

    public void setProtocols(ArrayList<String> protocols) {
        this.protocols = protocols;
    }

    public Object getHosts() {
        return hosts;
    }

    public void setHosts(Object hosts) {
        this.hosts = hosts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHttps_redirect_status_code() {
        return https_redirect_status_code;
    }

    public void setHttps_redirect_status_code(int https_redirect_status_code) {
        this.https_redirect_status_code = https_redirect_status_code;
    }

    public boolean isStrip_path() {
        return strip_path;
    }

    public void setStrip_path(boolean strip_path) {
        this.strip_path = strip_path;
    }

    public boolean isPreserve_host() {
        return preserve_host;
    }

    public void setPreserve_host(boolean preserve_host) {
        this.preserve_host = preserve_host;
    }

    public boolean isRequest_buffering() {
        return request_buffering;
    }

    public void setRequest_buffering(boolean request_buffering) {
        this.request_buffering = request_buffering;
    }

    public boolean isResponse_buffering() {
        return response_buffering;
    }

    public void setResponse_buffering(boolean response_buffering) {
        this.response_buffering = response_buffering;
    }

    public ArrayList<Object> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Object> tags) {
        this.tags = tags;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Object getMethods() {
        return methods;
    }

    public void setMethods(Object methods) {
        this.methods = methods;
    }

    public ArrayList<String> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<String> paths) {
        this.paths = paths;
    }

    public Object getHeaders() {
        return headers;
    }

    public void setHeaders(Object headers) {
        this.headers = headers;
    }

    public int getRegex_priority() {
        return regex_priority;
    }

    public void setRegex_priority(int regex_priority) {
        this.regex_priority = regex_priority;
    }

    public String getPath_handling() {
        return path_handling;
    }

    public void setPath_handling(String path_handling) {
        this.path_handling = path_handling;
    }

    public Object getSources() {
        return sources;
    }

    public void setSources(Object sources) {
        this.sources = sources;
    }

    public Object getDestinations() {
        return destinations;
    }

    public void setDestinations(Object destinations) {
        this.destinations = destinations;
    }

    public Object getSnis() {
        return snis;
    }

    public void setSnis(Object snis) {
        this.snis = snis;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdates_at() {
        return updates_at;
    }

    public void setUpdates_at(long updates_at) {
        this.updates_at = updates_at;
    }

    private ArrayList<String> protocols;
    private int https_redirect_status_code;
    private boolean strip_path;
    private boolean preserve_host;
    private boolean request_buffering;
    private boolean response_buffering;
    private ArrayList<Object> tags;
    private Service service;
    private Object methods;
    private Object hosts;
    private ArrayList<String> paths;
    private Object headers;
    private int regex_priority;
    private String path_handling;
    private Object sources;
    private Object destinations;
    private Object snis;
    private long created_at;
    private long updates_at;
}
