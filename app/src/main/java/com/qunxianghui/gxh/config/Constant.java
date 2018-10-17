package com.qunxianghui.gxh.config;

/**
 * Created by Administrator on 2018/3/22 0022.
 */
public class Constant {

    public static boolean MyCollectIsShow = false;
    public static final long TIME_OUT = 3000;//网络请求超时时//     public static final String BASE_URL = "http://api.qunxianghui.com/v2/";间

//    private static final String BASE_URL = "https://api.qunxianghui.com.cn/v2/";     //上线环境
    private static final String BASE_URL = "http://api.test.gongxianghui.net/v2/";     //线下问题


    /*app嵌入H5链接*/
    public static final String YouXuan = "https://www.qunxianghui.com/optimization";   //首页精选链接的跳转

    public static final String BenDiService = "https://fx.qunxianghui.com.cn/register/index.html";    //群享汇服务协议链接的跳转

    public static final String BIANMIN_HUANGLI_URL = "https://yun.rili.cn/wnl/m/huangli.html?channel=bttout";    //便民服务中的黄历

    public static final String HOME_CHECK_KUAIDI_URL = "https://m.kuaidi100.com/";    //查快递

    public static final String HOME_SEND_KUAIDI_URL = "https://m.kuaidi100.com/courier/?openid=ofaKJvxgdNh3kD14hfR47R6EMdZc";    //寄快递

    public static final String HOME_BUS_CHECK_URL = "http://m.8684.cn/";    //公交查询

    public static final String HOME_CAR_POCKET_URL = "https://m.ly.com/busn/webapp/bus/";      //    汽车票

    public static final String HOME_TRAIN_POCKET_URL = "https://m.ly.com/uniontrain/webapp/train/";      //    火车票

    public static final String HOME_AIR_POCKET_URL = "https://m.ly.com/flightnew/";  //飞机票

    public static final String HOME_HOTEL_ZHUSU_URL = "https://m.ly.com/hotel/";    //酒店住宿

    public static final String HOME_SEE_JOB_URL = "http://m.58.com/hz/job.shtml?from=benditoutiao";    //找工作



    public static final String HOME_NEWS_DETAIL_URL = "http://api.test.gongxianghui.net/theme/new_details/xinwen_/index.html#/";    //新闻详情`

    public static final String LOCAL_SERVICES_ISSUE_URL = "http://api.test.gongxianghui.net/theme/new_details/bendi_fabu/index.html#/";    //本地发布详情

    public static final String GOOD_SELECT__DETAIL_URL = "http://api.test.gongxianghui.net/theme/new_details/jingxuan_fabu/index.html#/";    //精选发布详情

    public static final String VIDEO_DETAIL_URL = "http://api.test.gongxianghui.net/theme/new_details/shipin_/index.html#/";    //视频详情

    public static final String HOME_GOOD_SELECT_URL = "http://api.test.gongxianghui.net/theme/new_details/youxuan_h5/index.html#/";    //首页上面优选的页面

    public static final String HOME_LOCAL_SERVICE_URL = "http://api.test.gongxianghui.net/theme/new_details/bendi_h5/index.html#/";  //首页上面本地服务的页面

    /*线上环境的详情页*/
//    public static final String HOME_NEWS_DETAIL_URL = "https://api.qunxianghui.com.cn/theme/new_details/xinwen_/index.html#/";    //新闻详情
//
//    public static final String LOCAL_SERVICES_ISSUE_URL = "https://api.qunxianghui.com.cn/theme/new_details/bendi_fabu/index.html#/";  //本地发布详情
//
//    public static final String GOOD_SELECT__DETAIL_URL = "https://api.qunxianghui.com.cn/theme/new_details/jingxuan_fabu/index.html#/";   //精选发布详情
//
//    public static final String VIDEO_DETAIL_URL = "https://api.qunxianghui.com.cn/theme/new_details/shipin_/index.html#/";   //视频详情
//
//    public static final String HOME_GOOD_SELECT_URL = "https://api.qunxianghui.com.cn/theme/new_details/youxuan_h5/index.html#/";  //首页上面优选的页面
//
//    public static final String HOME_LOCAL_SERVICE_URL = "https://api.qunxianghui.com.cn/theme/new_details/bendi_h5/index.html#/";

    public static final String COMMEON_QUESTION_URL = "https://api.qunxianghui.com.cn/theme/new_details/question/index.html";

    public static final String NEW_FUNCTION_URL = "https://api.qunxianghui.com.cn/theme/new_details/intro/introduce.html";    //关于我们新功能H5




    /*首页全部接口*/
    public static final String WELCOM_ADVER_URL = BASE_URL + "system/getEntry"; //开屏广告

    public static final String HOME_NEWS_LIST_URL = BASE_URL + "news/getList";    //首页新闻列表

    public static final String HOME_PULL_REFRESH_URL = BASE_URL + "news/getRecommendList";   //首页下拉刷新

    public static final String HOME_VIDEO_LIST_URL = BASE_URL + "video/getList";    //首页视频列表

    public static final String SEARCH_GET_LIST = BASE_URL + "search/getList";  //搜索结果

    public static final String SEARCH_GET_VIDEO_LIST = BASE_URL + "Video/searchVideo";    //搜索视频的结果

    public static final String SEARCH_AUTO_COMPLETE = BASE_URL + "search/autoComplete";    //获取搜索自动完成数据

    public static final String HOME_AIRLIST_URL = BASE_URL + "weather/getWeatherInfo";   //首页天气列表

    public static final String HOME_DISCLOSS_URL = BASE_URL + "info/publish";  //首页爆料

    public static final String CITY_LIST_URL = BASE_URL + "region/getList";    //获取城市列表

    public static final String SEARCH_GUESS_URL = BASE_URL + "search/getGuess";   //搜索首页显示猜的数据

    public static final String SEARCH_VIDEO_GUESS_URL = BASE_URL + "Video/getGuess"; //搜索视频汇显示猜的数据

    public static final String FETCH_COUNTRY_ADRESS = BASE_URL + "region/getAllArea";    //获取省市区

    public static final String HOME_PAGE_LUNBO_URL = BASE_URL + "ad/getList?place=首页图片轮播";    //首页轮播图

    public static final String PAST_ARTICAL_URL = BASE_URL + "user/paste";    //首页粘贴文章

    public static final String ADD_COLLECT_URL = BASE_URL + "collect/addCollect";    //添加收藏

    public static final String CANCEL_COLLECT_URL = BASE_URL + "user/unCollect";    //取消收藏

    public static final String GET_COLLECT_NEWS_URL = BASE_URL + "user/getCollect&model=news";    //获取收藏的新闻

    public static final String GET_NEWS_CONTENT_DETAIL_URL = BASE_URL + "detail/getInfo";    //获取内容详情

    public static final String GET_CITY_INFO = BASE_URL + "region/getCityInfo";//获取城市信息

    public static final String HOST_THIRD_STEPAREA_URL = BASE_URL + "region/getAllArea";    //获取三级省市区


    /*本地圈全部接口*/
    public static final String PUBLISH_ARTICLE = BASE_URL + "posts/publish";    //发布帖子

    public static final String LOCATION_NEWS_LIST_URL = BASE_URL + "posts/getList";    //获取本地圈的信息列表

    public static final String ISSURE_DISUSS_URL = BASE_URL + "comment/addComment";    //发布评论

    public static final String REPAY_COMMENT_URL = BASE_URL + "comment/replyComment";   //回复评论

    public static final String DELETE_DISCUSS_URL = BASE_URL + "comment/delComment";    //删除评论

    public static final String LIKE_URL = BASE_URL + "like/clickLike";    //喜欢

    public static final String VIDEO_LIKE_URL = BASE_URL + "Like/clickLikes";    //点赞

    public static final String ATTENTION_URL = BASE_URL + "follow/addFollow";    //关注

    public static final String UNLIKE_URL = BASE_URL + "like/unClickLike";    //不喜欢

    public static final String MYPOST_TIEZI_URL = BASE_URL + "posts/publish";    //发布帖子

    public static final String DELETE_POST_URL = BASE_URL + "posts/delete";    //删除帖子

    public static final String COMMENT_LIST = BASE_URL + "comment/getList";    //评论列表


    /*推广全部接口*/
    public static final String GENERALIZE_PAIHANG_URL = BASE_URL + "promote/getRankingV2";    //推广员工排行

    public static final String GENERALIZE_COMPANY_PUSH_URL = BASE_URL + "promote/getPushCompany";

    public static final String GENERALIZE_COMPANY_STATICS_URL = BASE_URL + "promote/getCompanyStatistics";    //推广公司统计

    public static final String GENERALIZE_PERSON_LIST_URL = BASE_URL + "promote/getList";   //我的推广列表ma

    public static final String GENERALIZE_RERSON_STATIS_URL = BASE_URL + "promote/getStatistics";    // 推广统计个人上面的头部


/*上传和频道管理接口接口*/
public static final String EDIT_VIDEO_TAB_URL = BASE_URL + "Video/editVideoCate";    //编辑视频汇分类接口

    public static final String ADD_VIDEO_TAB_URL = BASE_URL + "Video/addVideoCate";    //个人添加视频汇分类接口

    public static final String DELETE_VIDEO_TAB_URL = BASE_URL + "Video/delVideoCate";    //个人删除视频汇分类接口

    public static final String VIDEO_SUB_URL = BASE_URL + "Video/getVideoCate";    //用户订阅的视频的频道

    public static final String UPLOAD_VIDEO_ADD_SORT_URL = BASE_URL + "Video/addVideo";    //上传视频的类别

    public static final String UPLOAD_VIDEO_URL = BASE_URL + "System/uploadVideo";    //上传视频

    public static final String EDUCATION_VIDEO_URL = "http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180831/94b305ee4859ef6054b03b8b5051ae7673818a14.mp4";    //广告教学视频

    public static final String EDUCATION_VIDEO2_URL = "http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/video/20180831/94b305ee4859ef6054b03b8b5051ae7673818a1414.mp4";   //贴片教学视频

    public static final String EDUCATION_VIDEO_PIC = "http://qunxianghui-upload.oss-cn-hangzhou.aliyuncs.com/uplaod/image/20180831/f0086940e285a7229c050850674911b08060f302.png";    //视频截图

    public static final String UP_LOAD_OSS_PIC = BASE_URL + "system/oss_uploadImage";    //上传图片

    public static final String LOCAL_POST_SUB_URL = BASE_URL + "Posts/getPostsCate";    //本地圈用户订阅的

    public static final String EDIT_LOCAL_POST_SUB_URL = BASE_URL + "Posts/editPostsCate";    //个人编辑本地圈接口

    public static final String ADD_LOCAL_POST_SUB_URL = BASE_URL + "Posts/addPostsCate";    //个人添加本地圈接口

    public static final String DELETE_LOCAL_POST_SUB_URL = BASE_URL + "Posts/delPostsCate";    //个人删除本地圈接口

    public static final String UPLOAD_LOCAL_POST_SORT_SUB_URL = BASE_URL + "Posts/addPosts";    //上传本地圈的分类

    public static final String CHANNEL_GETALL = BASE_URL + "channel/getAll";//获取全部频道

    public static final String CHANNEL_GETLIST = BASE_URL + "channel/getList";    //频道列表（用户订阅的频道）

    public static final String CHANNEL_ADD_CHANNEL = BASE_URL + "channel/addChannel";    //添加频道

    public static final String CHANNEL_DELETE_CHANNEL = BASE_URL + "channel/delChannel";    //删除频道


    /*我的全部接口*/
    public static final String GET_ISSURE_POST_URL = BASE_URL + "user/myPosts";    //获取我发布帖子

    public static final String MYFANS_URL = BASE_URL + "user/myFans";    //我的粉丝

    public static final String MYFOCUS_URL = BASE_URL + "user/myFollow"; //我的关注

    public static final String DISCUSS_MINE_URL = BASE_URL + "msg/getComment";    //我的消息的评论我的

    public static final String DISCUSS_MINE_FOLLOW_URL = BASE_URL + "msg/getPosts";    //我的消息的我的跟帖

    public static final String DISCUSS_MINE_SSYSTEM_URL = BASE_URL + "msg/getList";    //我的消息的我的跟帖

    public static final String MYISSURE_GOOD_SELECT_URL = BASE_URL + "User/getMySelection";    //我的发布精选列表

    public static final String MYISSURE_LOCAL_SERVICE_URL = BASE_URL + "User/getMyService";    //我的发布的本地服务列表

    public static final String DELETE_MYISSUE_URL = BASE_URL + "user/delRelease";    //删除我的发布

    public static final String GET_ISSURE_DISCLOSS_URL = BASE_URL + "user/getMyInfo";    //获取我发布的爆料

    public static final String GET_ISSURE_VIDEO_URL = BASE_URL + "user/getRelease&model=video";    //获取我发布的视频

    public static final String GET_COLLECT_VIDEO_URL = BASE_URL + "user/getCollect&model=video";    //获取我收藏的视频

    public static final String GET_COLLECT_POST_URL = BASE_URL + "user/getCollect&model=posts";    //获取我收藏帖子

    public static final String EDIT_PERSON_DATA = BASE_URL + "user/editProfile";   //修改个人资料

    public static final String CATCH_USERDATA_URL = BASE_URL + "user/getProfile";    //获取个人资料

    public static final String GET_USER_DETAIL_URL = BASE_URL + "user/getUserInfo";    //获取用户详情

    public static final String MINE_COMPANY_CARD_URL = BASE_URL + "User/companyCard";    //我的企业名片

    public static final String SHARE_COMPANY_CARD_URL = BASE_URL + "Aboutus/showh5";    //分享我的企业名片

    public static final String GET_AD_LIST = BASE_URL + "user/getAdTemplates";    //我的植入广告

    public static final String QUESTION_POST_URL = BASE_URL + "Feedback/publish";    //意见反馈

    public static final String COOPEREATE_CALL_URL = BASE_URL + "user/CooperationPhone";    //合作热线

    public static final String CHECK_COMPANY_CENTER_ADVANCE = BASE_URL + "Aboutus/selectCore";    //查看核心优势或公司产品

    public static final String ADD_COMPANY_CENTER_ADVANCE = BASE_URL + "Aboutus/addCore";    //添加核心优势或公司产品

    public static final String DELETE_COMPANY_CENTER_ADVANCE = BASE_URL + "Aboutus/delCore";    //删除企业优势和产品

    public static final String CANCEL_ISSUE_URL = BASE_URL + "User/delRelease";    //删除我的发布

    public static final String EDIT_COMPANY_CENTER_ADVANCE = BASE_URL + "Aboutus/editCore";    //修改核心优势和产品

    public static final String CHECK_ADD = BASE_URL + "user/checkAddAd";    //广告权限

    public static final String ADD_AD = BASE_URL + "user/addAd";  //添加广告

    public static final String DELETE_AD = BASE_URL + "user/delAdTemplates";    //删除单条广告

    public static final String USED_AD = BASE_URL + "user/getAdTemplateV2";    //使用广告

    public static final String ADD_SILDE = BASE_URL + "user/setAdSlide";    //加入轮播

    public static final String GET_ADVERT = BASE_URL + "user/getAd";

    public static final String GENERAL_AD = BASE_URL + "user/getGeneralMaterial";    //通用广告

    public static final String EDIT_AD = BASE_URL + "user/editAdTemplates";    //修改广告

    public static final String ENTERPRISE_MATERIAL = BASE_URL + "user/getEnterpriseMaterial";

    public static final String ADD_COMPANY_URL = BASE_URL + "service/addCompany";    //企业添加

    public static final String GET_COMPANY_URL = BASE_URL + "service/getCompany";    //企业设置   获取

    public static final String EDIT_COMPANY_URL = BASE_URL + "service/editCompany";    //企业修改

    public static final String LOGIN_BINE_MOBILE_URL = BASE_URL + "user/bindMobile"; //第一次登录绑定手机号

    public static final String QQ_RESPONSE_URL = BASE_URL + "user/callback/qq";    //QQ登录回调

    public static final String WEIXIN_RESPONSE_URL = BASE_URL + "user/callback/weixin ";    //微信回调

    public static final String SINA_RESPONSE_URL = BASE_URL + "user/callback/weibo";    //新浪回调

    public static final String MINE_NEWMESSAGE_LIST_URL = BASE_URL + "NewMessage/getNewMessageList";    //新消息接口

    public static final String MINE_NEWMESSAGE_COUNT_URL = BASE_URL + "NewMessage/getNewsMessageCount"; //获取我的消息的数量

    public static final String MINE_LOCAL_DETAIL_URL = BASE_URL + "NewMessage/getPostsDetail"; //我的消息本地圈点击跳转详情

    public static final String REFIST_SEND_CODE_URL = BASE_URL + "captcha/send";    //注册页面发送验证码

    public static final String REGIST_URL = BASE_URL + "user/register";    //注册接口

    public static final String LOGIN_URL = BASE_URL + "user/login";    //登录接口

    public static final String LOGIN_OUT_URL = BASE_URL + "user/logout";    //用户登出

    public static final String SEEK_PASSWORD_URL = BASE_URL + "user/findPassword";    //找回密码

    public static final String ADD_REPORT_URL = BASE_URL + "report/reportAdd";    //添加举报信息

    public static final String GET_DISCLOSS_INFO_URL = BASE_URL + "user/getMyInfo";    //获取我的爆料信息

    public static final String GET_SHARE_INFO = BASE_URL + "user/shareInfoV2";    //获取分享信息

    public static final String PERSON_UPGRADE_URL = BASE_URL + "user/upgrade";    //用户升级
}
