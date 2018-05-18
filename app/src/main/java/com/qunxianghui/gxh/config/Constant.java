package com.qunxianghui.gxh.config;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class Constant {
    public static final long TIME_OUT = 3000;//网络请求超时时间
    public static final String URL = "http://112.124.22.238:8081/course_api/wares/hot?pageSize=8&curPage=1";
    public static final String SERVE_URL = "http://www.zzbcar.com/";
    /*主页*/
    public static final String API_MAIN_PAGE = SERVE_URL + "zzb/app/compound/mainpage";
    public static final String API_CAR_DETAIL = SERVE_URL + "zzb/app/car/queryCar";
    /*车辆品牌*/
    public static final String API_CAR_BRAND = SERVE_URL + "zzb/app/car/brand";

    /*车系*/
    public static final String API_CAR_SERIES = SERVE_URL + "zzb/app/car/series";
    /*获取热搜内容*/
    public static final String API_FIRE_SEARCH = SERVE_URL + "zzb/app/car/searchfreq";

    /*车辆筛选*/
    public static final String API_CAR_QUERY = SERVE_URL + "zzb/app/car/queryCarByCondition";

    /*获取短信验证码*/
    public static final String API_GET_CODE = SERVE_URL + "zzb/app/common/smscode";
    //平台规则
    public static final String PTGZ = "http://app.zzbcar.com/zzb/helpcenter/protocol.html";
    //加载的网页链接
    //常用电话
    public static final String COMMON_PHONE = "http://app.zzbcar.com/zzb/helpcenter/protocol.html";
    //查快递
    public static final String CHECK_EXPRESS = "http://app.zzbcar.com/zzb/helpcenter/protocol.html";
    //寄快递
    public static final String SEND_EXPRESS = "http://app.zzbcar.com/zzb/helpcenter/user1.html";
    //充话费
    public static final String RUSH_PHONE_MONEY = "http://app.zzbcar.com/zzb/helpcenter/user2.html";
    //公交查询
    public static final String BUSS_CHECK = "http://app.zzbcar.com/zzb/helpcenter/user3.html";
    //汽车票
    public static final String CAR_TCKET = "http://app.zzbcar.com/zzb/helpcenter/user4.html";
    //火车票
    public static final String TRAIN_TICKET = "http://app.zzbcar.com/zzb/helpcenter/user5.html";
    //飞机票
    public static final String AIR_TICKET = "http://app.zzbcar.com/zzb/helpcenter/user6.html";
    //农历黄历
    public static final String ALMANAC = "http://app.zzbcar.com/zzb/helpcenter/user7.html";
    //酒店住宿
    public static final String HPTEL_ZHUSU = "http://app.zzbcar.com/zzb/helpcenter/user8.html";

    //首页链接的跳转
    public static final String YouXuan = "http://www.qunxianghui.com.cn/optimization";
    public static final String BenDiService = "http://www.qunxianghui.com.cn/localservice/#/";


    // 真正的开发   先前的先忽略
    public static final String BASE_URL = "http://api.qunxianghui.com.cn/v1/";
    //首页新闻列表
    public static final String HOME_NEWS_LIST_URL = BASE_URL + "news/getList";
    //首页视频列表
    public static final String HOME_VIDEO_LIST_URL = BASE_URL + "video/getList";
    //获取全部频道
    public static final String CHANNEL_GETALL = BASE_URL + "channel/getAll";
    //频道列表（用户订阅的频道）
    public static final String CHANNEL_GETLIST = BASE_URL + "channel/getList";
    //添加频道
    public static final String CHANNEL_ADD_CHANNEL = BASE_URL + "channel/addChannel";
    //添加频道
    public static final String CHANNEL_DELETE_CHANNEL = BASE_URL + "channel/delChannel";

    //注册页面发送验证码
    public static final String REFIST_SEND_CODE_URL=BASE_URL+"captcha/send";
    //注册接口
    public static final String REGIST_URL=BASE_URL+"user/register";
    //登录接口
    public static final String LOGIN_URL=BASE_URL+"user/login";



}
