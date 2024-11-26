package org.kong.edge.framework.models;

public class ServiceModel {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String name;
    private Object tags;
    private int read_timeout;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

    public int getRead_timeout() {
        return read_timeout;
    }

    public void setRead_timeout(int read_timeout) {
        this.read_timeout = read_timeout;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public int getConnect_timeout() {
        return connect_timeout;
    }

    public void setConnect_timeout(int connect_timeout) {
        this.connect_timeout = connect_timeout;
    }

    public Object getCa_certificates() {
        return ca_certificates;
    }

    public void setCa_certificates(Object ca_certificates) {
        this.ca_certificates = ca_certificates;
    }

    public Object getClient_certificate() {
        return client_certificate;
    }

    public void setClient_certificate(Object client_certificate) {
        this.client_certificate = client_certificate;
    }

    public int getWrite_timeout() {
        return write_timeout;
    }

    public void setWrite_timeout(int write_timeout) {
        this.write_timeout = write_timeout;
    }



    private int retries;
    private int connect_timeout;
    private Object ca_certificates;
    private Object client_certificate;
    private int write_timeout;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;
    
}
