package com.jlgproject.model.newDebt;

import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;


import com.jlgproject.adapter.newDebt.BaseAdapter_jl;
import com.jlgproject.model.*;
import com.jlgproject.model.AssetNew;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王锋 on 2017/8/21.
 */

public class StoreMode implements Serializable {



    private List<com.jlgproject.model.newDebt.AssetNew> list;
    private LinearLayout linearLayout;
    private BaseAdapter_jl adapter_jl;

    public StoreMode(List<com.jlgproject.model.newDebt.AssetNew> list, LinearLayout linearLayout, BaseAdapter_jl adapter_jl) {
        this.list = list;
        this.linearLayout = linearLayout;
        this.adapter_jl = adapter_jl;
    }



    public List<com.jlgproject.model.newDebt.AssetNew> getList() {
        return list;
    }

    public void setList(List<com.jlgproject.model.newDebt.AssetNew> list) {
        this.list = list;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public void setLinearLayout(LinearLayout linearLayout) {
        this.linearLayout = linearLayout;
    }

    public BaseAdapter_jl getAdapter_jl() {
        return adapter_jl;
    }

    public void setAdapter_jl(BaseAdapter_jl adapter_jl) {
        this.adapter_jl = adapter_jl;
    }
}
