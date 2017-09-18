package com.jlgproject.model;

import com.jlgproject.model.newDebt.DebtRelation3Vo;
import com.jlgproject.model.newDebt.DebtRelation4Vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王锋 on 2017/6/6.
 */

public class DebtRelationVo implements Serializable {

    private DebtRelation1Vo debtRelation1Vo;
    private DebtRelation3Vo debtRelation3Vo;
    private DebtRelation4Vo debtRelation4Vo;


    public DebtRelationVo() {
    }

    public DebtRelationVo(DebtRelation1Vo debtRelation1Vo, DebtRelation3Vo debtRelation3Vo, DebtRelation4Vo debtRelation4Vo) {
        this.debtRelation1Vo = debtRelation1Vo;
        this.debtRelation3Vo = debtRelation3Vo;
        this.debtRelation4Vo = debtRelation4Vo;
    }

    public DebtRelation1Vo getDebtRelation1Vo() {
        return debtRelation1Vo;
    }

    public void setDebtRelation1Vo(DebtRelation1Vo debtRelation1Vo) {
        this.debtRelation1Vo = debtRelation1Vo;
    }

    public DebtRelation3Vo getDebtRelation3Vo() {
        return debtRelation3Vo;
    }

    public void setDebtRelation3Vo(DebtRelation3Vo debtRelation3Vo) {
        this.debtRelation3Vo = debtRelation3Vo;
    }

    public DebtRelation4Vo getDebtRelation4Vo() {
        return debtRelation4Vo;
    }

    public void setDebtRelation4Vo(DebtRelation4Vo debtRelation4Vo) {
        this.debtRelation4Vo = debtRelation4Vo;
    }

}
