package com.jlgproject.model.newDebt;

import java.util.List;

/**
 * @author 王锋 on 2017/8/21.
 */

public class DebtRelation4Vo {

    private List<com.jlgproject.model.newDebt.AssetNew> assetNew;

    public DebtRelation4Vo(List<AssetNew> assetNew) {
        this.assetNew = assetNew;
    }

    public List<AssetNew> getAssetNews() {
        return assetNew;
    }

    public void setAssetNews(List<AssetNew> assetNews) {
        this.assetNew = assetNews;
    }
}
