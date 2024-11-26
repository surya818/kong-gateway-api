package org.kong.edge.framework.models;

import java.util.ArrayList;

public class GatewayConfiguration {

    public ArrayList<ConfigItem> getData() {
        return data;
    }

    public void setData(ArrayList<ConfigItem> data) {
        this.data = data;
    }

    private ArrayList<ConfigItem> data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    private  Meta meta;
}









