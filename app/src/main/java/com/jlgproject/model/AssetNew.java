package com.jlgproject.model;

/**
 * @author 王锋 on 2017/8/19.
 */

public class AssetNew {

    private long id;
    private String name;
    private String model;
    private String totalAmout;
    private String assetNum;

    public AssetNew(long id, String name, String model, String totalAmout, String assetNum) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.totalAmout = totalAmout;
        this.assetNum = assetNum;
    }

    public AssetNew() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTotalAmout() {
        return totalAmout;
    }

    public void setTotalAmout(String totalAmout) {
        this.totalAmout = totalAmout;
    }

    public String getAssetNum() {
        return assetNum;
    }

    public void setAssetNum(String assetNum) {
        this.assetNum = assetNum;
    }
}
