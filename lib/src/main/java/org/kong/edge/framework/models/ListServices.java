package org.kong.edge.framework.models;

import java.util.ArrayList;

public class ListServices {

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public ArrayList<ServiceModel> getData() {
        return data;
    }

    public void setData(ArrayList<ServiceModel> data) {
        this.data = data;
    }

    private ArrayList<ServiceModel> data;
    private String next;
    private String offset;
}
