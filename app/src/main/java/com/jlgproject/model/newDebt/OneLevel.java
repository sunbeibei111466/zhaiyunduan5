package com.jlgproject.model.newDebt;

/**
 * @author 王锋 on 2017/8/12.
 */

public class OneLevel {


    private String name;
    private String image;
    private String id;
    private String pId;


    public OneLevel(String name, String image, String id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public OneLevel(String name, String image, String id, String pId) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }
}
