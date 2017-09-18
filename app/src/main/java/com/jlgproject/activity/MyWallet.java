package com.jlgproject.activity;

import android.content.Intent;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;

import com.jlgproject.R;
import com.jlgproject.adapter.DebtFragmentAdapter;
import com.jlgproject.base.BaseActivity;

import com.jlgproject.http.AddHeaders;
import com.jlgproject.http.BaseUrl;
import com.jlgproject.http.GetParmars;
import com.jlgproject.http.HttpMessageInfo;
import com.jlgproject.http.OkHttpUtils;

import com.jlgproject.model.MyWalletMode;
import com.jlgproject.model.News;
import com.jlgproject.model.weixin.WeiXinBean;
import com.jlgproject.util.ActivityCollector;
import com.jlgproject.util.L;
import com.jlgproject.util.ToastUtil;
import com.jlgproject.util.UserInfoState;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * 我的钱包
 */
public class MyWallet extends BaseActivity implements View.OnClickListener, HttpMessageInfo.IMessage {

    private TextView mTv_Title_Wallet, mTv_Title_Wallet_right,yueMoney,zhaiBiMoney;
    private ImageView mIv_Title_left_Wallet;
    private LinearLayout mLl_ivParent_title_Wallet;
    private TextView mTv_wallet_yue;
    private DebtFragmentAdapter debtFragmentAdapter;
    private LinearLayout chz;
    private LinearLayout debt_chz;
    private LinearLayout the_detail;
    private LinearLayout debt_money;

    @Override
    public int loadWindowLayout() {
        return R.layout.activity_my_wallet;
    }

    @Override
    public void initViews() {
        //动态设置标题
        mTv_Title_Wallet = (TextView) findViewById(R.id.tv_title_name);
        mTv_Title_Wallet.setText(getResources().getText(R.string.wallet));
        mIv_Title_left_Wallet = (ImageView) findViewById(R.id.iv_title_left);
        mIv_Title_left_Wallet.setOnClickListener(this);
        mIv_Title_left_Wallet.setVisibility(View.VISIBLE);
        mLl_ivParent_title_Wallet = (LinearLayout) findViewById(R.id.ll_ivParent_title);
        mLl_ivParent_title_Wallet.setOnClickListener(this);
        chz = (LinearLayout) findViewById(R.id.chongzhi);
        chz.setOnClickListener(this);
        debt_chz = (LinearLayout) findViewById(R.id.debt_rechage);
        debt_chz.setOnClickListener(this);
        the_detail = (LinearLayout) findViewById(R.id.the_detail);
        the_detail.setOnClickListener(this);
        yueMoney= (TextView) findViewById(R.id.money);//余额
        zhaiBiMoney= (TextView) findViewById(R.id.yue_money);//债比余额
        debt_money = (LinearLayout) findViewById(R.id.debt_mingxi);
        debt_money.setOnClickListener(this);

    }
    // 改变颜色


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_left:
                finish();
                break;
            case R.id.chongzhi: //余额充值
                Intent intent=new Intent(this, ChongZhi.class);
                intent.putExtra("type","0");
                startActivity(intent);
                break;
            case R.id.debt_rechage: //债比充值
                Intent intent1=new Intent(this, ChongZhi.class);
                intent1.putExtra("type","3");
                startActivity(intent1);
                break;
            case R.id.the_detail:
                //余额账单明细
                startActivity(new Intent(this, Billing_Details.class));
                break;
            case R.id.debt_mingxi:
                //债币账单明细
                startActivity(new Intent(this,Debt_Money.class));
                break;
        }
    }






    @Override
    protected void onResume() {
        super.onResume();
        getBalance();
    }

    public void getBalance() {
        if (ShowDrawDialog(this)) {
            HttpMessageInfo<MyWalletMode> info = new HttpMessageInfo<MyWalletMode>(BaseUrl.MY_WALLET, new MyWalletMode());
            info.setiMessage(this);
            AddHeaders header = new AddHeaders();
            header.add("Authorization", UserInfoState.getToken());
            OkHttpUtils.getInstance().getServiceMassage(OkHttpUtils.TYPE_GET, info, null, header, 1);
        }
    }

    @Override
    public void getServiceData(Object o) {
        if(o instanceof MyWalletMode){
            MyWalletMode wallet= (MyWalletMode) o;
            if(wallet!=null){
                DismissDialog();
                if(wallet.getState().equals("ok")){
                    yueMoney.setText(wallet.getData().getUser_money());//余额
                    zhaiBiMoney.setText(wallet.getData().getPay_points());
                }else{

                }
            }
        }
    }

    @Override
    public void getErrorData(Call call, IOException e) {
        DismissDialog();
        ToastUtil.showShort(this,"服务器繁忙，请稍后再试");
    }

}
