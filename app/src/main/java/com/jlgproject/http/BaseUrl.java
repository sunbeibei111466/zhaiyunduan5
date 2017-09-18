package com.jlgproject.http;

/**
 * URL
 *
 * @author 王锋 on 2017/5/16.
 */

public class BaseUrl {

    //    主机地址

  public final static String MAIN = "http://api.zhongjinzhaishi.com/";
//  public final static String MAIN = "http://test.api.zhongjinzhaishi.com/";
//    public final static  String MAIN="http://192.168.2.11:8056/";
  //  public final static  String MAIN="http://192.168.2.33:8056/";
    public final static String USER_TYPE = MAIN + "/api/provide";
    // 登录
    public final static String LOGIN = MAIN + "mobile";
    //注册
    public final static String RESIGER = MAIN + "api/regist/valid";
    //  更多新闻
    public final static String GENG_NEWS_2 = MAIN + "api/news/getNews?";
    //首页轮播图 &新闻
    public final static String BAN_NEWS = MAIN + "api/news/getFirstNews";
    // 验证码
    public final static String YZM = MAIN + "api/regist/smscode?";
    //重置密码
    public final static String UPDATE_PASSWOED = MAIN + "api/regist/changePwd";
    //支付
    public final static String GOPAY = MAIN + "/api/alipay/pay/createpay";
    //weixin支付
    public final static String WX_GOPAY = MAIN + "/api/pay/toPay";
    //余额支付
    public final static String YUEPAY = MAIN + "/api/my/lessbalance";
    //债事备案图片上传
    public final static String LOAD_IMAGE = MAIN + "api/image";
    //债事备案图片上传
    public final static String UPDETA_IMAGE = MAIN + "api/regist/changeimage";
    //债事备案查询接口
    public final static String QUERY = MAIN + "api/search?";
    //债事人管理接口
    public final static String DEBT_PRESON_MANGER = MAIN + "api/debt/byuser?";
    //债事备案
    public final static String ADD_DEBT = MAIN + "api/debtrelation/adddebtrelation/relation";

    //债事备案 --新 获取数据
    public final static String ADD_DEBT_NEW = MAIN + "api/comcategory";

    public final static String ADD_DEBT_NEW_SHANGCHUAN = MAIN + "api/debtrelation/adddebtrelation/relationnew";

    //债事管理
    public final static String DEBTS_MANGER = MAIN + "api/debtrelation?";
    //债事管理2
    public final static String DEBTS_MANGER_2 = MAIN + "api/debtrelation/getalldebtRelation?";
    //债事管理搜索
    public final static String DEBTS_MANGER_SEARCH = MAIN + "api/debtrelation/searchdebtrelation?";
    //债事管理详情2
    public final static String DEBTS_MANGER_DETAIL2 = MAIN + "api/debtrelation/detailnew?";
    //债事人详情基本信息
    public final static String DEBTS_PRESON_INFORMATION = MAIN + "api/debt?";
    //开通债行
    public final static String OPEN_DEBTS = MAIN + "api/opendebt";
    //给被人开通债行
    public final static String OPEN_OTHERS_DEBTS = MAIN + "api/open/opencloud";

    //添加债事人
    public final static String ADD_DEBT_PRESON = MAIN + "api/debt/adddebt";
    //我的页
    public final static String MY_PAGER = MAIN + "api/my/my";
    //我的钱包
    public final static String MY_WALLET = MAIN + "api/my/getbalance";
    //钱包支付宝充值 获取orderInfo
    public final static String ZFB_ORDERINFO_GET = MAIN + "api/alipay/mypay/createpay";
    //钱包微信充值 获取orderInfo
    public final static String WEIXIN_ORDERINFO_GET = MAIN + "api/mypay/toPay";
    //我的余额账单
    public final static String MY_BILL = MAIN + "api/my/bill?";
    //我的账单
    public final static String MY_ZONGBILL = MAIN + "api/my/getbill?";
    //我的债币账单
    public final static String MY_ZHAIBI = MAIN + "api/my/bill/bond?";
    //我的意见反馈
    public final static String MY_FK = MAIN + "api/my/feedback";
    //我的  修改手机号
    public final static String MY_PHONE = MAIN + "api/my/changePhone";
    //我的  充值
    public final static String MY_CZ = MAIN + "api/my/pay";
    //债事信息
    public final static String DEBTS_INFORMATION = MAIN + "api/debtrelation/debtinfo?";
    //资产信息
    public final static String ASSERT_INFORMATION = MAIN + "api/asset?";
    //资产信息新的
    public final static String ASSERT_INFORMATION_NEW = MAIN + "api/assetnew/getassetnew?";

    public final static String ASSERT_UPDATA_NEW = MAIN + "api/assetnew/updateassetnewbyid";

    //需求信息列表
    public final static String DEMAND_INFROMATIION = MAIN + "api/demand?";

    //需求信息列表  -----新的图片
    public final static String DEMAND_INFROMATIION_NEW = MAIN + "api/demandnew/getdemandnew?";

    //资产信息详情
    public final static String ASSERT_INFORMATION_DETAIL = MAIN + "api/asset/detail?";
    //资产列表删除
    public final static String ASSERT_DELETE = MAIN + "api/asset/delete";
    //新增资产
    public final static String ADD_ASSERTS = MAIN + "api/asset";
    //编辑资产
    public final static String EDITOR_ASSERTS = MAIN + "api/asset/update";
    //新增需求
    public final static String ADD_DEMAND = MAIN + "api/demand/adddemand";
    //删除需求
    public final static String DELETE_DEMAND = MAIN + "api/demand/delete?";
    //需求详情
    public final static String DEMAND_DETAILS = MAIN + "api/demand/detail?";
    //编辑需求
    public final static String EDITOR_DEMAND = MAIN + "api/demand/updatedemand";
    //股权信息列表
    public final static String SHARE_INFORMATION = MAIN + "api/equity/getequity?";
    //删除股权信息
    public final static String DELETE_SHARE = MAIN + "api/equity/deleteequity";
    //新增股权信息
    public final static String NEW_SHARES = MAIN + "api/equity/adddemand";
    //经营信息列表
    public final static String BUNESS_INFORMATIO = MAIN + "api/managestate/find?";
    //经营列表删除
    public final static String DELETE_BUNESS = MAIN + "api/managestate/delete";
    //新增经营
    public final static String ADD_BUNESS = MAIN + "api/managestate/add";
    //我的推荐行长
    public final static String TJHZ = MAIN + "api/my/tuijianhangzhang?";
    //我的推荐会员
    public final static String TJHY = MAIN + "api/my/tuijianhuiyuan?";
    //版本更新
    public final static String VERSION = MAIN + "api/version/getVersion?";
    //商学院视频详情
    public final static String BUNESS_VIDEO = MAIN + "back/college/app/detail_videocourse_college?";
    //商学院视频列表2
    public final static String VIDEO_LIST = MAIN + "back/college/app/get_videocourse_college?";
    //商学院
    public final static String BUNESSCHOOL = MAIN + "api/college?";
    //图文列表
    public final static String PIC_TEXT = MAIN + "api/imagetext/getImageText?";
    // 图文搜索
    public final static String PIC_TEXT_SERCH = MAIN + "api/imagetext/getImageTextSearch?";
    //视频搜素
    public final static String VIDEO_SERCH = MAIN + "back/college/app/search_videocourse_college?";
    //备案数列表
    public final static String RECORD_NUMBER = MAIN + "api/debtrelation/getrecommenddebtRelation?";
    //备案数搜索列表
    public final static String BERAN_SERCH = MAIN + "api/debtrelation/searchdebtRelation?";
    //答疑解惑列表
    public final static String DYJH_LIST = MAIN + "api/question/getquestion?";
    //答疑  根据标题得到字标题列表
    public final static String DYJH_FL_LIST = MAIN + "api/question/getsubtitle?";
    //名师风采列表
    public final static String TEACHER_FENG_CAI = MAIN + "api/teacher/getTeacher?";
    //商城接口
    public final static String SHANGC = MAIN + "api/regist/geturl";
    //活动专区
    public final static String ACTIVITY_PREFECTRUE = MAIN + "back/college/app/get_activity_college?";
    //课程预告
    public final static String COURSES_NOTICE = MAIN + "back/college/app/get_foreshow_college?";


}
