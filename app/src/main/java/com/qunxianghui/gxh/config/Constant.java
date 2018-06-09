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
    //欢迎页广告
    public static final String WELCOM_ADVER_URL = BASE_URL + "system/getEntry";
    //首页新闻列表
    public static final String HOME_NEWS_LIST_URL = BASE_URL + "news/getList";
    //首页视频列表
    public static final String HOME_VIDEO_LIST_URL = BASE_URL + "video/getList";
    //首页轮播图
    public static final String HOME_PAGE_LUNBO_URL = BASE_URL + "ad/getList?place=首页图片轮播";
    //首页爆料
    public static final String HOME_DISCLOSE_URL = BASE_URL + "iv_person_data_pic";
    //获取全部频道
    public static final String CHANNEL_GETALL = BASE_URL + "channel/getAll";
    //频道列表（用户订阅的频道）
    public static final String CHANNEL_GETLIST = BASE_URL + "channel/getList";
    //添加频道
    public static final String CHANNEL_ADD_CHANNEL = BASE_URL + "channel/addChannel";
    //删除频道
    public static final String CHANNEL_DELETE_CHANNEL = BASE_URL + "channel/delChannel";
    //注册页面发送验证码
    public static final String REFIST_SEND_CODE_URL = BASE_URL + "captcha/send";
    //注册接口
    public static final String REGIST_URL = BASE_URL + "user/register";
    //登录接口
    public static final String LOGIN_URL = BASE_URL + "user/login";
    //找回密码
    public static final String SEEK_PASSWORD_URL = BASE_URL + "user/findPassword";
    //首页天气列表
    public static final String HOME_AIRLIST_URL = BASE_URL + "weather/getWeatherInfo";
    //首页爆料
    public static final String HOME_DISCLOSS_URL = BASE_URL + "info/publish";
    //获取城市列表
    public static final String CITY_LIST_URL = BASE_URL + "region/getList";
    //修改个人资料
    public static final String EDIT_PERSON_DATA = BASE_URL + "user/editProfile";
    //搜索显示猜的数据
    public static final String SEARCH_GUESS_URL = BASE_URL + "search/getGuess";
    //获取个人资料
    public static final String CATCH_USERDATA_URL = BASE_URL + "user/getProfile";
    //添加收藏
    public static final String ADD_COLLECT_URL = BASE_URL + "collect/addCollect";
    //我的粉丝
    public static final String MYFANS_URL = BASE_URL + "user/myFans";
    //我的关注
    public static final String MYFOCUS_URL = BASE_URL + "user/myFollow";
    //获取收藏的新闻
    public static final String GET_COLLECT_NEWS_URL = BASE_URL + "user/getCollect&model=news";
    //获取我收藏的视频
    public static final String GET_COLLECT_VIDEO_URL = BASE_URL + "user/getCollect&model=video";
    //获取我收藏帖子
    public static final String GET_COLLECT_POST_URL = BASE_URL + "user/getCollect&model=posts";
    //获取本地圈的信息列表
    public static final String LOCATION_NEWS_LIST_URL = BASE_URL + "posts/getList";
    //发布评论
    public static final String ISSURE_DISUSS_URL = BASE_URL + "comment/addComment";
    //删除评论
    public static final String DELETE_DISCUSS_URL = BASE_URL + "comment/delComment";
    //评论列表
    public static final String COMMENT_LIST = BASE_URL + "comment/getList";
    // 推广统计个人上面的头部
    public static final String GENERALIZE_RERSON_STATIS_URL = BASE_URL + "promote/getStatistics";
    //喜欢
    public static final String LIKE_URL = BASE_URL + "like/clickLike";
    //不喜欢
    public static final String UNLIKE_URL = BASE_URL + "like/unClickLike";
    //发布帖子
    public static final String MYPOST_TIEZI_URL = BASE_URL + "posts/publish";
    //搜索结果
    public static final String SEARCH_GET_LIST = BASE_URL + "search/getList";
    //获取搜索自动完成数据
    public static final String SEARCH_AUTO_COMPLETE = BASE_URL + "search/autoComplete";
    //获取我的爆料信息
    public static final String GET_DISCLOSS_INFO_URL = BASE_URL + "user/getMyInfo";
    //获取我发布帖子
    public static final String GET_ISSURE_POST_URL = BASE_URL + "user/myPosts";
    //获取我发布的爆料
    public static final String GET_ISSURE_DISCLOSS_URL = BASE_URL + "user/getMyInfo";

    //获取我发布的视频
    public static final String GET_ISSURE_VIDEO_URL = BASE_URL + "user/getRelease&model=video";
    //用户升级
    public static final String PERSON_UPGRADE_URL = BASE_URL + "user/upgrade";
    //上传图片
    public static final String UP_LOAD_PIC = BASE_URL + "system/uploadImage";
    //发布帖子
    public static final String PUBLISH_ARTICLE = BASE_URL + "posts/publish";
    //推广员工排行
    public static final String GENERALIZE_PAIHANG_URL = BASE_URL + "promote/getRanking";
    //推广公司统计
    public static final String GENERALIZE_COMPANY_STATICS_URL = BASE_URL + "promote/getCompanyStatistics";

    //我的推广列表
    public static final String GENERALIZE_PERSON_LIST_URL = BASE_URL + "promote/getList";

    //我的消息的评论我的
    public static final String DISCUSS_MINE_URL = BASE_URL + "msg/getComment";
    //我的消息的我的跟帖
    public static final String DISCUSS_MINE_FOLLOW_URL = BASE_URL + "msg/getPosts";
    //我的消息的我的跟帖
    public static final String DISCUSS_MINE_SSYSTEM_URL = BASE_URL + "msg/getList";

}
