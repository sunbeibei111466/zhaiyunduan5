package com.jlgproject.model.newDebt;

/**
 * @author 王锋 on 2017/8/16.
 */

public class ListItemMode {

    private String name;//名称
    private String sanjiId;//三级Id
    private String erjiId;//二级Id;
    private String sanjiStr;//三级属性名
    private String guzhi;//估值
    private String shuliang;//数量

    public ListItemMode() {

    }

    public ListItemMode(String name, String sanjiId, String sanjiStr, String guzhi, String shuliang) {
        this.name = name;
        this.sanjiId = sanjiId;
        this.sanjiStr = sanjiStr;
        this.guzhi = guzhi;
        this.shuliang = shuliang;
    }

    public ListItemMode(String name, String sanjiId, String erjiId, String sanjiStr, String guzhi, String shuliang) {
        this.name = name;
        this.sanjiId = sanjiId;
        this.erjiId = erjiId;
        this.sanjiStr = sanjiStr;
        this.guzhi = guzhi;
        this.shuliang = shuliang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSanjiId() {
        return sanjiId;
    }

    public void setSanjiId(String sanjiId) {
        this.sanjiId = sanjiId;
    }

    public String getSanjiStr() {
        return sanjiStr;
    }

    public void setSanjiStr(String sanjiStr) {
        this.sanjiStr = sanjiStr;
    }

    public String getGuzhi() {
        return guzhi;
    }

    public void setGuzhi(String guzhi) {
        this.guzhi = guzhi;
    }

    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang;
    }

    public String getErjiId() {
        return erjiId;
    }

    public void setErjiId(String erjiId) {
        this.erjiId = erjiId;
    }
}
