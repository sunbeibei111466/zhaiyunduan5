package com.jlgproject.model.newDebt;

import java.util.List;

/**
 * @author 王锋 on 2017/8/21.
 */

public class DebtRelation3Vo {

    private List<String> demendid;

    public DebtRelation3Vo(List<String> demendid) {
        this.demendid = demendid;
    }

    public List<String> getDemendid() {
        return demendid;
    }

    public void setDemendid(List<String> demendid) {
        this.demendid = demendid;
    }
}
