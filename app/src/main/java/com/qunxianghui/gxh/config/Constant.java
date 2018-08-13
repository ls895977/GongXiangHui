package com.qunxianghui.gxh.config;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class Constant {
    public static final long TIME_OUT = 3000;//网络请求超时时间

    //首页精选链接的跳转
    public static final String YouXuan = "http://www.qunxianghui.com/optimization";
    //首页本地服务链接的跳转
    public static final String BenDiService = "http://www.qunxianghui.com/localservice";
    //正式环境
//  public static final String BASE_URL = "http://api.qunxianghui.com.cn/v1/";
//本地环境
    public static final String BASE_URL = "http://api.qunxianghui.com/v2/";
    //测试环境http://47.104.170.168:80/v2/user/login
//    public static final String BASE_URL = "http://47.104.170.168:80/v2/";
    //欢迎页广告
    public static final String WELCOM_ADVER_URL = BASE_URL + "system/getEntry";
    //首页新闻列表
    public static final String HOME_NEWS_LIST_URL = BASE_URL + "news/getList";
    //首页下拉刷新
    public static final String HOME_PULL_REFRESH_URL = BASE_URL + "news/getRecommendList";
    //首页视频列表
    public static final String HOME_VIDEO_LIST_URL = BASE_URL + "video/getList";
    //获取省份
    public static final String FETCH_PROVINCE_URL = BASE_URL + "province/getProvince";
    //获取城市
    public static final String FETCH_CITY_URL = BASE_URL + "city/getCity";
    //获取县城
    public static final String FETCH_COUNTRY_URL = BASE_URL + "area/getArea";
    //获取省市区
    public static final String FETCH_COUNTRY_ADRESS = BASE_URL + "region/getAllArea";
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
    //用户登出
    public static final String LOGIN_OUT_URL = BASE_URL + "user/logout";
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
    //搜索首页显示猜的数据
    public static final String SEARCH_GUESS_URL = BASE_URL + "search/getGuess";
    //搜索视频汇显示猜的数据
    public static final String SEARCH_VIDEO_GUESS_URL = BASE_URL + "Video/getGuess";
    //获取个人资料
    public static final String CATCH_USERDATA_URL = BASE_URL + "user/getProfile";
    //获取用户详情
    public static final String GET_USER_DETAIL_URL = BASE_URL + "user/getUserInfo";
    //添加收藏
    public static final String ADD_COLLECT_URL = BASE_URL + "collect/addCollect";
    //取消收藏
    public static final String CANCEL_COLLECT_URL = BASE_URL + "user/unCollect";
    //获取我发布帖子
    public static final String GET_ISSURE_POST_URL = BASE_URL + "user/myPosts";
    //我的粉丝
    public static final String MYFANS_URL = BASE_URL + "user/myFans";
    //我的关注
    public static final String MYFOCUS_URL = BASE_URL + "user/myFollow";
    //获取收藏的新闻
    public static final String GET_COLLECT_NEWS_URL = BASE_URL + "user/getCollect&model=news";
    //获取内容详情
    public static final String GET_NEWS_CONTENT_DETAIL_URL = BASE_URL + "detail/getInfo";
    //获取我收藏的视频
    public static final String GET_COLLECT_VIDEO_URL = BASE_URL + "user/getCollect&model=video";
    //获取我收藏帖子
    public static final String GET_COLLECT_POST_URL = BASE_URL + "user/getCollect&model=posts";
    //获取本地圈的信息列表
    public static final String LOCATION_NEWS_LIST_URL = BASE_URL + "posts/getList";
    //发布评论
    public static final String ISSURE_DISUSS_URL = BASE_URL + "comment/addComment";
    //回复评论
    public static final String REPAY_COMMENT_URL = BASE_URL + "comment/replyComment";
    //删除评论
    public static final String DELETE_DISCUSS_URL = BASE_URL + "comment/delComment";
    //删除帖子
    public static final String DELETE_POST_URL = BASE_URL + "posts/delete";
    //评论列表
    public static final String COMMENT_LIST = BASE_URL + "comment/getList";
    // 推广统计个人上面的头部
    public static final String GENERALIZE_RERSON_STATIS_URL = BASE_URL + "promote/getStatistics";
    //喜欢
    public static final String LIKE_URL = BASE_URL + "like/clickLike";
    //关注
    public static final String ATTENTION_URL = BASE_URL + "follow/addFollow";
    //不喜欢
    public static final String UNLIKE_URL = BASE_URL + "like/unClickLike";
    //发布帖子
    public static final String MYPOST_TIEZI_URL = BASE_URL + "posts/publish";
    //获取三级省市区
    public static final String HOST_THIRD_STEPAREA_URL = BASE_URL + "region/getAllArea";
    //添加举报信息
    public static final String ADD_REPORT_URL = BASE_URL + "report/reportAdd";
    //搜索结果
    public static final String SEARCH_GET_LIST = BASE_URL + "search/getList";
    //搜索视频的结果
    public static final String SEARCH_GET_VIDEO_LIST = BASE_URL + "Video/searchVideo";
    //获取搜索自动完成数据
    public static final String SEARCH_AUTO_COMPLETE = BASE_URL + "search/autoComplete";
    //获取我的爆料信息
    public static final String GET_DISCLOSS_INFO_URL = BASE_URL + "user/getMyInfo";
    //获取分享信息
    public static final String GET_SHARE_INFO = BASE_URL + "user/getShareInfo";
    //删除我的发布
    public static final String DELETE_MYISSUE_URL = BASE_URL + "user/delRelease";
    //获取我发布的爆料
    public static final String GET_ISSURE_DISCLOSS_URL = BASE_URL + "user/getMyInfo";
    //获取我发布的视频
    public static final String GET_ISSURE_VIDEO_URL = BASE_URL + "user/getRelease&model=video";
    //用户升级
    public static final String PERSON_UPGRADE_URL = BASE_URL + "user/upgrade";
    //上传图片
    public static final String UP_LOAD_PIC = BASE_URL + "system/uploadImage";
    public static final String UP_LOAD_OSS_PIC = BASE_URL + "system/oss_uploadImage";

    //本地圈用户订阅的
    public static final String LOCAL_POST_SUB_URL = BASE_URL + "Posts/getPostsCate";
    //个人编辑本地圈接口
    public static final String EDIT_LOCAL_POST_SUB_URL = BASE_URL + "Posts/editPostsCate";
    //个人添加本地圈接口
    public static final String ADD_LOCAL_POST_SUB_URL = BASE_URL + "Posts/addPostsCate";
    //个人删除本地圈接口
    public static final String DELETE_LOCAL_POST_SUB_URL = BASE_URL + "Posts/delPostsCate";
    //上传本地圈的分类
    public static final String UPLOAD_LOCAL_POST_SORT_SUB_URL = BASE_URL + "Posts/addPosts";
    //上传视频
    public static final String UPLOAD_VIDEO_URL = BASE_URL + "System/uploadVideo";
    //编辑视频汇分类接口
    public static final String EDIT_VIDEO_TAB_URL = BASE_URL + "Video/editVideoCate";
    //个人添加视频汇分类接口
    public static final String ADD_VIDEO_TAB_URL = BASE_URL + "Video/addVideoCate";
    //个人删除视频汇分类接口
    public static final String DELETE_VIDEO_TAB_URL = BASE_URL + "Video/delVideoCate";

    //用户订阅的视频的频道
    public static final String VIDEO_SUB_URL = BASE_URL + "Video/getVideoCate";
    //上传视频的类别
    public static final String UPLOAD_VIDEO_ADD_SORT_URL = BASE_URL + "Video/addVideo";
    //发布帖子
    public static final String PUBLISH_ARTICLE = BASE_URL + "posts/publish";
    //推广员工排行
    public static final String GENERALIZE_PAIHANG_URL = BASE_URL + "promote/getRanking";
    public static final String GENERALIZE_COMPANY_PUSH_URL = BASE_URL + "promote/getPushCompany";
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
    //我的发布精选列表
    public static final String MYISSURE_GOOD_SELECT_URL = BASE_URL + "User/getMySelection";
    //我的发布的本地服务列表
    public static final String MYISSURE_LOCAL_SERVICE_URL = BASE_URL + "User/getMySelection";
    //我的企业名片
    public static final String MINE_COMPANY_CARD_URL = BASE_URL + "User/companyCard";
    //分享我的企业名片
    public static final String SHARE_COMPANY_CARD_URL = BASE_URL + "Aboutus/showh5";
    //我的植入广告
    public static final String GET_AD_LIST = BASE_URL + "user/getAdList";
    //查看核心优势或公司产品
    public static final String CHECK_COMPANY_CENTER_ADVANCE = BASE_URL + "Aboutus/selectCore";
    //添加核心优势或公司产品
    public static final String ADD_COMPANY_CENTER_ADVANCE = BASE_URL + "Aboutus/addCore";
    //删除企业优势和产品
    public static final String DELETE_COMPANY_CENTER_ADVANCE = BASE_URL + "Aboutus/delCore";
    //修改核心优势和产品
    public static final String EDIT_COMPANY_CENTER_ADVANCE = BASE_URL + "Aboutus/editCore";
    //广告权限
    public static final String CHECK_ADD = BASE_URL + "user/checkAddAd";
    //添加广告
    public static final String ADD_AD = BASE_URL + "user/addAd";
    //删除单条广告
    public static final String DELETE_AD = BASE_URL + "user/delAd";
    //使用广告
    public static final String USED_AD = BASE_URL + "user/getAdTemplateV2";
    //加入轮播
    public static final String ADD_SILDE = BASE_URL + "user/setAdSlide";
    //修改某条广告
    public static final String EDIT_AD = BASE_URL + "user/editAd";

    public static final String ENTERPRISE_MATERIAL = BASE_URL + "user/getEnterpriseMaterial";
    //企业添加
    public static final String ADD_COMPANY_URL = BASE_URL + "service/addCompany";
    //企业设置   获取
    public static final String GET_COMPANY_URL = BASE_URL + "service/getCompany";
    //企业修改
    public static final String EDIT_COMPANY_URL = BASE_URL + "service/editCompany";
    //第一次登录绑定手机号
    public static final String LOGIN_BINE_MOBILE_URL = BASE_URL + "user/bindMobile";
    //QQ登录回调
    public static final String QQ_RESPONSE_URL = BASE_URL + "user/callback/qq";

    public static final String WEIXIN_RESPONSE_URL = BASE_URL + "user/callback/weixin ";
    public static final String SINA_RESPONSE_URL = BASE_URL + "user/callback/sina";
}
