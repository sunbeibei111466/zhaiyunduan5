package com.jlgproject.model.newDebt;

import java.util.List;

/**
 * @author 王锋 on 2017/8/19.
 */

public class Xu {


    private String id;
    private String name;
    private String pId;
    private List<String> list;


    public Xu(String id, String name, String pId, List<String> list) {
        this.id = id;
        this.name = name;
        this.pId = pId;
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
