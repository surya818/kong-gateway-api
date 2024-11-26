package org.kong.edge.framework.models;

import java.util.ArrayList;

public class GetControlPlanesResponse {
    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ArrayList<CreateControlPlaneResponse> getData() {
        return data;
    }

    public void setData(ArrayList<CreateControlPlaneResponse> data) {
        this.data = data;
    }

    private Meta meta;
    private ArrayList<CreateControlPlaneResponse> data;
}
 class Meta{
     public Page getPage() {
         return page;
     }

     public void setPage(Page page) {
         this.page = page;
     }

     private Page page;
}

 class Page{
    private int total;

     public int getTotal() {
         return total;
     }

     public void setTotal(int total) {
         this.total = total;
     }

     public int getSize() {
         return size;
     }

     public void setSize(int size) {
         this.size = size;
     }

     public int getNumber() {
         return number;
     }

     public void setNumber(int number) {
         this.number = number;
     }

     private int size;
     private int number;
}
