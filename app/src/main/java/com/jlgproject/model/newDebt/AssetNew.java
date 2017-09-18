package com.jlgproject.model.newDebt;

/**
 * @author 王锋 on 2017/8/21.
 */

public class AssetNew {

    private String name;//资产名称
    private String model;//资产型号
    private String totalAmout;//总价值
    private String assetNum;//	数量
    private String idasset;//id
    private String erjiId;

    public AssetNew(String name, String model, String totalAmout, String assetNum, String idasset) {
        this.name = name;
        this.model = model;
        this.totalAmout = totalAmout;
        this.assetNum = assetNum;
        this.idasset = idasset;
    }

    public AssetNew(String name, String model, String totalAmout, String assetNum, String idasset, String erjiId) {
        this.name = name;
        this.model = model;
        this.totalAmout = totalAmout;
        this.assetNum = assetNum;
        this.idasset = idasset;
        this.erjiId = erjiId;
    }

    public String getErjiId() {
        return erjiId;
    }

    public void setErjiId(String erjiId) {
        this.erjiId = erjiId;
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

    public String getIdasset() {
        return idasset;
    }

    public void setIdasset(String idasset) {
        this.idasset = idasset;
    }
}
