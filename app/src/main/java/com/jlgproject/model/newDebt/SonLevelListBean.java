package com.jlgproject.model.newDebt;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王锋 on 2017/8/19.
 */

public class SonLevelListBean implements Serializable{

    private String name;
    private String id;
    private String pId;
    private String url;
    private List<?> sonLevelList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<?> getSonLevelList() {
        return sonLevelList;
    }

    public void setSonLevelList(List<?> sonLevelList) {
        this.sonLevelList = sonLevelList;
    }

}
