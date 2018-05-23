package com.qunxianghui.gxh.bean.home;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author 小强
 * @time 2018/5/21  14:37
 * @desc
 */
public class GetCityBean implements MultiItemEntity {

    /**
     * code : 0
     * message :
     * data : {"A":[{"id":299,"city":"阿坝","pinyin":"aba","areas":[{"areaid":11320,"areaname":"马尔康","cityid":299,"displayorder":1},{"areaid":11321,"areaname":"汶川","cityid":299,"displayorder":2},
     * {"areaid":11322,"areaname":"理县","cityid":299,"displayorder":3},{"areaid":11323,"areaname":"茂县","cityid":299,"displayorder":4},{"areaid":11324,"areaname":"松潘","cityid":299,"displayorder":5},
     * {"areaid":11325,"areaname":"九寨沟","cityid":299,"displayorder":6},{"areaid":11326,"areaname":"金川","cityid":299,"displayorder":7},{"areaid":11327,"areaname":"小金","cityid":299,"displayorder":8},
     * {"areaid":11328,"areaname":"黑水","cityid":299,"displayorder":9},{"areaid":11329,"areaname":"壤塘","cityid":299,"displayorder":10},{"areaid":11330,"areaname":"阿坝县","cityid":299,
     * "displayorder":11},{"areaid":11331,"areaname":"若尔盖","cityid":299,"displayorder":12},{"areaid":11332,"areaname":"红原","cityid":299,"displayorder":13}]},{"id":314,"city":"阿克苏","pinyin":"akesu",
     * "areas":[{"areaid":11470,"areaname":"阿克苏市","cityid":314,"displayorder":1},{"areaid":11471,"areaname":"温宿","cityid":314,"displayorder":2},{"areaid":11472,"areaname":"库车","cityid":314,
     * "displayorder":3},{"areaid":11473,"areaname":"沙雅","cityid":314,"displayorder":4},{"areaid":11474,"areaname":"新和","cityid":314,"displayorder":5},{"areaid":11475,"areaname":"拜城","cityid":314,
     * "displayorder":6},{"areaid":11476,"areaname":"乌什","cityid":314,"displayorder":7},{"areaid":11477,"areaname":"阿瓦提","cityid":314,"displayorder":8},{"areaid":11478,"areaname":"柯坪","cityid":314,
     * "displayorder":9}]},{"id":230,"city":"阿拉善","pinyin":"alashan","areas":[{"areaid":10715,"areaname":"阿拉善左","cityid":230,"displayorder":1},{"areaid":10716,"areaname":"阿拉善右","cityid":230,
     * "displayorder":2},{"areaid":10717,"areaname":"额济纳","cityid":230,"displayorder":3}]},{"id":321,"city":"阿勒泰","pinyin":"aletai","areas":[{"areaid":11528,"areaname":"阿勒泰市","cityid":321,
     * "displayorder":1},{"areaid":11529,"areaname":"布尔津","cityid":321,"displayorder":2},{"areaid":11530,"areaname":"富蕴","cityid":321,"displayorder":3},{"areaid":11531,"areaname":"福海","cityid":321,
     * "displayorder":4},{"areaid":11532,"areaname":"哈巴河","cityid":321,"displayorder":5},{"areaid":11533,"areaname":"青河","cityid":321,"displayorder":6},{"areaid":11534,"areaname":"吉木乃",
     * "cityid":321,"displayorder":7}]},{"id":307,"city":"阿里","pinyin":"ali","areas":[{"areaid":11428,"areaname":"噶尔","cityid":307,"displayorder":1},{"areaid":11429,"areaname":"普兰","cityid":307,
     * "displayorder":2},{"areaid":11430,"areaname":"札达","cityid":307,"displayorder":3},{"areaid":11431,"areaname":"日土","cityid":307,"displayorder":4},{"areaid":11432,"areaname":"革吉","cityid":307,
     * "displayorder":5},{"areaid":11433,"areaname":"改则","cityid":307,"displayorder":6},{"areaid":11434,"areaname":"措勤","cityid":307,"displayorder":7}]},{"id":279,"city":"安康","pinyin":"ankang",
     * "areas":[{"areaid":11162,"areaname":"汉滨","cityid":279,"displayorder":1},{"areaid":11163,"areaname":"汉阴","cityid":279,"displayorder":2},{"areaid":11164,"areaname":"石泉","cityid":279,
     * "displayorder":3},{"areaid":11165,"areaname":"宁陕","cityid":279,"displayorder":4},{"areaid":11166,"areaname":"紫阳","cityid":279,"displayorder":5},{"areaid":11167,"areaname":"岚皋","cityid":279,
     * "displayorder":6},{"areaid":11168,"areaname":"平利","cityid":279,"displayorder":7},{"areaid":11169,"areaname":"镇坪","cityid":279,"displayorder":8},{"areaid":11170,"areaname":"旬阳","cityid":279,
     * "displayorder":9},{"areaid":11171,"areaname":"白河","cityid":279,"displayorder":10}]},{"id":45,"city":"安庆","pinyin":"anqing","areas":[{"areaid":428,"areaname":"大观","cityid":45,
     * "displayorder":1},{"areaid":429,"areaname":"迎江","cityid":45,"displayorder":2},{"areaid":430,"areaname":"宜秀","cityid":45,"displayorder":3},{"areaid":431,"areaname":"桐城","cityid":45,
     * "displayorder":4},{"areaid":432,"areaname":"枞阳","cityid":45,"displayorder":5},{"areaid":433,"areaname":"怀宁","cityid":45,"displayorder":6},{"areaid":434,"areaname":"潜山","cityid":45,
     * "displayorder":7},{"areaid":435,"areaname":"宿松","cityid":45,"displayorder":8},{"areaid":436,"areaname":"岳西","cityid":45,"displayorder":9},{"areaid":437,"areaname":"其他","cityid":45,
     * "displayorder":10}]},{"id":207,"city":"鞍山","pinyin":"anshan","areas":[{"areaid":10520,"areaname":"铁东","cityid":207,"displayorder":1},{"areaid":10521,"areaname":"铁西","cityid":207,
     * "displayorder":2},{"areaid":10522,"areaname":"立山","cityid":207,"displayorder":3},{"areaid":10523,"areaname":"海城","cityid":207,"displayorder":4},{"areaid":10524,"areaname":"千山","cityid":207,
     * "displayorder":5},{"areaid":10525,"areaname":"岫岩","cityid":207,"displayorder":6},{"areaid":10526,"areaname":"台安","cityid":207,"displayorder":7},{"areaid":10527,"areaname":"鞍山周边",
     * "cityid":207,"displayorder":8}]},{"id":95,"city":"安顺","pinyin":"anshun","areas":[{"areaid":818,"areaname":"西秀","cityid":95,"displayorder":1},{"areaid":819,"areaname":"平坝","cityid":95,
     * "displayorder":2},{"areaid":820,"areaname":"普定县","cityid":95,"displayorder":3},{"areaid":821,"areaname":"关岭","cityid":95,"displayorder":4},{"areaid":822,"areaname":"紫云","cityid":95,
     * "displayorder":5},{"areaid":823,"areaname":"镇宁","cityid":95,"displayorder":6},{"areaid":824,"areaname":"安顺周边","cityid":95,"displayorder":7}]},{"id":122,"city":"安阳","pinyin":"anyang",
     * "areas":[{"areaid":1097,"areaname":"文峰","cityid":122,"displayorder":1},{"areaid":1098,"areaname":"北关","cityid":122,"displayorder":2},{"areaid":1099,"areaname":"殷都","cityid":122,
     * "displayorder":3},{"areaid":1100,"areaname":"龙安","cityid":122,"displayorder":4},{"areaid":1101,"areaname":"安阳","cityid":122,"displayorder":5},{"areaid":1102,"areaname":"林州","cityid":122,
     * "displayorder":6},{"areaid":1103,"areaname":"其他","cityid":122,"displayorder":7}]}],"B":[{"id":179,"city":"白城","pinyin":"baicheng","areas":[{"areaid":10262,"areaname":"白城","cityid":179,
     * "displayorder":1},{"areaid":10263,"areaname":"洮北","cityid":179,"displayorder":2},{"areaid":10264,"areaname":"洮南","cityid":179,"displayorder":3},{"areaid":10265,"areaname":"大安","cityid":179,
     * "displayorder":4},{"areaid":10266,"areaname":"镇赉","cityid":179,"displayorder":5},{"areaid":10267,"areaname":"通榆","cityid":179,"displayorder":6},{"areaid":10268,"areaname":"白城周边",
     * "cityid":179,"displayorder":7}]},{"id":87,"city":"百色","pinyin":"baise","areas":[{"areaid":757,"areaname":"右江","cityid":87,"displayorder":1},{"areaid":758,"areaname":"隆林","cityid":87,
     * "displayorder":2},{"areaid":759,"areaname":"田阳","cityid":87,"displayorder":3},{"areaid":760,"areaname":"田东","cityid":87,"displayorder":4},{"areaid":761,"areaname":"平果","cityid":87,
     * "displayorder":5},{"areaid":762,"areaname":"德保","cityid":87,"displayorder":6},{"areaid":763,"areaname":"百色周边","cityid":87,"displayorder":7}]},{"id":177,"city":"白山","pinyin":"baishan",
     * "areas":[{"areaid":10248,"areaname":"八道江","cityid":177,"displayorder":1},{"areaid":10249,"areaname":"江源","cityid":177,"displayorder":2},{"areaid":10250,"areaname":"临江","cityid":177,
     * "displayorder":3},{"areaid":10251,"areaname":"抚松","cityid":177,"displayorder":4},{"areaid":10252,"areaname":"靖宇","cityid":177,"displayorder":5},{"areaid":10253,"areaname":"长白","cityid":177,
     * "displayorder":6},{"areaid":10254,"areaname":"白山周边","cityid":177,"displayorder":7}]},{"id":67,"city":"白银","pinyin":"baiyin","areas":[{"areaid":606,"areaname":"白银","cityid":67,
     * "displayorder":1},{"areaid":607,"areaname":"平川","cityid":67,"displayorder":2},{"areaid":608,"areaname":"靖远","cityid":67,"displayorder":3},{"areaid":609,"areaname":"会宁","cityid":67,
     * "displayorder":4},{"areaid":610,"areaname":"景泰","cityid":67,"displayorder":5},{"areaid":611,"areaname":"白银周边","cityid":67,"displayorder":6}]},{"id":40,"city":"蚌埠","pinyin":"bangbu",
     * "areas":[{"areaid":397,"areaname":"蚌山","cityid":40,"displayorder":1},{"areaid":398,"areaname":"龙子湖","cityid":40,"displayorder":2},{"areaid":399,"areaname":"禹会","cityid":40,"displayorder":3},
     * {"areaid":400,"areaname":"淮上","cityid":40,"displayorder":4},{"areaid":401,"areaname":"怀远","cityid":40,"displayorder":5},{"areaid":402,"areaname":"固镇","cityid":40,"displayorder":6},
     * {"areaid":403,"areaname":"五河","cityid":40,"displayorder":7},{"areaid":404,"areaname":"其他","cityid":40,"displayorder":8}]},{"id":108,"city":"保定","pinyin":"baoding","areas":[{"areaid":950,
     * "areaname":"高开","cityid":108,"displayorder":1},{"areaid":951,"areaname":"新市","cityid":108,"displayorder":2},{"areaid":952,"areaname":"北市","cityid":108,"displayorder":3},{"areaid":953,
     * "areaname":"南市","cityid":108,"displayorder":4},{"areaid":954,"areaname":"安国","cityid":108,"displayorder":5},{"areaid":955,"areaname":"涿州","cityid":108,"displayorder":6},{"areaid":956,
     * "areaname":"高碑店","cityid":108,"displayorder":7},{"areaid":957,"areaname":"定州","cityid":108,"displayorder":8},{"areaid":958,"areaname":"白沟","cityid":108,"displayorder":9},{"areaid":959,
     * "areaname":"保定周边","cityid":108,"displayorder":10}]},{"id":273,"city":"宝鸡","pinyin":"baoji","areas":[{"areaid":11083,"areaname":"渭滨","cityid":273,"displayorder":1},{"areaid":11084,
     * "areaname":"金台","cityid":273,"displayorder":2},{"areaid":11085,"areaname":"陈仓","cityid":273,"displayorder":3},{"areaid":11086,"areaname":"凤翔","cityid":273,"displayorder":4},{"areaid":11087,
     * "areaname":"岐山","cityid":273,"displayorder":5},{"areaid":11088,"areaname":"扶风","cityid":273,"displayorder":6},{"areaid":11089,"areaname":"眉县","cityid":273,"displayorder":7},{"areaid":11090,
     * "areaname":"陇县","cityid":273,"displayorder":8},{"areaid":11091,"areaname":"千阳","cityid":273,"displayorder":9},{"areaid":11092,"areaname":"麟游","cityid":273,"displayorder":10},{"areaid":11093,
     * "areaname":"凤县","cityid":273,"displayorder":11},{"areaid":11094,"areaname":"太白","cityid":273,"displayorder":12}]},{"id":326,"city":"保山","pinyin":"baoshan","areas":[{"areaid":11574,
     * "areaname":"隆阳","cityid":326,"displayorder":1},{"areaid":11575,"areaname":"施甸","cityid":326,"displayorder":2},{"areaid":11576,"areaname":"腾冲","cityid":326,"displayorder":3},{"areaid":11577,
     * "areaname":"龙陵","cityid":326,"displayorder":4},{"areaid":11578,"areaname":"昌宁","cityid":326,"displayorder":5}]},{"id":220,"city":"包头","pinyin":"baotou","areas":[{"areaid":10627,
     * "areaname":"昆都仑区","cityid":220,"displayorder":1},{"areaid":10628,"areaname":"青山","cityid":220,"displayorder":2},{"areaid":10629,"areaname":"东河","cityid":220,"displayorder":3},
     * {"areaid":10630,"areaname":"达尔罕茂明安联合旗","cityid":220,"displayorder":4},{"areaid":10631,"areaname":"九原","cityid":220,"displayorder":5},{"areaid":10632,"areaname":"白云矿区","cityid":220,
     * "displayorder":6},{"areaid":10633,"areaname":"石拐","cityid":220,"displayorder":7},{"areaid":10634,"areaname":"固阳","cityid":220,"displayorder":8},{"areaid":10635,"areaname":"土默特右旗",
     * "cityid":220,"displayorder":9},{"areaid":10636,"areaname":"滨河新区","cityid":220,"displayorder":10},{"areaid":10637,"areaname":"稀土高新区","cityid":220,"displayorder":11},{"areaid":10638,
     * "areaname":"包头周边","cityid":220,"displayorder":12}]},{"id":226,"city":"巴彦淖尔","pinyin":"bayannaoer","areas":[{"areaid":10680,"areaname":"临河","cityid":226,"displayorder":1},{"areaid":10681,
     * "areaname":"五原","cityid":226,"displayorder":2},{"areaid":10682,"areaname":"磴口","cityid":226,"displayorder":3},{"areaid":10683,"areaname":"乌拉特前旗","cityid":226,"displayorder":4},
     * {"areaid":10684,"areaname":"乌拉特中旗","cityid":226,"displayorder":5},{"areaid":10685,"areaname":"乌拉特后旗","cityid":226,"displayorder":6},{"areaid":10686,"areaname":"杭锦后旗","cityid":226,
     * "displayorder":7},{"areaid":10687,"areaname":"巴彦周边","cityid":226,"displayorder":8}]},{"id":316,"city":"巴音郭楞","pinyin":"bayinguoleng","areas":[{"areaid":11491,"areaname":"库尔勒","cityid":316,
     * "displayorder":1},{"areaid":11492,"areaname":"轮台","cityid":316,"displayorder":2},{"areaid":11493,"areaname":"尉犁","cityid":316,"displayorder":3},{"areaid":11494,"areaname":"若羌","cityid":316,
     * "displayorder":4},{"areaid":11495,"areaname":"且末","cityid":316,"displayorder":5},{"areaid":11496,"areaname":"焉耆","cityid":316,"displayorder":6},{"areaid":11497,"areaname":"和静","cityid":316,
     * "displayorder":7},{"areaid":11498,"areaname":"和硕","cityid":316,"displayorder":8},{"areaid":11499,"areaname":"博湖","cityid":316,"displayorder":9}]},{"id":294,"city":"巴中","pinyin":"bazhong",
     * "areas":[{"areaid":11291,"areaname":"巴州","cityid":294,"displayorder":1},{"areaid":11292,"areaname":"通江","cityid":294,"displayorder":2},{"areaid":11293,"areaname":"南江","cityid":294,
     * "displayorder":3},{"areaid":11294,"areaname":"平昌","cityid":294,"displayorder":4}]},{"id":84,"city":"北海","pinyin":"beihai","areas":[{"areaid":739,"areaname":"海城","cityid":84,
     * "displayorder":1},{"areaid":740,"areaname":"银海","cityid":84,"displayorder":2},{"areaid":741,"areaname":"铁山港区","cityid":84,"displayorder":3},{"areaid":742,"areaname":"合浦","cityid":84,
     * "displayorder":4},{"areaid":743,"areaname":"北海周边","cityid":84,"displayorder":5}]},{"id":1,"city":"北京","pinyin":"beijing","areas":[{"areaid":1,"areaname":"朝阳","cityid":1,"displayorder":1},
     * {"areaid":2,"areaname":"海淀","cityid":1,"displayorder":2},{"areaid":3,"areaname":"东城","cityid":1,"displayorder":3},{"areaid":4,"areaname":"西城","cityid":1,"displayorder":4},{"areaid":5,
     * "areaname":"崇文","cityid":1,"displayorder":5},{"areaid":6,"areaname":"宣武","cityid":1,"displayorder":6},{"areaid":7,"areaname":"丰台","cityid":1,"displayorder":7},{"areaid":8,"areaname":"通州",
     * "cityid":1,"displayorder":8},{"areaid":9,"areaname":"石景山","cityid":1,"displayorder":9},{"areaid":10,"areaname":"房山","cityid":1,"displayorder":10},{"areaid":11,"areaname":"昌平","cityid":1,
     * "displayorder":11},{"areaid":12,"areaname":"大兴","cityid":1,"displayorder":12},{"areaid":13,"areaname":"顺义","cityid":1,"displayorder":13},{"areaid":14,"areaname":"密云","cityid":1,
     * "displayorder":14},{"areaid":15,"areaname":"怀柔","cityid":1,"displayorder":15},{"areaid":16,"areaname":"延庆","cityid":1,"displayorder":16},{"areaid":17,"areaname":"平谷","cityid":1,
     * "displayorder":17},{"areaid":18,"areaname":"门头沟","cityid":1,"displayorder":18},{"areaid":19,"areaname":"燕郊","cityid":1,"displayorder":19},{"areaid":20,"areaname":"北京周边","cityid":1,
     * "displayorder":20}]},{"id":209,"city":"本溪","pinyin":"benxi","areas":[{"areaid":10538,"areaname":"平山","cityid":209,"displayorder":1},{"areaid":10539,"areaname":"溪湖","cityid":209,
     * "displayorder":2},{"areaid":10540,"areaname":"明山","cityid":209,"displayorder":3},{"areaid":10541,"areaname":"南芬","cityid":209,"displayorder":4},{"areaid":10542,"areaname":"本溪","cityid":209,
     * "displayorder":5},{"areaid":10543,"areaname":"桓仁","cityid":209,"displayorder":6},{"areaid":10544,"areaname":"其它","cityid":209,"displayorder":7}]},{"id":99,"city":"毕节","pinyin":"bijie",
     * "areas":[{"areaid":864,"areaname":"大方","cityid":99,"displayorder":1},{"areaid":865,"areaname":"黔西","cityid":99,"displayorder":2},{"areaid":866,"areaname":"金沙","cityid":99,"displayorder":3},
     * {"areaid":867,"areaname":"织金","cityid":99,"displayorder":4},{"areaid":868,"areaname":"纳雍","cityid":99,"displayorder":5},{"areaid":869,"areaname":"赫章","cityid":99,"displayorder":6},
     * {"areaid":870,"areaname":"威宁","cityid":99,"displayorder":7},{"areaid":871,"areaname":"七星关","cityid":99,"displayorder":8},{"areaid":872,"areaname":"百里杜鹃","cityid":99,"displayorder":9},
     * {"areaid":873,"areaname":"毕节周边","cityid":99,"displayorder":10}]},{"id":258,"city":"滨州","pinyin":"binzhou","areas":[{"areaid":10925,"areaname":"滨城","cityid":258,"displayorder":1},
     * {"areaid":10926,"areaname":"惠民","cityid":258,"displayorder":2},{"areaid":10927,"areaname":"阳信","cityid":258,"displayorder":3},{"areaid":10928,"areaname":"无棣","cityid":258,"displayorder":4},
     * {"areaid":10929,"areaname":"沾化","cityid":258,"displayorder":5},{"areaid":10930,"areaname":"博兴","cityid":258,"displayorder":6},{"areaid":10931,"areaname":"邹平","cityid":258,
     * "displayorder":7}]},{"id":318,"city":"博尔塔拉","pinyin":"boertala","areas":[{"areaid":11508,"areaname":"博乐","cityid":318,"displayorder":1},{"areaid":11509,"areaname":"精河","cityid":318,
     * "displayorder":2},{"areaid":11510,"areaname":"温泉","cityid":318,"displayorder":3}]}],"C":[{"id":104,"city":"沧州","pinyin":"cangzhou","areas":[{"areaid":908,"areaname":"新华","cityid":104,
     * "displayorder":1},{"areaid":909,"areaname":"沧县","cityid":104,"displayorder":2},{"areaid":910,"areaname":"运河","cityid":104,"displayorder":3},{"areaid":911,"areaname":"南皮","cityid":104,
     * "displayorder":4},{"areaid":912,"areaname":"任丘","cityid":104,"displayorder":5},{"areaid":913,"areaname":"青县","cityid":104,"displayorder":6},{"areaid":914,"areaname":"河间","cityid":104,
     * "displayorder":7},{"areaid":915,"areaname":"肃宁","cityid":104,"displayorder":8},{"areaid":916,"areaname":"黄骅","cityid":104,"displayorder":9},{"areaid":917,"areaname":"东光","cityid":104,
     * "displayorder":10},{"areaid":918,"areaname":"泊头","cityid":104,"displayorder":11},{"areaid":919,"areaname":"海兴","cityid":104,"displayorder":12},{"areaid":920,"areaname":"盐山","cityid":104,
     * "displayorder":13},{"areaid":921,"areaname":"吴桥","cityid":104,"displayorder":14},{"areaid":922,"areaname":"孟村","cityid":104,"displayorder":15},{"areaid":923,"areaname":"献县","cityid":104,
     * "displayorder":16},{"areaid":924,"areaname":"其他","cityid":104,"displayorder":17}]},{"id":172,"city":"长春","pinyin":"changchun","areas":[{"areaid":10201,"areaname":"朝阳","cityid":172,
     * "displayorder":1},{"areaid":10202,"areaname":"南关","cityid":172,"displayorder":2},{"areaid":10203,"areaname":"绿园","cityid":172,"displayorder":3},{"areaid":10204,"areaname":"宽城","cityid":172,
     * "displayorder":4},{"areaid":10205,"areaname":"二道","cityid":172,"displayorder":5},{"areaid":10206,"areaname":"双阳","cityid":172,"displayorder":6},{"areaid":10207,"areaname":"经开","cityid":172,
     * "displayorder":7},{"areaid":10208,"areaname":"高新","cityid":172,"displayorder":8},{"areaid":10209,"areaname":"汽车城","cityid":172,"displayorder":9},{"areaid":10210,"areaname":"净月","cityid":172,
     * "displayorder":10},{"areaid":10211,"areaname":"九台","cityid":172,"displayorder":11},{"areaid":10212,"areaname":"榆树","cityid":172,"displayorder":12},{"areaid":10213,"areaname":"德惠",
     * "cityid":172,"displayorder":13},{"areaid":10214,"areaname":"农安","cityid":172,"displayorder":14},{"areaid":10215,"areaname":"长春周边","cityid":172,"displayorder":15}]},{"id":164,"city":"常德",
     * "pinyin":"changde","areas":[{"areaid":10137,"areaname":"武陵","cityid":164,"displayorder":1},{"areaid":10138,"areaname":"鼎城","cityid":164,"displayorder":2},{"areaid":10139,"areaname":"津市",
     * "cityid":164,"displayorder":3},{"areaid":10140,"areaname":"澧县","cityid":164,"displayorder":4},{"areaid":10141,"areaname":"桃源","cityid":164,"displayorder":5},{"areaid":10142,"areaname":"安乡",
     * "cityid":164,"displayorder":6},{"areaid":10143,"areaname":"石门","cityid":164,"displayorder":7},{"areaid":10144,"areaname":"临澧","cityid":164,"displayorder":8},{"areaid":10145,"areaname":"汉寿",
     * "cityid":164,"displayorder":9},{"areaid":10146,"areaname":"其它","cityid":164,"displayorder":10}]},{"id":303,"city":"昌都","pinyin":"changdu","areas":[{"areaid":11376,"areaname":"昌都县",
     * "cityid":303,"displayorder":1},{"areaid":11377,"areaname":"江达","cityid":303,"displayorder":2},{"areaid":11378,"areaname":"贡觉","cityid":303,"displayorder":3},{"areaid":11379,"areaname":"类乌齐",
     * "cityid":303,"displayorder":4},{"areaid":11380,"areaname":"丁青","cityid":303,"displayorder":5},{"areaid":11381,"areaname":"察雅","cityid":303,"displayorder":6},{"areaid":11382,"areaname":"八宿",
     * "cityid":303,"displayorder":7},{"areaid":11383,"areaname":"左贡","cityid":303,"displayorder":8},{"areaid":11384,"areaname":"芒康","cityid":303,"displayorder":9},{"areaid":11385,"areaname":"洛隆",
     * "cityid":303,"displayorder":10},{"areaid":11386,"areaname":"边坝","cityid":303,"displayorder":11}]},{"id":317,"city":"昌吉","pinyin":"changji","areas":[{"areaid":11500,"areaname":"昌吉市",
     * "cityid":317,"displayorder":1},{"areaid":11501,"areaname":"米泉","cityid":317,"displayorder":2},{"areaid":11502,"areaname":"阜康","cityid":317,"displayorder":3},{"areaid":11503,"areaname":"呼图壁",
     * "cityid":317,"displayorder":4},{"areaid":11504,"areaname":"玛纳斯","cityid":317,"displayorder":5},{"areaid":11505,"areaname":"奇台","cityid":317,"displayorder":6},{"areaid":11506,
     * "areaname":"吉木萨尔","cityid":317,"displayorder":7},{"areaid":11507,"areaname":"木垒","cityid":317,"displayorder":8}]},{"id":158,"city":"长沙","pinyin":"changsha","areas":[{"areaid":10087,
     * "areaname":"芙蓉","cityid":158,"displayorder":1},{"areaid":10088,"areaname":"岳麓","cityid":158,"displayorder":2},{"areaid":10089,"areaname":"雨花","cityid":158,"displayorder":3},{"areaid":10090,
     * "areaname":"天心","cityid":158,"displayorder":4},{"areaid":10091,"areaname":"星沙","cityid":158,"displayorder":5},{"areaid":10092,"areaname":"开福","cityid":158,"displayorder":6},{"areaid":10093,
     * "areaname":"长沙周边","cityid":158,"displayorder":7}]},{"id":263,"city":"长治","pinyin":"changzhi","areas":[{"areaid":10967,"areaname":"城区","cityid":263,"displayorder":1},{"areaid":10968,
     * "areaname":"郊区","cityid":263,"displayorder":2},{"areaid":10969,"areaname":"长治县","cityid":263,"displayorder":3},{"areaid":10970,"areaname":"襄垣","cityid":263,"displayorder":4},{"areaid":10971,
     * "areaname":"屯留","cityid":263,"displayorder":5},{"areaid":10972,"areaname":"平顺","cityid":263,"displayorder":6},{"areaid":10973,"areaname":"黎城","cityid":263,"displayorder":7},{"areaid":10974,
     * "areaname":"壶关","cityid":263,"displayorder":8},{"areaid":10975,"areaname":"长子","cityid":263,"displayorder":9},{"areaid":10976,"areaname":"武乡","cityid":263,"displayorder":10},{"areaid":10977,
     * "areaname":"沁县","cityid":263,"displayorder":11},{"areaid":10978,"areaname":"沁源","cityid":263,"displayorder":12},{"areaid":10979,"areaname":"潞城","cityid":263,"displayorder":13}]},{"id":184,
     * "city":"常州","pinyin":"changzhou","areas":[{"areaid":10317,"areaname":"天宁","cityid":184,"displayorder":1},{"areaid":10318,"areaname":"武进","cityid":184,"displayorder":2},{"areaid":10319,
     * "areaname":"钟楼","cityid":184,"displayorder":3},{"areaid":10320,"areaname":"戚墅堰","cityid":184,"displayorder":4},{"areaid":10321,"areaname":"新北","cityid":184,"displayorder":5},{"areaid":10322,
     * "areaname":"金坛","cityid":184,"displayorder":6},{"areaid":10323,"areaname":"溧阳","cityid":184,"displayorder":7},{"areaid":10324,"areaname":"常州周边","cityid":184,"displayorder":8}]},{"id":49,
     * "city":"巢湖","pinyin":"chaohu","areas":[{"areaid":464,"areaname":"居巢","cityid":49,"displayorder":1},{"areaid":465,"areaname":"庐江","cityid":49,"displayorder":2},{"areaid":466,"areaname":"含山",
     * "cityid":49,"displayorder":3},{"areaid":467,"areaname":"和县","cityid":49,"displayorder":4},{"areaid":468,"areaname":"巢湖周边","cityid":49,"displayorder":5}]},{"id":217,"city":"朝阳",
     * "pinyin":"chaoyang","areas":[{"areaid":10596,"areaname":"双塔","cityid":217,"displayorder":1},{"areaid":10597,"areaname":"龙城","cityid":217,"displayorder":2},{"areaid":10598,"areaname":"北票",
     * "cityid":217,"displayorder":3},{"areaid":10599,"areaname":"凌源","cityid":217,"displayorder":4},{"areaid":10600,"areaname":"朝阳县","cityid":217,"displayorder":5},{"areaid":10601,"areaname":"建平",
     * "cityid":217,"displayorder":6},{"areaid":10602,"areaname":"喀喇沁","cityid":217,"displayorder":7},{"areaid":10603,"areaname":"朝阳周边","cityid":217,"displayorder":8}]},{"id":23,"city":"潮州",
     * "pinyin":"chaozhou","areas":[{"areaid":248,"areaname":"湘桥","cityid":23,"displayorder":1},{"areaid":249,"areaname":"枫溪","cityid":23,"displayorder":2},{"areaid":250,"areaname":"潮安",
     * "cityid":23,"displayorder":3},{"areaid":251,"areaname":"饶平","cityid":23,"displayorder":4},{"areaid":252,"areaname":"潮州周边","cityid":23,"displayorder":5}]},{"id":110,"city":"承德",
     * "pinyin":"chengde","areas":[{"areaid":970,"areaname":"双桥","cityid":110,"displayorder":1},{"areaid":971,"areaname":"承德市","cityid":110,"displayorder":2},{"areaid":972,"areaname":"承德县",
     * "cityid":110,"displayorder":3},{"areaid":973,"areaname":"滦平","cityid":110,"displayorder":4},{"areaid":974,"areaname":"围场","cityid":110,"displayorder":5},{"areaid":975,"areaname":"兴隆",
     * "cityid":110,"displayorder":6},{"areaid":976,"areaname":"丰宁","cityid":110,"displayorder":7},{"areaid":977,"areaname":"平泉","cityid":110,"displayorder":8},{"areaid":978,"areaname":"隆化",
     * "cityid":110,"displayorder":9},{"areaid":979,"areaname":"宽城","cityid":110,"displayorder":10},{"areaid":980,"areaname":"双滦","cityid":110,"displayorder":11},{"areaid":981,"areaname":"营子",
     * "cityid":110,"displayorder":12},{"areaid":982,"areaname":"开发区","cityid":110,"displayorder":13},{"areaid":983,"areaname":"承德周边","cityid":110,"displayorder":14}]},{"id":281,"city":"成都",
     * "pinyin":"chengdu","areas":[{"areaid":11179,"areaname":"武侯","cityid":281,"displayorder":1},{"areaid":11180,"areaname":"青羊","cityid":281,"displayorder":2},{"areaid":11181,"areaname":"金牛",
     * "cityid":281,"displayorder":3},{"areaid":11182,"areaname":"锦江","cityid":281,"displayorder":4},{"areaid":11183,"areaname":"成华","cityid":281,"displayorder":5},{"areaid":11184,"areaname":"高新",
     * "cityid":281,"displayorder":6},{"areaid":11185,"areaname":"双流","cityid":281,"displayorder":7},{"areaid":11186,"areaname":"温江","cityid":281,"displayorder":8},{"areaid":11187,"areaname":"郫县",
     * "cityid":281,"displayorder":9},{"areaid":11188,"areaname":"高新西区","cityid":281,"displayorder":10},{"areaid":11189,"areaname":"龙泉驿","cityid":281,"displayorder":11},{"areaid":11190,
     * "areaname":"新都","cityid":281,"displayorder":12},{"areaid":11191,"areaname":"青白江","cityid":281,"displayorder":13},{"areaid":11192,"areaname":"金堂","cityid":281,"displayorder":14},
     * {"areaid":11193,"areaname":"都江堰","cityid":281,"displayorder":15},{"areaid":11194,"areaname":"彭州","cityid":281,"displayorder":16},{"areaid":11195,"areaname":"新津","cityid":281,
     * "displayorder":17},{"areaid":11196,"areaname":"崇州","cityid":281,"displayorder":18},{"areaid":11197,"areaname":"大邑","cityid":281,"displayorder":19},{"areaid":11198,"areaname":"邛崃",
     * "cityid":281,"displayorder":20},{"areaid":11199,"areaname":"蒲江","cityid":281,"displayorder":21},{"areaid":11200,"areaname":"其他","cityid":281,"displayorder":22}]},{"id":167,"city":"郴州",
     * "pinyin":"chenzhou","areas":[{"areaid":10158,"areaname":"北湖","cityid":167,"displayorder":1},{"areaid":10159,"areaname":"苏仙","cityid":167,"displayorder":2},{"areaid":10160,"areaname":"资兴",
     * "cityid":167,"displayorder":3},{"areaid":10161,"areaname":"桂阳","cityid":167,"displayorder":4},{"areaid":10162,"areaname":"宜章","cityid":167,"displayorder":5},{"areaid":10163,"areaname":"永兴",
     * "cityid":167,"displayorder":6},{"areaid":10164,"areaname":"嘉禾","cityid":167,"displayorder":7},{"areaid":10165,"areaname":"临武","cityid":167,"displayorder":8},{"areaid":10166,"areaname":"其它",
     * "cityid":167,"displayorder":9}]},{"id":222,"city":"赤峰","pinyin":"chifeng","areas":[{"areaid":10646,"areaname":"红山","cityid":222,"displayorder":1},{"areaid":10647,"areaname":"元宝山",
     * "cityid":222,"displayorder":2},{"areaid":10648,"areaname":"松山","cityid":222,"displayorder":3},{"areaid":10649,"areaname":"翁牛特旗","cityid":222,"displayorder":4},{"areaid":10650,
     * "areaname":"喀喇沁旗","cityid":222,"displayorder":5},{"areaid":10651,"areaname":"宁城","cityid":222,"displayorder":6},{"areaid":10652,"areaname":"敖汉旗","cityid":222,"displayorder":7},
     * {"areaid":10653,"areaname":"林西","cityid":222,"displayorder":8},{"areaid":10654,"areaname":"其他","cityid":222,"displayorder":9},{"areaid":10655,"areaname":"新城区","cityid":222,
     * "displayorder":10}]},{"id":52,"city":"池州","pinyin":"chizhou","areas":[{"areaid":481,"areaname":"贵池","cityid":52,"displayorder":1},{"areaid":482,"areaname":"东至","cityid":52,"displayorder":2},
     * {"areaid":483,"areaname":"石台","cityid":52,"displayorder":3},{"areaid":484,"areaname":"青阳","cityid":52,"displayorder":4},{"areaid":485,"areaname":"池州周边","cityid":52,"displayorder":5}]},
     * {"id":86,"city":"崇左","pinyin":"chongzuo","areas":[{"areaid":749,"areaname":"江州","cityid":86,"displayorder":1},{"areaid":750,"areaname":"凭祥","cityid":86,"displayorder":2},{"areaid":751,
     * "areaname":"扶绥","cityid":86,"displayorder":3},{"areaid":752,"areaname":"大新","cityid":86,"displayorder":4},{"areaid":753,"areaname":"天等","cityid":86,"displayorder":5},{"areaid":754,
     * "areaname":"宁明","cityid":86,"displayorder":6},{"areaid":755,"areaname":"龙州","cityid":86,"displayorder":7},{"areaid":756,"areaname":"崇左周边","cityid":86,"displayorder":8}]},{"id":333,
     * "city":"楚雄","pinyin":"chuxiong","areas":[{"areaid":11627,"areaname":"楚雄市","cityid":333,"displayorder":1},{"areaid":11628,"areaname":"双柏","cityid":333,"displayorder":2},{"areaid":11629,
     * "areaname":"牟定","cityid":333,"displayorder":3},{"areaid":11630,"areaname":"南华","cityid":333,"displayorder":4},{"areaid":11631,"areaname":"姚安","cityid":333,"displayorder":5},{"areaid":11632,
     * "areaname":"大姚","cityid":333,"displayorder":6},{"areaid":11633,"areaname":"永仁","cityid":333,"displayorder":7},{"areaid":11634,"areaname":"元谋","cityid":333,"displayorder":8},{"areaid":11635,
     * "areaname":"武定","cityid":333,"displayorder":9},{"areaid":11636,"areaname":"禄丰","cityid":333,"displayorder":10}]},{"id":47,"city":"滁州","pinyin":"chuzhou","areas":[{"areaid":447,
     * "areaname":"琅琊","cityid":47,"displayorder":1},{"areaid":448,"areaname":"南谯","cityid":47,"displayorder":2},{"areaid":449,"areaname":"来安","cityid":47,"displayorder":3},{"areaid":450,
     * "areaname":"全椒","cityid":47,"displayorder":4},{"areaid":451,"areaname":"定远","cityid":47,"displayorder":5},{"areaid":452,"areaname":"凤阳","cityid":47,"displayorder":6},{"areaid":453,
     * "areaname":"天长","cityid":47,"displayorder":7},{"areaid":454,"areaname":"明光","cityid":47,"displayorder":8},{"areaid":455,"areaname":"滁州周边","cityid":47,"displayorder":9}]}],"D":[{"id":334,
     * "city":"大理","pinyin":"dali","areas":[{"areaid":11637,"areaname":"大理市","cityid":334,"displayorder":1},{"areaid":11638,"areaname":"祥云","cityid":334,"displayorder":2},{"areaid":11639,
     * "areaname":"宾川","cityid":334,"displayorder":3},{"areaid":11640,"areaname":"弥渡","cityid":334,"displayorder":4},{"areaid":11641,"areaname":"永平","cityid":334,"displayorder":5},{"areaid":11642,
     * "areaname":"云龙","cityid":334,"displayorder":6},{"areaid":11643,"areaname":"洱源","cityid":334,"displayorder":7},{"areaid":11644,"areaname":"剑川","cityid":334,"displayorder":8},{"areaid":11645,
     * "areaname":"鹤庆","cityid":334,"displayorder":9},{"areaid":11646,"areaname":"漾濞","cityid":334,"displayorder":10},{"areaid":11647,"areaname":"南涧","cityid":334,"displayorder":11},
     * {"areaid":11648,"areaname":"巍山","cityid":334,"displayorder":12}]},{"id":206,"city":"大连","pinyin":"dalian"},{"id":210,"city":"丹东","pinyin":"dandong","areas":[{"areaid":10545,"areaname":"振兴",
     * "cityid":210,"displayorder":1},{"areaid":10546,"areaname":"元宝","cityid":210,"displayorder":2},{"areaid":10547,"areaname":"振安","cityid":210,"displayorder":3},{"areaid":10548,"areaname":"东港",
     * "cityid":210,"displayorder":4},{"areaid":10549,"areaname":"凤城","cityid":210,"displayorder":5},{"areaid":10550,"areaname":"宽甸","cityid":210,"displayorder":6},{"areaid":10551,"areaname":"其他",
     * "cityid":210,"displayorder":7}]},{"id":137,"city":"大庆","pinyin":"daqing","areas":[{"areaid":1234,"areaname":"萨尔图","cityid":137,"displayorder":1},{"areaid":1235,"areaname":"让胡路","cityid":137,
     * "displayorder":2},{"areaid":1236,"areaname":"龙凤","cityid":137,"displayorder":3},{"areaid":1237,"areaname":"红岗","cityid":137,"displayorder":4},{"areaid":1238,"areaname":"大同","cityid":137,
     * "displayorder":5},{"areaid":1239,"areaname":"其他","cityid":137,"displayorder":6}]},{"id":261,"city":"大同","pinyin":"datong","areas":[{"areaid":10951,"areaname":"城区","cityid":261,
     * "displayorder":1},{"areaid":10952,"areaname":"矿区","cityid":261,"displayorder":2},{"areaid":10953,"areaname":"南郊","cityid":261,"displayorder":3},{"areaid":10954,"areaname":"新荣","cityid":261,
     * "displayorder":4},{"areaid":10955,"areaname":"阳高","cityid":261,"displayorder":5},{"areaid":10956,"areaname":"天镇","cityid":261,"displayorder":6},{"areaid":10957,"areaname":"广灵","cityid":261,
     * "displayorder":7},{"areaid":10958,"areaname":"灵丘","cityid":261,"displayorder":8},{"areaid":10959,"areaname":"浑源","cityid":261,"displayorder":9},{"areaid":10960,"areaname":"左云","cityid":261,
     * "displayorder":10},{"areaid":10961,"areaname":"大同县","cityid":261,"displayorder":11}]},{"id":144,"city":"大兴安岭","pinyin":"daxinganling","areas":[{"areaid":1292,"areaname":"呼玛","cityid":144,
     * "displayorder":1},{"areaid":1293,"areaname":"塔河","cityid":144,"displayorder":2},{"areaid":1294,"areaname":"漠河","cityid":144,"displayorder":3},{"areaid":1295,"areaname":"加格达奇","cityid":144,
     * "displayorder":4},{"areaid":1296,"areaname":"松岭","cityid":144,"displayorder":5},{"areaid":1297,"areaname":"新林","cityid":144,"displayorder":6},{"areaid":1298,"areaname":"呼中","cityid":144,
     * "displayorder":7},{"areaid":1299,"areaname":"大兴安岭周边","cityid":144,"displayorder":8}]},{"id":295,"city":"达州","pinyin":"dazhou","areas":[{"areaid":11295,"areaname":"通川","cityid":295,
     * "displayorder":1},{"areaid":11296,"areaname":"万源","cityid":295,"displayorder":2},{"areaid":11297,"areaname":"达县","cityid":295,"displayorder":3},{"areaid":11298,"areaname":"宣汉","cityid":295,
     * "displayorder":4},{"areaid":11299,"areaname":"开江","cityid":295,"displayorder":5},{"areaid":11300,"areaname":"大竹","cityid":295,"displayorder":6},{"areaid":11301,"areaname":"渠县","cityid":295,
     * "displayorder":7}]},{"id":335,"city":"德宏","pinyin":"dehong","areas":[{"areaid":11649,"areaname":"潞西","cityid":335,"displayorder":1},{"areaid":11650,"areaname":"瑞丽","cityid":335,
     * "displayorder":2},{"areaid":11651,"areaname":"梁河","cityid":335,"displayorder":3},{"areaid":11652,"areaname":"盈江","cityid":335,"displayorder":4},{"areaid":11653,"areaname":"陇川","cityid":335,
     * "displayorder":5}]},{"id":284,"city":"德阳","pinyin":"deyang","areas":[{"areaid":11220,"areaname":"旌阳","cityid":284,"displayorder":1},{"areaid":11221,"areaname":"广汉","cityid":284,
     * "displayorder":2},{"areaid":11222,"areaname":"什邡","cityid":284,"displayorder":3},{"areaid":11223,"areaname":"绵竹","cityid":284,"displayorder":4},{"areaid":11224,"areaname":"罗江","cityid":284,
     * "displayorder":5},{"areaid":11225,"areaname":"中江","cityid":284,"displayorder":6}]},{"id":256,"city":"德州","pinyin":"dezhou","areas":[{"areaid":10906,"areaname":"德城","cityid":256,
     * "displayorder":1},{"areaid":10907,"areaname":"乐陵","cityid":256,"displayorder":2},{"areaid":10908,"areaname":"禹城","cityid":256,"displayorder":3},{"areaid":10909,"areaname":"陵县","cityid":256,
     * "displayorder":4},{"areaid":10910,"areaname":"宁津","cityid":256,"displayorder":5},{"areaid":10911,"areaname":"庆云","cityid":256,"displayorder":6},{"areaid":10912,"areaname":"临邑","cityid":256,
     * "displayorder":7},{"areaid":10913,"areaname":"齐河","cityid":256,"displayorder":8},{"areaid":10914,"areaname":"平原","cityid":256,"displayorder":9},{"areaid":10915,"areaname":"夏津","cityid":256,
     * "displayorder":10},{"areaid":10916,"areaname":"武城","cityid":256,"displayorder":11}]},{"id":74,"city":"定西","pinyin":"dingxi","areas":[{"areaid":668,"areaname":"安定","cityid":74,
     * "displayorder":1},{"areaid":669,"areaname":"通渭","cityid":74,"displayorder":2},{"areaid":670,"areaname":"临洮","cityid":74,"displayorder":3},{"areaid":671,"areaname":"漳县","cityid":74,
     * "displayorder":4},{"areaid":672,"areaname":"岷县","cityid":74,"displayorder":5},{"areaid":673,"areaname":"渭源","cityid":74,"displayorder":6},{"areaid":674,"areaname":"陇西","cityid":74,
     * "displayorder":7},{"areaid":675,"areaname":"定西周边","cityid":74,"displayorder":8}]},{"id":337,"city":"迪庆","pinyin":"diqing","areas":[{"areaid":11658,"areaname":"香格里拉","cityid":337,
     * "displayorder":1},{"areaid":11659,"areaname":"德钦","cityid":337,"displayorder":2},{"areaid":11660,"areaname":"维西","cityid":337,"displayorder":3}]},{"id":21,"city":"东莞","pinyin":"dong",
     * "areas":[{"areaid":215,"areaname":"莞城","cityid":21,"displayorder":1},{"areaid":216,"areaname":"长安","cityid":21,"displayorder":2},{"areaid":217,"areaname":"南城","cityid":21,"displayorder":3},
     * {"areaid":218,"areaname":"东城","cityid":21,"displayorder":4},{"areaid":219,"areaname":"虎门","cityid":21,"displayorder":5},{"areaid":220,"areaname":"万江","cityid":21,"displayorder":6},
     * {"areaid":221,"areaname":"塘厦","cityid":21,"displayorder":7},{"areaid":222,"areaname":"常平","cityid":21,"displayorder":8},{"areaid":223,"areaname":"樟木头","cityid":21,"displayorder":9},
     * {"areaid":224,"areaname":"石龙","cityid":21,"displayorder":10},{"areaid":225,"areaname":"凤岗","cityid":21,"displayorder":11},{"areaid":226,"areaname":"松山湖","cityid":21,"displayorder":12},
     * {"areaid":227,"areaname":"厚街","cityid":21,"displayorder":13},{"areaid":228,"areaname":"高埗","cityid":21,"displayorder":14},{"areaid":229,"areaname":"石碣","cityid":21,"displayorder":15},
     * {"areaid":230,"areaname":"东莞周边","cityid":21,"displayorder":16}]},{"id":247,"city":"东营","pinyin":"dongying","areas":[{"areaid":10821,"areaname":"东营区","cityid":247,"displayorder":1},
     * {"areaid":10822,"areaname":"河口","cityid":247,"displayorder":2},{"areaid":10823,"areaname":"垦利","cityid":247,"displayorder":3},{"areaid":10824,"areaname":"利津","cityid":247,"displayorder":4},
     * {"areaid":10825,"areaname":"广饶","cityid":247,"displayorder":5}]}],"E":[{"id":224,"city":"鄂尔多斯","pinyin":"eerduosi","areas":[{"areaid":10665,"areaname":"东胜","cityid":224,"displayorder":1},
     * {"areaid":10666,"areaname":"达拉特旗","cityid":224,"displayorder":2},{"areaid":10667,"areaname":"准格尔旗","cityid":224,"displayorder":3},{"areaid":10668,"areaname":"鄂托克旗","cityid":224,
     * "displayorder":4},{"areaid":10669,"areaname":"鄂托克前旗","cityid":224,"displayorder":5},{"areaid":10670,"areaname":"杭锦旗","cityid":224,"displayorder":6},{"areaid":10671,"areaname":"乌审旗",
     * "cityid":224,"displayorder":7},{"areaid":10672,"areaname":"伊金霍洛旗","cityid":224,"displayorder":8},{"areaid":10673,"areaname":"康巴什区","cityid":224,"displayorder":9},{"areaid":10674,
     * "areaname":"其它","cityid":224,"displayorder":10}]},{"id":157,"city":"恩施","pinyin":"enshi","areas":[{"areaid":10079,"areaname":"恩施市","cityid":157,"displayorder":1},{"areaid":10080,
     * "areaname":"利川","cityid":157,"displayorder":2},{"areaid":10081,"areaname":"建始","cityid":157,"displayorder":3},{"areaid":10082,"areaname":"巴东","cityid":157,"displayorder":4},{"areaid":10083,
     * "areaname":"咸丰","cityid":157,"displayorder":5},{"areaid":10084,"areaname":"宣恩","cityid":157,"displayorder":6},{"areaid":10085,"areaname":"来凤","cityid":157,"displayorder":7},{"areaid":10086,
     * "areaname":"鹤峰","cityid":157,"displayorder":8}]},{"id":153,"city":"鄂州","pinyin":"ezhou","areas":[{"areaid":10046,"areaname":"鄂城区","cityid":153,"displayorder":1},{"areaid":10047,
     * "areaname":"华容","cityid":153,"displayorder":2},{"areaid":10048,"areaname":"梁子湖","cityid":153,"displayorder":3},{"areaid":10049,"areaname":"鄂州周边","cityid":153,"displayorder":4}]}],
     * "F":[{"id":85,"city":"防城港","pinyin":"fangchenggang","areas":[{"areaid":744,"areaname":"港口","cityid":85,"displayorder":1},{"areaid":745,"areaname":"防城","cityid":85,"displayorder":2},
     * {"areaid":746,"areaname":"上思","cityid":85,"displayorder":3},{"areaid":747,"areaname":"东兴","cityid":85,"displayorder":4},{"areaid":748,"areaname":"防城港周边","cityid":85,"displayorder":5}]},
     * {"id":10,"city":"佛山","pinyin":"foshan","areas":[{"areaid":128,"areaname":"禅城","cityid":10,"displayorder":1},{"areaid":129,"areaname":"南海","cityid":10,"displayorder":2},{"areaid":130,
     * "areaname":"顺德","cityid":10,"displayorder":3},{"areaid":131,"areaname":"三水","cityid":10,"displayorder":4},{"areaid":132,"areaname":"高明","cityid":10,"displayorder":5},{"areaid":133,
     * "areaname":"佛山周边","cityid":10,"displayorder":6}]},{"id":208,"city":"抚顺","pinyin":"fushun","areas":[{"areaid":10528,"areaname":"顺城","cityid":208,"displayorder":1},{"areaid":10529,
     * "areaname":"新抚","cityid":208,"displayorder":2},{"areaid":10530,"areaname":"东洲","cityid":208,"displayorder":3},{"areaid":10531,"areaname":"望花","cityid":208,"displayorder":4},{"areaid":10532,
     * "areaname":"开发区","cityid":208,"displayorder":5},{"areaid":10533,"areaname":"胜利","cityid":208,"displayorder":6},{"areaid":10534,"areaname":"抚顺","cityid":208,"displayorder":7},{"areaid":10535,
     * "areaname":"新宾","cityid":208,"displayorder":8},{"areaid":10536,"areaname":"清原","cityid":208,"displayorder":9},{"areaid":10537,"areaname":"其他","cityid":208,"displayorder":10}]},{"id":213,
     * "city":"阜新","pinyin":"fuxin","areas":[{"areaid":10568,"areaname":"海州","cityid":213,"displayorder":1},{"areaid":10569,"areaname":"新邱","cityid":213,"displayorder":2},{"areaid":10570,
     * "areaname":"太平","cityid":213,"displayorder":3},{"areaid":10571,"areaname":"清河门","cityid":213,"displayorder":4},{"areaid":10572,"areaname":"细河","cityid":213,"displayorder":5},{"areaid":10573,
     * "areaname":"彰武","cityid":213,"displayorder":6},{"areaid":10574,"areaname":"阜新县","cityid":213,"displayorder":7},{"areaid":10575,"areaname":"阜新周边","cityid":213,"displayorder":8}]},{"id":48,
     * "city":"阜阳","pinyin":"fuyang","areas":[{"areaid":456,"areaname":"颍州","cityid":48,"displayorder":1},{"areaid":457,"areaname":"颍泉","cityid":48,"displayorder":2},{"areaid":458,
     * "areaname":"颍东经济开发区","cityid":48,"displayorder":3},{"areaid":459,"areaname":"界首","cityid":48,"displayorder":4},{"areaid":460,"areaname":"阜南","cityid":48,"displayorder":5},{"areaid":461,
     * "areaname":"太和","cityid":48,"displayorder":6},{"areaid":462,"areaname":"颍上","cityid":48,"displayorder":7},{"areaid":463,"areaname":"临泉","cityid":48,"displayorder":8}]},{"id":55,"city":"福州",
     * "pinyin":"fuzhou","areas":[{"areaid":500,"areaname":"台江","cityid":55,"displayorder":1},{"areaid":501,"areaname":"鼓楼","cityid":55,"displayorder":2},{"areaid":502,"areaname":"仓山","cityid":55,
     * "displayorder":3},{"areaid":503,"areaname":"晋安","cityid":55,"displayorder":4},{"areaid":504,"areaname":"马尾","cityid":55,"displayorder":5},{"areaid":505,"areaname":"闽侯","cityid":55,
     * "displayorder":6},{"areaid":506,"areaname":"长乐","cityid":55,"displayorder":7},{"areaid":507,"areaname":"福清","cityid":55,"displayorder":8},{"areaid":508,"areaname":"平潭","cityid":55,
     * "displayorder":9},{"areaid":509,"areaname":"连江","cityid":55,"displayorder":10},{"areaid":510,"areaname":"永泰","cityid":55,"displayorder":11},{"areaid":511,"areaname":"闽清","cityid":55,
     * "displayorder":12},{"areaid":512,"areaname":"罗源","cityid":55,"displayorder":13},{"areaid":513,"areaname":"福州周边","cityid":55,"displayorder":14}]},{"id":204,"city":"抚州","pinyin":"fuzhou",
     * "areas":[{"areaid":10503,"areaname":"临川","cityid":204,"displayorder":1},{"areaid":10504,"areaname":"南城","cityid":204,"displayorder":2},{"areaid":10505,"areaname":"东乡","cityid":204,
     * "displayorder":3},{"areaid":10506,"areaname":"崇仁","cityid":204,"displayorder":4},{"areaid":10507,"areaname":"乐安","cityid":204,"displayorder":5},{"areaid":10508,"areaname":"抚州周边",
     * "cityid":204,"displayorder":6}]}],"G":[{"id":77,"city":"甘南","pinyin":"gannan","areas":[{"areaid":684,"areaname":"合作","cityid":77,"displayorder":1},{"areaid":685,"areaname":"临潭","cityid":77,
     * "displayorder":2},{"areaid":686,"areaname":"卓尼","cityid":77,"displayorder":3},{"areaid":687,"areaname":"舟曲","cityid":77,"displayorder":4},{"areaid":688,"areaname":"迭部","cityid":77,
     * "displayorder":5},{"areaid":689,"areaname":"玛曲","cityid":77,"displayorder":6},{"areaid":690,"areaname":"碌曲","cityid":77,"displayorder":7},{"areaid":691,"areaname":"夏河","cityid":77,
     * "displayorder":8},{"areaid":692,"areaname":"甘南周边","cityid":77,"displayorder":9}]},{"id":200,"city":"赣州","pinyin":"ganzhou","areas":[{"areaid":10450,"areaname":"章贡","cityid":200,
     * "displayorder":1},{"areaid":10451,"areaname":"南康","cityid":200,"displayorder":2},{"areaid":10452,"areaname":"瑞金","cityid":200,"displayorder":3},{"areaid":10453,"areaname":"赣县","cityid":200,
     * "displayorder":4},{"areaid":10454,"areaname":"信丰","cityid":200,"displayorder":5},{"areaid":10455,"areaname":"大余","cityid":200,"displayorder":6},{"areaid":10456,"areaname":"上犹","cityid":200,
     * "displayorder":7},{"areaid":10457,"areaname":"崇义","cityid":200,"displayorder":8},{"areaid":10458,"areaname":"安远","cityid":200,"displayorder":9},{"areaid":10459,"areaname":"龙南","cityid":200,
     * "displayorder":10},{"areaid":10460,"areaname":"定南","cityid":200,"displayorder":11},{"areaid":10461,"areaname":"全南","cityid":200,"displayorder":12},{"areaid":10462,"areaname":"兴国",
     * "cityid":200,"displayorder":13},{"areaid":10463,"areaname":"宁都","cityid":200,"displayorder":14},{"areaid":10464,"areaname":"于都","cityid":200,"displayorder":15},{"areaid":10465,
     * "areaname":"会昌","cityid":200,"displayorder":16},{"areaid":10466,"areaname":"寻乌","cityid":200,"displayorder":17},{"areaid":10467,"areaname":"石城","cityid":200,"displayorder":18},
     * {"areaid":10468,"areaname":"章江新区","cityid":200,"displayorder":19},{"areaid":10469,"areaname":"开发区","cityid":200,"displayorder":20},{"areaid":10470,"areaname":"站北区","cityid":200,
     * "displayorder":21},{"areaid":10471,"areaname":"健康路","cityid":200,"displayorder":22}]},{"id":300,"city":"甘孜","pinyin":"ganzi","areas":[{"areaid":11333,"areaname":"康定","cityid":300,
     * "displayorder":1},{"areaid":11334,"areaname":"泸定","cityid":300,"displayorder":2},{"areaid":11335,"areaname":"丹巴","cityid":300,"displayorder":3},{"areaid":11336,"areaname":"九龙","cityid":300,
     * "displayorder":4},{"areaid":11337,"areaname":"雅江","cityid":300,"displayorder":5},{"areaid":11338,"areaname":"道孚","cityid":300,"displayorder":6},{"areaid":11339,"areaname":"炉霍","cityid":300,
     * "displayorder":7},{"areaid":11340,"areaname":"甘孜县","cityid":300,"displayorder":8},{"areaid":11341,"areaname":"新龙","cityid":300,"displayorder":9},{"areaid":11342,"areaname":"德格","cityid":300,
     * "displayorder":10},{"areaid":11343,"areaname":"白玉","cityid":300,"displayorder":11},{"areaid":11344,"areaname":"石渠","cityid":300,"displayorder":12},{"areaid":11345,"areaname":"色达",
     * "cityid":300,"displayorder":13},{"areaid":11346,"areaname":"理塘","cityid":300,"displayorder":14},{"areaid":11347,"areaname":"巴塘","cityid":300,"displayorder":15},{"areaid":11348,
     * "areaname":"乡城","cityid":300,"displayorder":16},{"areaid":11349,"areaname":"稻城","cityid":300,"displayorder":17},{"areaid":11350,"areaname":"得荣","cityid":300,"displayorder":18}]},{"id":286,
     * "city":"广安","pinyin":"guangan","areas":[{"areaid":11235,"areaname":"广安区","cityid":286,"displayorder":1},{"areaid":11236,"areaname":"华蓥","cityid":286,"displayorder":2},{"areaid":11237,
     * "areaname":"岳池","cityid":286,"displayorder":3},{"areaid":11238,"areaname":"武胜","cityid":286,"displayorder":4},{"areaid":11239,"areaname":"邻水","cityid":286,"displayorder":5},{"areaid":11240,
     * "areaname":"广安城南","cityid":286,"displayorder":6},{"areaid":11241,"areaname":"广安城北","cityid":286,"displayorder":7}]},{"id":291,"city":"广元","pinyin":"guangyuan","areas":[{"areaid":11269,
     * "areaname":"市中","cityid":291,"displayorder":1},{"areaid":11270,"areaname":"元坝","cityid":291,"displayorder":2},{"areaid":11271,"areaname":"朝天","cityid":291,"displayorder":3},{"areaid":11272,
     * "areaname":"旺苍","cityid":291,"displayorder":4},{"areaid":11273,"areaname":"青川","cityid":291,"displayorder":5},{"areaid":11274,"areaname":"剑阁","cityid":291,"displayorder":6},{"areaid":11275,
     * "areaname":"苍溪","cityid":291,"displayorder":7}]},{"id":5,"city":"广州","pinyin":"guangzhou","areas":[{"areaid":73,"areaname":"天河","cityid":5,"displayorder":1},{"areaid":74,"areaname":"海珠",
     * "cityid":5,"displayorder":2},{"areaid":75,"areaname":"越秀","cityid":5,"displayorder":3},{"areaid":76,"areaname":"白云","cityid":5,"displayorder":4},{"areaid":77,"areaname":"荔湾","cityid":5,
     * "displayorder":5},{"areaid":78,"areaname":"番禺","cityid":5,"displayorder":6},{"areaid":79,"areaname":"黄埔","cityid":5,"displayorder":7},{"areaid":80,"areaname":"花都","cityid":5,
     * "displayorder":8},{"areaid":81,"areaname":"增城","cityid":5,"displayorder":9},{"areaid":82,"areaname":"萝岗","cityid":5,"displayorder":10},{"areaid":83,"areaname":"东莞","cityid":5,
     * "displayorder":11},{"areaid":84,"areaname":"佛山","cityid":5,"displayorder":12},{"areaid":85,"areaname":"从化","cityid":5,"displayorder":13},{"areaid":86,"areaname":"南沙","cityid":5,
     * "displayorder":14},{"areaid":87,"areaname":"经济开发区","cityid":5,"displayorder":15},{"areaid":88,"areaname":"广州周边","cityid":5,"displayorder":16}]},{"id":82,"city":"贵港","pinyin":"guigang",
     * "areas":[{"areaid":729,"areaname":"港北","cityid":82,"displayorder":1},{"areaid":730,"areaname":"港南","cityid":82,"displayorder":2},{"areaid":731,"areaname":"覃塘","cityid":82,"displayorder":3},
     * {"areaid":732,"areaname":"桂平","cityid":82,"displayorder":4},{"areaid":733,"areaname":"平南","cityid":82,"displayorder":5}]},{"id":80,"city":"桂林","pinyin":"guilin","areas":[{"areaid":710,
     * "areaname":"七星","cityid":80,"displayorder":1},{"areaid":711,"areaname":"象山","cityid":80,"displayorder":2},{"areaid":712,"areaname":"秀峰","cityid":80,"displayorder":3},{"areaid":713,
     * "areaname":"叠彩","cityid":80,"displayorder":4},{"areaid":714,"areaname":"雁山","cityid":80,"displayorder":5},{"areaid":715,"areaname":"临桂","cityid":80,"displayorder":6},{"areaid":716,
     * "areaname":"灵川","cityid":80,"displayorder":7},{"areaid":717,"areaname":"阳朔县","cityid":80,"displayorder":8},{"areaid":718,"areaname":"兴安县","cityid":80,"displayorder":9},{"areaid":719,
     * "areaname":"八里街","cityid":80,"displayorder":10},{"areaid":720,"areaname":"桂林周边","cityid":80,"displayorder":11}]},{"id":92,"city":"贵阳","pinyin":"guiyang","areas":[{"areaid":799,
     * "areaname":"云岩","cityid":92,"displayorder":1},{"areaid":800,"areaname":"南明","cityid":92,"displayorder":2},{"areaid":801,"areaname":"花溪","cityid":92,"displayorder":3},{"areaid":802,
     * "areaname":"白云","cityid":92,"displayorder":4},{"areaid":803,"areaname":"小河","cityid":92,"displayorder":5},{"areaid":804,"areaname":"乌当","cityid":92,"displayorder":6},{"areaid":805,
     * "areaname":"金阳新区","cityid":92,"displayorder":7},{"areaid":806,"areaname":"清镇","cityid":92,"displayorder":8},{"areaid":807,"areaname":"小河片","cityid":92,"displayorder":9},{"areaid":808,
     * "areaname":"贵阳周边","cityid":92,"displayorder":10}]},{"id":240,"city":"果洛","pinyin":"guoluo","areas":[{"areaid":10763,"areaname":"玛沁","cityid":240,"displayorder":1},{"areaid":10764,
     * "areaname":"班玛","cityid":240,"displayorder":2},{"areaid":10765,"areaname":"甘德","cityid":240,"displayorder":3},{"areaid":10766,"areaname":"达日","cityid":240,"displayorder":4},{"areaid":10767,
     * "areaname":"久治","cityid":240,"displayorder":5},{"areaid":10768,"areaname":"玛多","cityid":240,"displayorder":6}]},{"id":234,"city":"固原","pinyin":"guyuan","areas":[{"areaid":10731,
     * "areaname":"原州","cityid":234,"displayorder":1},{"areaid":10732,"areaname":"西吉","cityid":234,"displayorder":2},{"areaid":10733,"areaname":"隆德","cityid":234,"displayorder":3},{"areaid":10734,
     * "areaname":"泾源","cityid":234,"displayorder":4},{"areaid":10735,"areaname":"彭阳","cityid":234,"displayorder":5}]}],"H":[{"id":132,"city":"哈尔滨","pinyin":"haerbin","areas":[{"areaid":1185,
     * "areaname":"南岗","cityid":132,"displayorder":1},{"areaid":1186,"areaname":"道里","cityid":132,"displayorder":2},{"areaid":1187,"areaname":"道外","cityid":132,"displayorder":3},{"areaid":1188,
     * "areaname":"香坊","cityid":132,"displayorder":4},{"areaid":1189,"areaname":"江北","cityid":132,"displayorder":5},{"areaid":1190,"areaname":"开发区","cityid":132,"displayorder":6},{"areaid":1191,
     * "areaname":"依兰","cityid":132,"displayorder":7},{"areaid":1192,"areaname":"方正","cityid":132,"displayorder":8},{"areaid":1193,"areaname":"宾县","cityid":132,"displayorder":9},{"areaid":1194,
     * "areaname":"巴彦","cityid":132,"displayorder":10},{"areaid":1195,"areaname":"木兰","cityid":132,"displayorder":11},{"areaid":1196,"areaname":"通河","cityid":132,"displayorder":12},{"areaid":1197,
     * "areaname":"哈尔滨周边","cityid":132,"displayorder":13}]},{"id":238,"city":"海北","pinyin":"haibei","areas":[{"areaid":10755,"areaname":"海晏","cityid":238,"displayorder":1},{"areaid":10756,
     * "areaname":"祁连","cityid":238,"displayorder":2},{"areaid":10757,"areaname":"刚察","cityid":238,"displayorder":3},{"areaid":10758,"areaname":"门源","cityid":238,"displayorder":4}]},{"id":237,
     * "city":"海东","pinyin":"haidong","areas":[{"areaid":10749,"areaname":"平安","cityid":237,"displayorder":1},{"areaid":10750,"areaname":"乐都","cityid":237,"displayorder":2},{"areaid":10751,
     * "areaname":"民和","cityid":237,"displayorder":3},{"areaid":10752,"areaname":"互助","cityid":237,"displayorder":4},{"areaid":10753,"areaname":"化隆","cityid":237,"displayorder":5},{"areaid":10754,
     * "areaname":"循化","cityid":237,"displayorder":6}]},{"id":101,"city":"海口","pinyin":"haikou","areas":[{"areaid":885,"areaname":"龙华","cityid":101,"displayorder":1},{"areaid":886,"areaname":"秀英",
     * "cityid":101,"displayorder":2},{"areaid":887,"areaname":"琼山","cityid":101,"displayorder":3},{"areaid":888,"areaname":"美兰","cityid":101,"displayorder":4},{"areaid":889,"areaname":"海口周边",
     * "cityid":101,"displayorder":5}]},{"id":242,"city":"海西","pinyin":"haixi","areas":[{"areaid":10775,"areaname":"德令哈","cityid":242,"displayorder":1},{"areaid":10776,"areaname":"格尔木",
     * "cityid":242,"displayorder":2},{"areaid":10777,"areaname":"乌兰","cityid":242,"displayorder":3},{"areaid":10778,"areaname":"都兰","cityid":242,"displayorder":4},{"areaid":10779,"areaname":"天峻",
     * "cityid":242,"displayorder":5},{"areaid":10780,"areaname":"冷湖行委","cityid":242,"displayorder":6},{"areaid":10781,"areaname":"大柴旦行委","cityid":242,"displayorder":7},{"areaid":10782,
     * "areaname":"茫崖行委","cityid":242,"displayorder":8}]},{"id":312,"city":"哈密","pinyin":"hami","areas":[{"areaid":11459,"areaname":"哈密市","cityid":312,"displayorder":1},{"areaid":11460,
     * "areaname":"伊吾","cityid":312,"displayorder":2},{"areaid":11461,"areaname":"巴里坤","cityid":312,"displayorder":3}]},{"id":106,"city":"邯郸","pinyin":"handan","areas":[{"areaid":925,
     * "areaname":"丛台","cityid":106,"displayorder":1},{"areaid":926,"areaname":"邯山","cityid":106,"displayorder":2},{"areaid":927,"areaname":"复兴","cityid":106,"displayorder":3},{"areaid":928,
     * "areaname":"高开区","cityid":106,"displayorder":4},{"areaid":929,"areaname":"峰峰矿区","cityid":106,"displayorder":5},{"areaid":930,"areaname":"武安","cityid":106,"displayorder":6},{"areaid":931,
     * "areaname":"邯郸县","cityid":106,"displayorder":7},{"areaid":932,"areaname":"魏县","cityid":106,"displayorder":8},{"areaid":933,"areaname":"成安县","cityid":106,"displayorder":9},{"areaid":934,
     * "areaname":"大名县","cityid":106,"displayorder":10},{"areaid":935,"areaname":"涉县","cityid":106,"displayorder":11},{"areaid":936,"areaname":"肥乡县","cityid":106,"displayorder":12},{"areaid":937,
     * "areaname":"永年县","cityid":106,"displayorder":13},{"areaid":938,"areaname":"临漳县","cityid":106,"displayorder":14},{"areaid":939,"areaname":"磁县","cityid":106,"displayorder":15},{"areaid":940,
     * "areaname":"邯郸周边","cityid":106,"displayorder":16}]},{"id":26,"city":"杭州","pinyin":"hangzhou","areas":[{"areaid":265,"areaname":"西湖","cityid":26,"displayorder":1},{"areaid":266,
     * "areaname":"拱墅","cityid":26,"displayorder":2},{"areaid":267,"areaname":"江干","cityid":26,"displayorder":3},{"areaid":268,"areaname":"下城","cityid":26,"displayorder":4},{"areaid":269,
     * "areaname":"上城","cityid":26,"displayorder":5},{"areaid":270,"areaname":"余杭","cityid":26,"displayorder":6},{"areaid":271,"areaname":"萧山","cityid":26,"displayorder":7},{"areaid":272,
     * "areaname":"滨江","cityid":26,"displayorder":8},{"areaid":273,"areaname":"建德","cityid":26,"displayorder":9},{"areaid":274,"areaname":"富阳","cityid":26,"displayorder":10},{"areaid":275,
     * "areaname":"临安","cityid":26,"displayorder":11},{"areaid":276,"areaname":"桐庐","cityid":26,"displayorder":12},{"areaid":277,"areaname":"淳安","cityid":26,"displayorder":13},{"areaid":278,
     * "areaname":"杭州周边","cityid":26,"displayorder":14}]},{"id":277,"city":"汉中","pinyin":"hanzhong","areas":[{"areaid":11133,"areaname":"汉台","cityid":277,"displayorder":1},{"areaid":11134,
     * "areaname":"南郑","cityid":277,"displayorder":2},{"areaid":11135,"areaname":"城固","cityid":277,"displayorder":3},{"areaid":11136,"areaname":"洋县","cityid":277,"displayorder":4},{"areaid":11137,
     * "areaname":"西乡","cityid":277,"displayorder":5},{"areaid":11138,"areaname":"勉县","cityid":277,"displayorder":6},{"areaid":11139,"areaname":"宁强","cityid":277,"displayorder":7},{"areaid":11140,
     * "areaname":"略阳","cityid":277,"displayorder":8},{"areaid":11141,"areaname":"镇巴","cityid":277,"displayorder":9},{"areaid":11142,"areaname":"留坝","cityid":277,"displayorder":10},{"areaid":11143,
     * "areaname":"佛坪","cityid":277,"displayorder":11}]},{"id":125,"city":"漯河","pinyin":"he","areas":[{"areaid":1119,"areaname":"源汇","cityid":125,"displayorder":1},{"areaid":1120,"areaname":"郾城",
     * "cityid":125,"displayorder":2},{"areaid":1121,"areaname":"召陵","cityid":125,"displayorder":3},{"areaid":1122,"areaname":"高新区","cityid":125,"displayorder":4},{"areaid":1123,"areaname":"舞阳",
     * "cityid":125,"displayorder":5},{"areaid":1124,"areaname":"临颍","cityid":125,"displayorder":6}]},{"id":120,"city":"鹤壁","pinyin":"hebi","areas":[{"areaid":1083,"areaname":"淇滨","cityid":120,
     * "displayorder":1},{"areaid":1084,"areaname":"山城","cityid":120,"displayorder":2},{"areaid":1085,"areaname":"鹤山","cityid":120,"displayorder":3},{"areaid":1086,"areaname":"浚县","cityid":120,
     * "displayorder":4},{"areaid":1087,"areaname":"淇县","cityid":120,"displayorder":5}]},{"id":88,"city":"河池","pinyin":"hechi","areas":[{"areaid":764,"areaname":"金城江","cityid":88,"displayorder":1},
     * {"areaid":765,"areaname":"宜州","cityid":88,"displayorder":2},{"areaid":766,"areaname":"南丹","cityid":88,"displayorder":3},{"areaid":767,"areaname":"天峨","cityid":88,"displayorder":4},
     * {"areaid":768,"areaname":"凤山","cityid":88,"displayorder":5},{"areaid":769,"areaname":"东兰","cityid":88,"displayorder":6},{"areaid":770,"areaname":"罗城","cityid":88,"displayorder":7},
     * {"areaid":771,"areaname":"环江","cityid":88,"displayorder":8},{"areaid":772,"areaname":"巴马","cityid":88,"displayorder":9},{"areaid":773,"areaname":"都安","cityid":88,"displayorder":10},
     * {"areaid":774,"areaname":"大化","cityid":88,"displayorder":11},{"areaid":775,"areaname":"河池学院","cityid":88,"displayorder":12}]},{"id":38,"city":"合肥","pinyin":"hefei","areas":[{"areaid":378,
     * "areaname":"蜀山","cityid":38,"displayorder":1},{"areaid":379,"areaname":"庐阳","cityid":38,"displayorder":2},{"areaid":380,"areaname":"包河","cityid":38,"displayorder":3},{"areaid":381,
     * "areaname":"瑶海","cityid":38,"displayorder":4},{"areaid":382,"areaname":"经开","cityid":38,"displayorder":5},{"areaid":383,"areaname":"高新","cityid":38,"displayorder":6},{"areaid":384,
     * "areaname":"新站","cityid":38,"displayorder":7},{"areaid":385,"areaname":"滨湖新区","cityid":38,"displayorder":8},{"areaid":386,"areaname":"政务","cityid":38,"displayorder":9},{"areaid":387,
     * "areaname":"合肥周边","cityid":38,"displayorder":10}]},{"id":135,"city":"鹤岗","pinyin":"hegang","areas":[{"areaid":1217,"areaname":"工农","cityid":135,"displayorder":1},{"areaid":1218,
     * "areaname":"向阳","cityid":135,"displayorder":2},{"areaid":1219,"areaname":"南山","cityid":135,"displayorder":3},{"areaid":1220,"areaname":"东山","cityid":135,"displayorder":4},{"areaid":1221,
     * "areaname":"兴山","cityid":135,"displayorder":5},{"areaid":1222,"areaname":"兴安","cityid":135,"displayorder":6},{"areaid":1223,"areaname":"绥滨","cityid":135,"displayorder":7},{"areaid":1224,
     * "areaname":"萝北","cityid":135,"displayorder":8}]},{"id":142,"city":"黑河","pinyin":"heihe","areas":[{"areaid":1275,"areaname":"爱辉","cityid":142,"displayorder":1},{"areaid":1276,"areaname":"北安",
     * "cityid":142,"displayorder":2},{"areaid":1277,"areaname":"五大连池","cityid":142,"displayorder":3},{"areaid":1278,"areaname":"嫩江","cityid":142,"displayorder":4},{"areaid":1279,"areaname":"逊克",
     * "cityid":142,"displayorder":5},{"areaid":1280,"areaname":"孙吴","cityid":142,"displayorder":6},{"areaid":1281,"areaname":"黑河周边","cityid":142,"displayorder":7}]},{"id":113,"city":"衡水",
     * "pinyin":"hengshui","areas":[{"areaid":1325,"areaname":"桃城","cityid":113,"displayorder":1},{"areaid":1326,"areaname":"深州","cityid":113,"displayorder":2},{"areaid":1327,"areaname":"冀州",
     * "cityid":113,"displayorder":3},{"areaid":1328,"areaname":"安平","cityid":113,"displayorder":4},{"areaid":1329,"areaname":"故城","cityid":113,"displayorder":5},{"areaid":1330,"areaname":"阜城",
     * "cityid":113,"displayorder":6},{"areaid":1331,"areaname":"枣强","cityid":113,"displayorder":7},{"areaid":1332,"areaname":"景县","cityid":113,"displayorder":8},{"areaid":1333,"areaname":"武强",
     * "cityid":113,"displayorder":9},{"areaid":1334,"areaname":"饶阳","cityid":113,"displayorder":10},{"areaid":1335,"areaname":"开发区","cityid":113,"displayorder":11},{"areaid":1336,"areaname":"武邑县",
     * "cityid":113,"displayorder":12},{"areaid":1337,"areaname":"其他","cityid":113,"displayorder":13}]},{"id":161,"city":"衡阳","pinyin":"hengyang","areas":[{"areaid":10111,"areaname":"蒸湘",
     * "cityid":161,"displayorder":1},{"areaid":10112,"areaname":"雁峰","cityid":161,"displayorder":2},{"areaid":10113,"areaname":"石鼓","cityid":161,"displayorder":3},{"areaid":10114,"areaname":"珠晖",
     * "cityid":161,"displayorder":4},{"areaid":10115,"areaname":"南岳","cityid":161,"displayorder":5},{"areaid":10116,"areaname":"立新开发区","cityid":161,"displayorder":6},{"areaid":10117,
     * "areaname":"华新开发区","cityid":161,"displayorder":7},{"areaid":10118,"areaname":"衡阳周边","cityid":161,"displayorder":8}]},{"id":313,"city":"和田","pinyin":"hetian","areas":[{"areaid":11462,
     * "areaname":"和田市","cityid":313,"displayorder":1},{"areaid":11463,"areaname":"和田县","cityid":313,"displayorder":2},{"areaid":11464,"areaname":"墨玉","cityid":313,"displayorder":3},
     * {"areaid":11465,"areaname":"皮山","cityid":313,"displayorder":4},{"areaid":11466,"areaname":"洛浦","cityid":313,"displayorder":5},{"areaid":11467,"areaname":"策勒","cityid":313,"displayorder":6},
     * {"areaid":11468,"areaname":"于田","cityid":313,"displayorder":7},{"areaid":11469,"areaname":"民丰","cityid":313,"displayorder":8}]},{"id":18,"city":"河源","pinyin":"heyuan","areas":[{"areaid":193,
     * "areaname":"源城","cityid":18,"displayorder":1},{"areaid":194,"areaname":"紫金","cityid":18,"displayorder":2},{"areaid":195,"areaname":"龙川","cityid":18,"displayorder":3},{"areaid":196,
     * "areaname":"连平","cityid":18,"displayorder":4},{"areaid":197,"areaname":"和平县","cityid":18,"displayorder":5},{"areaid":198,"areaname":"东源","cityid":18,"displayorder":6},{"areaid":199,
     * "areaname":"河源周边","cityid":18,"displayorder":7}]},{"id":259,"city":"菏泽","pinyin":"heze","areas":[{"areaid":10932,"areaname":"牡丹","cityid":259,"displayorder":1},{"areaid":10933,
     * "areaname":"曹县","cityid":259,"displayorder":2},{"areaid":10934,"areaname":"单县","cityid":259,"displayorder":3},{"areaid":10935,"areaname":"成武","cityid":259,"displayorder":4},{"areaid":10936,
     * "areaname":"巨野","cityid":259,"displayorder":5},{"areaid":10937,"areaname":"郓城","cityid":259,"displayorder":6},{"areaid":10938,"areaname":"鄄城","cityid":259,"displayorder":7},{"areaid":10939,
     * "areaname":"定陶","cityid":259,"displayorder":8},{"areaid":10940,"areaname":"东明","cityid":259,"displayorder":9}]},{"id":90,"city":"贺州","pinyin":"hezhou","areas":[{"areaid":783,"areaname":"八步",
     * "cityid":90,"displayorder":1},{"areaid":784,"areaname":"平桂","cityid":90,"displayorder":2},{"areaid":785,"areaname":"昭平","cityid":90,"displayorder":3},{"areaid":786,"areaname":"钟山",
     * "cityid":90,"displayorder":4},{"areaid":787,"areaname":"富川","cityid":90,"displayorder":5},{"areaid":788,"areaname":"贺州周边","cityid":90,"displayorder":6}]},{"id":331,"city":"红河",
     * "pinyin":"honghe","areas":[{"areaid":11611,"areaname":"蒙自","cityid":331,"displayorder":1},{"areaid":11612,"areaname":"个旧","cityid":331,"displayorder":2},{"areaid":11613,"areaname":"开远",
     * "cityid":331,"displayorder":3},{"areaid":11614,"areaname":"绿春","cityid":331,"displayorder":4},{"areaid":11615,"areaname":"建水","cityid":331,"displayorder":5},{"areaid":11616,"areaname":"石屏",
     * "cityid":331,"displayorder":6},{"areaid":11617,"areaname":"弥勒","cityid":331,"displayorder":7},{"areaid":11618,"areaname":"泸西","cityid":331,"displayorder":8},{"areaid":11619,"areaname":"元阳",
     * "cityid":331,"displayorder":9},{"areaid":11620,"areaname":"红河县","cityid":331,"displayorder":10},{"areaid":11621,"areaname":"金平","cityid":331,"displayorder":11},{"areaid":11622,
     * "areaname":"河口","cityid":331,"displayorder":12},{"areaid":11623,"areaname":"屏边","cityid":331,"displayorder":13}]},{"id":188,"city":"淮安","pinyin":"huaian","areas":[{"areaid":10351,
     * "areaname":"清河","cityid":188,"displayorder":1},{"areaid":10352,"areaname":"清浦","cityid":188,"displayorder":2},{"areaid":10353,"areaname":"淮阴","cityid":188,"displayorder":3},{"areaid":10354,
     * "areaname":"淮安区","cityid":188,"displayorder":4},{"areaid":10355,"areaname":"涟水","cityid":188,"displayorder":5},{"areaid":10356,"areaname":"洪泽","cityid":188,"displayorder":6},{"areaid":10357,
     * "areaname":"金湖","cityid":188,"displayorder":7},{"areaid":10358,"areaname":"盱眙","cityid":188,"displayorder":8},{"areaid":10359,"areaname":"其他","cityid":188,"displayorder":9},{"areaid":10360,
     * "areaname":"经济开发区","cityid":188,"displayorder":10}]},{"id":43,"city":"淮北","pinyin":"huaibei","areas":[{"areaid":418,"areaname":"相山","cityid":43,"displayorder":1},{"areaid":419,
     * "areaname":"烈山","cityid":43,"displayorder":2},{"areaid":420,"areaname":"杜集","cityid":43,"displayorder":3},{"areaid":421,"areaname":"濉溪","cityid":43,"displayorder":4},{"areaid":422,
     * "areaname":"淮北周边","cityid":43,"displayorder":5}]},{"id":169,"city":"怀化","pinyin":"huaihua","areas":[{"areaid":10178,"areaname":"鹤城","cityid":169,"displayorder":1},{"areaid":10179,
     * "areaname":"洪江","cityid":169,"displayorder":2},{"areaid":10180,"areaname":"沅陵","cityid":169,"displayorder":3},{"areaid":10181,"areaname":"辰溪","cityid":169,"displayorder":4},{"areaid":10182,
     * "areaname":"溆浦","cityid":169,"displayorder":5},{"areaid":10183,"areaname":"中方","cityid":169,"displayorder":6},{"areaid":10184,"areaname":"会同","cityid":169,"displayorder":7},{"areaid":10185,
     * "areaname":"其它","cityid":169,"displayorder":8}]},{"id":41,"city":"淮南","pinyin":"huainan","areas":[{"areaid":405,"areaname":"田家庵","cityid":41,"displayorder":1},{"areaid":406,"areaname":"大通",
     * "cityid":41,"displayorder":2},{"areaid":407,"areaname":"谢家集","cityid":41,"displayorder":3},{"areaid":408,"areaname":"八公山","cityid":41,"displayorder":4},{"areaid":409,"areaname":"潘集",
     * "cityid":41,"displayorder":5},{"areaid":410,"areaname":"凤台","cityid":41,"displayorder":6},{"areaid":411,"areaname":"毛集实验区","cityid":41,"displayorder":7}]},{"id":154,"city":"黄冈",
     * "pinyin":"huanggang","areas":[{"areaid":10050,"areaname":"黄州","cityid":154,"displayorder":1},{"areaid":10051,"areaname":"武穴","cityid":154,"displayorder":2},{"areaid":10052,"areaname":"麻城",
     * "cityid":154,"displayorder":3},{"areaid":10053,"areaname":"浠水","cityid":154,"displayorder":4},{"areaid":10054,"areaname":"黄梅","cityid":154,"displayorder":5},{"areaid":10055,"areaname":"蕲春",
     * "cityid":154,"displayorder":6},{"areaid":10056,"areaname":"团风","cityid":154,"displayorder":7},{"areaid":10057,"areaname":"红安","cityid":154,"displayorder":8},{"areaid":10058,"areaname":"罗田",
     * "cityid":154,"displayorder":9},{"areaid":10059,"areaname":"英山","cityid":154,"displayorder":10},{"areaid":10060,"areaname":"龙感湖","cityid":154,"displayorder":11}]},{"id":239,"city":"黄南",
     * "pinyin":"huangnan","areas":[{"areaid":10759,"areaname":"同仁","cityid":239,"displayorder":1},{"areaid":10760,"areaname":"尖扎","cityid":239,"displayorder":2},{"areaid":10761,"areaname":"泽库",
     * "cityid":239,"displayorder":3},{"areaid":10762,"areaname":"河南","cityid":239,"displayorder":4}]},{"id":46,"city":"黄山","pinyin":"huangshan","areas":[{"areaid":438,"areaname":"黄山","cityid":46,
     * "displayorder":1},{"areaid":439,"areaname":"徽州","cityid":46,"displayorder":2},{"areaid":440,"areaname":"屯溪","cityid":46,"displayorder":3},{"areaid":441,"areaname":"黄山风景区","cityid":46,
     * "displayorder":4},{"areaid":442,"areaname":"祁门","cityid":46,"displayorder":5},{"areaid":443,"areaname":"休宁","cityid":46,"displayorder":6},{"areaid":444,"areaname":"黟县","cityid":46,
     * "displayorder":7},{"areaid":445,"areaname":"歙县","cityid":46,"displayorder":8},{"areaid":446,"areaname":"黄山周边","cityid":46,"displayorder":9}]},{"id":147,"city":"黄石","pinyin":"huangshi",
     * "areas":[{"areaid":10000,"areaname":"黄石港","cityid":147,"displayorder":1},{"areaid":1019,"areaname":"西塞山","cityid":147,"displayorder":2},{"areaid":1020,"areaname":"团城山","cityid":147,
     * "displayorder":3},{"areaid":1021,"areaname":"下陆","cityid":147,"displayorder":4},{"areaid":1022,"areaname":"铁山","cityid":147,"displayorder":5},{"areaid":1023,"areaname":"大冶","cityid":147,
     * "displayorder":6},{"areaid":1024,"areaname":"阳新","cityid":147,"displayorder":7},{"areaid":1025,"areaname":"花湖","cityid":147,"displayorder":8},{"areaid":1026,"areaname":"其它","cityid":147,
     * "displayorder":9}]},{"id":219,"city":"呼和浩特","pinyin":"huhehaote","areas":[{"areaid":10613,"areaname":"新城","cityid":219,"displayorder":1},{"areaid":10614,"areaname":"赛罕","cityid":219,
     * "displayorder":2},{"areaid":10615,"areaname":"回民","cityid":219,"displayorder":3},{"areaid":10616,"areaname":"玉泉","cityid":219,"displayorder":4},{"areaid":10617,"areaname":"如意开发区",
     * "cityid":219,"displayorder":5},{"areaid":10618,"areaname":"金桥开发区","cityid":219,"displayorder":6},{"areaid":10619,"areaname":"金川开发区","cityid":219,"displayorder":7},{"areaid":10620,
     * "areaname":"金山开发区","cityid":219,"displayorder":8},{"areaid":10621,"areaname":"土默特左","cityid":219,"displayorder":9},{"areaid":10622,"areaname":"托克托","cityid":219,"displayorder":10},
     * {"areaid":10623,"areaname":"和林格尔","cityid":219,"displayorder":11},{"areaid":10624,"areaname":"清水河","cityid":219,"displayorder":12},{"areaid":10625,"areaname":"武川","cityid":219,
     * "displayorder":13},{"areaid":10626,"areaname":"呼和浩特周边","cityid":219,"displayorder":14}]},{"id":15,"city":"惠州","pinyin":"huizhou","areas":[{"areaid":171,"areaname":"惠城","cityid":15,
     * "displayorder":1},{"areaid":172,"areaname":"博罗","cityid":15,"displayorder":2},{"areaid":173,"areaname":"大亚湾","cityid":15,"displayorder":3},{"areaid":174,"areaname":"仲恺","cityid":15,
     * "displayorder":4},{"areaid":175,"areaname":"惠东","cityid":15,"displayorder":5},{"areaid":176,"areaname":"龙门","cityid":15,"displayorder":6},{"areaid":177,"areaname":"惠阳","cityid":15,
     * "displayorder":7},{"areaid":178,"areaname":"惠州周边","cityid":15,"displayorder":8}]},{"id":218,"city":"葫芦岛","pinyin":"huludao","areas":[{"areaid":10604,"areaname":"龙港","cityid":218,
     * "displayorder":1},{"areaid":10605,"areaname":"连山","cityid":218,"displayorder":2},{"areaid":10606,"areaname":"南票","cityid":218,"displayorder":3},{"areaid":10607,"areaname":"兴城","cityid":218,
     * "displayorder":4},{"areaid":10608,"areaname":"绥中","cityid":218,"displayorder":5},{"areaid":10609,"areaname":"建昌","cityid":218,"displayorder":6},{"areaid":10610,"areaname":"开发区","cityid":218,
     * "displayorder":7},{"areaid":10611,"areaname":"北港工业区","cityid":218,"displayorder":8},{"areaid":10612,"areaname":"葫芦岛周边","cityid":218,"displayorder":9}]},{"id":221,"city":"呼伦贝尔",
     * "pinyin":"hulunbeier","areas":[{"areaid":10639,"areaname":"海拉尔","cityid":221,"displayorder":1},{"areaid":10640,"areaname":"满洲里","cityid":221,"displayorder":2},{"areaid":10641,
     * "areaname":"扎兰屯","cityid":221,"displayorder":3},{"areaid":10642,"areaname":"牙克石","cityid":221,"displayorder":4},{"areaid":10643,"areaname":"根河","cityid":221,"displayorder":5},
     * {"areaid":10644,"areaname":"额尔古纳","cityid":221,"displayorder":6},{"areaid":10645,"areaname":"呼伦贝尔周边","cityid":221,"displayorder":7}]},{"id":29,"city":"湖州","pinyin":"huzhou",
     * "areas":[{"areaid":302,"areaname":"吴兴","cityid":29,"displayorder":1},{"areaid":303,"areaname":"南浔","cityid":29,"displayorder":2},{"areaid":304,"areaname":"长兴","cityid":29,"displayorder":3},
     * {"areaid":305,"areaname":"德清","cityid":29,"displayorder":4},{"areaid":306,"areaname":"安吉","cityid":29,"displayorder":5},{"areaid":307,"areaname":"其他","cityid":29,"displayorder":6}]}],
     * "J":[{"id":139,"city":"佳木斯","pinyin":"jiamusi","areas":[{"areaid":1247,"areaname":"前进","cityid":139,"displayorder":1},{"areaid":1248,"areaname":"永红","cityid":139,"displayorder":2},
     * {"areaid":1249,"areaname":"向阳","cityid":139,"displayorder":3},{"areaid":1250,"areaname":"东风","cityid":139,"displayorder":4},{"areaid":1251,"areaname":"郊区","cityid":139,"displayorder":5},
     * {"areaid":1252,"areaname":"同江","cityid":139,"displayorder":6},{"areaid":1253,"areaname":"富锦","cityid":139,"displayorder":7},{"areaid":1254,"areaname":"桦南","cityid":139,"displayorder":8},
     * {"areaid":1255,"areaname":"桦川","cityid":139,"displayorder":9},{"areaid":1256,"areaname":"汤原","cityid":139,"displayorder":10},{"areaid":1257,"areaname":"抚远","cityid":139,"displayorder":11}]},
     * {"id":201,"city":"吉安","pinyin":"jian","areas":[{"areaid":10472,"areaname":"吉州","cityid":201,"displayorder":1},{"areaid":10473,"areaname":"青原","cityid":201,"displayorder":2},{"areaid":10474,
     * "areaname":"井冈山","cityid":201,"displayorder":3},{"areaid":10475,"areaname":"遂川","cityid":201,"displayorder":4},{"areaid":10476,"areaname":"吉安县","cityid":201,"displayorder":5},
     * {"areaid":10477,"areaname":"吉水","cityid":201,"displayorder":6},{"areaid":10478,"areaname":"峡江","cityid":201,"displayorder":7},{"areaid":10479,"areaname":"新干","cityid":201,"displayorder":8},
     * {"areaid":10480,"areaname":"永丰","cityid":201,"displayorder":9},{"areaid":10481,"areaname":"泰和","cityid":201,"displayorder":10},{"areaid":10482,"areaname":"万安","cityid":201,
     * "displayorder":11},{"areaid":10483,"areaname":"安福","cityid":201,"displayorder":12},{"areaid":10484,"areaname":"永新","cityid":201,"displayorder":13},{"areaid":10485,"areaname":"庐陵新区",
     * "cityid":201,"displayorder":14}]},{"id":11,"city":"江门","pinyin":"jiangmen","areas":[{"areaid":134,"areaname":"蓬江","cityid":11,"displayorder":1},{"areaid":135,"areaname":"新会","cityid":11,
     * "displayorder":2},{"areaid":136,"areaname":"江海","cityid":11,"displayorder":3},{"areaid":137,"areaname":"台山","cityid":11,"displayorder":4},{"areaid":138,"areaname":"鹤山","cityid":11,
     * "displayorder":5},{"areaid":139,"areaname":"开平","cityid":11,"displayorder":6},{"areaid":140,"areaname":"恩平","cityid":11,"displayorder":7},{"areaid":141,"areaname":"南新","cityid":11,
     * "displayorder":8},{"areaid":142,"areaname":"北新","cityid":11,"displayorder":9},{"areaid":143,"areaname":"江门周边","cityid":11,"displayorder":10}]},{"id":116,"city":"焦作","pinyin":"jiaozuo",
     * "areas":[{"areaid":1041,"areaname":"解放","cityid":116,"displayorder":1},{"areaid":1042,"areaname":"山阳","cityid":116,"displayorder":2},{"areaid":1043,"areaname":"中站","cityid":116,
     * "displayorder":3},{"areaid":1044,"areaname":"马村","cityid":116,"displayorder":4},{"areaid":1045,"areaname":"高新","cityid":116,"displayorder":5},{"areaid":1046,"areaname":"孟州","cityid":116,
     * "displayorder":6},{"areaid":1047,"areaname":"沁阳","cityid":116,"displayorder":7},{"areaid":1048,"areaname":"温县","cityid":116,"displayorder":8},{"areaid":1049,"areaname":"武陟","cityid":116,
     * "displayorder":9},{"areaid":1050,"areaname":"博爱","cityid":116,"displayorder":10},{"areaid":1051,"areaname":"修武","cityid":116,"displayorder":11},{"areaid":1052,"areaname":"其他","cityid":116,
     * "displayorder":12}]},{"id":27,"city":"嘉兴","pinyin":"jiaxing","areas":[{"areaid":279,"areaname":"南湖","cityid":27,"displayorder":1},{"areaid":280,"areaname":"秀洲","cityid":27,"displayorder":2},
     * {"areaid":281,"areaname":"经济开发区","cityid":27,"displayorder":3},{"areaid":282,"areaname":"平湖","cityid":27,"displayorder":4},{"areaid":283,"areaname":"海宁","cityid":27,"displayorder":5},
     * {"areaid":284,"areaname":"桐乡","cityid":27,"displayorder":6},{"areaid":285,"areaname":"海盐","cityid":27,"displayorder":7},{"areaid":286,"areaname":"嘉善县","cityid":27,"displayorder":8},
     * {"areaid":287,"areaname":"嘉兴市区","cityid":27,"displayorder":9},{"areaid":288,"areaname":"嘉兴周边","cityid":27,"displayorder":10}]},{"id":65,"city":"嘉峪关","pinyin":"jiayuguan",
     * "areas":[{"areaid":596,"areaname":"五一","cityid":65,"displayorder":1},{"areaid":597,"areaname":"新华","cityid":65,"displayorder":2},{"areaid":598,"areaname":"前进","cityid":65,"displayorder":3},
     * {"areaid":599,"areaname":"胜利","cityid":65,"displayorder":4},{"areaid":600,"areaname":"建设","cityid":65,"displayorder":5},{"areaid":601,"areaname":"镜铁山矿区","cityid":65,"displayorder":6},
     * {"areaid":602,"areaname":"嘉峪关周边","cityid":65,"displayorder":7}]},{"id":24,"city":"揭阳","pinyin":"jieyang","areas":[{"areaid":253,"areaname":"榕城","cityid":24,"displayorder":1},{"areaid":254,
     * "areaname":"普宁","cityid":24,"displayorder":2},{"areaid":255,"areaname":"揭东","cityid":24,"displayorder":3},{"areaid":256,"areaname":"惠来","cityid":24,"displayorder":4},{"areaid":257,
     * "areaname":"揭西","cityid":24,"displayorder":5},{"areaid":258,"areaname":"其他","cityid":24,"displayorder":6}]},{"id":173,"city":"吉林","pinyin":"jilin","areas":[{"areaid":10216,"areaname":"船营",
     * "cityid":173,"displayorder":1},{"areaid":10217,"areaname":"昌邑","cityid":173,"displayorder":2},{"areaid":10218,"areaname":"龙潭","cityid":173,"displayorder":3},{"areaid":10219,"areaname":"丰满",
     * "cityid":173,"displayorder":4},{"areaid":10220,"areaname":"桦甸","cityid":173,"displayorder":5},{"areaid":10221,"areaname":"磐石","cityid":173,"displayorder":6},{"areaid":10222,"areaname":"舒兰",
     * "cityid":173,"displayorder":7},{"areaid":10223,"areaname":"蛟河","cityid":173,"displayorder":8},{"areaid":10224,"areaname":"永吉","cityid":173,"displayorder":9},{"areaid":10225,
     * "areaname":"吉林周边","cityid":173,"displayorder":10}]},{"id":243,"city":"济南","pinyin":"jinan","areas":[{"areaid":10783,"areaname":"历下","cityid":243,"displayorder":1},{"areaid":10784,
     * "areaname":"市中","cityid":243,"displayorder":2},{"areaid":10785,"areaname":"天桥","cityid":243,"displayorder":3},{"areaid":10786,"areaname":"历城","cityid":243,"displayorder":4},{"areaid":10787,
     * "areaname":"槐荫","cityid":243,"displayorder":5},{"areaid":10788,"areaname":"高新","cityid":243,"displayorder":6},{"areaid":10789,"areaname":"长清","cityid":243,"displayorder":7},{"areaid":10790,
     * "areaname":"平阴","cityid":243,"displayorder":8},{"areaid":10791,"areaname":"章丘","cityid":243,"displayorder":9},{"areaid":10792,"areaname":"济阳","cityid":243,"displayorder":10},{"areaid":10793,
     * "areaname":"商河","cityid":243,"displayorder":11},{"areaid":10794,"areaname":"其他","cityid":243,"displayorder":12}]},{"id":66,"city":"金昌","pinyin":"jinchang","areas":[{"areaid":603,
     * "areaname":"永昌","cityid":66,"displayorder":1},{"areaid":604,"areaname":"河西堡","cityid":66,"displayorder":2},{"areaid":605,"areaname":"金川","cityid":66,"displayorder":3}]},{"id":264,
     * "city":"晋城","pinyin":"jincheng","areas":[{"areaid":10980,"areaname":"城区","cityid":264,"displayorder":1},{"areaid":10981,"areaname":"沁水","cityid":264,"displayorder":2},{"areaid":10982,
     * "areaname":"阳城","cityid":264,"displayorder":3},{"areaid":10983,"areaname":"陵川","cityid":264,"displayorder":4},{"areaid":10984,"areaname":"泽州","cityid":264,"displayorder":5},{"areaid":10985,
     * "areaname":"高平","cityid":264,"displayorder":6}]},{"id":195,"city":"景德镇","pinyin":"jingdezhen","areas":[{"areaid":10416,"areaname":"乐平","cityid":195,"displayorder":1},{"areaid":10417,
     * "areaname":"浮梁","cityid":195,"displayorder":2},{"areaid":10418,"areaname":"珠山","cityid":195,"displayorder":3},{"areaid":10419,"areaname":"昌江","cityid":195,"displayorder":4}]},{"id":156,
     * "city":"荆门","pinyin":"jingmen","areas":[{"areaid":10069,"areaname":"蓬江","cityid":156,"displayorder":1},{"areaid":10070,"areaname":"新会","cityid":156,"displayorder":2},{"areaid":10071,
     * "areaname":"江海","cityid":156,"displayorder":3},{"areaid":10072,"areaname":"台山","cityid":156,"displayorder":4},{"areaid":10073,"areaname":"鹤山","cityid":156,"displayorder":5},{"areaid":10074,
     * "areaname":"开平","cityid":156,"displayorder":6},{"areaid":10075,"areaname":"恩平","cityid":156,"displayorder":7},{"areaid":10076,"areaname":"南新","cityid":156,"displayorder":8},{"areaid":10077,
     * "areaname":"北新","cityid":156,"displayorder":9},{"areaid":10078,"areaname":"江门周边","cityid":156,"displayorder":10}]},{"id":151,"city":"荆州","pinyin":"jingzhou","areas":[{"areaid":10030,
     * "areaname":"沙市","cityid":151,"displayorder":1},{"areaid":10031,"areaname":"荆州","cityid":151,"displayorder":2},{"areaid":10032,"areaname":"洪湖","cityid":151,"displayorder":3},{"areaid":10033,
     * "areaname":"石首","cityid":151,"displayorder":4},{"areaid":10034,"areaname":"松滋","cityid":151,"displayorder":5},{"areaid":10035,"areaname":"监利","cityid":151,"displayorder":6},{"areaid":10036,
     * "areaname":"公安","cityid":151,"displayorder":7},{"areaid":10037,"areaname":"江陵","cityid":151,"displayorder":8},{"areaid":10038,"areaname":"其他","cityid":151,"displayorder":9}]},{"id":31,
     * "city":"金华","pinyin":"jinhua","areas":[{"areaid":318,"areaname":"金华市区","cityid":31,"displayorder":1},{"areaid":319,"areaname":"义乌","cityid":31,"displayorder":2},{"areaid":320,
     * "areaname":"永康","cityid":31,"displayorder":3},{"areaid":321,"areaname":"东阳","cityid":31,"displayorder":4},{"areaid":322,"areaname":"兰溪","cityid":31,"displayorder":5},{"areaid":323,
     * "areaname":"浦江县","cityid":31,"displayorder":6},{"areaid":324,"areaname":"磐安","cityid":31,"displayorder":7},{"areaid":325,"areaname":"武义县","cityid":31,"displayorder":8},{"areaid":326,
     * "areaname":"其他","cityid":31,"displayorder":9}]},{"id":244,"city":"济宁","pinyin":"jining","areas":[{"areaid":10795,"areaname":"市中","cityid":244,"displayorder":1},{"areaid":10796,
     * "areaname":"任城","cityid":244,"displayorder":2},{"areaid":10797,"areaname":"曲阜","cityid":244,"displayorder":3},{"areaid":10798,"areaname":"兖州","cityid":244,"displayorder":4},{"areaid":10799,
     * "areaname":"邹城","cityid":244,"displayorder":5},{"areaid":10800,"areaname":"微山","cityid":244,"displayorder":6},{"areaid":10801,"areaname":"鱼台","cityid":244,"displayorder":7},{"areaid":10802,
     * "areaname":"金乡","cityid":244,"displayorder":8},{"areaid":10803,"areaname":"嘉祥","cityid":244,"displayorder":9},{"areaid":10804,"areaname":"汶上","cityid":244,"displayorder":10},{"areaid":10805,
     * "areaname":"泗水","cityid":244,"displayorder":11},{"areaid":10806,"areaname":"梁山","cityid":244,"displayorder":12}]},{"id":266,"city":"晋中","pinyin":"jinzhong","areas":[{"areaid":10992,
     * "areaname":"榆次","cityid":266,"displayorder":1},{"areaid":10993,"areaname":"榆社","cityid":266,"displayorder":2},{"areaid":10994,"areaname":"左权","cityid":266,"displayorder":3},{"areaid":10995,
     * "areaname":"和顺","cityid":266,"displayorder":4},{"areaid":10996,"areaname":"昔阳","cityid":266,"displayorder":5},{"areaid":10997,"areaname":"寿阳","cityid":266,"displayorder":6},{"areaid":10998,
     * "areaname":"太谷","cityid":266,"displayorder":7},{"areaid":10999,"areaname":"祁县","cityid":266,"displayorder":8},{"areaid":11000,"areaname":"平遥","cityid":266,"displayorder":9},{"areaid":11001,
     * "areaname":"灵石","cityid":266,"displayorder":10},{"areaid":11002,"areaname":"介休","cityid":266,"displayorder":11}]},{"id":211,"city":"锦州","pinyin":"jinzhou","areas":[{"areaid":10552,
     * "areaname":"凌河","cityid":211,"displayorder":1},{"areaid":10553,"areaname":"古塔","cityid":211,"displayorder":2},{"areaid":10554,"areaname":"太和","cityid":211,"displayorder":3},{"areaid":10555,
     * "areaname":"松山新区","cityid":211,"displayorder":4},{"areaid":10556,"areaname":"经济开发区","cityid":211,"displayorder":5},{"areaid":10557,"areaname":"凌海","cityid":211,"displayorder":6},
     * {"areaid":10558,"areaname":"北镇","cityid":211,"displayorder":7},{"areaid":10559,"areaname":"黑山","cityid":211,"displayorder":8},{"areaid":10560,"areaname":"义县","cityid":211,
     * "displayorder":9}]},{"id":197,"city":"九江","pinyin":"jiujiang","areas":[{"areaid":10426,"areaname":"九江市区","cityid":197,"displayorder":1},{"areaid":10427,"areaname":"共青城","cityid":197,
     * "displayorder":2},{"areaid":10428,"areaname":"庐山","cityid":197,"displayorder":3},{"areaid":10429,"areaname":"瑞昌","cityid":197,"displayorder":4},{"areaid":10430,"areaname":"九江县","cityid":197,
     * "displayorder":5},{"areaid":10431,"areaname":"武宁","cityid":197,"displayorder":6},{"areaid":10432,"areaname":"修水","cityid":197,"displayorder":7},{"areaid":10433,"areaname":"永修","cityid":197,
     * "displayorder":8},{"areaid":10434,"areaname":"德安","cityid":197,"displayorder":9},{"areaid":10435,"areaname":"星子","cityid":197,"displayorder":10},{"areaid":10436,"areaname":"都昌","cityid":197,
     * "displayorder":11},{"areaid":10437,"areaname":"湖口","cityid":197,"displayorder":12},{"areaid":10438,"areaname":"彭泽","cityid":197,"displayorder":13},{"areaid":10439,"areaname":"浔阳区",
     * "cityid":197,"displayorder":14},{"areaid":10440,"areaname":"九江周边","cityid":197,"displayorder":15}]},{"id":72,"city":"酒泉","pinyin":"jiuquan","areas":[{"areaid":644,"areaname":"酒泉",
     * "cityid":72,"displayorder":1},{"areaid":645,"areaname":"玉门","cityid":72,"displayorder":2},{"areaid":646,"areaname":"敦煌","cityid":72,"displayorder":3},{"areaid":647,"areaname":"金塔",
     * "cityid":72,"displayorder":4},{"areaid":648,"areaname":"安西","cityid":72,"displayorder":5},{"areaid":649,"areaname":"肃北","cityid":72,"displayorder":6},{"areaid":650,"areaname":"阿克塞",
     * "cityid":72,"displayorder":7},{"areaid":651,"areaname":"酒泉周边","cityid":72,"displayorder":8}]},{"id":134,"city":"鸡西","pinyin":"jixi","areas":[{"areaid":1208,"areaname":"鸡冠","cityid":134,
     * "displayorder":1},{"areaid":1209,"areaname":"恒山","cityid":134,"displayorder":2},{"areaid":1210,"areaname":"滴道","cityid":134,"displayorder":3},{"areaid":1211,"areaname":"梨树","cityid":134,
     * "displayorder":4},{"areaid":1212,"areaname":"城子河","cityid":134,"displayorder":5},{"areaid":1213,"areaname":"麻山","cityid":134,"displayorder":6},{"areaid":1214,"areaname":"虎林","cityid":134,
     * "displayorder":7},{"areaid":1215,"areaname":"密山","cityid":134,"displayorder":8},{"areaid":1216,"areaname":"鸡东","cityid":134,"displayorder":9}]}],"K":[{"id":131,"city":"开封",
     * "pinyin":"kaifeng","areas":[{"areaid":1175,"areaname":"鼓楼","cityid":131,"displayorder":1},{"areaid":1176,"areaname":"龙亭","cityid":131,"displayorder":2},{"areaid":1177,"areaname":"顺河",
     * "cityid":131,"displayorder":3},{"areaid":1178,"areaname":"禹王台","cityid":131,"displayorder":4},{"areaid":1179,"areaname":"金明","cityid":131,"displayorder":5},{"areaid":1180,"areaname":"杞县",
     * "cityid":131,"displayorder":6},{"areaid":1181,"areaname":"通许","cityid":131,"displayorder":7},{"areaid":1182,"areaname":"尉氏","cityid":131,"displayorder":8},{"areaid":1183,"areaname":"开封县",
     * "cityid":131,"displayorder":9},{"areaid":1184,"areaname":"兰考","cityid":131,"displayorder":10}]},{"id":315,"city":"喀什","pinyin":"kashi","areas":[{"areaid":11479,"areaname":"喀什市","cityid":315,
     * "displayorder":1},{"areaid":11480,"areaname":"疏附","cityid":315,"displayorder":2},{"areaid":11481,"areaname":"疏勒","cityid":315,"displayorder":3},{"areaid":11482,"areaname":"英吉沙","cityid":315,
     * "displayorder":4},{"areaid":11483,"areaname":"泽普","cityid":315,"displayorder":5},{"areaid":11484,"areaname":"莎车","cityid":315,"displayorder":6},{"areaid":11485,"areaname":"叶城","cityid":315,
     * "displayorder":7},{"areaid":11486,"areaname":"麦盖提","cityid":315,"displayorder":8},{"areaid":11487,"areaname":"岳普湖","cityid":315,"displayorder":9},{"areaid":11488,"areaname":"伽师",
     * "cityid":315,"displayorder":10},{"areaid":11489,"areaname":"巴楚","cityid":315,"displayorder":11},{"areaid":11490,"areaname":"塔什库尔","cityid":315,"displayorder":12}]},{"id":310,"city":"克拉玛依",
     * "pinyin":"kelamayi","areas":[{"areaid":11452,"areaname":"克拉玛依区","cityid":310,"displayorder":1},{"areaid":11453,"areaname":"独山子","cityid":310,"displayorder":2},{"areaid":11454,
     * "areaname":"白碱滩","cityid":310,"displayorder":3},{"areaid":11455,"areaname":"乌尔禾","cityid":310,"displayorder":4}]},{"id":323,"city":"昆明","pinyin":"kunming","areas":[{"areaid":11542,
     * "areaname":"五华","cityid":323,"displayorder":1},{"areaid":11543,"areaname":"盘龙","cityid":323,"displayorder":2},{"areaid":11544,"areaname":"官渡","cityid":323,"displayorder":3},{"areaid":11545,
     * "areaname":"西山","cityid":323,"displayorder":4},{"areaid":11546,"areaname":"安宁","cityid":323,"displayorder":5},{"areaid":11547,"areaname":"呈贡","cityid":323,"displayorder":6},{"areaid":11548,
     * "areaname":"宜良","cityid":323,"displayorder":7},{"areaid":11549,"areaname":"石林","cityid":323,"displayorder":8},{"areaid":11550,"areaname":"晋宁","cityid":323,"displayorder":9},{"areaid":11551,
     * "areaname":"东川","cityid":323,"displayorder":10},{"areaid":11552,"areaname":"富民","cityid":323,"displayorder":11},{"areaid":11553,"areaname":"嵩明","cityid":323,"displayorder":12},
     * {"areaid":11554,"areaname":"寻甸","cityid":323,"displayorder":13},{"areaid":11555,"areaname":"禄劝","cityid":323,"displayorder":14}]}],"L":[{"id":89,"city":"来宾","pinyin":"laibin",
     * "areas":[{"areaid":776,"areaname":"兴宾","cityid":89,"displayorder":1},{"areaid":777,"areaname":"合山","cityid":89,"displayorder":2},{"areaid":778,"areaname":"象州","cityid":89,"displayorder":3},
     * {"areaid":779,"areaname":"武宣","cityid":89,"displayorder":4},{"areaid":780,"areaname":"忻城","cityid":89,"displayorder":5},{"areaid":781,"areaname":"金秀","cityid":89,"displayorder":6},
     * {"areaid":782,"areaname":"来宾周边","cityid":89,"displayorder":7}]},{"id":254,"city":"莱芜","pinyin":"laiwu","areas":[{"areaid":10888,"areaname":"莱城","cityid":254,"displayorder":1},
     * {"areaid":10889,"areaname":"钢城","cityid":254,"displayorder":2}]},{"id":112,"city":"廊坊","pinyin":"langfang","areas":[{"areaid":997,"areaname":"广阳","cityid":112,"displayorder":1},
     * {"areaid":998,"areaname":"安次","cityid":112,"displayorder":2},{"areaid":999,"areaname":"开发区","cityid":112,"displayorder":3},{"areaid":1000,"areaname":"三河","cityid":112,"displayorder":4},
     * {"areaid":1001,"areaname":"霸州","cityid":112,"displayorder":5},{"areaid":1002,"areaname":"固安","cityid":112,"displayorder":6},{"areaid":1003,"areaname":"燕郊","cityid":112,"displayorder":7},
     * {"areaid":1004,"areaname":"香河","cityid":112,"displayorder":8},{"areaid":1005,"areaname":"永清","cityid":112,"displayorder":9},{"areaid":1006,"areaname":"大厂","cityid":112,"displayorder":10},
     * {"areaid":1007,"areaname":"文安","cityid":112,"displayorder":11},{"areaid":1008,"areaname":"大城","cityid":112,"displayorder":12},{"areaid":1009,"areaname":"其他","cityid":112,
     * "displayorder":13}]},{"id":64,"city":"兰州","pinyin":"lanzhou","areas":[{"areaid":590,"areaname":"城关","cityid":64,"displayorder":1},{"areaid":591,"areaname":"七里河","cityid":64,
     * "displayorder":2},{"areaid":592,"areaname":"安宁","cityid":64,"displayorder":3},{"areaid":593,"areaname":"西固","cityid":64,"displayorder":4},{"areaid":594,"areaname":"红古新区","cityid":64,
     * "displayorder":5},{"areaid":595,"areaname":"兰州周边","cityid":64,"displayorder":6}]},{"id":302,"city":"拉萨","pinyin":"lasa","areas":[{"areaid":11368,"areaname":"城关","cityid":302,
     * "displayorder":1},{"areaid":11369,"areaname":"堆龙德庆","cityid":302,"displayorder":2},{"areaid":11370,"areaname":"达孜","cityid":302,"displayorder":3},{"areaid":11371,"areaname":"林周",
     * "cityid":302,"displayorder":4},{"areaid":11372,"areaname":"当雄","cityid":302,"displayorder":5},{"areaid":11373,"areaname":"墨竹工卡","cityid":302,"displayorder":6},{"areaid":11374,
     * "areaname":"曲水","cityid":302,"displayorder":7},{"areaid":11375,"areaname":"尼木","cityid":302,"displayorder":8}]},{"id":289,"city":"乐山","pinyin":"leshan","areas":[{"areaid":11252,
     * "areaname":"市中","cityid":289,"displayorder":1},{"areaid":11253,"areaname":"沙湾","cityid":289,"displayorder":2},{"areaid":11254,"areaname":"五通桥","cityid":289,"displayorder":3},{"areaid":11255,
     * "areaname":"金口河","cityid":289,"displayorder":4},{"areaid":11256,"areaname":"峨眉山","cityid":289,"displayorder":5},{"areaid":11257,"areaname":"犍为","cityid":289,"displayorder":6},
     * {"areaid":11258,"areaname":"井研","cityid":289,"displayorder":7},{"areaid":11259,"areaname":"夹江","cityid":289,"displayorder":8},{"areaid":11260,"areaname":"沐川","cityid":289,"displayorder":9},
     * {"areaid":11261,"areaname":"峨边","cityid":289,"displayorder":10},{"areaid":11262,"areaname":"马边","cityid":289,"displayorder":11}]},{"id":301,"city":"凉山","pinyin":"liangshan",
     * "areas":[{"areaid":11351,"areaname":"西昌","cityid":301,"displayorder":1},{"areaid":11352,"areaname":"盐源","cityid":301,"displayorder":2},{"areaid":11353,"areaname":"德昌","cityid":301,
     * "displayorder":3},{"areaid":11354,"areaname":"会理","cityid":301,"displayorder":4},{"areaid":11355,"areaname":"会东","cityid":301,"displayorder":5},{"areaid":11356,"areaname":"宁南","cityid":301,
     * "displayorder":6},{"areaid":11357,"areaname":"普格","cityid":301,"displayorder":7},{"areaid":11358,"areaname":"布拖","cityid":301,"displayorder":8},{"areaid":11359,"areaname":"金阳","cityid":301,
     * "displayorder":9},{"areaid":11360,"areaname":"昭觉","cityid":301,"displayorder":10},{"areaid":11361,"areaname":"喜德","cityid":301,"displayorder":11},{"areaid":11362,"areaname":"冕宁",
     * "cityid":301,"displayorder":12},{"areaid":11363,"areaname":"越西","cityid":301,"displayorder":13},{"areaid":11364,"areaname":"甘洛","cityid":301,"displayorder":14},{"areaid":11365,
     * "areaname":"美姑","cityid":301,"displayorder":15},{"areaid":11366,"areaname":"雷波","cityid":301,"displayorder":16},{"areaid":11367,"areaname":"木里","cityid":301,"displayorder":17}]},{"id":187,
     * "city":"连云港","pinyin":"lianyungang","areas":[{"areaid":10343,"areaname":"新浦","cityid":187,"displayorder":1},{"areaid":10344,"areaname":"连云","cityid":187,"displayorder":2},{"areaid":10345,
     * "areaname":"海州","cityid":187,"displayorder":3},{"areaid":10346,"areaname":"东海","cityid":187,"displayorder":4},{"areaid":10347,"areaname":"赣榆","cityid":187,"displayorder":5},{"areaid":10348,
     * "areaname":"灌云","cityid":187,"displayorder":6},{"areaid":10349,"areaname":"灌南","cityid":187,"displayorder":7},{"areaid":10350,"areaname":"其它","cityid":187,"displayorder":8}]},{"id":257,
     * "city":"聊城","pinyin":"liaocheng","areas":[{"areaid":10917,"areaname":"东昌府","cityid":257,"displayorder":1},{"areaid":10918,"areaname":"临清","cityid":257,"displayorder":2},{"areaid":10919,
     * "areaname":"阳谷","cityid":257,"displayorder":3},{"areaid":10920,"areaname":"莘县","cityid":257,"displayorder":4},{"areaid":10921,"areaname":"茌平","cityid":257,"displayorder":5},{"areaid":10922,
     * "areaname":"东阿","cityid":257,"displayorder":6},{"areaid":10923,"areaname":"冠县","cityid":257,"displayorder":7},{"areaid":10924,"areaname":"高唐","cityid":257,"displayorder":8}]},{"id":214,
     * "city":"辽阳","pinyin":"liaoyang","areas":[{"areaid":10576,"areaname":"白塔","cityid":214,"displayorder":1},{"areaid":10577,"areaname":"文圣","cityid":214,"displayorder":2},{"areaid":10578,
     * "areaname":"宏伟","cityid":214,"displayorder":3},{"areaid":10579,"areaname":"太子河","cityid":214,"displayorder":4},{"areaid":10580,"areaname":"弓长岭","cityid":214,"displayorder":5},
     * {"areaid":10581,"areaname":"灯塔","cityid":214,"displayorder":6},{"areaid":10582,"areaname":"辽阳县","cityid":214,"displayorder":7},{"areaid":10583,"areaname":"其它","cityid":214,
     * "displayorder":8}]},{"id":175,"city":"辽源","pinyin":"liaoyuan","areas":[{"areaid":10235,"areaname":"龙山","cityid":175,"displayorder":1},{"areaid":10236,"areaname":"西安区","cityid":175,
     * "displayorder":2},{"areaid":10237,"areaname":"东丰","cityid":175,"displayorder":3},{"areaid":10238,"areaname":"东辽","cityid":175,"displayorder":4},{"areaid":10239,"areaname":"其它","cityid":175,
     * "displayorder":5}]},{"id":328,"city":"丽江","pinyin":"lijiang","areas":[{"areaid":11590,"areaname":"古城","cityid":328,"displayorder":1},{"areaid":11591,"areaname":"永胜","cityid":328,
     * "displayorder":2},{"areaid":11592,"areaname":"华坪","cityid":328,"displayorder":3},{"areaid":11593,"areaname":"玉龙","cityid":328,"displayorder":4},{"areaid":11594,"areaname":"宁蒗","cityid":328,
     * "displayorder":5}]},{"id":329,"city":"临沧","pinyin":"lincang","areas":[{"areaid":11595,"areaname":"临翔","cityid":329,"displayorder":1},{"areaid":11596,"areaname":"凤庆","cityid":329,
     * "displayorder":2},{"areaid":11597,"areaname":"云县","cityid":329,"displayorder":3},{"areaid":11598,"areaname":"永德","cityid":329,"displayorder":4},{"areaid":11599,"areaname":"镇康","cityid":329,
     * "displayorder":5},{"areaid":11600,"areaname":"双江","cityid":329,"displayorder":6},{"areaid":11601,"areaname":"耿马","cityid":329,"displayorder":7},{"areaid":11602,"areaname":"沧源","cityid":329,
     * "displayorder":8}]},{"id":269,"city":"临汾","pinyin":"linfen","areas":[{"areaid":11030,"areaname":"尧都","cityid":269,"displayorder":1},{"areaid":11031,"areaname":"曲沃","cityid":269,
     * "displayorder":2},{"areaid":11032,"areaname":"翼城","cityid":269,"displayorder":3},{"areaid":11033,"areaname":"襄汾","cityid":269,"displayorder":4},{"areaid":11034,"areaname":"洪洞","cityid":269,
     * "displayorder":5},{"areaid":11035,"areaname":"古县","cityid":269,"displayorder":6},{"areaid":11036,"areaname":"安泽","cityid":269,"displayorder":7},{"areaid":11037,"areaname":"浮山","cityid":269,
     * "displayorder":8},{"areaid":11038,"areaname":"吉县","cityid":269,"displayorder":9},{"areaid":11039,"areaname":"乡宁","cityid":269,"displayorder":10},{"areaid":11040,"areaname":"大宁","cityid":269,
     * "displayorder":11},{"areaid":11041,"areaname":"隰县","cityid":269,"displayorder":12},{"areaid":11042,"areaname":"永和","cityid":269,"displayorder":13},{"areaid":11043,"areaname":"蒲县",
     * "cityid":269,"displayorder":14},{"areaid":11044,"areaname":"汾西","cityid":269,"displayorder":15},{"areaid":11045,"areaname":"侯马","cityid":269,"displayorder":16},{"areaid":11046,
     * "areaname":"霍州","cityid":269,"displayorder":17}]},{"id":76,"city":"临夏","pinyin":"linxia","areas":[{"areaid":676,"areaname":"临夏市","cityid":76,"displayorder":1},{"areaid":677,"areaname":"东乡",
     * "cityid":76,"displayorder":2},{"areaid":678,"areaname":"广河","cityid":76,"displayorder":3},{"areaid":679,"areaname":"和政","cityid":76,"displayorder":4},{"areaid":680,"areaname":"积石",
     * "cityid":76,"displayorder":5},{"areaid":681,"areaname":"康乐","cityid":76,"displayorder":6},{"areaid":682,"areaname":"临夏","cityid":76,"displayorder":7},{"areaid":683,"areaname":"永靖",
     * "cityid":76,"displayorder":8}]},{"id":255,"city":"临沂","pinyin":"linyi","areas":[{"areaid":10890,"areaname":"兰山","cityid":255,"displayorder":1},{"areaid":10891,"areaname":"罗庄","cityid":255,
     * "displayorder":2},{"areaid":10892,"areaname":"河东","cityid":255,"displayorder":3},{"areaid":10893,"areaname":"沂南","cityid":255,"displayorder":4},{"areaid":10894,"areaname":"郯城","cityid":255,
     * "displayorder":5},{"areaid":10895,"areaname":"沂水","cityid":255,"displayorder":6},{"areaid":10896,"areaname":"苍山","cityid":255,"displayorder":7},{"areaid":10897,"areaname":"费县","cityid":255,
     * "displayorder":8},{"areaid":10898,"areaname":"平邑","cityid":255,"displayorder":9},{"areaid":10899,"areaname":"莒南","cityid":255,"displayorder":10},{"areaid":10900,"areaname":"蒙阴","cityid":255,
     * "displayorder":11},{"areaid":10901,"areaname":"临沭","cityid":255,"displayorder":12},{"areaid":10902,"areaname":"北城新区","cityid":255,"displayorder":13},{"areaid":10903,"areaname":"开发区",
     * "cityid":255,"displayorder":14},{"areaid":10904,"areaname":"高新区","cityid":255,"displayorder":15},{"areaid":10905,"areaname":"临港","cityid":255,"displayorder":16}]},{"id":308,"city":"林芝",
     * "pinyin":"linzhi","areas":[{"areaid":11435,"areaname":"林芝县","cityid":308,"displayorder":1},{"areaid":11436,"areaname":"工布江达","cityid":308,"displayorder":2},{"areaid":11437,"areaname":"米林",
     * "cityid":308,"displayorder":3},{"areaid":11438,"areaname":"墨脱","cityid":308,"displayorder":4},{"areaid":11439,"areaname":"波密","cityid":308,"displayorder":5},{"areaid":11440,"areaname":"察隅",
     * "cityid":308,"displayorder":6},{"areaid":11441,"areaname":"朗县","cityid":308,"displayorder":7}]},{"id":35,"city":"丽水","pinyin":"lishui","areas":[{"areaid":348,"areaname":"莲都","cityid":35,
     * "displayorder":1},{"areaid":349,"areaname":"龙泉","cityid":35,"displayorder":2},{"areaid":350,"areaname":"青田","cityid":35,"displayorder":3},{"areaid":351,"areaname":"缙云","cityid":35,
     * "displayorder":4},{"areaid":352,"areaname":"遂昌","cityid":35,"displayorder":5},{"areaid":353,"areaname":"松阳","cityid":35,"displayorder":6},{"areaid":354,"areaname":"景宁","cityid":35,
     * "displayorder":7},{"areaid":355,"areaname":"云和","cityid":35,"displayorder":8},{"areaid":356,"areaname":"庆元","cityid":35,"displayorder":9}]},{"id":50,"city":"六安","pinyin":"liuan",
     * "areas":[{"areaid":469,"areaname":"六安市区","cityid":50,"displayorder":1},{"areaid":470,"areaname":"金安","cityid":50,"displayorder":2},{"areaid":471,"areaname":"裕安","cityid":50,
     * "displayorder":3},{"areaid":472,"areaname":"寿县","cityid":50,"displayorder":4},{"areaid":473,"areaname":"舒城","cityid":50,"displayorder":5},{"areaid":474,"areaname":"霍邱县","cityid":50,
     * "displayorder":6},{"areaid":475,"areaname":"金寨","cityid":50,"displayorder":7},{"areaid":476,"areaname":"霍山","cityid":50,"displayorder":8}]},{"id":93,"city":"六盘水","pinyin":"liupanshui",
     * "areas":[{"areaid":809,"areaname":"钟山","cityid":93,"displayorder":1},{"areaid":810,"areaname":"六枝特区","cityid":93,"displayorder":2},{"areaid":811,"areaname":"盘县","cityid":93,
     * "displayorder":3},{"areaid":812,"areaname":"水城","cityid":93,"displayorder":4},{"areaid":813,"areaname":"六盘水周边","cityid":93,"displayorder":5}]},{"id":79,"city":"柳州","pinyin":"liuzhou",
     * "areas":[{"areaid":700,"areaname":"城中","cityid":79,"displayorder":1},{"areaid":701,"areaname":"鱼峰","cityid":79,"displayorder":2},{"areaid":702,"areaname":"柳北","cityid":79,"displayorder":3},
     * {"areaid":703,"areaname":"柳南","cityid":79,"displayorder":4},{"areaid":704,"areaname":"柳城","cityid":79,"displayorder":5},{"areaid":705,"areaname":"柳江","cityid":79,"displayorder":6},
     * {"areaid":706,"areaname":"鹿寨","cityid":79,"displayorder":7},{"areaid":707,"areaname":"融安","cityid":79,"displayorder":8},{"areaid":708,"areaname":"融水","cityid":79,"displayorder":9},
     * {"areaid":709,"areaname":"三江","cityid":79,"displayorder":10}]},{"id":75,"city":"陇南","pinyin":"longnan","areas":[{"areaid":661,"areaname":"武都","cityid":75,"displayorder":1},{"areaid":662,
     * "areaname":"成县","cityid":75,"displayorder":2},{"areaid":663,"areaname":"徽县","cityid":75,"displayorder":3},{"areaid":664,"areaname":"两当","cityid":75,"displayorder":4},{"areaid":665,
     * "areaname":"宕昌","cityid":75,"displayorder":5},{"areaid":666,"areaname":"文县","cityid":75,"displayorder":6},{"areaid":667,"areaname":"陇南周边","cityid":75,"displayorder":7}]},{"id":62,
     * "city":"龙岩","pinyin":"longyan","areas":[{"areaid":574,"areaname":"新罗","cityid":62,"displayorder":1},{"areaid":575,"areaname":"漳平","cityid":62,"displayorder":2},{"areaid":576,"areaname":"长汀",
     * "cityid":62,"displayorder":3},{"areaid":577,"areaname":"永定","cityid":62,"displayorder":4},{"areaid":578,"areaname":"上杭","cityid":62,"displayorder":5},{"areaid":579,"areaname":"武平",
     * "cityid":62,"displayorder":6},{"areaid":580,"areaname":"连城","cityid":62,"displayorder":7}]},{"id":170,"city":"娄底","pinyin":"loudi","areas":[{"areaid":10186,"areaname":"娄星","cityid":170,
     * "displayorder":1},{"areaid":10187,"areaname":"冷水江","cityid":170,"displayorder":2},{"areaid":10188,"areaname":"涟源","cityid":170,"displayorder":3},{"areaid":10189,"areaname":"双峰","cityid":170,
     * "displayorder":4},{"areaid":10190,"areaname":"新化","cityid":170,"displayorder":5},{"areaid":10191,"areaname":"娄底周边","cityid":170,"displayorder":6}]},{"id":117,"city":"洛阳","pinyin":"luoyang",
     * "areas":[{"areaid":1053,"areaname":"涧西","cityid":117,"displayorder":1},{"areaid":1054,"areaname":"西工","cityid":117,"displayorder":2},{"areaid":1055,"areaname":"老城","cityid":117,
     * "displayorder":3},{"areaid":1056,"areaname":"瀍河","cityid":117,"displayorder":4},{"areaid":1057,"areaname":"洛龙","cityid":117,"displayorder":5},{"areaid":1058,"areaname":"吉利","cityid":117,
     * "displayorder":6},{"areaid":1059,"areaname":"伊滨","cityid":117,"displayorder":7},{"areaid":1060,"areaname":"洛阳周边","cityid":117,"displayorder":8}]},{"id":339,"city":"吕梁","pinyin":"lvliang"}],
     * "M":[{"id":42,"city":"马鞍山","pinyin":"maanshan","areas":[{"areaid":412,"areaname":"花山","cityid":42,"displayorder":1},{"areaid":413,"areaname":"雨山","cityid":42,"displayorder":2},{"areaid":414,
     * "areaname":"金家庄","cityid":42,"displayorder":3},{"areaid":415,"areaname":"当涂","cityid":42,"displayorder":4},{"areaid":416,"areaname":"博望区","cityid":42,"displayorder":5},{"areaid":417,
     * "areaname":"其它","cityid":42,"displayorder":6}]},{"id":13,"city":"茂名","pinyin":"maoming","areas":[{"areaid":155,"areaname":"茂南","cityid":13,"displayorder":1},{"areaid":156,"areaname":"茂港",
     * "cityid":13,"displayorder":2},{"areaid":157,"areaname":"高州","cityid":13,"displayorder":3},{"areaid":158,"areaname":"化州","cityid":13,"displayorder":4},{"areaid":159,"areaname":"信宜",
     * "cityid":13,"displayorder":5},{"areaid":160,"areaname":"电白","cityid":13,"displayorder":6},{"areaid":161,"areaname":"其他","cityid":13,"displayorder":7}]},{"id":297,"city":"眉山",
     * "pinyin":"meishan","areas":[{"areaid":11306,"areaname":"东坡","cityid":297,"displayorder":1},{"areaid":11307,"areaname":"仁寿","cityid":297,"displayorder":2},{"areaid":11308,"areaname":"彭山",
     * "cityid":297,"displayorder":3},{"areaid":11309,"areaname":"洪雅","cityid":297,"displayorder":4},{"areaid":11310,"areaname":"丹棱","cityid":297,"displayorder":5},{"areaid":11311,"areaname":"青神",
     * "cityid":297,"displayorder":6}]},{"id":16,"city":"梅州","pinyin":"meizhou","areas":[{"areaid":179,"areaname":"梅江","cityid":16,"displayorder":1},{"areaid":180,"areaname":"兴宁","cityid":16,
     * "displayorder":2},{"areaid":181,"areaname":"梅县","cityid":16,"displayorder":3},{"areaid":182,"areaname":"大埔","cityid":16,"displayorder":4},{"areaid":183,"areaname":"丰顺","cityid":16,
     * "displayorder":5},{"areaid":184,"areaname":"五华","cityid":16,"displayorder":6},{"areaid":185,"areaname":"平远","cityid":16,"displayorder":7},{"areaid":186,"areaname":"蕉岭","cityid":16,
     * "displayorder":8},{"areaid":187,"areaname":"梅州周边","cityid":16,"displayorder":9}]},{"id":283,"city":"绵阳","pinyin":"mianyang","areas":[{"areaid":11208,"areaname":"涪城","cityid":283,
     * "displayorder":1},{"areaid":11209,"areaname":"游仙","cityid":283,"displayorder":2},{"areaid":11210,"areaname":"江油","cityid":283,"displayorder":3},{"areaid":11211,"areaname":"三台","cityid":283,
     * "displayorder":4},{"areaid":11212,"areaname":"盐亭","cityid":283,"displayorder":5},{"areaid":11213,"areaname":"安县","cityid":283,"displayorder":6},{"areaid":11214,"areaname":"梓潼","cityid":283,
     * "displayorder":7},{"areaid":11215,"areaname":"平武","cityid":283,"displayorder":8},{"areaid":11216,"areaname":"北川","cityid":283,"displayorder":9},{"areaid":11217,"areaname":"高新","cityid":283,
     * "displayorder":10},{"areaid":11218,"areaname":"经开","cityid":283,"displayorder":11},{"areaid":11219,"areaname":"科创园","cityid":283,"displayorder":12}]},{"id":141,"city":"牡丹江",
     * "pinyin":"mudanjiang","areas":[{"areaid":1263,"areaname":"江东","cityid":141,"displayorder":1},{"areaid":1264,"areaname":"安西","cityid":141,"displayorder":2},{"areaid":1265,"areaname":"安爱",
     * "cityid":141,"displayorder":3},{"areaid":1266,"areaname":"民阳","cityid":141,"displayorder":4},{"areaid":1267,"areaname":"明绥","cityid":141,"displayorder":5},{"areaid":1268,"areaname":"芬河",
     * "cityid":141,"displayorder":6},{"areaid":1269,"areaname":"宁安","cityid":141,"displayorder":7},{"areaid":1270,"areaname":"海林","cityid":141,"displayorder":8},{"areaid":1271,"areaname":"穆棱",
     * "cityid":141,"displayorder":9},{"areaid":1272,"areaname":"东宁县","cityid":141,"displayorder":10},{"areaid":1273,"areaname":"林口县","cityid":141,"displayorder":11},{"areaid":1274,"areaname":"其他",
     * "cityid":141,"displayorder":12}]}],"N":[{"id":194,"city":"南昌","pinyin":"nanchang","areas":[{"areaid":10403,"areaname":"东湖","cityid":194,"displayorder":1},{"areaid":10404,"areaname":"西湖",
     * "cityid":194,"displayorder":2},{"areaid":10405,"areaname":"新建县","cityid":194,"displayorder":3},{"areaid":10406,"areaname":"南昌县","cityid":194,"displayorder":4},{"areaid":10407,
     * "areaname":"青云谱","cityid":194,"displayorder":5},{"areaid":10408,"areaname":"湾里","cityid":194,"displayorder":6},{"areaid":10409,"areaname":"青山湖","cityid":194,"displayorder":7},
     * {"areaid":10410,"areaname":"红谷滩新区","cityid":194,"displayorder":8},{"areaid":10411,"areaname":"高新开发区","cityid":194,"displayorder":9},{"areaid":10412,"areaname":"象湖","cityid":194,
     * "displayorder":10},{"areaid":10413,"areaname":"小蓝经济开发区","cityid":194,"displayorder":11},{"areaid":10414,"areaname":"昌北经济开发区","cityid":194,"displayorder":12},{"areaid":10415,
     * "areaname":"南昌周边","cityid":194,"displayorder":13}]},{"id":285,"city":"南充","pinyin":"nanchong","areas":[{"areaid":11226,"areaname":"顺庆","cityid":285,"displayorder":1},{"areaid":11227,
     * "areaname":"高坪","cityid":285,"displayorder":2},{"areaid":11228,"areaname":"嘉陵","cityid":285,"displayorder":3},{"areaid":11229,"areaname":"阆中","cityid":285,"displayorder":4},{"areaid":11230,
     * "areaname":"南部","cityid":285,"displayorder":5},{"areaid":11231,"areaname":"营山","cityid":285,"displayorder":6},{"areaid":11232,"areaname":"蓬安","cityid":285,"displayorder":7},{"areaid":11233,
     * "areaname":"仪陇","cityid":285,"displayorder":8},{"areaid":11234,"areaname":"西充","cityid":285,"displayorder":9}]},{"id":181,"city":"南京","pinyin":"nanjing","areas":[{"areaid":10278,
     * "areaname":"玄武","cityid":181,"displayorder":1},{"areaid":10279,"areaname":"鼓楼","cityid":181,"displayorder":2},{"areaid":10280,"areaname":"建邺","cityid":181,"displayorder":3},{"areaid":10281,
     * "areaname":"白下","cityid":181,"displayorder":4},{"areaid":10282,"areaname":"秦淮","cityid":181,"displayorder":5},{"areaid":10283,"areaname":"下关","cityid":181,"displayorder":6},{"areaid":10284,
     * "areaname":"雨花台","cityid":181,"displayorder":7},{"areaid":10285,"areaname":"浦口","cityid":181,"displayorder":8},{"areaid":10286,"areaname":"栖霞","cityid":181,"displayorder":9},{"areaid":10287,
     * "areaname":"江宁","cityid":181,"displayorder":10},{"areaid":10288,"areaname":"六合","cityid":181,"displayorder":11},{"areaid":10289,"areaname":"高淳","cityid":181,"displayorder":12},
     * {"areaid":10290,"areaname":"溧水","cityid":181,"displayorder":13},{"areaid":10291,"areaname":"大厂","cityid":181,"displayorder":14},{"areaid":10292,"areaname":"南京周边","cityid":181,
     * "displayorder":15}]},{"id":78,"city":"南宁","pinyin":"nanning","areas":[{"areaid":693,"areaname":"青秀","cityid":78,"displayorder":1},{"areaid":694,"areaname":"兴宁","cityid":78,"displayorder":2},
     * {"areaid":695,"areaname":"江南","cityid":78,"displayorder":3},{"areaid":696,"areaname":"西乡塘","cityid":78,"displayorder":4},{"areaid":697,"areaname":"邕宁","cityid":78,"displayorder":5},
     * {"areaid":698,"areaname":"良庆","cityid":78,"displayorder":6},{"areaid":699,"areaname":"南宁周边","cityid":78,"displayorder":7}]},{"id":61,"city":"南平","pinyin":"nanping","areas":[{"areaid":569,
     * "areaname":"延平","cityid":61,"displayorder":1},{"areaid":570,"areaname":"邵武","cityid":61,"displayorder":2},{"areaid":571,"areaname":"武夷山","cityid":61,"displayorder":3},{"areaid":572,
     * "areaname":"建瓯","cityid":61,"displayorder":4},{"areaid":573,"areaname":"建阳顺昌南平周边","cityid":61,"displayorder":5}]},{"id":186,"city":"南通","pinyin":"nantong","areas":[{"areaid":10333,
     * "areaname":"崇川","cityid":186,"displayorder":1},{"areaid":10334,"areaname":"如皋","cityid":186,"displayorder":2},{"areaid":10335,"areaname":"通州","cityid":186,"displayorder":3},{"areaid":10336,
     * "areaname":"海门","cityid":186,"displayorder":4},{"areaid":10337,"areaname":"港闸","cityid":186,"displayorder":5},{"areaid":10338,"areaname":"启东","cityid":186,"displayorder":6},{"areaid":10339,
     * "areaname":"海安","cityid":186,"displayorder":7},{"areaid":10340,"areaname":"如东","cityid":186,"displayorder":8},{"areaid":10341,"areaname":"开发区","cityid":186,"displayorder":9},{"areaid":10342,
     * "areaname":"南通周边","cityid":186,"displayorder":10}]},{"id":127,"city":"南阳","pinyin":"nanyang","areas":[{"areaid":1132,"areaname":"卧龙","cityid":127,"displayorder":1},{"areaid":1133,
     * "areaname":"宛城","cityid":127,"displayorder":2},{"areaid":1134,"areaname":"油田","cityid":127,"displayorder":3},{"areaid":1135,"areaname":"镇平","cityid":127,"displayorder":4},{"areaid":1136,
     * "areaname":"内乡","cityid":127,"displayorder":5},{"areaid":1137,"areaname":"西峡","cityid":127,"displayorder":6},{"areaid":1138,"areaname":"淅川","cityid":127,"displayorder":7},{"areaid":1139,
     * "areaname":"邓州","cityid":127,"displayorder":8},{"areaid":1140,"areaname":"新野","cityid":127,"displayorder":9},{"areaid":1141,"areaname":"唐河","cityid":127,"displayorder":10},{"areaid":1142,
     * "areaname":"桐柏","cityid":127,"displayorder":11},{"areaid":1143,"areaname":"社旗","cityid":127,"displayorder":12},{"areaid":1144,"areaname":"方城","cityid":127,"displayorder":13},{"areaid":1145,
     * "areaname":"南召","cityid":127,"displayorder":14},{"areaid":1146,"areaname":"其他","cityid":127,"displayorder":15}]},{"id":306,"city":"那曲","pinyin":"naqu","areas":[{"areaid":11417,
     * "areaname":"那曲县","cityid":306,"displayorder":1},{"areaid":11418,"areaname":"嘉黎","cityid":306,"displayorder":2},{"areaid":11419,"areaname":"比如","cityid":306,"displayorder":3},{"areaid":11420,
     * "areaname":"聂荣","cityid":306,"displayorder":4},{"areaid":11421,"areaname":"安多","cityid":306,"displayorder":5},{"areaid":11422,"areaname":"申扎","cityid":306,"displayorder":6},{"areaid":11423,
     * "areaname":"索县","cityid":306,"displayorder":7},{"areaid":11424,"areaname":"班戈","cityid":306,"displayorder":8},{"areaid":11425,"areaname":"巴青","cityid":306,"displayorder":9},{"areaid":11426,
     * "areaname":"尼玛","cityid":306,"displayorder":10},{"areaid":11427,"areaname":"双湖","cityid":306,"displayorder":11}]},{"id":288,"city":"内江","pinyin":"neijiang","areas":[{"areaid":11247,
     * "areaname":"市中","cityid":288,"displayorder":1},{"areaid":11248,"areaname":"东兴","cityid":288,"displayorder":2},{"areaid":11249,"areaname":"威远","cityid":288,"displayorder":3},{"areaid":11250,
     * "areaname":"资中","cityid":288,"displayorder":4},{"areaid":11251,"areaname":"隆昌","cityid":288,"displayorder":5}]},{"id":28,"city":"宁波","pinyin":"ningbo","areas":[{"areaid":289,"areaname":"海曙",
     * "cityid":28,"displayorder":1},{"areaid":290,"areaname":"江东","cityid":28,"displayorder":2},{"areaid":291,"areaname":"江北","cityid":28,"displayorder":3},{"areaid":292,"areaname":"鄞州",
     * "cityid":28,"displayorder":4},{"areaid":293,"areaname":"北仑","cityid":28,"displayorder":5},{"areaid":294,"areaname":"镇海","cityid":28,"displayorder":6},{"areaid":295,"areaname":"高新区",
     * "cityid":28,"displayorder":7},{"areaid":296,"areaname":"慈溪","cityid":28,"displayorder":8},{"areaid":297,"areaname":"余姚","cityid":28,"displayorder":9},{"areaid":298,"areaname":"奉化",
     * "cityid":28,"displayorder":10},{"areaid":299,"areaname":"宁海","cityid":28,"displayorder":11},{"areaid":300,"areaname":"象山","cityid":28,"displayorder":12},{"areaid":301,"areaname":"宁波周边",
     * "cityid":28,"displayorder":13}]},{"id":63,"city":"宁德","pinyin":"ningde","areas":[{"areaid":581,"areaname":"蕉城","cityid":63,"displayorder":1},{"areaid":582,"areaname":"福安","cityid":63,
     * "displayorder":2},{"areaid":583,"areaname":"福鼎","cityid":63,"displayorder":3},{"areaid":584,"areaname":"霞浦","cityid":63,"displayorder":4},{"areaid":585,"areaname":"柘荣","cityid":63,
     * "displayorder":5},{"areaid":586,"areaname":"寿宁","cityid":63,"displayorder":6},{"areaid":587,"areaname":"古田","cityid":63,"displayorder":7},{"areaid":588,"areaname":"屏南","cityid":63,
     * "displayorder":8},{"areaid":589,"areaname":"周宁","cityid":63,"displayorder":9}]},{"id":336,"city":"怒江","pinyin":"nujiang","areas":[{"areaid":11654,"areaname":"泸水","cityid":336,
     * "displayorder":1},{"areaid":11655,"areaname":"福贡","cityid":336,"displayorder":2},{"areaid":11656,"areaname":"贡山","cityid":336,"displayorder":3},{"areaid":11657,"areaname":"兰坪","cityid":336,
     * "displayorder":4}]}],"P":[{"id":215,"city":"盘锦","pinyin":"panjin","areas":[{"areaid":10584,"areaname":"兴隆台","cityid":215,"displayorder":1},{"areaid":10585,"areaname":"双台子","cityid":215,
     * "displayorder":2},{"areaid":10586,"areaname":"盘山","cityid":215,"displayorder":3},{"areaid":10587,"areaname":"大洼","cityid":215,"displayorder":4},{"areaid":10588,"areaname":"其它","cityid":215,
     * "displayorder":5}]},{"id":293,"city":"攀枝花","pinyin":"panzhihua","areas":[{"areaid":11286,"areaname":"东区","cityid":293,"displayorder":1},{"areaid":11287,"areaname":"西区","cityid":293,
     * "displayorder":2},{"areaid":11288,"areaname":"仁和","cityid":293,"displayorder":3},{"areaid":11289,"areaname":"米易","cityid":293,"displayorder":4},{"areaid":11290,"areaname":"盐边","cityid":293,
     * "displayorder":5}]},{"id":129,"city":"平顶山","pinyin":"pingdingshan","areas":[{"areaid":1157,"areaname":"新华","cityid":129,"displayorder":1},{"areaid":1158,"areaname":"卫东","cityid":129,
     * "displayorder":2},{"areaid":1159,"areaname":"湛河","cityid":129,"displayorder":3},{"areaid":1160,"areaname":"石龙","cityid":129,"displayorder":4},{"areaid":1161,"areaname":"汝州","cityid":129,
     * "displayorder":5},{"areaid":1162,"areaname":"舞钢","cityid":129,"displayorder":6},{"areaid":1163,"areaname":"平顶山周边","cityid":129,"displayorder":7}]},{"id":71,"city":"平凉","pinyin":"pingliang",
     * "areas":[{"areaid":637,"areaname":"崆峒","cityid":71,"displayorder":1},{"areaid":638,"areaname":"庄浪","cityid":71,"displayorder":2},{"areaid":639,"areaname":"静宁","cityid":71,"displayorder":3},
     * {"areaid":640,"areaname":"泾川","cityid":71,"displayorder":4},{"areaid":641,"areaname":"灵台","cityid":71,"displayorder":5},{"areaid":642,"areaname":"崇信","cityid":71,"displayorder":6},
     * {"areaid":643,"areaname":"华亭","cityid":71,"displayorder":7}]},{"id":196,"city":"萍乡","pinyin":"pingxiang","areas":[{"areaid":10420,"areaname":"安源","cityid":196,"displayorder":1},
     * {"areaid":10421,"areaname":"芦溪","cityid":196,"displayorder":2},{"areaid":10422,"areaname":"湘东","cityid":196,"displayorder":3},{"areaid":10423,"areaname":"上栗","cityid":196,"displayorder":4},
     * {"areaid":10424,"areaname":"莲花","cityid":196,"displayorder":5},{"areaid":10425,"areaname":"其它","cityid":196,"displayorder":6}]},{"id":57,"city":"莆田","pinyin":"putian","areas":[{"areaid":522,
     * "areaname":"城厢","cityid":57,"displayorder":1},{"areaid":523,"areaname":"荔城","cityid":57,"displayorder":2},{"areaid":524,"areaname":"秀屿","cityid":57,"displayorder":3},{"areaid":525,
     * "areaname":"涵江","cityid":57,"displayorder":4},{"areaid":526,"areaname":"仙游","cityid":57,"displayorder":5},{"areaid":527,"areaname":"湄洲岛","cityid":57,"displayorder":6},{"areaid":528,
     * "areaname":"南日岛","cityid":57,"displayorder":7},{"areaid":529,"areaname":"黄瓜岛","cityid":57,"displayorder":8}]}],"Q":[{"id":105,"city":"迁安","pinyin":"qianan"},{"id":97,"city":"黔东南",
     * "pinyin":"qiandongnan","areas":[{"areaid":838,"areaname":"凯里","cityid":97,"displayorder":1},{"areaid":839,"areaname":"黄平","cityid":97,"displayorder":2},{"areaid":840,"areaname":"镇远",
     * "cityid":97,"displayorder":3},{"areaid":841,"areaname":"三穗","cityid":97,"displayorder":4},{"areaid":842,"areaname":"台江","cityid":97,"displayorder":5},{"areaid":843,"areaname":"剑河",
     * "cityid":97,"displayorder":6},{"areaid":844,"areaname":"岑巩","cityid":97,"displayorder":7},{"areaid":845,"areaname":"锦屏","cityid":97,"displayorder":8},{"areaid":846,"areaname":"从江",
     * "cityid":97,"displayorder":9},{"areaid":847,"areaname":"榕江","cityid":97,"displayorder":10},{"areaid":848,"areaname":"黎平","cityid":97,"displayorder":11},{"areaid":849,"areaname":"丹寨",
     * "cityid":97,"displayorder":12},{"areaid":850,"areaname":"天柱","cityid":97,"displayorder":13},{"areaid":851,"areaname":"麻江","cityid":97,"displayorder":14},{"areaid":852,"areaname":"雷山",
     * "cityid":97,"displayorder":15},{"areaid":853,"areaname":"施秉","cityid":97,"displayorder":16},{"areaid":854,"areaname":"黔东南周边","cityid":97,"displayorder":17}]},{"id":96,"city":"黔南",
     * "pinyin":"qiannan","areas":[{"areaid":825,"areaname":"都匀","cityid":96,"displayorder":1},{"areaid":826,"areaname":"福泉","cityid":96,"displayorder":2},{"areaid":827,"areaname":"荔波","cityid":96,
     * "displayorder":3},{"areaid":828,"areaname":"贵定","cityid":96,"displayorder":4},{"areaid":829,"areaname":"独山","cityid":96,"displayorder":5},{"areaid":830,"areaname":"瓮安","cityid":96,
     * "displayorder":6},{"areaid":831,"areaname":"平塘","cityid":96,"displayorder":7},{"areaid":832,"areaname":"罗甸","cityid":96,"displayorder":8},{"areaid":833,"areaname":"长顺","cityid":96,
     * "displayorder":9},{"areaid":834,"areaname":"龙里","cityid":96,"displayorder":10},{"areaid":835,"areaname":"惠水","cityid":96,"displayorder":11},{"areaid":836,"areaname":"三都","cityid":96,
     * "displayorder":12},{"areaid":837,"areaname":"黔南周边","cityid":96,"displayorder":13}]},{"id":98,"city":"黔西南","pinyin":"qianxinan","areas":[{"areaid":855,"areaname":"兴义","cityid":98,
     * "displayorder":1},{"areaid":856,"areaname":"兴仁","cityid":98,"displayorder":2},{"areaid":857,"areaname":"晴隆","cityid":98,"displayorder":3},{"areaid":858,"areaname":"贞丰","cityid":98,
     * "displayorder":4},{"areaid":859,"areaname":"望谟","cityid":98,"displayorder":5},{"areaid":860,"areaname":"册亨","cityid":98,"displayorder":6},{"areaid":861,"areaname":"安龙","cityid":98,
     * "displayorder":7},{"areaid":862,"areaname":"普安","cityid":98,"displayorder":8},{"areaid":863,"areaname":"黔西南周边","cityid":98,"displayorder":9}]},{"id":251,"city":"青岛","pinyin":"qingdao",
     * "areas":[{"areaid":10861,"areaname":"市南","cityid":251,"displayorder":1},{"areaid":10862,"areaname":"市北","cityid":251,"displayorder":2},{"areaid":10863,"areaname":"四方","cityid":251,
     * "displayorder":3},{"areaid":10864,"areaname":"城阳","cityid":251,"displayorder":4},{"areaid":10865,"areaname":"李沧","cityid":251,"displayorder":5},{"areaid":10866,"areaname":"崂山","cityid":251,
     * "displayorder":6},{"areaid":10867,"areaname":"黄岛","cityid":251,"displayorder":7},{"areaid":10868,"areaname":"即墨","cityid":251,"displayorder":8},{"areaid":10869,"areaname":"胶州","cityid":251,
     * "displayorder":9},{"areaid":10870,"areaname":"胶南","cityid":251,"displayorder":10},{"areaid":10871,"areaname":"平度","cityid":251,"displayorder":11},{"areaid":10872,"areaname":"莱西",
     * "cityid":251,"displayorder":12}]},{"id":73,"city":"庆阳","pinyin":"qingyang","areas":[{"areaid":652,"areaname":"西峰","cityid":73,"displayorder":1},{"areaid":653,"areaname":"环县","cityid":73,
     * "displayorder":2},{"areaid":654,"areaname":"华池","cityid":73,"displayorder":3},{"areaid":655,"areaname":"庆城","cityid":73,"displayorder":4},{"areaid":656,"areaname":"镇原","cityid":73,
     * "displayorder":5},{"areaid":657,"areaname":"宁县","cityid":73,"displayorder":6},{"areaid":658,"areaname":"正宁","cityid":73,"displayorder":7},{"areaid":659,"areaname":"合水","cityid":73,
     * "displayorder":8},{"areaid":660,"areaname":"庆阳周边","cityid":73,"displayorder":9}]},{"id":20,"city":"清远","pinyin":"qingyuan","areas":[{"areaid":207,"areaname":"清城","cityid":20,
     * "displayorder":1},{"areaid":208,"areaname":"清新","cityid":20,"displayorder":2},{"areaid":209,"areaname":"英德","cityid":20,"displayorder":3},{"areaid":210,"areaname":"连州","cityid":20,
     * "displayorder":4},{"areaid":211,"areaname":"佛冈","cityid":20,"displayorder":5},{"areaid":212,"areaname":"阳山","cityid":20,"displayorder":6},{"areaid":213,"areaname":"连南","cityid":20,
     * "displayorder":7},{"areaid":214,"areaname":"连山","cityid":20,"displayorder":8}]},{"id":114,"city":"秦皇岛","pinyin":"qinhuangdao","areas":[{"areaid":1010,"areaname":"海港","cityid":114,
     * "displayorder":1},{"areaid":1011,"areaname":"北戴河","cityid":114,"displayorder":2},{"areaid":1012,"areaname":"山海关","cityid":114,"displayorder":3},{"areaid":1013,"areaname":"昌黎","cityid":114,
     * "displayorder":4},{"areaid":1014,"areaname":"抚宁","cityid":114,"displayorder":5},{"areaid":1015,"areaname":"卢龙","cityid":114,"displayorder":6},{"areaid":1016,"areaname":"青龙开发区","cityid":114,
     * "displayorder":7},{"areaid":1017,"areaname":"其他","cityid":114,"displayorder":8}]},{"id":83,"city":"钦州","pinyin":"qinzhou","areas":[{"areaid":734,"areaname":"市区","cityid":83,
     * "displayorder":1},{"areaid":735,"areaname":"钦南","cityid":83,"displayorder":2},{"areaid":736,"areaname":"钦北","cityid":83,"displayorder":3},{"areaid":737,"areaname":"灵山","cityid":83,
     * "displayorder":4},{"areaid":738,"areaname":"浦北","cityid":83,"displayorder":5}]},{"id":133,"city":"齐齐哈尔","pinyin":"qiqihaer","areas":[{"areaid":1198,"areaname":"龙沙","cityid":133,
     * "displayorder":1},{"areaid":1199,"areaname":"建华","cityid":133,"displayorder":2},{"areaid":1200,"areaname":"铁锋","cityid":133,"displayorder":3},{"areaid":1201,"areaname":"昂昂溪","cityid":133,
     * "displayorder":4},{"areaid":1202,"areaname":"富拉尔基","cityid":133,"displayorder":5},{"areaid":1203,"areaname":"碾子山","cityid":133,"displayorder":6},{"areaid":1204,"areaname":"梅里斯","cityid":133,
     * "displayorder":7},{"areaid":1205,"areaname":"讷河","cityid":133,"displayorder":8},{"areaid":1206,"areaname":"泰来","cityid":133,"displayorder":9},{"areaid":1207,"areaname":"其它","cityid":133,
     * "displayorder":10}]},{"id":140,"city":"七台河","pinyin":"qitaihe","areas":[{"areaid":1258,"areaname":"桃山","cityid":140,"displayorder":1},{"areaid":1259,"areaname":"新兴","cityid":140,
     * "displayorder":2},{"areaid":1260,"areaname":"茄子河","cityid":140,"displayorder":3},{"areaid":1261,"areaname":"勃利","cityid":140,"displayorder":4},{"areaid":1262,"areaname":"七台河周边","cityid":140,
     * "displayorder":5}]},{"id":59,"city":"泉州","pinyin":"quanzhou","areas":[{"areaid":543,"areaname":"洛江","cityid":59,"displayorder":1},{"areaid":544,"areaname":"泉港","cityid":59,"displayorder":2},
     * {"areaid":545,"areaname":"石狮","cityid":59,"displayorder":3},{"areaid":546,"areaname":"晋江","cityid":59,"displayorder":4},{"areaid":547,"areaname":"南安","cityid":59,"displayorder":5},
     * {"areaid":548,"areaname":"惠安","cityid":59,"displayorder":6},{"areaid":549,"areaname":"安溪","cityid":59,"displayorder":7},{"areaid":550,"areaname":"永春","cityid":59,"displayorder":8},
     * {"areaid":551,"areaname":"德化","cityid":59,"displayorder":9},{"areaid":552,"areaname":"鲤城","cityid":59,"displayorder":10},{"areaid":553,"areaname":"丰泽","cityid":59,"displayorder":11},
     * {"areaid":554,"areaname":"金门县","cityid":59,"displayorder":12},{"areaid":555,"areaname":"泉州周边","cityid":59,"displayorder":13}]},{"id":324,"city":"曲靖","pinyin":"qujing",
     * "areas":[{"areaid":11556,"areaname":"麒麟","cityid":324,"displayorder":1},{"areaid":11557,"areaname":"宣威","cityid":324,"displayorder":2},{"areaid":11558,"areaname":"马龙","cityid":324,
     * "displayorder":3},{"areaid":11559,"areaname":"陆良","cityid":324,"displayorder":4},{"areaid":11560,"areaname":"师宗","cityid":324,"displayorder":5},{"areaid":11561,"areaname":"罗平","cityid":324,
     * "displayorder":6},{"areaid":11562,"areaname":"富源","cityid":324,"displayorder":7},{"areaid":11563,"areaname":"会泽","cityid":324,"displayorder":8},{"areaid":11564,"areaname":"沾益","cityid":324,
     * "displayorder":9}]}],"R":[{"id":305,"city":"日喀则","pinyin":"rikaze","areas":[{"areaid":11399,"areaname":"日喀则市","cityid":305,"displayorder":1},{"areaid":11400,"areaname":"南木林","cityid":305,
     * "displayorder":2},{"areaid":11401,"areaname":"江孜","cityid":305,"displayorder":3},{"areaid":11402,"areaname":"定日","cityid":305,"displayorder":4},{"areaid":11403,"areaname":"萨迦","cityid":305,
     * "displayorder":5},{"areaid":11404,"areaname":"拉孜","cityid":305,"displayorder":6},{"areaid":11405,"areaname":"昂仁","cityid":305,"displayorder":7},{"areaid":11406,"areaname":"谢通门","cityid":305,
     * "displayorder":8},{"areaid":11407,"areaname":"白朗","cityid":305,"displayorder":9},{"areaid":11408,"areaname":"仁布","cityid":305,"displayorder":10},{"areaid":11409,"areaname":"康马","cityid":305,
     * "displayorder":11},{"areaid":11410,"areaname":"定结","cityid":305,"displayorder":12},{"areaid":11411,"areaname":"仲巴","cityid":305,"displayorder":13},{"areaid":11412,"areaname":"亚东",
     * "cityid":305,"displayorder":14},{"areaid":11413,"areaname":"吉隆","cityid":305,"displayorder":15},{"areaid":11414,"areaname":"聂拉木","cityid":305,"displayorder":16},{"areaid":11415,
     * "areaname":"萨嘎","cityid":305,"displayorder":17},{"areaid":11416,"areaname":"岗巴","cityid":305,"displayorder":18}]},{"id":253,"city":"日照","pinyin":"rizhao","areas":[{"areaid":10879,
     * "areaname":"东港","cityid":253,"displayorder":1},{"areaid":10880,"areaname":"石臼","cityid":253,"displayorder":2},{"areaid":10881,"areaname":"新市区","cityid":253,"displayorder":3},{"areaid":10882,
     * "areaname":"开发区","cityid":253,"displayorder":4},{"areaid":10883,"areaname":"高新区","cityid":253,"displayorder":5},{"areaid":10884,"areaname":"山海天旅游度假区","cityid":253,"displayorder":6},
     * {"areaid":10885,"areaname":"岚山","cityid":253,"displayorder":7},{"areaid":10886,"areaname":"五莲","cityid":253,"displayorder":8},{"areaid":10887,"areaname":"莒县","cityid":253,
     * "displayorder":9}]}],"S":[{"id":126,"city":"三门峡","pinyin":"sanmenxia","areas":[{"areaid":1125,"areaname":"湖滨","cityid":126,"displayorder":1},{"areaid":1126,"areaname":"开发区","cityid":126,
     * "displayorder":2},{"areaid":1127,"areaname":"陕县","cityid":126,"displayorder":3},{"areaid":1128,"areaname":"灵宝","cityid":126,"displayorder":4},{"areaid":1129,"areaname":"义马","cityid":126,
     * "displayorder":5},{"areaid":1130,"areaname":"渑池","cityid":126,"displayorder":6},{"areaid":1131,"areaname":"卢氏","cityid":126,"displayorder":7}]},{"id":58,"city":"三明","pinyin":"sanming",
     * "areas":[{"areaid":530,"areaname":"梅列","cityid":58,"displayorder":1},{"areaid":531,"areaname":"三元","cityid":58,"displayorder":2},{"areaid":532,"areaname":"永安","cityid":58,"displayorder":3},
     * {"areaid":533,"areaname":"沙县","cityid":58,"displayorder":4},{"areaid":534,"areaname":"尤溪","cityid":58,"displayorder":5},{"areaid":535,"areaname":"大田","cityid":58,"displayorder":6},
     * {"areaid":536,"areaname":"明溪","cityid":58,"displayorder":7},{"areaid":537,"areaname":"清流","cityid":58,"displayorder":8},{"areaid":538,"areaname":"宁化","cityid":58,"displayorder":9},
     * {"areaid":539,"areaname":"泰宁","cityid":58,"displayorder":10},{"areaid":540,"areaname":"建宁","cityid":58,"displayorder":11},{"areaid":541,"areaname":"将乐","cityid":58,"displayorder":12},
     * {"areaid":542,"areaname":"三明周边","cityid":58,"displayorder":13}]},{"id":102,"city":"三亚","pinyin":"sanya","areas":[{"areaid":890,"areaname":"河西","cityid":102,"displayorder":1},{"areaid":891,
     * "areaname":"河东","cityid":102,"displayorder":2},{"areaid":892,"areaname":"三亚湾","cityid":102,"displayorder":3},{"areaid":893,"areaname":"凤凰镇","cityid":102,"displayorder":4},{"areaid":894,
     * "areaname":"大东海","cityid":102,"displayorder":5},{"areaid":895,"areaname":"三亚周边","cityid":102,"displayorder":6}]},{"id":2,"city":"上海","pinyin":"shanghai","areas":[{"areaid":21,
     * "areaname":"黄浦","cityid":2,"displayorder":1},{"areaid":22,"areaname":"卢湾","cityid":2,"displayorder":2},{"areaid":23,"areaname":"静安","cityid":2,"displayorder":3},{"areaid":24,"areaname":"徐汇",
     * "cityid":2,"displayorder":4},{"areaid":25,"areaname":"浦东","cityid":2,"displayorder":5},{"areaid":26,"areaname":"长宁","cityid":2,"displayorder":6},{"areaid":27,"areaname":"虹口","cityid":2,
     * "displayorder":7},{"areaid":28,"areaname":"杨浦","cityid":2,"displayorder":8},{"areaid":29,"areaname":"普陀","cityid":2,"displayorder":9},{"areaid":30,"areaname":"闸北","cityid":2,
     * "displayorder":10},{"areaid":31,"areaname":"闵行","cityid":2,"displayorder":11},{"areaid":32,"areaname":"宝山","cityid":2,"displayorder":12},{"areaid":33,"areaname":"嘉定","cityid":2,
     * "displayorder":13},{"areaid":34,"areaname":"青浦","cityid":2,"displayorder":14},{"areaid":35,"areaname":"奉贤","cityid":2,"displayorder":15},{"areaid":36,"areaname":"南汇","cityid":2,
     * "displayorder":16},{"areaid":37,"areaname":"崇明","cityid":2,"displayorder":17},{"areaid":38,"areaname":"金山","cityid":2,"displayorder":18},{"areaid":39,"areaname":"松江","cityid":2,
     * "displayorder":19},{"areaid":40,"areaname":"上海周边","cityid":2,"displayorder":20}]},{"id":280,"city":"商洛","pinyin":"shangluo","areas":[{"areaid":11172,"areaname":"商州","cityid":280,
     * "displayorder":1},{"areaid":11173,"areaname":"洛南","cityid":280,"displayorder":2},{"areaid":11174,"areaname":"丹凤","cityid":280,"displayorder":3},{"areaid":11175,"areaname":"商南","cityid":280,
     * "displayorder":4},{"areaid":11176,"areaname":"山阳","cityid":280,"displayorder":5},{"areaid":11177,"areaname":"镇安","cityid":280,"displayorder":6},{"areaid":11178,"areaname":"柞水","cityid":280,
     * "displayorder":7}]},{"id":128,"city":"商丘","pinyin":"shangqiu","areas":[{"areaid":1147,"areaname":"梁园","cityid":128,"displayorder":1},{"areaid":1148,"areaname":"睢阳","cityid":128,
     * "displayorder":2},{"areaid":1149,"areaname":"永城","cityid":128,"displayorder":3},{"areaid":1150,"areaname":"宁陵","cityid":128,"displayorder":4},{"areaid":1151,"areaname":"虞城","cityid":128,
     * "displayorder":5},{"areaid":1152,"areaname":"民权","cityid":128,"displayorder":6},{"areaid":1153,"areaname":"夏邑","cityid":128,"displayorder":7},{"areaid":1154,"areaname":"柘城","cityid":128,
     * "displayorder":8},{"areaid":1155,"areaname":"睢县","cityid":128,"displayorder":9},{"areaid":1156,"areaname":"其他","cityid":128,"displayorder":10}]},{"id":202,"city":"上饶","pinyin":"shangrao",
     * "areas":[{"areaid":10486,"areaname":"信州","cityid":202,"displayorder":1},{"areaid":10487,"areaname":"德兴","cityid":202,"displayorder":2},{"areaid":10488,"areaname":"上饶县","cityid":202,
     * "displayorder":3},{"areaid":10489,"areaname":"广丰","cityid":202,"displayorder":4},{"areaid":10490,"areaname":"玉山","cityid":202,"displayorder":5},{"areaid":10491,"areaname":"鄱阳","cityid":202,
     * "displayorder":6},{"areaid":10492,"areaname":"余干","cityid":202,"displayorder":7},{"areaid":10493,"areaname":"上饶周边","cityid":202,"displayorder":8}]},{"id":304,"city":"山南","pinyin":"shannan",
     * "areas":[{"areaid":11387,"areaname":"乃东","cityid":304,"displayorder":1},{"areaid":11388,"areaname":"扎囊","cityid":304,"displayorder":2},{"areaid":11389,"areaname":"贡嘎","cityid":304,
     * "displayorder":3},{"areaid":11390,"areaname":"桑日","cityid":304,"displayorder":4},{"areaid":11391,"areaname":"琼结","cityid":304,"displayorder":5},{"areaid":11392,"areaname":"曲松","cityid":304,
     * "displayorder":6},{"areaid":11393,"areaname":"措美","cityid":304,"displayorder":7},{"areaid":11394,"areaname":"洛扎","cityid":304,"displayorder":8},{"areaid":11395,"areaname":"加查","cityid":304,
     * "displayorder":9},{"areaid":11396,"areaname":"隆子","cityid":304,"displayorder":10},{"areaid":11397,"areaname":"错那","cityid":304,"displayorder":11},{"areaid":11398,"areaname":"浪卡子",
     * "cityid":304,"displayorder":12}]},{"id":8,"city":"汕头","pinyin":"shantou","areas":[{"areaid":108,"areaname":"金平","cityid":8,"displayorder":1},{"areaid":109,"areaname":"龙湖","cityid":8,
     * "displayorder":2},{"areaid":110,"areaname":"澄海","cityid":8,"displayorder":3},{"areaid":111,"areaname":"潮阳","cityid":8,"displayorder":4},{"areaid":112,"areaname":"潮南","cityid":8,
     * "displayorder":5},{"areaid":113,"areaname":"濠江","cityid":8,"displayorder":6},{"areaid":114,"areaname":"南澳","cityid":8,"displayorder":7},{"areaid":115,"areaname":"其他","cityid":8,
     * "displayorder":8}]},{"id":17,"city":"汕尾","pinyin":"shanwei","areas":[{"areaid":188,"areaname":"汕尾城区","cityid":17,"displayorder":1},{"areaid":189,"areaname":"海丰","cityid":17,
     * "displayorder":2},{"areaid":190,"areaname":"陆丰","cityid":17,"displayorder":3},{"areaid":191,"areaname":"陆河","cityid":17,"displayorder":4},{"areaid":192,"areaname":"汕尾周边","cityid":17,
     * "displayorder":5}]},{"id":9,"city":"韶关","pinyin":"shaoguan","areas":[{"areaid":116,"areaname":"武江","cityid":9,"displayorder":1},{"areaid":117,"areaname":"浈江","cityid":9,"displayorder":2},
     * {"areaid":118,"areaname":"北江","cityid":9,"displayorder":3},{"areaid":119,"areaname":"曲江","cityid":9,"displayorder":4},{"areaid":120,"areaname":"乐昌","cityid":9,"displayorder":5},
     * {"areaid":121,"areaname":"南雄","cityid":9,"displayorder":6},{"areaid":122,"areaname":"新丰","cityid":9,"displayorder":7},{"areaid":123,"areaname":"仁化","cityid":9,"displayorder":8},
     * {"areaid":124,"areaname":"始兴","cityid":9,"displayorder":9},{"areaid":125,"areaname":"乳源","cityid":9,"displayorder":10},{"areaid":126,"areaname":"翁源县","cityid":9,"displayorder":11},
     * {"areaid":127,"areaname":"其它","cityid":9,"displayorder":12}]},{"id":30,"city":"绍兴","pinyin":"shaoxing","areas":[{"areaid":308,"areaname":"越城","cityid":30,"displayorder":1},{"areaid":309,
     * "areaname":"镜湖","cityid":30,"displayorder":2},{"areaid":310,"areaname":"袍江","cityid":30,"displayorder":3},{"areaid":311,"areaname":"滨海","cityid":30,"displayorder":4},{"areaid":312,
     * "areaname":"柯桥","cityid":30,"displayorder":5},{"areaid":313,"areaname":"上虞","cityid":30,"displayorder":6},{"areaid":314,"areaname":"诸暨","cityid":30,"displayorder":7},{"areaid":315,
     * "areaname":"嵊州","cityid":30,"displayorder":8},{"areaid":316,"areaname":"新昌","cityid":30,"displayorder":9},{"areaid":317,"areaname":"其他","cityid":30,"displayorder":10}]},{"id":162,
     * "city":"邵阳","pinyin":"shaoyang","areas":[{"areaid":10119,"areaname":"双清","cityid":162,"displayorder":1},{"areaid":10120,"areaname":"大祥","cityid":162,"displayorder":2},{"areaid":10121,
     * "areaname":"北塔","cityid":162,"displayorder":3},{"areaid":10122,"areaname":"武冈","cityid":162,"displayorder":4},{"areaid":10123,"areaname":"邵东","cityid":162,"displayorder":5},{"areaid":10124,
     * "areaname":"邵阳","cityid":162,"displayorder":6},{"areaid":10125,"areaname":"新邵","cityid":162,"displayorder":7},{"areaid":10126,"areaname":"隆回","cityid":162,"displayorder":8},{"areaid":10127,
     * "areaname":"洞口","cityid":162,"displayorder":9},{"areaid":10128,"areaname":"绥宁","cityid":162,"displayorder":10},{"areaid":10129,"areaname":"新宁","cityid":162,"displayorder":11},
     * {"areaid":10130,"areaname":"城步县","cityid":162,"displayorder":12}]},{"id":6,"city":"深圳","pinyin":"shen","areas":[{"areaid":89,"areaname":"罗湖","cityid":6,"displayorder":1},{"areaid":90,
     * "areaname":"福田","cityid":6,"displayorder":2},{"areaid":91,"areaname":"南山","cityid":6,"displayorder":3},{"areaid":92,"areaname":"盐田","cityid":6,"displayorder":4},{"areaid":93,"areaname":"宝安",
     * "cityid":6,"displayorder":5},{"areaid":94,"areaname":"龙岗区","cityid":6,"displayorder":6},{"areaid":95,"areaname":"布吉","cityid":6,"displayorder":7},{"areaid":96,"areaname":"坪山新区","cityid":6,
     * "displayorder":8},{"areaid":97,"areaname":"光明新区","cityid":6,"displayorder":9},{"areaid":98,"areaname":"龙华新区","cityid":6,"displayorder":10},{"areaid":99,"areaname":"大鹏新区","cityid":6,
     * "displayorder":11},{"areaid":100,"areaname":"深圳周边","cityid":6,"displayorder":12}]},{"id":205,"city":"沈阳","pinyin":"shenyang","areas":[{"areaid":10509,"areaname":"和平","cityid":205,
     * "displayorder":1},{"areaid":10510,"areaname":"沈河","cityid":205,"displayorder":2},{"areaid":10511,"areaname":"皇姑","cityid":205,"displayorder":3},{"areaid":10512,"areaname":"大东","cityid":205,
     * "displayorder":4},{"areaid":10513,"areaname":"铁西","cityid":205,"displayorder":5},{"areaid":10514,"areaname":"东陵","cityid":205,"displayorder":6},{"areaid":10515,"areaname":"于洪","cityid":205,
     * "displayorder":7},{"areaid":10516,"areaname":"沈北新区","cityid":205,"displayorder":8},{"areaid":10517,"areaname":"苏家屯","cityid":205,"displayorder":9},{"areaid":10518,"areaname":"浑南新区",
     * "cityid":205,"displayorder":10},{"areaid":10519,"areaname":"沈阳周边","cityid":205,"displayorder":11}]},{"id":322,"city":"石河子","pinyin":"shihezi","areas":[{"areaid":11535,"areaname":"老街",
     * "cityid":322,"displayorder":1},{"areaid":11536,"areaname":"红山","cityid":322,"displayorder":2},{"areaid":11537,"areaname":"新城","cityid":322,"displayorder":3},{"areaid":11538,"areaname":"向阳",
     * "cityid":322,"displayorder":4},{"areaid":11539,"areaname":"东城","cityid":322,"displayorder":5},{"areaid":11540,"areaname":"北泉","cityid":322,"displayorder":6},{"areaid":11541,
     * "areaname":"石河子乡","cityid":322,"displayorder":7}]},{"id":103,"city":"石家庄","pinyin":"shijiazhuang","areas":[{"areaid":896,"areaname":"桥西","cityid":103,"displayorder":1},{"areaid":897,
     * "areaname":"桥东","cityid":103,"displayorder":2},{"areaid":898,"areaname":"裕华","cityid":103,"displayorder":3},{"areaid":899,"areaname":"长安","cityid":103,"displayorder":4},{"areaid":900,
     * "areaname":"新华","cityid":103,"displayorder":5},{"areaid":901,"areaname":"正定","cityid":103,"displayorder":6},{"areaid":902,"areaname":"井陉矿区","cityid":103,"displayorder":7},{"areaid":903,
     * "areaname":"开发区","cityid":103,"displayorder":8},{"areaid":904,"areaname":"栾城","cityid":103,"displayorder":9},{"areaid":905,"areaname":"藁城","cityid":103,"displayorder":10},{"areaid":906,
     * "areaname":"鹿泉","cityid":103,"displayorder":11},{"areaid":907,"areaname":"石家庄周边","cityid":103,"displayorder":12}]},{"id":148,"city":"十堰","pinyin":"shiyan","areas":[{"areaid":10001,
     * "areaname":"张湾","cityid":148,"displayorder":1},{"areaid":10002,"areaname":"茅箭","cityid":148,"displayorder":2},{"areaid":10003,"areaname":"丹江口","cityid":148,"displayorder":3},{"areaid":10004,
     * "areaname":"武当山","cityid":148,"displayorder":4},{"areaid":10005,"areaname":"郧县","cityid":148,"displayorder":5},{"areaid":10006,"areaname":"竹山","cityid":148,"displayorder":6},{"areaid":10007,
     * "areaname":"房县","cityid":148,"displayorder":7},{"areaid":10008,"areaname":"郧西","cityid":148,"displayorder":8},{"areaid":10009,"areaname":"竹溪","cityid":148,"displayorder":9},{"areaid":10010,
     * "areaname":"十堰周边","cityid":148,"displayorder":10}]},{"id":232,"city":"石嘴山","pinyin":"shizuishan","areas":[{"areaid":10724,"areaname":"大武口","cityid":232,"displayorder":1},{"areaid":10725,
     * "areaname":"惠农","cityid":232,"displayorder":2},{"areaid":10726,"areaname":"平罗","cityid":232,"displayorder":3}]},{"id":136,"city":"双鸭山","pinyin":"shuangyashan","areas":[{"areaid":1225,
     * "areaname":"尖山","cityid":136,"displayorder":1},{"areaid":1226,"areaname":"岭东","cityid":136,"displayorder":2},{"areaid":1227,"areaname":"四方台","cityid":136,"displayorder":3},{"areaid":1228,
     * "areaname":"宝山","cityid":136,"displayorder":4},{"areaid":1229,"areaname":"集贤","cityid":136,"displayorder":5},{"areaid":1230,"areaname":"友谊","cityid":136,"displayorder":6},{"areaid":1231,
     * "areaname":"宝清","cityid":136,"displayorder":7},{"areaid":1232,"areaname":"饶河","cityid":136,"displayorder":8},{"areaid":1233,"areaname":"双鸭山周边","cityid":136,"displayorder":9}]},{"id":265,
     * "city":"朔州","pinyin":"shuozhou","areas":[{"areaid":10986,"areaname":"朔城","cityid":265,"displayorder":1},{"areaid":10987,"areaname":"平鲁","cityid":265,"displayorder":2},{"areaid":10988,
     * "areaname":"山阴","cityid":265,"displayorder":3},{"areaid":10989,"areaname":"应县","cityid":265,"displayorder":4},{"areaid":10990,"areaname":"右玉","cityid":265,"displayorder":5},{"areaid":10991,
     * "areaname":"怀仁","cityid":265,"displayorder":6}]},{"id":174,"city":"四平","pinyin":"siping","areas":[{"areaid":10226,"areaname":"铁西","cityid":174,"displayorder":1},{"areaid":10227,
     * "areaname":"铁东","cityid":174,"displayorder":2},{"areaid":10228,"areaname":"双辽","cityid":174,"displayorder":3},{"areaid":10229,"areaname":"公主岭","cityid":174,"displayorder":4},{"areaid":10230,
     * "areaname":"梨树县","cityid":174,"displayorder":5},{"areaid":10231,"areaname":"伊通县","cityid":174,"displayorder":6},{"areaid":10232,"areaname":"孤家子镇","cityid":174,"displayorder":7},
     * {"areaid":10233,"areaname":"榆树台镇","cityid":174,"displayorder":8},{"areaid":10234,"areaname":"四平周边","cityid":174,"displayorder":9}]},{"id":178,"city":"松原","pinyin":"songyuan",
     * "areas":[{"areaid":10255,"areaname":"前郭","cityid":178,"displayorder":1},{"areaid":10256,"areaname":"长岭","cityid":178,"displayorder":2},{"areaid":10257,"areaname":"扶余","cityid":178,
     * "displayorder":3},{"areaid":10258,"areaname":"乾安","cityid":178,"displayorder":4},{"areaid":10259,"areaname":"宁江","cityid":178,"displayorder":5},{"areaid":10260,"areaname":"经济技术开发区",
     * "cityid":178,"displayorder":6},{"areaid":10261,"areaname":"农业高新产业开发区","cityid":178,"displayorder":7}]},{"id":143,"city":"绥化","pinyin":"suihua","areas":[{"areaid":1282,"areaname":"北林",
     * "cityid":143,"displayorder":1},{"areaid":1283,"areaname":"安达","cityid":143,"displayorder":2},{"areaid":1284,"areaname":"肇东","cityid":143,"displayorder":3},{"areaid":1285,"areaname":"海伦",
     * "cityid":143,"displayorder":4},{"areaid":1286,"areaname":"望奎","cityid":143,"displayorder":5},{"areaid":1287,"areaname":"兰西","cityid":143,"displayorder":6},{"areaid":1288,"areaname":"青冈",
     * "cityid":143,"displayorder":7},{"areaid":1289,"areaname":"庆安","cityid":143,"displayorder":8},{"areaid":1290,"areaname":"明水","cityid":143,"displayorder":9},{"areaid":1291,"areaname":"绥棱",
     * "cityid":143,"displayorder":10}]},{"id":287,"city":"遂宁","pinyin":"suining","areas":[{"areaid":11242,"areaname":"船山","cityid":287,"displayorder":1},{"areaid":11243,"areaname":"安居",
     * "cityid":287,"displayorder":2},{"areaid":11244,"areaname":"蓬溪","cityid":287,"displayorder":3},{"areaid":11245,"areaname":"射洪","cityid":287,"displayorder":4},{"areaid":11246,"areaname":"大英",
     * "cityid":287,"displayorder":5}]},{"id":149,"city":"随州","pinyin":"suizhou","areas":[{"areaid":10011,"areaname":"广水","cityid":149,"displayorder":1},{"areaid":10012,"areaname":"曾都",
     * "cityid":149,"displayorder":2},{"areaid":10013,"areaname":"随县","cityid":149,"displayorder":3},{"areaid":10014,"areaname":"随州周边","cityid":149,"displayorder":4}]},{"id":192,"city":"宿迁",
     * "pinyin":"suqian","areas":[{"areaid":10392,"areaname":"宿豫/宿城","cityid":192,"displayorder":1},{"areaid":10393,"areaname":"泗洪","cityid":192,"displayorder":2},{"areaid":10394,"areaname":"泗阳",
     * "cityid":192,"displayorder":3},{"areaid":10395,"areaname":"沭阳","cityid":192,"displayorder":4}]},{"id":54,"city":"宿州","pinyin":"suzhou","areas":[{"areaid":494,"areaname":"墉桥","cityid":54,
     * "displayorder":1},{"areaid":495,"areaname":"泗县","cityid":54,"displayorder":2},{"areaid":496,"areaname":"灵璧","cityid":54,"displayorder":3},{"areaid":497,"areaname":"萧县","cityid":54,
     * "displayorder":4},{"areaid":498,"areaname":"砀山","cityid":54,"displayorder":5},{"areaid":499,"areaname":"其他","cityid":54,"displayorder":6}]},{"id":190,"city":"苏州","pinyin":"suzhou",
     * "areas":[{"areaid":10371,"areaname":"沧浪","cityid":190,"displayorder":1},{"areaid":10372,"areaname":"相城","cityid":190,"displayorder":2},{"areaid":10373,"areaname":"平江","cityid":190,
     * "displayorder":3},{"areaid":10374,"areaname":"金阊","cityid":190,"displayorder":4},{"areaid":10375,"areaname":"工业园","cityid":190,"displayorder":5},{"areaid":10376,"areaname":"吴中","cityid":190,
     * "displayorder":6},{"areaid":10377,"areaname":"昆山","cityid":190,"displayorder":7},{"areaid":10378,"areaname":"常熟","cityid":190,"displayorder":8},{"areaid":10379,"areaname":"张家港","cityid":190,
     * "displayorder":9},{"areaid":10380,"areaname":"太仓","cityid":190,"displayorder":10},{"areaid":10381,"areaname":"吴江","cityid":190,"displayorder":11},{"areaid":10382,"areaname":"高新区",
     * "cityid":190,"displayorder":12},{"areaid":10383,"areaname":"苏州周边","cityid":190,"displayorder":13}]}],"T":[{"id":320,"city":"塔城","pinyin":"tacheng","areas":[{"areaid":11521,"areaname":"塔城市",
     * "cityid":320,"displayorder":1},{"areaid":11522,"areaname":"乌苏","cityid":320,"displayorder":2},{"areaid":11523,"areaname":"额敏","cityid":320,"displayorder":3},{"areaid":11524,"areaname":"沙湾",
     * "cityid":320,"displayorder":4},{"areaid":11525,"areaname":"托里","cityid":320,"displayorder":5},{"areaid":11526,"areaname":"裕民","cityid":320,"displayorder":6},{"areaid":11527,
     * "areaname":"和布克赛尔","cityid":320,"displayorder":7}]},{"id":252,"city":"泰安","pinyin":"taian","areas":[{"areaid":10873,"areaname":"泰山","cityid":252,"displayorder":1},{"areaid":10874,
     * "areaname":"岱岳","cityid":252,"displayorder":2},{"areaid":10875,"areaname":"新泰","cityid":252,"displayorder":3},{"areaid":10876,"areaname":"肥城","cityid":252,"displayorder":4},{"areaid":10877,
     * "areaname":"宁阳","cityid":252,"displayorder":5},{"areaid":10878,"areaname":"东平","cityid":252,"displayorder":6}]},{"id":260,"city":"太原","pinyin":"taiyuan","areas":[{"areaid":10941,
     * "areaname":"小店","cityid":260,"displayorder":1},{"areaid":10942,"areaname":"迎泽","cityid":260,"displayorder":2},{"areaid":10943,"areaname":"杏花岭","cityid":260,"displayorder":3},{"areaid":10944,
     * "areaname":"尖草坪","cityid":260,"displayorder":4},{"areaid":10945,"areaname":"万柏林","cityid":260,"displayorder":5},{"areaid":10946,"areaname":"晋源","cityid":260,"displayorder":6},
     * {"areaid":10947,"areaname":"清徐","cityid":260,"displayorder":7},{"areaid":10948,"areaname":"阳曲","cityid":260,"displayorder":8},{"areaid":10949,"areaname":"古交","cityid":260,"displayorder":9},
     * {"areaid":10950,"areaname":"娄烦","cityid":260,"displayorder":10}]},{"id":34,"city":"台州","pinyin":"taizhou","areas":[{"areaid":338,"areaname":"椒江","cityid":34,"displayorder":1},{"areaid":339,
     * "areaname":"路桥","cityid":34,"displayorder":2},{"areaid":340,"areaname":"黄岩","cityid":34,"displayorder":3},{"areaid":341,"areaname":"温岭","cityid":34,"displayorder":4},{"areaid":342,
     * "areaname":"临海","cityid":34,"displayorder":5},{"areaid":343,"areaname":"玉环","cityid":34,"displayorder":6},{"areaid":344,"areaname":"仙居","cityid":34,"displayorder":7},{"areaid":345,
     * "areaname":"天台","cityid":34,"displayorder":8},{"areaid":346,"areaname":"三门","cityid":34,"displayorder":9},{"areaid":347,"areaname":"其他","cityid":34,"displayorder":10}]},{"id":193,
     * "city":"泰州","pinyin":"taizhou","areas":[{"areaid":10396,"areaname":"海陵","cityid":193,"displayorder":1},{"areaid":10397,"areaname":"高港","cityid":193,"displayorder":2},{"areaid":10398,
     * "areaname":"姜堰","cityid":193,"displayorder":3},{"areaid":10399,"areaname":"泰兴","cityid":193,"displayorder":4},{"areaid":10400,"areaname":"靖江","cityid":193,"displayorder":5},{"areaid":10401,
     * "areaname":"兴化","cityid":193,"displayorder":6},{"areaid":10402,"areaname":"其他","cityid":193,"displayorder":7}]},{"id":111,"city":"唐山","pinyin":"tangshan","areas":[{"areaid":984,
     * "areaname":"路北","cityid":111,"displayorder":1},{"areaid":985,"areaname":"路南","cityid":111,"displayorder":2},{"areaid":986,"areaname":"遵化市","cityid":111,"displayorder":3},{"areaid":987,
     * "areaname":"开平","cityid":111,"displayorder":4},{"areaid":988,"areaname":"迁西","cityid":111,"displayorder":5},{"areaid":989,"areaname":"古冶","cityid":111,"displayorder":6},{"areaid":990,
     * "areaname":"丰南","cityid":111,"displayorder":7},{"areaid":991,"areaname":"丰润","cityid":111,"displayorder":8},{"areaid":992,"areaname":"高新区","cityid":111,"displayorder":9},{"areaid":993,
     * "areaname":"南堡开发区","cityid":111,"displayorder":10},{"areaid":994,"areaname":"曹妃甸","cityid":111,"displayorder":11},{"areaid":995,"areaname":"海港开发区","cityid":111,"displayorder":12},
     * {"areaid":996,"areaname":"其他","cityid":111,"displayorder":13}]},{"id":3,"city":"天津","pinyin":"tianjin","areas":[{"areaid":41,"areaname":"南开","cityid":3,"displayorder":1},{"areaid":42,
     * "areaname":"河西","cityid":3,"displayorder":2},{"areaid":43,"areaname":"河东","cityid":3,"displayorder":3},{"areaid":44,"areaname":"和平","cityid":3,"displayorder":4},{"areaid":45,"areaname":"河北",
     * "cityid":3,"displayorder":5},{"areaid":46,"areaname":"红桥","cityid":3,"displayorder":6},{"areaid":47,"areaname":"塘沽","cityid":3,"displayorder":7},{"areaid":48,"areaname":"东丽","cityid":3,
     * "displayorder":8},{"areaid":49,"areaname":"西青","cityid":3,"displayorder":9},{"areaid":50,"areaname":"北辰","cityid":3,"displayorder":10},{"areaid":51,"areaname":"津南","cityid":3,
     * "displayorder":11},{"areaid":52,"areaname":"开发区","cityid":3,"displayorder":12},{"areaid":53,"areaname":"大港","cityid":3,"displayorder":13},{"areaid":54,"areaname":"天津周边","cityid":3,
     * "displayorder":14}]},{"id":68,"city":"天水","pinyin":"tianshui","areas":[{"areaid":612,"areaname":"路北","cityid":68,"displayorder":1},{"areaid":613,"areaname":"路南","cityid":68,
     * "displayorder":2},{"areaid":614,"areaname":"遵化市","cityid":68,"displayorder":3},{"areaid":615,"areaname":"开平","cityid":68,"displayorder":4},{"areaid":616,"areaname":"迁安市","cityid":68,
     * "displayorder":5},{"areaid":617,"areaname":"迁西","cityid":68,"displayorder":6},{"areaid":618,"areaname":"古冶","cityid":68,"displayorder":7},{"areaid":619,"areaname":"丰南","cityid":68,
     * "displayorder":8},{"areaid":620,"areaname":"丰润","cityid":68,"displayorder":9},{"areaid":621,"areaname":"高新区","cityid":68,"displayorder":10},{"areaid":622,"areaname":"南堡开发区","cityid":68,
     * "displayorder":11},{"areaid":623,"areaname":"曹妃甸","cityid":68,"displayorder":12},{"areaid":624,"areaname":"海港开发区","cityid":68,"displayorder":13},{"areaid":625,"areaname":"其他","cityid":68,
     * "displayorder":14}]},{"id":216,"city":"铁岭","pinyin":"tieling","areas":[{"areaid":10589,"areaname":"银州","cityid":216,"displayorder":1},{"areaid":10590,"areaname":"清河","cityid":216,
     * "displayorder":2},{"areaid":10591,"areaname":"调兵山","cityid":216,"displayorder":3},{"areaid":10592,"areaname":"开原","cityid":216,"displayorder":4},{"areaid":10593,"areaname":"铁岭县",
     * "cityid":216,"displayorder":5},{"areaid":10594,"areaname":"西丰","cityid":216,"displayorder":6},{"areaid":10595,"areaname":"昌图","cityid":216,"displayorder":7}]},{"id":272,"city":"铜川",
     * "pinyin":"tongchuan","areas":[{"areaid":11079,"areaname":"耀州","cityid":272,"displayorder":1},{"areaid":11080,"areaname":"王益","cityid":272,"displayorder":2},{"areaid":11081,"areaname":"印台",
     * "cityid":272,"displayorder":3},{"areaid":11082,"areaname":"宜君","cityid":272,"displayorder":4}]},{"id":176,"city":"通化","pinyin":"tonghua","areas":[{"areaid":10240,"areaname":"东昌",
     * "cityid":176,"displayorder":1},{"areaid":10241,"areaname":"二道江","cityid":176,"displayorder":2},{"areaid":10242,"areaname":"梅河口","cityid":176,"displayorder":3},{"areaid":10243,
     * "areaname":"集安","cityid":176,"displayorder":4},{"areaid":10244,"areaname":"通化","cityid":176,"displayorder":5},{"areaid":10245,"areaname":"辉南","cityid":176,"displayorder":6},{"areaid":10246,
     * "areaname":"柳河","cityid":176,"displayorder":7},{"areaid":10247,"areaname":"通化周边","cityid":176,"displayorder":8}]},{"id":223,"city":"通辽","pinyin":"tongliao","areas":[{"areaid":10656,
     * "areaname":"科尔沁","cityid":223,"displayorder":1},{"areaid":10657,"areaname":"霍林郭勒","cityid":223,"displayorder":2},{"areaid":10658,"areaname":"开鲁","cityid":223,"displayorder":3},
     * {"areaid":10659,"areaname":"科尔沁左翼中旗","cityid":223,"displayorder":4},{"areaid":10660,"areaname":"科尔沁左翼后旗","cityid":223,"displayorder":5},{"areaid":10661,"areaname":"库伦旗","cityid":223,
     * "displayorder":6},{"areaid":10662,"areaname":"奈曼旗","cityid":223,"displayorder":7},{"areaid":10663,"areaname":"扎鲁特旗","cityid":223,"displayorder":8},{"areaid":10664,"areaname":"通辽周边",
     * "cityid":223,"displayorder":9}]},{"id":44,"city":"铜陵","pinyin":"tongling","areas":[{"areaid":423,"areaname":"铜官山","cityid":44,"displayorder":1},{"areaid":424,"areaname":"狮子山","cityid":44,
     * "displayorder":2},{"areaid":425,"areaname":"郊区","cityid":44,"displayorder":3},{"areaid":426,"areaname":"铜陵县","cityid":44,"displayorder":4},{"areaid":427,"areaname":"铜陵周边","cityid":44,
     * "displayorder":5}]},{"id":100,"city":"铜仁","pinyin":"tongren","areas":[{"areaid":874,"areaname":"万山","cityid":100,"displayorder":1},{"areaid":875,"areaname":"江口","cityid":100,
     * "displayorder":2},{"areaid":876,"areaname":"碧江","cityid":100,"displayorder":3},{"areaid":877,"areaname":"石阡","cityid":100,"displayorder":4},{"areaid":878,"areaname":"思南","cityid":100,
     * "displayorder":5},{"areaid":879,"areaname":"德江","cityid":100,"displayorder":6},{"areaid":880,"areaname":"松桃","cityid":100,"displayorder":7},{"areaid":881,"areaname":"玉屏","cityid":100,
     * "displayorder":8},{"areaid":882,"areaname":"印江","cityid":100,"displayorder":9},{"areaid":883,"areaname":"沿河","cityid":100,"displayorder":10},{"areaid":884,"areaname":"铜仁周边","cityid":100,
     * "displayorder":11}]},{"id":311,"city":"吐鲁番","pinyin":"tulufan","areas":[{"areaid":11456,"areaname":"吐鲁番市","cityid":311,"displayorder":1},{"areaid":11457,"areaname":"鄯善","cityid":311,
     * "displayorder":2},{"areaid":11458,"areaname":"托克逊","cityid":311,"displayorder":3}]}],"W":[{"id":249,"city":"潍坊","pinyin":"weifang","areas":[{"areaid":10840,"areaname":"潍城","cityid":249,
     * "displayorder":1},{"areaid":10841,"areaname":"寒亭","cityid":249,"displayorder":2},{"areaid":10842,"areaname":"坊子","cityid":249,"displayorder":3},{"areaid":10843,"areaname":"奎文","cityid":249,
     * "displayorder":4},{"areaid":10844,"areaname":"高新区","cityid":249,"displayorder":5},{"areaid":10845,"areaname":"滨海新区","cityid":249,"displayorder":6},{"areaid":10846,"areaname":"经开区",
     * "cityid":249,"displayorder":7},{"areaid":10847,"areaname":"青州","cityid":249,"displayorder":8},{"areaid":10848,"areaname":"诸城","cityid":249,"displayorder":9},{"areaid":10849,"areaname":"寿光",
     * "cityid":249,"displayorder":10},{"areaid":10850,"areaname":"安丘","cityid":249,"displayorder":11},{"areaid":10851,"areaname":"高密","cityid":249,"displayorder":12},{"areaid":10852,
     * "areaname":"昌邑","cityid":249,"displayorder":13},{"areaid":10853,"areaname":"临朐","cityid":249,"displayorder":14},{"areaid":10854,"areaname":"昌乐","cityid":249,"displayorder":15}]},{"id":250,
     * "city":"威海","pinyin":"weihai","areas":[{"areaid":10855,"areaname":"环翠","cityid":250,"displayorder":1},{"areaid":10856,"areaname":"高区","cityid":250,"displayorder":2},{"areaid":10857,
     * "areaname":"经区","cityid":250,"displayorder":3},{"areaid":10858,"areaname":"文登","cityid":250,"displayorder":4},{"areaid":10859,"areaname":"乳山","cityid":250,"displayorder":5},{"areaid":10860,
     * "areaname":"荣成","cityid":250,"displayorder":6}]},{"id":275,"city":"渭南","pinyin":"weinan","areas":[{"areaid":11109,"areaname":"临渭","cityid":275,"displayorder":1},{"areaid":11110,
     * "areaname":"韩城","cityid":275,"displayorder":2},{"areaid":11111,"areaname":"华阴","cityid":275,"displayorder":3},{"areaid":11112,"areaname":"华县","cityid":275,"displayorder":4},{"areaid":11113,
     * "areaname":"潼关","cityid":275,"displayorder":5},{"areaid":11114,"areaname":"大荔","cityid":275,"displayorder":6},{"areaid":11115,"areaname":"合阳","cityid":275,"displayorder":7},{"areaid":11116,
     * "areaname":"澄城","cityid":275,"displayorder":8},{"areaid":11117,"areaname":"蒲城","cityid":275,"displayorder":9},{"areaid":11118,"areaname":"白水","cityid":275,"displayorder":10},{"areaid":11119,
     * "areaname":"富平","cityid":275,"displayorder":11}]},{"id":330,"city":"文山","pinyin":"wenshan","areas":[{"areaid":11603,"areaname":"文山市","cityid":330,"displayorder":1},{"areaid":11604,
     * "areaname":"砚山","cityid":330,"displayorder":2},{"areaid":11605,"areaname":"西畴","cityid":330,"displayorder":3},{"areaid":11606,"areaname":"麻栗坡","cityid":330,"displayorder":4},{"areaid":11607,
     * "areaname":"马关","cityid":330,"displayorder":5},{"areaid":11608,"areaname":"丘北","cityid":330,"displayorder":6},{"areaid":11609,"areaname":"广南","cityid":330,"displayorder":7},{"areaid":11610,
     * "areaname":"富宁","cityid":330,"displayorder":8}]},{"id":37,"city":"温州","pinyin":"wenzhou","areas":[{"areaid":366,"areaname":"鹿城","cityid":37,"displayorder":1},{"areaid":367,"areaname":"龙湾",
     * "cityid":37,"displayorder":2},{"areaid":368,"areaname":"瓯海","cityid":37,"displayorder":3},{"areaid":369,"areaname":"乐清","cityid":37,"displayorder":4},{"areaid":370,"areaname":"瑞安",
     * "cityid":37,"displayorder":5},{"areaid":371,"areaname":"永嘉","cityid":37,"displayorder":6},{"areaid":372,"areaname":"洞头","cityid":37,"displayorder":7},{"areaid":373,"areaname":"平阳",
     * "cityid":37,"displayorder":8},{"areaid":374,"areaname":"苍南","cityid":37,"displayorder":9},{"areaid":375,"areaname":"泰顺","cityid":37,"displayorder":10},{"areaid":376,"areaname":"文成",
     * "cityid":37,"displayorder":11},{"areaid":377,"areaname":"温州周边","cityid":37,"displayorder":12}]},{"id":225,"city":"乌海","pinyin":"wuhai","areas":[{"areaid":10675,"areaname":"海勃湾","cityid":225,
     * "displayorder":1},{"areaid":10676,"areaname":"海南","cityid":225,"displayorder":2},{"areaid":10677,"areaname":"乌达","cityid":225,"displayorder":3},{"areaid":10678,"areaname":"滨河东区",
     * "cityid":225,"displayorder":4},{"areaid":10679,"areaname":"滨河西区","cityid":225,"displayorder":5}]},{"id":145,"city":"武汉","pinyin":"wuhan","areas":[{"areaid":1300,"areaname":"武昌","cityid":145,
     * "displayorder":1},{"areaid":1301,"areaname":"洪山","cityid":145,"displayorder":2},{"areaid":1302,"areaname":"黄陂","cityid":145,"displayorder":3},{"areaid":1303,"areaname":"江岸","cityid":145,
     * "displayorder":4},{"areaid":1304,"areaname":"东西湖","cityid":145,"displayorder":5},{"areaid":1305,"areaname":"江汉","cityid":145,"displayorder":6},{"areaid":1306,"areaname":"蔡甸","cityid":145,
     * "displayorder":7},{"areaid":1307,"areaname":"沌口开发区","cityid":145,"displayorder":8},{"areaid":1308,"areaname":"汉阳","cityid":145,"displayorder":9},{"areaid":1309,"areaname":"硚口","cityid":145,
     * "displayorder":10},{"areaid":1310,"areaname":"江夏","cityid":145,"displayorder":11},{"areaid":1311,"areaname":"青山","cityid":145,"displayorder":12},{"areaid":1312,"areaname":"新洲","cityid":145,
     * "displayorder":13},{"areaid":1313,"areaname":"汉南","cityid":145,"displayorder":14},{"areaid":1314,"areaname":"武汉周边","cityid":145,"displayorder":15}]},{"id":39,"city":"芜湖","pinyin":"wuhu",
     * "areas":[{"areaid":388,"areaname":"镜湖","cityid":39,"displayorder":1},{"areaid":389,"areaname":"鸠江","cityid":39,"displayorder":2},{"areaid":390,"areaname":"弋江","cityid":39,"displayorder":3},
     * {"areaid":391,"areaname":"无为","cityid":39,"displayorder":4},{"areaid":392,"areaname":"三山","cityid":39,"displayorder":5},{"areaid":393,"areaname":"南陵","cityid":39,"displayorder":6},
     * {"areaid":394,"areaname":"芜湖县","cityid":39,"displayorder":7},{"areaid":395,"areaname":"繁昌","cityid":39,"displayorder":8},{"areaid":396,"areaname":"其他","cityid":39,"displayorder":9}]},
     * {"id":227,"city":"乌兰察布","pinyin":"wulanchabu","areas":[{"areaid":10688,"areaname":"集宁","cityid":227,"displayorder":1},{"areaid":10689,"areaname":"丰镇","cityid":227,"displayorder":2},
     * {"areaid":10690,"areaname":"卓资","cityid":227,"displayorder":3},{"areaid":10691,"areaname":"化德","cityid":227,"displayorder":4},{"areaid":10692,"areaname":"商都","cityid":227,"displayorder":5},
     * {"areaid":10693,"areaname":"兴和","cityid":227,"displayorder":6},{"areaid":10694,"areaname":"凉城","cityid":227,"displayorder":7},{"areaid":10695,"areaname":"乌兰察布周边","cityid":227,
     * "displayorder":8}]},{"id":309,"city":"乌鲁木齐","pinyin":"wulumuqi","areas":[{"areaid":11442,"areaname":"天山","cityid":309,"displayorder":1},{"areaid":11443,"areaname":"沙依巴克","cityid":309,
     * "displayorder":2},{"areaid":11444,"areaname":"新市","cityid":309,"displayorder":3},{"areaid":11445,"areaname":"水磨沟","cityid":309,"displayorder":4},{"areaid":11446,"areaname":"开发","cityid":309,
     * "displayorder":5},{"areaid":11447,"areaname":"头屯河","cityid":309,"displayorder":6},{"areaid":11448,"areaname":"米东","cityid":309,"displayorder":7},{"areaid":11449,"areaname":"东山","cityid":309,
     * "displayorder":8},{"areaid":11450,"areaname":"乌鲁木齐县","cityid":309,"displayorder":9},{"areaid":11451,"areaname":"达坂城","cityid":309,"displayorder":10}]},{"id":69,"city":"武威","pinyin":"wuwei",
     * "areas":[{"areaid":626,"areaname":"凉州","cityid":69,"displayorder":1},{"areaid":627,"areaname":"民勤","cityid":69,"displayorder":2},{"areaid":628,"areaname":"古浪","cityid":69,"displayorder":3},
     * {"areaid":629,"areaname":"天祝","cityid":69,"displayorder":4},{"areaid":630,"areaname":"武威周边","cityid":69,"displayorder":5}]},{"id":182,"city":"无锡","pinyin":"wuxi","areas":[{"areaid":10293,
     * "areaname":"崇安","cityid":182,"displayorder":1},{"areaid":10294,"areaname":"南长","cityid":182,"displayorder":2},{"areaid":10295,"areaname":"北塘","cityid":182,"displayorder":3},{"areaid":10296,
     * "areaname":"锡山","cityid":182,"displayorder":4},{"areaid":10297,"areaname":"惠山","cityid":182,"displayorder":5},{"areaid":10298,"areaname":"滨湖","cityid":182,"displayorder":6},{"areaid":10299,
     * "areaname":"新区","cityid":182,"displayorder":7},{"areaid":10300,"areaname":"江阴","cityid":182,"displayorder":8},{"areaid":10301,"areaname":"宜兴","cityid":182,"displayorder":9},{"areaid":10302,
     * "areaname":"无锡周边","cityid":182,"displayorder":10}]},{"id":233,"city":"吴忠","pinyin":"wuzhong","areas":[{"areaid":10727,"areaname":"利通","cityid":233,"displayorder":1},{"areaid":10728,
     * "areaname":"盐池","cityid":233,"displayorder":2},{"areaid":10729,"areaname":"同心","cityid":233,"displayorder":3},{"areaid":10730,"areaname":"青铜峡","cityid":233,"displayorder":4}]},{"id":81,
     * "city":"梧州","pinyin":"wuzhou","areas":[{"areaid":721,"areaname":"蝶山","cityid":81,"displayorder":1},{"areaid":722,"areaname":"万秀","cityid":81,"displayorder":2},{"areaid":723,"areaname":"长洲",
     * "cityid":81,"displayorder":3},{"areaid":724,"areaname":"岑溪","cityid":81,"displayorder":4},{"areaid":725,"areaname":"苍梧","cityid":81,"displayorder":5},{"areaid":726,"areaname":"藤县",
     * "cityid":81,"displayorder":6},{"areaid":727,"areaname":"蒙山","cityid":81,"displayorder":7},{"areaid":728,"areaname":"其它","cityid":81,"displayorder":8}]}],"X":[{"id":56,"city":"厦门",
     * "pinyin":"xiamen","areas":[{"areaid":514,"areaname":"思明","cityid":56,"displayorder":1},{"areaid":515,"areaname":"湖里","cityid":56,"displayorder":2},{"areaid":516,"areaname":"集美","cityid":56,
     * "displayorder":3},{"areaid":517,"areaname":"杏林","cityid":56,"displayorder":4},{"areaid":518,"areaname":"海沧","cityid":56,"displayorder":5},{"areaid":519,"areaname":"同安","cityid":56,
     * "displayorder":6},{"areaid":520,"areaname":"翔安","cityid":56,"displayorder":7},{"areaid":521,"areaname":"厦门周边","cityid":56,"displayorder":8}]},{"id":271,"city":"西安","pinyin":"xian",
     * "areas":[{"areaid":11060,"areaname":"莲湖","cityid":271,"displayorder":1},{"areaid":11061,"areaname":"新城","cityid":271,"displayorder":2},{"areaid":11062,"areaname":"碑林","cityid":271,
     * "displayorder":3},{"areaid":11063,"areaname":"雁塔","cityid":271,"displayorder":4},{"areaid":11064,"areaname":"灞桥","cityid":271,"displayorder":5},{"areaid":11065,"areaname":"蓝田","cityid":271,
     * "displayorder":6},{"areaid":11066,"areaname":"未央","cityid":271,"displayorder":7},{"areaid":11067,"areaname":"阎良","cityid":271,"displayorder":8},{"areaid":11068,"areaname":"临潼","cityid":271,
     * "displayorder":9},{"areaid":11069,"areaname":"长安","cityid":271,"displayorder":10},{"areaid":11070,"areaname":"高新","cityid":271,"displayorder":11},{"areaid":11071,"areaname":"沣渭新区",
     * "cityid":271,"displayorder":12},{"areaid":11072,"areaname":"曲江新区","cityid":271,"displayorder":13},{"areaid":11073,"areaname":"高陵","cityid":271,"displayorder":14},{"areaid":11074,
     * "areaname":"户县","cityid":271,"displayorder":15},{"areaid":11075,"areaname":"周至","cityid":271,"displayorder":16},{"areaid":11076,"areaname":"浐灞","cityid":271,"displayorder":17},
     * {"areaid":11077,"areaname":"泾渭新区","cityid":271,"displayorder":18},{"areaid":11078,"areaname":"其他","cityid":271,"displayorder":19}]},{"id":146,"city":"襄樊","pinyin":"xiangfan",
     * "areas":[{"areaid":1315,"areaname":"樊城","cityid":146,"displayorder":1},{"areaid":1316,"areaname":"襄城","cityid":146,"displayorder":2},{"areaid":1317,"areaname":"襄州","cityid":146,
     * "displayorder":3},{"areaid":1318,"areaname":"枣阳","cityid":146,"displayorder":4},{"areaid":1319,"areaname":"宜城","cityid":146,"displayorder":5},{"areaid":1320,"areaname":"老河","cityid":146,
     * "displayorder":6},{"areaid":1321,"areaname":"口鱼","cityid":146,"displayorder":7},{"areaid":1322,"areaname":"梁洲","cityid":146,"displayorder":8},{"areaid":1323,"areaname":"高新区","cityid":146,
     * "displayorder":9},{"areaid":1324,"areaname":"襄阳周边","cityid":146,"displayorder":10}]},{"id":160,"city":"湘潭","pinyin":"xiangtan","areas":[{"areaid":10104,"areaname":"雨湖","cityid":160,
     * "displayorder":1},{"areaid":10105,"areaname":"岳塘","cityid":160,"displayorder":2},{"areaid":10106,"areaname":"九华经济开发区","cityid":160,"displayorder":3},{"areaid":10107,"areaname":"韶山",
     * "cityid":160,"displayorder":4},{"areaid":10108,"areaname":"湘潭县","cityid":160,"displayorder":5},{"areaid":10109,"areaname":"湘乡","cityid":160,"displayorder":6},{"areaid":10110,
     * "areaname":"湘潭周边","cityid":160,"displayorder":7}]},{"id":171,"city":"湘西","pinyin":"xiangxi","areas":[{"areaid":10192,"areaname":"吉首","cityid":171,"displayorder":1},{"areaid":10193,
     * "areaname":"泸溪","cityid":171,"displayorder":2},{"areaid":10194,"areaname":"凤凰","cityid":171,"displayorder":3},{"areaid":10195,"areaname":"花垣","cityid":171,"displayorder":4},{"areaid":10196,
     * "areaname":"保靖","cityid":171,"displayorder":5},{"areaid":10197,"areaname":"古丈","cityid":171,"displayorder":6},{"areaid":10198,"areaname":"永顺","cityid":171,"displayorder":7},{"areaid":10199,
     * "areaname":"龙山","cityid":171,"displayorder":8},{"areaid":10200,"areaname":"湘西周边","cityid":171,"displayorder":9}]},{"id":152,"city":"咸宁","pinyin":"xianning","areas":[{"areaid":10039,
     * "areaname":"咸安","cityid":152,"displayorder":1},{"areaid":10040,"areaname":"赤壁","cityid":152,"displayorder":2},{"areaid":10041,"areaname":"嘉鱼","cityid":152,"displayorder":3},{"areaid":10042,
     * "areaname":"通城","cityid":152,"displayorder":4},{"areaid":10043,"areaname":"崇阳","cityid":152,"displayorder":5},{"areaid":10044,"areaname":"通山","cityid":152,"displayorder":6},{"areaid":10045,
     * "areaname":"咸宁周边","cityid":152,"displayorder":7}]},{"id":274,"city":"咸阳","pinyin":"xianyang","areas":[{"areaid":11095,"areaname":"秦都","cityid":274,"displayorder":1},{"areaid":11096,
     * "areaname":"杨陵","cityid":274,"displayorder":2},{"areaid":11097,"areaname":"渭城","cityid":274,"displayorder":3},{"areaid":11098,"areaname":"兴平","cityid":274,"displayorder":4},{"areaid":11099,
     * "areaname":"三原","cityid":274,"displayorder":5},{"areaid":11100,"areaname":"泾阳","cityid":274,"displayorder":6},{"areaid":11101,"areaname":"乾县","cityid":274,"displayorder":7},{"areaid":11102,
     * "areaname":"礼泉","cityid":274,"displayorder":8},{"areaid":11103,"areaname":"永寿","cityid":274,"displayorder":9},{"areaid":11104,"areaname":"彬县","cityid":274,"displayorder":10},{"areaid":11105,
     * "areaname":"长武","cityid":274,"displayorder":11},{"areaid":11106,"areaname":"旬邑","cityid":274,"displayorder":12},{"areaid":11107,"areaname":"淳化","cityid":274,"displayorder":13},
     * {"areaid":11108,"areaname":"武功","cityid":274,"displayorder":14}]},{"id":155,"city":"孝感","pinyin":"xiaogan","areas":[{"areaid":10061,"areaname":"孝南","cityid":155,"displayorder":1},
     * {"areaid":10062,"areaname":"汉川","cityid":155,"displayorder":2},{"areaid":10063,"areaname":"孝昌","cityid":155,"displayorder":3},{"areaid":10064,"areaname":"云梦","cityid":155,"displayorder":4},
     * {"areaid":10065,"areaname":"应城","cityid":155,"displayorder":5},{"areaid":10066,"areaname":"大悟","cityid":155,"displayorder":6},{"areaid":10067,"areaname":"安陆","cityid":155,"displayorder":7},
     * {"areaid":10068,"areaname":"其他","cityid":155,"displayorder":8}]},{"id":229,"city":"锡林郭勒","pinyin":"xilinguole","areas":[{"areaid":10703,"areaname":"二连浩特","cityid":229,"displayorder":1},
     * {"areaid":10704,"areaname":"锡林浩特","cityid":229,"displayorder":2},{"areaid":10705,"areaname":"阿巴嘎","cityid":229,"displayorder":3},{"areaid":10706,"areaname":"苏尼特左","cityid":229,
     * "displayorder":4},{"areaid":10707,"areaname":"苏尼特右","cityid":229,"displayorder":5},{"areaid":10708,"areaname":"东乌珠穆沁","cityid":229,"displayorder":6},{"areaid":10709,"areaname":"西乌珠穆沁",
     * "cityid":229,"displayorder":7},{"areaid":10710,"areaname":"太仆寺","cityid":229,"displayorder":8},{"areaid":10711,"areaname":"镶黄","cityid":229,"displayorder":9},{"areaid":10712,
     * "areaname":"正镶白","cityid":229,"displayorder":10},{"areaid":10713,"areaname":"正蓝","cityid":229,"displayorder":11},{"areaid":10714,"areaname":"多伦","cityid":229,"displayorder":12}]},{"id":228,
     * "city":"兴安盟","pinyin":"xinganmeng","areas":[{"areaid":10696,"areaname":"阿尔山","cityid":228,"displayorder":1},{"areaid":10697,"areaname":"乌兰浩特","cityid":228,"displayorder":2},{"areaid":10698,
     * "areaname":"突泉","cityid":228,"displayorder":3},{"areaid":10699,"areaname":"扎赉特旗","cityid":228,"displayorder":4},{"areaid":10700,"areaname":"科尔沁右翼中旗","cityid":228,"displayorder":5},
     * {"areaid":10701,"areaname":"科尔沁右翼前旗","cityid":228,"displayorder":6},{"areaid":10702,"areaname":"兴安盟周边","cityid":228,"displayorder":7}]},{"id":107,"city":"邢台","pinyin":"xingtai",
     * "areas":[{"areaid":941,"areaname":"桥东","cityid":107,"displayorder":1},{"areaid":942,"areaname":"桥西","cityid":107,"displayorder":2},{"areaid":943,"areaname":"南宫","cityid":107,
     * "displayorder":3},{"areaid":944,"areaname":"沙河","cityid":107,"displayorder":4},{"areaid":945,"areaname":"清河","cityid":107,"displayorder":5},{"areaid":946,"areaname":"南和","cityid":107,
     * "displayorder":6},{"areaid":947,"areaname":"平乡","cityid":107,"displayorder":7},{"areaid":948,"areaname":"邢台县","cityid":107,"displayorder":8},{"areaid":949,"areaname":"其他","cityid":107,
     * "displayorder":9}]},{"id":236,"city":"西宁","pinyin":"xining","areas":[{"areaid":10739,"areaname":"城东","cityid":236,"displayorder":1},{"areaid":10740,"areaname":"城中","cityid":236,
     * "displayorder":2},{"areaid":10741,"areaname":"城西","cityid":236,"displayorder":3},{"areaid":10742,"areaname":"城北","cityid":236,"displayorder":4},{"areaid":10743,"areaname":"大通自治县",
     * "cityid":236,"displayorder":5},{"areaid":10744,"areaname":"湟中","cityid":236,"displayorder":6},{"areaid":10745,"areaname":"湟源","cityid":236,"displayorder":7},{"areaid":10746,
     * "areaname":"城南新区","cityid":236,"displayorder":8},{"areaid":10747,"areaname":"海湖新区","cityid":236,"displayorder":9},{"areaid":10748,"areaname":"生物园区","cityid":236,"displayorder":10}]},
     * {"id":121,"city":"新乡","pinyin":"xinxiang","areas":[{"areaid":1088,"areaname":"红旗","cityid":121,"displayorder":1},{"areaid":1089,"areaname":"卫滨","cityid":121,"displayorder":2},{"areaid":1090,
     * "areaname":"凤泉","cityid":121,"displayorder":3},{"areaid":1091,"areaname":"牧野","cityid":121,"displayorder":4},{"areaid":1092,"areaname":"卫辉","cityid":121,"displayorder":5},{"areaid":1093,
     * "areaname":"辉县","cityid":121,"displayorder":6},{"areaid":1094,"areaname":"新乡县","cityid":121,"displayorder":7},{"areaid":1095,"areaname":"长垣","cityid":121,"displayorder":8},{"areaid":1096,
     * "areaname":"其他","cityid":121,"displayorder":9}]},{"id":118,"city":"信阳","pinyin":"xinyang","areas":[{"areaid":1061,"areaname":"信阳市区","cityid":118,"displayorder":1},{"areaid":1062,
     * "areaname":"浉河","cityid":118,"displayorder":2},{"areaid":1063,"areaname":"平桥","cityid":118,"displayorder":3},{"areaid":1064,"areaname":"固始","cityid":118,"displayorder":4},{"areaid":1065,
     * "areaname":"潢川","cityid":118,"displayorder":5},{"areaid":1066,"areaname":"光山","cityid":118,"displayorder":6},{"areaid":1067,"areaname":"罗山","cityid":118,"displayorder":7},{"areaid":1068,
     * "areaname":"淮滨","cityid":118,"displayorder":8},{"areaid":1069,"areaname":"新县","cityid":118,"displayorder":9},{"areaid":1070,"areaname":"息县","cityid":118,"displayorder":10},{"areaid":1071,
     * "areaname":"商城","cityid":118,"displayorder":11}]},{"id":198,"city":"新余","pinyin":"xinyu","areas":[{"areaid":10441,"areaname":"渝水","cityid":198,"displayorder":1},{"areaid":10442,
     * "areaname":"仙女湖","cityid":198,"displayorder":2},{"areaid":10443,"areaname":"分宜","cityid":198,"displayorder":3},{"areaid":10444,"areaname":"新余周边","cityid":198,"displayorder":4}]},{"id":268,
     * "city":"忻州","pinyin":"xinzhou","areas":[{"areaid":11016,"areaname":"忻府","cityid":268,"displayorder":1},{"areaid":11017,"areaname":"定襄","cityid":268,"displayorder":2},{"areaid":11018,
     * "areaname":"五台","cityid":268,"displayorder":3},{"areaid":11019,"areaname":"代县","cityid":268,"displayorder":4},{"areaid":11020,"areaname":"繁峙","cityid":268,"displayorder":5},{"areaid":11021,
     * "areaname":"宁武","cityid":268,"displayorder":6},{"areaid":11022,"areaname":"静乐","cityid":268,"displayorder":7},{"areaid":11023,"areaname":"神池","cityid":268,"displayorder":8},{"areaid":11024,
     * "areaname":"五寨","cityid":268,"displayorder":9},{"areaid":11025,"areaname":"岢岚","cityid":268,"displayorder":10},{"areaid":11026,"areaname":"河曲","cityid":268,"displayorder":11},
     * {"areaid":11027,"areaname":"保德","cityid":268,"displayorder":12},{"areaid":11028,"areaname":"偏关","cityid":268,"displayorder":13},{"areaid":11029,"areaname":"原平","cityid":268,
     * "displayorder":14}]},{"id":332,"city":"西双版纳","pinyin":"xishuangbanna","areas":[{"areaid":11624,"areaname":"景洪","cityid":332,"displayorder":1},{"areaid":11625,"areaname":"勐海","cityid":332,
     * "displayorder":2},{"areaid":11626,"areaname":"勐腊","cityid":332,"displayorder":3}]},{"id":53,"city":"宣城","pinyin":"xuancheng","areas":[{"areaid":486,"areaname":"宣州","cityid":53,
     * "displayorder":1},{"areaid":487,"areaname":"宁国","cityid":53,"displayorder":2},{"areaid":488,"areaname":"郎溪","cityid":53,"displayorder":3},{"areaid":489,"areaname":"广德","cityid":53,
     * "displayorder":4},{"areaid":490,"areaname":"泾县","cityid":53,"displayorder":5},{"areaid":491,"areaname":"绩溪","cityid":53,"displayorder":6},{"areaid":492,"areaname":"旌德","cityid":53,
     * "displayorder":7},{"areaid":493,"areaname":"其它","cityid":53,"displayorder":8}]},{"id":124,"city":"许昌","pinyin":"xuchang","areas":[{"areaid":1112,"areaname":"长葛","cityid":124,
     * "displayorder":1},{"areaid":1113,"areaname":"魏都","cityid":124,"displayorder":2},{"areaid":1114,"areaname":"禹州","cityid":124,"displayorder":3},{"areaid":1115,"areaname":"许昌县","cityid":124,
     * "displayorder":4},{"areaid":1116,"areaname":"鄢陵","cityid":124,"displayorder":5},{"areaid":1117,"areaname":"襄城","cityid":124,"displayorder":6},{"areaid":1118,"areaname":"其他","cityid":124,
     * "displayorder":7}]},{"id":183,"city":"徐州","pinyin":"xuzhou","areas":[{"areaid":10303,"areaname":"云龙","cityid":183,"displayorder":1},{"areaid":10304,"areaname":"鼓楼","cityid":183,
     * "displayorder":2},{"areaid":10305,"areaname":"九里","cityid":183,"displayorder":3},{"areaid":10306,"areaname":"贾汪","cityid":183,"displayorder":4},{"areaid":10307,"areaname":"泉山","cityid":183,
     * "displayorder":5},{"areaid":10308,"areaname":"邳州","cityid":183,"displayorder":6},{"areaid":10309,"areaname":"新沂","cityid":183,"displayorder":7},{"areaid":10310,"areaname":"铜山","cityid":183,
     * "displayorder":8},{"areaid":10311,"areaname":"睢宁","cityid":183,"displayorder":9},{"areaid":10312,"areaname":"沛县","cityid":183,"displayorder":10},{"areaid":10313,"areaname":"丰县","cityid":183,
     * "displayorder":11},{"areaid":10314,"areaname":"金山桥开发区","cityid":183,"displayorder":12},{"areaid":10315,"areaname":"新城区","cityid":183,"displayorder":13},{"areaid":10316,"areaname":"徐州周边",
     * "cityid":183,"displayorder":14}]}],"Y":[{"id":298,"city":"雅安","pinyin":"yaan","areas":[{"areaid":11312,"areaname":"雨城","cityid":298,"displayorder":1},{"areaid":11313,"areaname":"名山",
     * "cityid":298,"displayorder":2},{"areaid":11314,"areaname":"荥经","cityid":298,"displayorder":3},{"areaid":11315,"areaname":"汉源","cityid":298,"displayorder":4},{"areaid":11316,"areaname":"石棉",
     * "cityid":298,"displayorder":5},{"areaid":11317,"areaname":"天全","cityid":298,"displayorder":6},{"areaid":11318,"areaname":"芦山","cityid":298,"displayorder":7},{"areaid":11319,"areaname":"宝兴",
     * "cityid":298,"displayorder":8}]},{"id":276,"city":"延安","pinyin":"yanan","areas":[{"areaid":11120,"areaname":"宝塔","cityid":276,"displayorder":1},{"areaid":11121,"areaname":"延长","cityid":276,
     * "displayorder":2},{"areaid":11122,"areaname":"延川","cityid":276,"displayorder":3},{"areaid":11123,"areaname":"子长","cityid":276,"displayorder":4},{"areaid":11124,"areaname":"安塞","cityid":276,
     * "displayorder":5},{"areaid":11125,"areaname":"志丹","cityid":276,"displayorder":6},{"areaid":11126,"areaname":"吴起","cityid":276,"displayorder":7},{"areaid":11127,"areaname":"甘泉","cityid":276,
     * "displayorder":8},{"areaid":11128,"areaname":"富县","cityid":276,"displayorder":9},{"areaid":11129,"areaname":"洛川","cityid":276,"displayorder":10},{"areaid":11130,"areaname":"宜川","cityid":276,
     * "displayorder":11},{"areaid":11131,"areaname":"黄龙","cityid":276,"displayorder":12},{"areaid":11132,"areaname":"黄陵","cityid":276,"displayorder":13}]},{"id":180,"city":"延边","pinyin":"yanbian",
     * "areas":[{"areaid":10269,"areaname":"延吉","cityid":180,"displayorder":1},{"areaid":10270,"areaname":"敦化","cityid":180,"displayorder":2},{"areaid":10271,"areaname":"珲春","cityid":180,
     * "displayorder":3},{"areaid":10272,"areaname":"和龙","cityid":180,"displayorder":4},{"areaid":10273,"areaname":"图们","cityid":180,"displayorder":5},{"areaid":10274,"areaname":"龙井","cityid":180,
     * "displayorder":6},{"areaid":10275,"areaname":"汪清","cityid":180,"displayorder":7},{"areaid":10276,"areaname":"安图","cityid":180,"displayorder":8},{"areaid":10277,"areaname":"其他","cityid":180,
     * "displayorder":9}]},{"id":189,"city":"盐城","pinyin":"yancheng","areas":[{"areaid":10361,"areaname":"亭湖","cityid":189,"displayorder":1},{"areaid":10362,"areaname":"盐都","cityid":189,
     * "displayorder":2},{"areaid":10363,"areaname":"大丰","cityid":189,"displayorder":3},{"areaid":10364,"areaname":"东台","cityid":189,"displayorder":4},{"areaid":10365,"areaname":"阜宁","cityid":189,
     * "displayorder":5},{"areaid":10366,"areaname":"建湖","cityid":189,"displayorder":6},{"areaid":10367,"areaname":"射阳","cityid":189,"displayorder":7},{"areaid":10368,"areaname":"滨海","cityid":189,
     * "displayorder":8},{"areaid":10369,"areaname":"响水","cityid":189,"displayorder":9},{"areaid":10370,"areaname":"其他","cityid":189,"displayorder":10}]},{"id":123,"city":"濮阳","pinyin":"yang",
     * "areas":[{"areaid":1104,"areaname":"华龙","cityid":123,"displayorder":1},{"areaid":1105,"areaname":"高新区","cityid":123,"displayorder":2},{"areaid":1106,"areaname":"濮阳","cityid":123,
     * "displayorder":3},{"areaid":1107,"areaname":"清丰","cityid":123,"displayorder":4},{"areaid":1108,"areaname":"南乐","cityid":123,"displayorder":5},{"areaid":1109,"areaname":"范县","cityid":123,
     * "displayorder":6},{"areaid":1110,"areaname":"台前","cityid":123,"displayorder":7},{"areaid":1111,"areaname":"其他","cityid":123,"displayorder":8}]},{"id":19,"city":"阳江","pinyin":"yangjiang",
     * "areas":[{"areaid":200,"areaname":"江城","cityid":19,"displayorder":1},{"areaid":201,"areaname":"阳春","cityid":19,"displayorder":2},{"areaid":202,"areaname":"阳东","cityid":19,"displayorder":3},
     * {"areaid":203,"areaname":"阳西","cityid":19,"displayorder":4},{"areaid":204,"areaname":"海陵","cityid":19,"displayorder":5},{"areaid":205,"areaname":"岗侨","cityid":19,"displayorder":6},
     * {"areaid":206,"areaname":"高新区","cityid":19,"displayorder":7}]},{"id":262,"city":"阳泉","pinyin":"yangquan","areas":[{"areaid":10962,"areaname":"城区","cityid":262,"displayorder":1},
     * {"areaid":10963,"areaname":"矿区","cityid":262,"displayorder":2},{"areaid":10964,"areaname":"郊区","cityid":262,"displayorder":3},{"areaid":10965,"areaname":"平定","cityid":262,"displayorder":4},
     * {"areaid":10966,"areaname":"盂县","cityid":262,"displayorder":5}]},{"id":185,"city":"扬州","pinyin":"yangzhou","areas":[{"areaid":10325,"areaname":"广陵","cityid":185,"displayorder":1},
     * {"areaid":10326,"areaname":"维扬","cityid":185,"displayorder":2},{"areaid":10327,"areaname":"邗江","cityid":185,"displayorder":3},{"areaid":10328,"areaname":"江都","cityid":185,"displayorder":4},
     * {"areaid":10329,"areaname":"仪征","cityid":185,"displayorder":5},{"areaid":10330,"areaname":"高邮","cityid":185,"displayorder":6},{"areaid":10331,"areaname":"宝应县","cityid":185,"displayorder":7},
     * {"areaid":10332,"areaname":"扬州周边","cityid":185,"displayorder":8}]},{"id":248,"city":"烟台","pinyin":"yantai","areas":[{"areaid":10826,"areaname":"芝罘","cityid":248,"displayorder":1},
     * {"areaid":10827,"areaname":"福山","cityid":248,"displayorder":2},{"areaid":10828,"areaname":"牟平","cityid":248,"displayorder":3},{"areaid":10829,"areaname":"莱山","cityid":248,"displayorder":4},
     * {"areaid":10830,"areaname":"开发区","cityid":248,"displayorder":5},{"areaid":10831,"areaname":"龙口","cityid":248,"displayorder":6},{"areaid":10832,"areaname":"莱阳","cityid":248,"displayorder":7},
     * {"areaid":10833,"areaname":"莱州","cityid":248,"displayorder":8},{"areaid":10834,"areaname":"蓬莱","cityid":248,"displayorder":9},{"areaid":10835,"areaname":"招远","cityid":248,"displayorder":10},
     * {"areaid":10836,"areaname":"栖霞","cityid":248,"displayorder":11},{"areaid":10837,"areaname":"海阳","cityid":248,"displayorder":12},{"areaid":10838,"areaname":"长岛","cityid":248,
     * "displayorder":13},{"areaid":10839,"areaname":"高新","cityid":248,"displayorder":14}]},{"id":292,"city":"宜宾","pinyin":"yibin","areas":[{"areaid":11276,"areaname":"翠屏","cityid":292,
     * "displayorder":1},{"areaid":11277,"areaname":"宜宾县","cityid":292,"displayorder":2},{"areaid":11278,"areaname":"南溪","cityid":292,"displayorder":3},{"areaid":11279,"areaname":"江安","cityid":292,
     * "displayorder":4},{"areaid":11280,"areaname":"长宁","cityid":292,"displayorder":5},{"areaid":11281,"areaname":"高县","cityid":292,"displayorder":6},{"areaid":11282,"areaname":"珙县","cityid":292,
     * "displayorder":7},{"areaid":11283,"areaname":"筠连","cityid":292,"displayorder":8},{"areaid":11284,"areaname":"兴文","cityid":292,"displayorder":9},{"areaid":11285,"areaname":"屏山","cityid":292,
     * "displayorder":10}]},{"id":150,"city":"宜昌","pinyin":"yichang","areas":[{"areaid":10015,"areaname":"西陵","cityid":150,"displayorder":1},{"areaid":10016,"areaname":"伍家岗","cityid":150,
     * "displayorder":2},{"areaid":10017,"areaname":"点军","cityid":150,"displayorder":3},{"areaid":10018,"areaname":"猇亭","cityid":150,"displayorder":4},{"areaid":10019,"areaname":"夷陵","cityid":150,
     * "displayorder":5},{"areaid":10020,"areaname":"东山","cityid":150,"displayorder":6},{"areaid":10021,"areaname":"宜都","cityid":150,"displayorder":7},{"areaid":10022,"areaname":"当阳","cityid":150,
     * "displayorder":8},{"areaid":10023,"areaname":"枝江","cityid":150,"displayorder":9},{"areaid":10024,"areaname":"长阳","cityid":150,"displayorder":10},{"areaid":10025,"areaname":"远安","cityid":150,
     * "displayorder":11},{"areaid":10026,"areaname":"兴山","cityid":150,"displayorder":12},{"areaid":10027,"areaname":"秭归","cityid":150,"displayorder":13},{"areaid":10028,"areaname":"五峰",
     * "cityid":150,"displayorder":14},{"areaid":10029,"areaname":"宜昌周边","cityid":150,"displayorder":15}]},{"id":138,"city":"伊春","pinyin":"yichun","areas":[{"areaid":1240,"areaname":"伊春",
     * "cityid":138,"displayorder":1},{"areaid":1241,"areaname":"南岔","cityid":138,"displayorder":2},{"areaid":1242,"areaname":"友好","cityid":138,"displayorder":3},{"areaid":1243,"areaname":"西林",
     * "cityid":138,"displayorder":4},{"areaid":1244,"areaname":"翠峦","cityid":138,"displayorder":5},{"areaid":1245,"areaname":"新青","cityid":138,"displayorder":6},{"areaid":1246,"areaname":"伊春周边",
     * "cityid":138,"displayorder":7}]},{"id":203,"city":"宜春","pinyin":"yichun","areas":[{"areaid":10494,"areaname":"袁州","cityid":203,"displayorder":1},{"areaid":10495,"areaname":"丰城","cityid":203,
     * "displayorder":2},{"areaid":10496,"areaname":"高安","cityid":203,"displayorder":3},{"areaid":10497,"areaname":"樟树","cityid":203,"displayorder":4},{"areaid":10498,"areaname":"万载","cityid":203,
     * "displayorder":5},{"areaid":10499,"areaname":"上高","cityid":203,"displayorder":6},{"areaid":10500,"areaname":"奉新","cityid":203,"displayorder":7},{"areaid":10501,"areaname":"宜丰","cityid":203,
     * "displayorder":8},{"areaid":10502,"areaname":"其它","cityid":203,"displayorder":9}]},{"id":319,"city":"伊犁","pinyin":"yili","areas":[{"areaid":11511,"areaname":"伊宁市","cityid":319,
     * "displayorder":1},{"areaid":11512,"areaname":"奎屯","cityid":319,"displayorder":2},{"areaid":11513,"areaname":"霍城","cityid":319,"displayorder":3},{"areaid":11514,"areaname":"巩留","cityid":319,
     * "displayorder":4},{"areaid":11515,"areaname":"新源","cityid":319,"displayorder":5},{"areaid":11516,"areaname":"昭苏","cityid":319,"displayorder":6},{"areaid":11517,"areaname":"特克斯","cityid":319,
     * "displayorder":7},{"areaid":11518,"areaname":"尼勒克","cityid":319,"displayorder":8},{"areaid":11519,"areaname":"察布查尔","cityid":319,"displayorder":9},{"areaid":11520,"areaname":"伊宁县",
     * "cityid":319,"displayorder":10}]},{"id":231,"city":"银川","pinyin":"yinchuan","areas":[{"areaid":10718,"areaname":"兴庆","cityid":231,"displayorder":1},{"areaid":10719,"areaname":"金凤",
     * "cityid":231,"displayorder":2},{"areaid":10720,"areaname":"西夏","cityid":231,"displayorder":3},{"areaid":10721,"areaname":"贺兰","cityid":231,"displayorder":4},{"areaid":10722,"areaname":"永宁",
     * "cityid":231,"displayorder":5},{"areaid":10723,"areaname":"灵武","cityid":231,"displayorder":6}]},{"id":212,"city":"营口","pinyin":"yingkou","areas":[{"areaid":10561,"areaname":"站前",
     * "cityid":212,"displayorder":1},{"areaid":10562,"areaname":"西市","cityid":212,"displayorder":2},{"areaid":10563,"areaname":"鲅鱼圈","cityid":212,"displayorder":3},{"areaid":10564,"areaname":"老边",
     * "cityid":212,"displayorder":4},{"areaid":10565,"areaname":"盖州","cityid":212,"displayorder":5},{"areaid":10566,"areaname":"大石桥","cityid":212,"displayorder":6},{"areaid":10567,"areaname":"其它",
     * "cityid":212,"displayorder":7}]},{"id":199,"city":"鹰潭","pinyin":"yingtan","areas":[{"areaid":10445,"areaname":"月湖","cityid":199,"displayorder":1},{"areaid":10446,"areaname":"贵溪",
     * "cityid":199,"displayorder":2},{"areaid":10447,"areaname":"余江","cityid":199,"displayorder":3},{"areaid":10448,"areaname":"龙虎山","cityid":199,"displayorder":4},{"areaid":10449,"areaname":"其他",
     * "cityid":199,"displayorder":5}]},{"id":36,"city":"义乌","pinyin":"yiwu","areas":[{"areaid":357,"areaname":"稠城","cityid":36,"displayorder":1},{"areaid":358,"areaname":"北苑","cityid":36,
     * "displayorder":2},{"areaid":359,"areaname":"稠江","cityid":36,"displayorder":3},{"areaid":360,"areaname":"江东","cityid":36,"displayorder":4},{"areaid":361,"areaname":"后宅","cityid":36,
     * "displayorder":5},{"areaid":362,"areaname":"城西","cityid":36,"displayorder":6},{"areaid":363,"areaname":"廿三里","cityid":36,"displayorder":7},{"areaid":364,"areaname":"义乌市区","cityid":36,
     * "displayorder":8},{"areaid":365,"areaname":"义乌周边","cityid":36,"displayorder":9}]},{"id":166,"city":"益阳","pinyin":"yiyang","areas":[{"areaid":10151,"areaname":"赫山","cityid":166,
     * "displayorder":1},{"areaid":10152,"areaname":"资阳","cityid":166,"displayorder":2},{"areaid":10153,"areaname":"沅江","cityid":166,"displayorder":3},{"areaid":10154,"areaname":"南县","cityid":166,
     * "displayorder":4},{"areaid":10155,"areaname":"桃江","cityid":166,"displayorder":5},{"areaid":10156,"areaname":"安化","cityid":166,"displayorder":6},{"areaid":10157,"areaname":"益阳周边",
     * "cityid":166,"displayorder":7}]},{"id":168,"city":"永州","pinyin":"yongzhou","areas":[{"areaid":10167,"areaname":"冷水滩","cityid":168,"displayorder":1},{"areaid":10168,"areaname":"零陵",
     * "cityid":168,"displayorder":2},{"areaid":10169,"areaname":"祁阳","cityid":168,"displayorder":3},{"areaid":10170,"areaname":"宁远","cityid":168,"displayorder":4},{"areaid":10171,"areaname":"东安",
     * "cityid":168,"displayorder":5},{"areaid":10172,"areaname":"双牌","cityid":168,"displayorder":6},{"areaid":10173,"areaname":"道县","cityid":168,"displayorder":7},{"areaid":10174,"areaname":"江华",
     * "cityid":168,"displayorder":8},{"areaid":10175,"areaname":"江永","cityid":168,"displayorder":9},{"areaid":10176,"areaname":"新田","cityid":168,"displayorder":10},{"areaid":10177,"areaname":"蓝山",
     * "cityid":168,"displayorder":11}]},{"id":163,"city":"岳阳","pinyin":"yueyang","areas":[{"areaid":10131,"areaname":"岳阳楼","cityid":163,"displayorder":1},{"areaid":10132,"areaname":"云溪",
     * "cityid":163,"displayorder":2},{"areaid":10133,"areaname":"临湘","cityid":163,"displayorder":3},{"areaid":10134,"areaname":"汨罗","cityid":163,"displayorder":4},{"areaid":10135,"areaname":"君山",
     * "cityid":163,"displayorder":5},{"areaid":10136,"areaname":"其他","cityid":163,"displayorder":6}]},{"id":91,"city":"玉林","pinyin":"yulin","areas":[{"areaid":789,"areaname":"榆阳","cityid":91,
     * "displayorder":1},{"areaid":790,"areaname":"神木","cityid":91,"displayorder":2},{"areaid":791,"areaname":"府谷","cityid":91,"displayorder":3},{"areaid":792,"areaname":"横山","cityid":91,
     * "displayorder":4},{"areaid":793,"areaname":"靖边","cityid":91,"displayorder":5},{"areaid":794,"areaname":"定边","cityid":91,"displayorder":6},{"areaid":795,"areaname":"绥德","cityid":91,
     * "displayorder":7},{"areaid":796,"areaname":"米脂","cityid":91,"displayorder":8},{"areaid":797,"areaname":"佳县","cityid":91,"displayorder":9},{"areaid":798,"areaname":"其它","cityid":91,
     * "displayorder":10}]},{"id":278,"city":"榆林","pinyin":"yulin","areas":[{"areaid":11144,"areaname":"榆阳","cityid":278,"displayorder":1},{"areaid":11145,"areaname":"神木","cityid":278,
     * "displayorder":2},{"areaid":11146,"areaname":"府谷","cityid":278,"displayorder":3},{"areaid":11147,"areaname":"横山","cityid":278,"displayorder":4},{"areaid":11148,"areaname":"靖边","cityid":278,
     * "displayorder":5},{"areaid":11149,"areaname":"定边","cityid":278,"displayorder":6},{"areaid":11150,"areaname":"绥德","cityid":278,"displayorder":7},{"areaid":11151,"areaname":"米脂","cityid":278,
     * "displayorder":8},{"areaid":11152,"areaname":"佳县","cityid":278,"displayorder":9},{"areaid":11153,"areaname":"吴堡","cityid":278,"displayorder":10},{"areaid":11154,"areaname":"清涧","cityid":278,
     * "displayorder":11},{"areaid":11155,"areaname":"子洲","cityid":278,"displayorder":12},{"areaid":11156,"areaname":"开发区","cityid":278,"displayorder":13},{"areaid":11157,"areaname":"东沙",
     * "cityid":278,"displayorder":14},{"areaid":11158,"areaname":"西沙","cityid":278,"displayorder":15},{"areaid":11159,"areaname":"南郊","cityid":278,"displayorder":16},{"areaid":11160,
     * "areaname":"北郊","cityid":278,"displayorder":17},{"areaid":11161,"areaname":"市中心","cityid":278,"displayorder":18}]},{"id":267,"city":"运城","pinyin":"yuncheng","areas":[{"areaid":11003,
     * "areaname":"盐湖","cityid":267,"displayorder":1},{"areaid":11004,"areaname":"临猗","cityid":267,"displayorder":2},{"areaid":11005,"areaname":"万荣","cityid":267,"displayorder":3},{"areaid":11006,
     * "areaname":"闻喜","cityid":267,"displayorder":4},{"areaid":11007,"areaname":"稷山","cityid":267,"displayorder":5},{"areaid":11008,"areaname":"新绛","cityid":267,"displayorder":6},{"areaid":11009,
     * "areaname":"绛县","cityid":267,"displayorder":7},{"areaid":11010,"areaname":"垣曲","cityid":267,"displayorder":8},{"areaid":11011,"areaname":"夏县","cityid":267,"displayorder":9},{"areaid":11012,
     * "areaname":"平陆","cityid":267,"displayorder":10},{"areaid":11013,"areaname":"芮城","cityid":267,"displayorder":11},{"areaid":11014,"areaname":"永济","cityid":267,"displayorder":12},
     * {"areaid":11015,"areaname":"河津","cityid":267,"displayorder":13}]},{"id":25,"city":"云浮","pinyin":"yunfu","areas":[{"areaid":259,"areaname":"云城","cityid":25,"displayorder":1},{"areaid":260,
     * "areaname":"罗定","cityid":25,"displayorder":2},{"areaid":261,"areaname":"云安","cityid":25,"displayorder":3},{"areaid":262,"areaname":"新兴","cityid":25,"displayorder":4},{"areaid":263,
     * "areaname":"郁南","cityid":25,"displayorder":5},{"areaid":264,"areaname":"云浮周边","cityid":25,"displayorder":6}]},{"id":241,"city":"玉树","pinyin":"yushu","areas":[{"areaid":10769,
     * "areaname":"玉树县","cityid":241,"displayorder":1},{"areaid":10770,"areaname":"杂多","cityid":241,"displayorder":2},{"areaid":10771,"areaname":"称多","cityid":241,"displayorder":3},{"areaid":10772,
     * "areaname":"治多","cityid":241,"displayorder":4},{"areaid":10773,"areaname":"囊谦","cityid":241,"displayorder":5},{"areaid":10774,"areaname":"曲麻莱","cityid":241,"displayorder":6}]},{"id":325,
     * "city":"玉溪","pinyin":"yuxi","areas":[{"areaid":11565,"areaname":"红塔","cityid":325,"displayorder":1},{"areaid":11566,"areaname":"江川","cityid":325,"displayorder":2},{"areaid":11567,
     * "areaname":"澄江","cityid":325,"displayorder":3},{"areaid":11568,"areaname":"通海","cityid":325,"displayorder":4},{"areaid":11569,"areaname":"华宁","cityid":325,"displayorder":5},{"areaid":11570,
     * "areaname":"易门","cityid":325,"displayorder":6},{"areaid":11571,"areaname":"峨山","cityid":325,"displayorder":7},{"areaid":11572,"areaname":"新平","cityid":325,"displayorder":8},{"areaid":11573,
     * "areaname":"元江","cityid":325,"displayorder":9}]}],"Z":[{"id":246,"city":"枣庄","pinyin":"zaozhuang","areas":[{"areaid":10815,"areaname":"市中","cityid":246,"displayorder":1},{"areaid":10816,
     * "areaname":"薛城","cityid":246,"displayorder":2},{"areaid":10817,"areaname":"峄城","cityid":246,"displayorder":3},{"areaid":10818,"areaname":"台儿庄","cityid":246,"displayorder":4},{"areaid":10819,
     * "areaname":"山亭","cityid":246,"displayorder":5},{"areaid":10820,"areaname":"滕州","cityid":246,"displayorder":6}]},{"id":165,"city":"张家界","pinyin":"zhangjiajie","areas":[{"areaid":10147,
     * "areaname":"永定","cityid":165,"displayorder":1},{"areaid":10148,"areaname":"武陵源","cityid":165,"displayorder":2},{"areaid":10149,"areaname":"慈利","cityid":165,"displayorder":3},{"areaid":10150,
     * "areaname":"桑植","cityid":165,"displayorder":4}]},{"id":109,"city":"张家口","pinyin":"zhangjiakou","areas":[{"areaid":960,"areaname":"桥东","cityid":109,"displayorder":1},{"areaid":961,
     * "areaname":"桥西","cityid":109,"displayorder":2},{"areaid":962,"areaname":"宣化区","cityid":109,"displayorder":3},{"areaid":963,"areaname":"高新区","cityid":109,"displayorder":4},{"areaid":964,
     * "areaname":"张北县","cityid":109,"displayorder":5},{"areaid":965,"areaname":"怀来","cityid":109,"displayorder":6},{"areaid":966,"areaname":"万全","cityid":109,"displayorder":7},{"areaid":967,
     * "areaname":"蔚县","cityid":109,"displayorder":8},{"areaid":968,"areaname":"宣化县","cityid":109,"displayorder":9},{"areaid":969,"areaname":"其他","cityid":109,"displayorder":10}]},{"id":70,
     * "city":"张掖","pinyin":"zhangye","areas":[{"areaid":631,"areaname":"临泽","cityid":70,"displayorder":1},{"areaid":632,"areaname":"高台","cityid":70,"displayorder":2},{"areaid":633,"areaname":"山丹",
     * "cityid":70,"displayorder":3},{"areaid":634,"areaname":"民乐","cityid":70,"displayorder":4},{"areaid":635,"areaname":"肃南","cityid":70,"displayorder":5},{"areaid":636,"areaname":"张掖周边",
     * "cityid":70,"displayorder":6}]},{"id":60,"city":"漳州","pinyin":"zhangzhou","areas":[{"areaid":556,"areaname":"芗城","cityid":60,"displayorder":1},{"areaid":557,"areaname":"龙文","cityid":60,
     * "displayorder":2},{"areaid":558,"areaname":"龙海","cityid":60,"displayorder":3},{"areaid":559,"areaname":"漳浦","cityid":60,"displayorder":4},{"areaid":560,"areaname":"平和","cityid":60,
     * "displayorder":5},{"areaid":561,"areaname":"东山","cityid":60,"displayorder":6},{"areaid":562,"areaname":"诏安","cityid":60,"displayorder":7},{"areaid":563,"areaname":"角美","cityid":60,
     * "displayorder":8},{"areaid":564,"areaname":"长泰县","cityid":60,"displayorder":9},{"areaid":565,"areaname":"云霄县","cityid":60,"displayorder":10},{"areaid":566,"areaname":"南靖县","cityid":60,
     * "displayorder":11},{"areaid":567,"areaname":"华安县","cityid":60,"displayorder":12},{"areaid":568,"areaname":"其他","cityid":60,"displayorder":13}]},{"id":12,"city":"湛江","pinyin":"zhanjiang",
     * "areas":[{"areaid":144,"areaname":"霞山","cityid":12,"displayorder":1},{"areaid":145,"areaname":"赤坎","cityid":12,"displayorder":2},{"areaid":146,"areaname":"开发区","cityid":12,"displayorder":3},
     * {"areaid":147,"areaname":"坡头","cityid":12,"displayorder":4},{"areaid":148,"areaname":"麻章","cityid":12,"displayorder":5},{"areaid":149,"areaname":"廉江","cityid":12,"displayorder":6},
     * {"areaid":150,"areaname":"遂溪","cityid":12,"displayorder":7},{"areaid":151,"areaname":"雷州","cityid":12,"displayorder":8},{"areaid":152,"areaname":"吴川","cityid":12,"displayorder":9},
     * {"areaid":153,"areaname":"徐闻","cityid":12,"displayorder":10},{"areaid":154,"areaname":"其他","cityid":12,"displayorder":11}]},{"id":14,"city":"肇庆","pinyin":"zhaoqing","areas":[{"areaid":162,
     * "areaname":"端州","cityid":14,"displayorder":1},{"areaid":163,"areaname":"鼎湖","cityid":14,"displayorder":2},{"areaid":164,"areaname":"四会","cityid":14,"displayorder":3},{"areaid":165,
     * "areaname":"高要","cityid":14,"displayorder":4},{"areaid":166,"areaname":"德庆","cityid":14,"displayorder":5},{"areaid":167,"areaname":"广宁","cityid":14,"displayorder":6},{"areaid":168,
     * "areaname":"封开","cityid":14,"displayorder":7},{"areaid":169,"areaname":"怀集","cityid":14,"displayorder":8},{"areaid":170,"areaname":"其他","cityid":14,"displayorder":9}]},{"id":327,"city":"昭通",
     * "pinyin":"zhaotong","areas":[{"areaid":11579,"areaname":"昭阳","cityid":327,"displayorder":1},{"areaid":11580,"areaname":"鲁甸","cityid":327,"displayorder":2},{"areaid":11581,"areaname":"巧家",
     * "cityid":327,"displayorder":3},{"areaid":11582,"areaname":"盐津","cityid":327,"displayorder":4},{"areaid":11583,"areaname":"大关","cityid":327,"displayorder":5},{"areaid":11584,"areaname":"永善",
     * "cityid":327,"displayorder":6},{"areaid":11585,"areaname":"绥江","cityid":327,"displayorder":7},{"areaid":11586,"areaname":"镇雄","cityid":327,"displayorder":8},{"areaid":11587,"areaname":"彝良",
     * "cityid":327,"displayorder":9},{"areaid":11588,"areaname":"威信","cityid":327,"displayorder":10},{"areaid":11589,"areaname":"水富","cityid":327,"displayorder":11}]},{"id":115,"city":"郑州",
     * "pinyin":"zhengzhou","areas":[{"areaid":1030,"areaname":"中原","cityid":115,"displayorder":1},{"areaid":1031,"areaname":"二七","cityid":115,"displayorder":2},{"areaid":1032,"areaname":"管城区",
     * "cityid":115,"displayorder":3},{"areaid":1033,"areaname":"金水","cityid":115,"displayorder":4},{"areaid":1034,"areaname":"上街","cityid":115,"displayorder":5},{"areaid":1035,"areaname":"惠济",
     * "cityid":115,"displayorder":6},{"areaid":1036,"areaname":"郑东新区","cityid":115,"displayorder":7},{"areaid":1037,"areaname":"高新区","cityid":115,"displayorder":8},{"areaid":1038,"areaname":"经开区",
     * "cityid":115,"displayorder":9},{"areaid":1039,"areaname":"航空港","cityid":115,"displayorder":10},{"areaid":1040,"areaname":"郑州周边","cityid":115,"displayorder":11}]},{"id":191,"city":"镇江",
     * "pinyin":"zhenjiang","areas":[{"areaid":10384,"areaname":"京口","cityid":191,"displayorder":1},{"areaid":10385,"areaname":"润州","cityid":191,"displayorder":2},{"areaid":10386,"areaname":"丹徒",
     * "cityid":191,"displayorder":3},{"areaid":10387,"areaname":"丹阳","cityid":191,"displayorder":4},{"areaid":10388,"areaname":"扬中","cityid":191,"displayorder":5},{"areaid":10389,"areaname":"句容",
     * "cityid":191,"displayorder":6},{"areaid":10390,"areaname":"镇江新区","cityid":191,"displayorder":7},{"areaid":10391,"areaname":"其他","cityid":191,"displayorder":8}]},{"id":4,"city":"重庆",
     * "pinyin":"zhongqing","areas":[{"areaid":55,"areaname":"江北","cityid":4,"displayorder":1},{"areaid":56,"areaname":"万州","cityid":4,"displayorder":2},{"areaid":57,"areaname":"渝中","cityid":4,
     * "displayorder":3},{"areaid":58,"areaname":"九龙坡","cityid":4,"displayorder":4},{"areaid":59,"areaname":"涪陵","cityid":4,"displayorder":5},{"areaid":60,"areaname":"长寿","cityid":4,
     * "displayorder":6},{"areaid":61,"areaname":"沙坪坝","cityid":4,"displayorder":7},{"areaid":62,"areaname":"合川","cityid":4,"displayorder":8},{"areaid":63,"areaname":"南岸","cityid":4,
     * "displayorder":9},{"areaid":64,"areaname":"渝北","cityid":4,"displayorder":10},{"areaid":65,"areaname":"巴南","cityid":4,"displayorder":11},{"areaid":66,"areaname":"北碚","cityid":4,
     * "displayorder":12},{"areaid":67,"areaname":"大渡口","cityid":4,"displayorder":13},{"areaid":68,"areaname":"永川","cityid":4,"displayorder":14},{"areaid":69,"areaname":"两江新区","cityid":4,
     * "displayorder":15},{"areaid":70,"areaname":"璧山","cityid":4,"displayorder":16},{"areaid":71,"areaname":"石柱","cityid":4,"displayorder":17},{"areaid":72,"areaname":"重庆周边","cityid":4,
     * "displayorder":18}]},{"id":22,"city":"中山","pinyin":"zhongshan","areas":[{"areaid":231,"areaname":"石岐","cityid":22,"displayorder":1},{"areaid":232,"areaname":"东区","cityid":22,
     * "displayorder":2},{"areaid":233,"areaname":"西区","cityid":22,"displayorder":3},{"areaid":234,"areaname":"南区","cityid":22,"displayorder":4},{"areaid":235,"areaname":"五桂山","cityid":22,
     * "displayorder":5},{"areaid":236,"areaname":"南朗","cityid":22,"displayorder":6},{"areaid":237,"areaname":"小榄","cityid":22,"displayorder":7},{"areaid":238,"areaname":"古镇","cityid":22,
     * "displayorder":8},{"areaid":239,"areaname":"三乡","cityid":22,"displayorder":9},{"areaid":240,"areaname":"坦洲","cityid":22,"displayorder":10},{"areaid":241,"areaname":"港口","cityid":22,
     * "displayorder":11},{"areaid":242,"areaname":"火炬开发区","cityid":22,"displayorder":12},{"areaid":243,"areaname":"大涌","cityid":22,"displayorder":13},{"areaid":244,"areaname":"黄圃","cityid":22,
     * "displayorder":14},{"areaid":245,"areaname":"南头","cityid":22,"displayorder":15},{"areaid":246,"areaname":"沙溪","cityid":22,"displayorder":16},{"areaid":247,"areaname":"中山周边","cityid":22,
     * "displayorder":17}]},{"id":235,"city":"中卫","pinyin":"zhongwei","areas":[{"areaid":10736,"areaname":"沙坡头","cityid":235,"displayorder":1},{"areaid":10737,"areaname":"中宁","cityid":235,
     * "displayorder":2},{"areaid":10738,"areaname":"海原","cityid":235,"displayorder":3}]},{"id":32,"city":"衢州","pinyin":"zhou","areas":[{"areaid":327,"areaname":"柯城","cityid":32,"displayorder":1},
     * {"areaid":328,"areaname":"衢江","cityid":32,"displayorder":2},{"areaid":329,"areaname":"巨化","cityid":32,"displayorder":3},{"areaid":330,"areaname":"江山","cityid":32,"displayorder":4},
     * {"areaid":331,"areaname":"常山","cityid":32,"displayorder":5},{"areaid":332,"areaname":"开化","cityid":32,"displayorder":6},{"areaid":333,"areaname":"龙游","cityid":32,"displayorder":7}]},
     * {"id":51,"city":"亳州","pinyin":"zhou","areas":[{"areaid":477,"areaname":"利辛","cityid":51,"displayorder":1},{"areaid":478,"areaname":"蒙城","cityid":51,"displayorder":2},{"areaid":479,
     * "areaname":"涡阳","cityid":51,"displayorder":3},{"areaid":480,"areaname":"谯城","cityid":51,"displayorder":4}]},{"id":282,"city":"泸州","pinyin":"zhou","areas":[{"areaid":11201,"areaname":"江阳",
     * "cityid":282,"displayorder":1},{"areaid":11202,"areaname":"纳溪","cityid":282,"displayorder":2},{"areaid":11203,"areaname":"龙马潭","cityid":282,"displayorder":3},{"areaid":11204,"areaname":"泸县",
     * "cityid":282,"displayorder":4},{"areaid":11205,"areaname":"合江","cityid":282,"displayorder":5},{"areaid":11206,"areaname":"叙永","cityid":282,"displayorder":6},{"areaid":11207,"areaname":"古蔺",
     * "cityid":282,"displayorder":7}]},{"id":130,"city":"周口","pinyin":"zhoukou","areas":[{"areaid":1164,"areaname":"川汇","cityid":130,"displayorder":1},{"areaid":1165,"areaname":"项城","cityid":130,
     * "displayorder":2},{"areaid":1166,"areaname":"商水","cityid":130,"displayorder":3},{"areaid":1167,"areaname":"淮阳","cityid":130,"displayorder":4},{"areaid":1168,"areaname":"太康","cityid":130,
     * "displayorder":5},{"areaid":1169,"areaname":"鹿邑","cityid":130,"displayorder":6},{"areaid":1170,"areaname":"西华","cityid":130,"displayorder":7},{"areaid":1171,"areaname":"扶沟","cityid":130,
     * "displayorder":8},{"areaid":1172,"areaname":"沈丘","cityid":130,"displayorder":9},{"areaid":1173,"areaname":"郸城","cityid":130,"displayorder":10},{"areaid":1174,"areaname":"其他","cityid":130,
     * "displayorder":11}]},{"id":33,"city":"舟山","pinyin":"zhoushan","areas":[{"areaid":334,"areaname":"定海","cityid":33,"displayorder":1},{"areaid":335,"areaname":"普陀","cityid":33,
     * "displayorder":2},{"areaid":336,"areaname":"岱山","cityid":33,"displayorder":3},{"areaid":337,"areaname":"嵊泗","cityid":33,"displayorder":4}]},{"id":7,"city":"珠海","pinyin":"zhuhai",
     * "areas":[{"areaid":101,"areaname":"香洲","cityid":7,"displayorder":1},{"areaid":102,"areaname":"斗门","cityid":7,"displayorder":2},{"areaid":103,"areaname":"金湾","cityid":7,"displayorder":3},
     * {"areaid":104,"areaname":"坦洲","cityid":7,"displayorder":4},{"areaid":105,"areaname":"高新区","cityid":7,"displayorder":5},{"areaid":106,"areaname":"横琴","cityid":7,"displayorder":6},
     * {"areaid":107,"areaname":"珠海周边","cityid":7,"displayorder":7}]},{"id":119,"city":"驻马店","pinyin":"zhumadian","areas":[{"areaid":1072,"areaname":"驿城","cityid":119,"displayorder":1},
     * {"areaid":1073,"areaname":"确山","cityid":119,"displayorder":2},{"areaid":1074,"areaname":"新蔡","cityid":119,"displayorder":3},{"areaid":1075,"areaname":"上蔡","cityid":119,"displayorder":4},
     * {"areaid":1076,"areaname":"西平","cityid":119,"displayorder":5},{"areaid":1077,"areaname":"泌阳","cityid":119,"displayorder":6},{"areaid":1078,"areaname":"平舆","cityid":119,"displayorder":7},
     * {"areaid":1079,"areaname":"汝南","cityid":119,"displayorder":8},{"areaid":1080,"areaname":"遂平","cityid":119,"displayorder":9},{"areaid":1081,"areaname":"正阳","cityid":119,"displayorder":10},
     * {"areaid":1082,"areaname":"其他","cityid":119,"displayorder":11}]},{"id":159,"city":"株洲","pinyin":"zhuzhou","areas":[{"areaid":10094,"areaname":"天元","cityid":159,"displayorder":1},
     * {"areaid":10095,"areaname":"荷塘","cityid":159,"displayorder":2},{"areaid":10096,"areaname":"攸县","cityid":159,"displayorder":3},{"areaid":10097,"areaname":"石峰","cityid":159,"displayorder":4},
     * {"areaid":10098,"areaname":"茶陵","cityid":159,"displayorder":5},{"areaid":10099,"areaname":"芦淞","cityid":159,"displayorder":6},{"areaid":10100,"areaname":"炎陵","cityid":159,"displayorder":7},
     * {"areaid":10101,"areaname":"醴陵","cityid":159,"displayorder":8},{"areaid":10102,"areaname":"株洲县","cityid":159,"displayorder":9},{"areaid":10103,"areaname":"其他","cityid":159,
     * "displayorder":10}]},{"id":245,"city":"淄博","pinyin":"zibo","areas":[{"areaid":10807,"areaname":"张店","cityid":245,"displayorder":1},{"areaid":10808,"areaname":"淄川","cityid":245,
     * "displayorder":2},{"areaid":10809,"areaname":"博山","cityid":245,"displayorder":3},{"areaid":10810,"areaname":"临淄","cityid":245,"displayorder":4},{"areaid":10811,"areaname":"周村","cityid":245,
     * "displayorder":5},{"areaid":10812,"areaname":"桓台","cityid":245,"displayorder":6},{"areaid":10813,"areaname":"高青","cityid":245,"displayorder":7},{"areaid":10814,"areaname":"沂源","cityid":245,
     * "displayorder":8}]},{"id":290,"city":"自贡","pinyin":"zigong","areas":[{"areaid":11263,"areaname":"自流井","cityid":290,"displayorder":1},{"areaid":11264,"areaname":"贡井","cityid":290,
     * "displayorder":2},{"areaid":11265,"areaname":"大安","cityid":290,"displayorder":3},{"areaid":11266,"areaname":"沿滩","cityid":290,"displayorder":4},{"areaid":11267,"areaname":"荣县","cityid":290,
     * "displayorder":5},{"areaid":11268,"areaname":"富顺","cityid":290,"displayorder":6}]},{"id":296,"city":"资阳","pinyin":"ziyang","areas":[{"areaid":11302,"areaname":"雁江","cityid":296,
     * "displayorder":1},{"areaid":11303,"areaname":"简阳","cityid":296,"displayorder":2},{"areaid":11304,"areaname":"安岳","cityid":296,"displayorder":3},{"areaid":11305,"areaname":"乐至","cityid":296,
     * "displayorder":4}]},{"id":94,"city":"遵义","pinyin":"zunyi","areas":[{"areaid":814,"areaname":"雁江","cityid":94,"displayorder":1},{"areaid":815,"areaname":"安岳","cityid":94,"displayorder":2},
     * {"areaid":816,"areaname":"乐至","cityid":94,"displayorder":3},{"areaid":817,"areaname":"简阳","cityid":94,"displayorder":4}]}]}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    public static class DataBean {
        /**
         * id : 299
         * city : 阿坝
         * pinyin : aba
         * areas : [{"areaid":11320,"areaname":"马尔康","cityid":299,"displayorder":1},{"areaid":11321,"areaname":"汶川","cityid":299,"displayorder":2},{"areaid":11322,"areaname":"理县","cityid":299,
         * "displayorder":3},{"areaid":11323,"areaname":"茂县","cityid":299,"displayorder":4},{"areaid":11324,"areaname":"松潘","cityid":299,"displayorder":5},{"areaid":11325,"areaname":"九寨沟",
         * "cityid":299,"displayorder":6},{"areaid":11326,"areaname":"金川","cityid":299,"displayorder":7},{"areaid":11327,"areaname":"小金","cityid":299,"displayorder":8},{"areaid":11328,
         * "areaname":"黑水","cityid":299,"displayorder":9},{"areaid":11329,"areaname":"壤塘","cityid":299,"displayorder":10},{"areaid":11330,"areaname":"阿坝县","cityid":299,"displayorder":11},
         * {"areaid":11331,"areaname":"若尔盖","cityid":299,"displayorder":12},{"areaid":11332,"areaname":"红原","cityid":299,"displayorder":13}]
         */

        private List<ABean> A;
        /**
         * id : 179
         * city : 白城
         * pinyin : baicheng
         * areas : [{"areaid":10262,"areaname":"白城","cityid":179,"displayorder":1},{"areaid":10263,"areaname":"洮北","cityid":179,"displayorder":2},{"areaid":10264,"areaname":"洮南","cityid":179,
         * "displayorder":3},{"areaid":10265,"areaname":"大安","cityid":179,"displayorder":4},{"areaid":10266,"areaname":"镇赉","cityid":179,"displayorder":5},{"areaid":10267,"areaname":"通榆",
         * "cityid":179,"displayorder":6},{"areaid":10268,"areaname":"白城周边","cityid":179,"displayorder":7}]
         */

        private List<BBean> B;
        /**
         * id : 104
         * city : 沧州
         * pinyin : cangzhou
         * areas : [{"areaid":908,"areaname":"新华","cityid":104,"displayorder":1},{"areaid":909,"areaname":"沧县","cityid":104,"displayorder":2},{"areaid":910,"areaname":"运河","cityid":104,
         * "displayorder":3},{"areaid":911,"areaname":"南皮","cityid":104,"displayorder":4},{"areaid":912,"areaname":"任丘","cityid":104,"displayorder":5},{"areaid":913,"areaname":"青县","cityid":104,
         * "displayorder":6},{"areaid":914,"areaname":"河间","cityid":104,"displayorder":7},{"areaid":915,"areaname":"肃宁","cityid":104,"displayorder":8},{"areaid":916,"areaname":"黄骅","cityid":104,
         * "displayorder":9},{"areaid":917,"areaname":"东光","cityid":104,"displayorder":10},{"areaid":918,"areaname":"泊头","cityid":104,"displayorder":11},{"areaid":919,"areaname":"海兴","cityid":104,
         * "displayorder":12},{"areaid":920,"areaname":"盐山","cityid":104,"displayorder":13},{"areaid":921,"areaname":"吴桥","cityid":104,"displayorder":14},{"areaid":922,"areaname":"孟村","cityid":104,
         * "displayorder":15},{"areaid":923,"areaname":"献县","cityid":104,"displayorder":16},{"areaid":924,"areaname":"其他","cityid":104,"displayorder":17}]
         */

        private List<CBean> C;
        /**
         * id : 334
         * city : 大理
         * pinyin : dali
         * areas : [{"areaid":11637,"areaname":"大理市","cityid":334,"displayorder":1},{"areaid":11638,"areaname":"祥云","cityid":334,"displayorder":2},{"areaid":11639,"areaname":"宾川","cityid":334,
         * "displayorder":3},{"areaid":11640,"areaname":"弥渡","cityid":334,"displayorder":4},{"areaid":11641,"areaname":"永平","cityid":334,"displayorder":5},{"areaid":11642,"areaname":"云龙",
         * "cityid":334,"displayorder":6},{"areaid":11643,"areaname":"洱源","cityid":334,"displayorder":7},{"areaid":11644,"areaname":"剑川","cityid":334,"displayorder":8},{"areaid":11645,
         * "areaname":"鹤庆","cityid":334,"displayorder":9},{"areaid":11646,"areaname":"漾濞","cityid":334,"displayorder":10},{"areaid":11647,"areaname":"南涧","cityid":334,"displayorder":11},
         * {"areaid":11648,"areaname":"巍山","cityid":334,"displayorder":12}]
         */

        private List<DBean> D;
        /**
         * id : 224
         * city : 鄂尔多斯
         * pinyin : eerduosi
         * areas : [{"areaid":10665,"areaname":"东胜","cityid":224,"displayorder":1},{"areaid":10666,"areaname":"达拉特旗","cityid":224,"displayorder":2},{"areaid":10667,"areaname":"准格尔旗","cityid":224,
         * "displayorder":3},{"areaid":10668,"areaname":"鄂托克旗","cityid":224,"displayorder":4},{"areaid":10669,"areaname":"鄂托克前旗","cityid":224,"displayorder":5},{"areaid":10670,"areaname":"杭锦旗",
         * "cityid":224,"displayorder":6},{"areaid":10671,"areaname":"乌审旗","cityid":224,"displayorder":7},{"areaid":10672,"areaname":"伊金霍洛旗","cityid":224,"displayorder":8},{"areaid":10673,
         * "areaname":"康巴什区","cityid":224,"displayorder":9},{"areaid":10674,"areaname":"其它","cityid":224,"displayorder":10}]
         */

        private List<EBean> E;
        /**
         * id : 85
         * city : 防城港
         * pinyin : fangchenggang
         * areas : [{"areaid":744,"areaname":"港口","cityid":85,"displayorder":1},{"areaid":745,"areaname":"防城","cityid":85,"displayorder":2},{"areaid":746,"areaname":"上思","cityid":85,
         * "displayorder":3},{"areaid":747,"areaname":"东兴","cityid":85,"displayorder":4},{"areaid":748,"areaname":"防城港周边","cityid":85,"displayorder":5}]
         */

        private List<FBean> F;
        /**
         * id : 77
         * city : 甘南
         * pinyin : gannan
         * areas : [{"areaid":684,"areaname":"合作","cityid":77,"displayorder":1},{"areaid":685,"areaname":"临潭","cityid":77,"displayorder":2},{"areaid":686,"areaname":"卓尼","cityid":77,
         * "displayorder":3},{"areaid":687,"areaname":"舟曲","cityid":77,"displayorder":4},{"areaid":688,"areaname":"迭部","cityid":77,"displayorder":5},{"areaid":689,"areaname":"玛曲","cityid":77,
         * "displayorder":6},{"areaid":690,"areaname":"碌曲","cityid":77,"displayorder":7},{"areaid":691,"areaname":"夏河","cityid":77,"displayorder":8},{"areaid":692,"areaname":"甘南周边","cityid":77,
         * "displayorder":9}]
         */

        private List<GBean> G;
        /**
         * id : 132
         * city : 哈尔滨
         * pinyin : haerbin
         * areas : [{"areaid":1185,"areaname":"南岗","cityid":132,"displayorder":1},{"areaid":1186,"areaname":"道里","cityid":132,"displayorder":2},{"areaid":1187,"areaname":"道外","cityid":132,
         * "displayorder":3},{"areaid":1188,"areaname":"香坊","cityid":132,"displayorder":4},{"areaid":1189,"areaname":"江北","cityid":132,"displayorder":5},{"areaid":1190,"areaname":"开发区",
         * "cityid":132,"displayorder":6},{"areaid":1191,"areaname":"依兰","cityid":132,"displayorder":7},{"areaid":1192,"areaname":"方正","cityid":132,"displayorder":8},{"areaid":1193,"areaname":"宾县",
         * "cityid":132,"displayorder":9},{"areaid":1194,"areaname":"巴彦","cityid":132,"displayorder":10},{"areaid":1195,"areaname":"木兰","cityid":132,"displayorder":11},{"areaid":1196,
         * "areaname":"通河","cityid":132,"displayorder":12},{"areaid":1197,"areaname":"哈尔滨周边","cityid":132,"displayorder":13}]
         */

        private List<HBean> H;
        /**
         * id : 139
         * city : 佳木斯
         * pinyin : jiamusi
         * areas : [{"areaid":1247,"areaname":"前进","cityid":139,"displayorder":1},{"areaid":1248,"areaname":"永红","cityid":139,"displayorder":2},{"areaid":1249,"areaname":"向阳","cityid":139,
         * "displayorder":3},{"areaid":1250,"areaname":"东风","cityid":139,"displayorder":4},{"areaid":1251,"areaname":"郊区","cityid":139,"displayorder":5},{"areaid":1252,"areaname":"同江","cityid":139,
         * "displayorder":6},{"areaid":1253,"areaname":"富锦","cityid":139,"displayorder":7},{"areaid":1254,"areaname":"桦南","cityid":139,"displayorder":8},{"areaid":1255,"areaname":"桦川","cityid":139,
         * "displayorder":9},{"areaid":1256,"areaname":"汤原","cityid":139,"displayorder":10},{"areaid":1257,"areaname":"抚远","cityid":139,"displayorder":11}]
         */

        private List<JBean> J;
        /**
         * id : 131
         * city : 开封
         * pinyin : kaifeng
         * areas : [{"areaid":1175,"areaname":"鼓楼","cityid":131,"displayorder":1},{"areaid":1176,"areaname":"龙亭","cityid":131,"displayorder":2},{"areaid":1177,"areaname":"顺河","cityid":131,
         * "displayorder":3},{"areaid":1178,"areaname":"禹王台","cityid":131,"displayorder":4},{"areaid":1179,"areaname":"金明","cityid":131,"displayorder":5},{"areaid":1180,"areaname":"杞县",
         * "cityid":131,"displayorder":6},{"areaid":1181,"areaname":"通许","cityid":131,"displayorder":7},{"areaid":1182,"areaname":"尉氏","cityid":131,"displayorder":8},{"areaid":1183,
         * "areaname":"开封县","cityid":131,"displayorder":9},{"areaid":1184,"areaname":"兰考","cityid":131,"displayorder":10}]
         */

        private List<KBean> K;
        /**
         * id : 89
         * city : 来宾
         * pinyin : laibin
         * areas : [{"areaid":776,"areaname":"兴宾","cityid":89,"displayorder":1},{"areaid":777,"areaname":"合山","cityid":89,"displayorder":2},{"areaid":778,"areaname":"象州","cityid":89,
         * "displayorder":3},{"areaid":779,"areaname":"武宣","cityid":89,"displayorder":4},{"areaid":780,"areaname":"忻城","cityid":89,"displayorder":5},{"areaid":781,"areaname":"金秀","cityid":89,
         * "displayorder":6},{"areaid":782,"areaname":"来宾周边","cityid":89,"displayorder":7}]
         */

        private List<LBean> L;
        /**
         * id : 42
         * city : 马鞍山
         * pinyin : maanshan
         * areas : [{"areaid":412,"areaname":"花山","cityid":42,"displayorder":1},{"areaid":413,"areaname":"雨山","cityid":42,"displayorder":2},{"areaid":414,"areaname":"金家庄","cityid":42,
         * "displayorder":3},{"areaid":415,"areaname":"当涂","cityid":42,"displayorder":4},{"areaid":416,"areaname":"博望区","cityid":42,"displayorder":5},{"areaid":417,"areaname":"其它","cityid":42,
         * "displayorder":6}]
         */

        private List<MBean> M;
        /**
         * id : 194
         * city : 南昌
         * pinyin : nanchang
         * areas : [{"areaid":10403,"areaname":"东湖","cityid":194,"displayorder":1},{"areaid":10404,"areaname":"西湖","cityid":194,"displayorder":2},{"areaid":10405,"areaname":"新建县","cityid":194,
         * "displayorder":3},{"areaid":10406,"areaname":"南昌县","cityid":194,"displayorder":4},{"areaid":10407,"areaname":"青云谱","cityid":194,"displayorder":5},{"areaid":10408,"areaname":"湾里",
         * "cityid":194,"displayorder":6},{"areaid":10409,"areaname":"青山湖","cityid":194,"displayorder":7},{"areaid":10410,"areaname":"红谷滩新区","cityid":194,"displayorder":8},{"areaid":10411,
         * "areaname":"高新开发区","cityid":194,"displayorder":9},{"areaid":10412,"areaname":"象湖","cityid":194,"displayorder":10},{"areaid":10413,"areaname":"小蓝经济开发区","cityid":194,"displayorder":11},
         * {"areaid":10414,"areaname":"昌北经济开发区","cityid":194,"displayorder":12},{"areaid":10415,"areaname":"南昌周边","cityid":194,"displayorder":13}]
         */

        private List<NBean> N;
        /**
         * id : 215
         * city : 盘锦
         * pinyin : panjin
         * areas : [{"areaid":10584,"areaname":"兴隆台","cityid":215,"displayorder":1},{"areaid":10585,"areaname":"双台子","cityid":215,"displayorder":2},{"areaid":10586,"areaname":"盘山","cityid":215,
         * "displayorder":3},{"areaid":10587,"areaname":"大洼","cityid":215,"displayorder":4},{"areaid":10588,"areaname":"其它","cityid":215,"displayorder":5}]
         */

        private List<PBean> P;
        /**
         * id : 105
         * city : 迁安
         * pinyin : qianan
         */

        private List<QBean> Q;
        /**
         * id : 305
         * city : 日喀则
         * pinyin : rikaze
         * areas : [{"areaid":11399,"areaname":"日喀则市","cityid":305,"displayorder":1},{"areaid":11400,"areaname":"南木林","cityid":305,"displayorder":2},{"areaid":11401,"areaname":"江孜","cityid":305,
         * "displayorder":3},{"areaid":11402,"areaname":"定日","cityid":305,"displayorder":4},{"areaid":11403,"areaname":"萨迦","cityid":305,"displayorder":5},{"areaid":11404,"areaname":"拉孜",
         * "cityid":305,"displayorder":6},{"areaid":11405,"areaname":"昂仁","cityid":305,"displayorder":7},{"areaid":11406,"areaname":"谢通门","cityid":305,"displayorder":8},{"areaid":11407,
         * "areaname":"白朗","cityid":305,"displayorder":9},{"areaid":11408,"areaname":"仁布","cityid":305,"displayorder":10},{"areaid":11409,"areaname":"康马","cityid":305,"displayorder":11},
         * {"areaid":11410,"areaname":"定结","cityid":305,"displayorder":12},{"areaid":11411,"areaname":"仲巴","cityid":305,"displayorder":13},{"areaid":11412,"areaname":"亚东","cityid":305,
         * "displayorder":14},{"areaid":11413,"areaname":"吉隆","cityid":305,"displayorder":15},{"areaid":11414,"areaname":"聂拉木","cityid":305,"displayorder":16},{"areaid":11415,"areaname":"萨嘎",
         * "cityid":305,"displayorder":17},{"areaid":11416,"areaname":"岗巴","cityid":305,"displayorder":18}]
         */

        private List<RBean> R;
        /**
         * id : 126
         * city : 三门峡
         * pinyin : sanmenxia
         * areas : [{"areaid":1125,"areaname":"湖滨","cityid":126,"displayorder":1},{"areaid":1126,"areaname":"开发区","cityid":126,"displayorder":2},{"areaid":1127,"areaname":"陕县","cityid":126,
         * "displayorder":3},{"areaid":1128,"areaname":"灵宝","cityid":126,"displayorder":4},{"areaid":1129,"areaname":"义马","cityid":126,"displayorder":5},{"areaid":1130,"areaname":"渑池","cityid":126,
         * "displayorder":6},{"areaid":1131,"areaname":"卢氏","cityid":126,"displayorder":7}]
         */

        private List<SBean> S;
        /**
         * id : 320
         * city : 塔城
         * pinyin : tacheng
         * areas : [{"areaid":11521,"areaname":"塔城市","cityid":320,"displayorder":1},{"areaid":11522,"areaname":"乌苏","cityid":320,"displayorder":2},{"areaid":11523,"areaname":"额敏","cityid":320,
         * "displayorder":3},{"areaid":11524,"areaname":"沙湾","cityid":320,"displayorder":4},{"areaid":11525,"areaname":"托里","cityid":320,"displayorder":5},{"areaid":11526,"areaname":"裕民",
         * "cityid":320,"displayorder":6},{"areaid":11527,"areaname":"和布克赛尔","cityid":320,"displayorder":7}]
         */

        private List<TBean> T;
        /**
         * id : 249
         * city : 潍坊
         * pinyin : weifang
         * areas : [{"areaid":10840,"areaname":"潍城","cityid":249,"displayorder":1},{"areaid":10841,"areaname":"寒亭","cityid":249,"displayorder":2},{"areaid":10842,"areaname":"坊子","cityid":249,
         * "displayorder":3},{"areaid":10843,"areaname":"奎文","cityid":249,"displayorder":4},{"areaid":10844,"areaname":"高新区","cityid":249,"displayorder":5},{"areaid":10845,"areaname":"滨海新区",
         * "cityid":249,"displayorder":6},{"areaid":10846,"areaname":"经开区","cityid":249,"displayorder":7},{"areaid":10847,"areaname":"青州","cityid":249,"displayorder":8},{"areaid":10848,
         * "areaname":"诸城","cityid":249,"displayorder":9},{"areaid":10849,"areaname":"寿光","cityid":249,"displayorder":10},{"areaid":10850,"areaname":"安丘","cityid":249,"displayorder":11},
         * {"areaid":10851,"areaname":"高密","cityid":249,"displayorder":12},{"areaid":10852,"areaname":"昌邑","cityid":249,"displayorder":13},{"areaid":10853,"areaname":"临朐","cityid":249,
         * "displayorder":14},{"areaid":10854,"areaname":"昌乐","cityid":249,"displayorder":15}]
         */

        private List<WBean> W;
        /**
         * id : 56
         * city : 厦门
         * pinyin : xiamen
         * areas : [{"areaid":514,"areaname":"思明","cityid":56,"displayorder":1},{"areaid":515,"areaname":"湖里","cityid":56,"displayorder":2},{"areaid":516,"areaname":"集美","cityid":56,
         * "displayorder":3},{"areaid":517,"areaname":"杏林","cityid":56,"displayorder":4},{"areaid":518,"areaname":"海沧","cityid":56,"displayorder":5},{"areaid":519,"areaname":"同安","cityid":56,
         * "displayorder":6},{"areaid":520,"areaname":"翔安","cityid":56,"displayorder":7},{"areaid":521,"areaname":"厦门周边","cityid":56,"displayorder":8}]
         */

        private List<XBean> X;
        /**
         * id : 298
         * city : 雅安
         * pinyin : yaan
         * areas : [{"areaid":11312,"areaname":"雨城","cityid":298,"displayorder":1},{"areaid":11313,"areaname":"名山","cityid":298,"displayorder":2},{"areaid":11314,"areaname":"荥经","cityid":298,
         * "displayorder":3},{"areaid":11315,"areaname":"汉源","cityid":298,"displayorder":4},{"areaid":11316,"areaname":"石棉","cityid":298,"displayorder":5},{"areaid":11317,"areaname":"天全",
         * "cityid":298,"displayorder":6},{"areaid":11318,"areaname":"芦山","cityid":298,"displayorder":7},{"areaid":11319,"areaname":"宝兴","cityid":298,"displayorder":8}]
         */

        private List<YBean> Y;
        /**
         * id : 246
         * city : 枣庄
         * pinyin : zaozhuang
         * areas : [{"areaid":10815,"areaname":"市中","cityid":246,"displayorder":1},{"areaid":10816,"areaname":"薛城","cityid":246,"displayorder":2},{"areaid":10817,"areaname":"峄城","cityid":246,
         * "displayorder":3},{"areaid":10818,"areaname":"台儿庄","cityid":246,"displayorder":4},{"areaid":10819,"areaname":"山亭","cityid":246,"displayorder":5},{"areaid":10820,"areaname":"滕州",
         * "cityid":246,"displayorder":6}]
         */

        private List<ZBean> Z;

        public List<ABean> getA() {
            return A;
        }

        public void setA(List<ABean> A) {
            this.A = A;
        }

        public List<BBean> getB() {
            return B;
        }

        public void setB(List<BBean> B) {
            this.B = B;
        }

        public List<CBean> getC() {
            return C;
        }

        public void setC(List<CBean> C) {
            this.C = C;
        }

        public List<DBean> getD() {
            return D;
        }

        public void setD(List<DBean> D) {
            this.D = D;
        }

        public List<EBean> getE() {
            return E;
        }

        public void setE(List<EBean> E) {
            this.E = E;
        }

        public List<FBean> getF() {
            return F;
        }

        public void setF(List<FBean> F) {
            this.F = F;
        }

        public List<GBean> getG() {
            return G;
        }

        public void setG(List<GBean> G) {
            this.G = G;
        }

        public List<HBean> getH() {
            return H;
        }

        public void setH(List<HBean> H) {
            this.H = H;
        }

        public List<JBean> getJ() {
            return J;
        }

        public void setJ(List<JBean> J) {
            this.J = J;
        }

        public List<KBean> getK() {
            return K;
        }

        public void setK(List<KBean> K) {
            this.K = K;
        }

        public List<LBean> getL() {
            return L;
        }

        public void setL(List<LBean> L) {
            this.L = L;
        }

        public List<MBean> getM() {
            return M;
        }

        public void setM(List<MBean> M) {
            this.M = M;
        }

        public List<NBean> getN() {
            return N;
        }

        public void setN(List<NBean> N) {
            this.N = N;
        }

        public List<PBean> getP() {
            return P;
        }

        public void setP(List<PBean> P) {
            this.P = P;
        }

        public List<QBean> getQ() {
            return Q;
        }

        public void setQ(List<QBean> Q) {
            this.Q = Q;
        }

        public List<RBean> getR() {
            return R;
        }

        public void setR(List<RBean> R) {
            this.R = R;
        }

        public List<SBean> getS() {
            return S;
        }

        public void setS(List<SBean> S) {
            this.S = S;
        }

        public List<TBean> getT() {
            return T;
        }

        public void setT(List<TBean> T) {
            this.T = T;
        }

        public List<WBean> getW() {
            return W;
        }

        public void setW(List<WBean> W) {
            this.W = W;
        }

        public List<XBean> getX() {
            return X;
        }

        public void setX(List<XBean> X) {
            this.X = X;
        }

        public List<YBean> getY() {
            return Y;
        }

        public void setY(List<YBean> Y) {
            this.Y = Y;
        }

        public List<ZBean> getZ() {
            return Z;
        }

        public void setZ(List<ZBean> Z) {
            this.Z = Z;
        }

        public static class ABean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 11320
             * areaname : 马尔康
             * cityid : 299
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }
            }

            @Override
            public String toString() {
                return "ABean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class BBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 10262
             * areaname : 白城
             * cityid : 179
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "BBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class CBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 908
             * areaname : 新华
             * cityid : 104
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }
            }

            @Override
            public String toString() {
                return "CBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class DBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 11637
             * areaname : 大理市
             * cityid : 334
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "DBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class EBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 10665
             * areaname : 东胜
             * cityid : 224
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "EBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class FBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 744
             * areaname : 港口
             * cityid : 85
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "FBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class GBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 684
             * areaname : 合作
             * cityid : 77
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "GBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class HBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 1185
             * areaname : 南岗
             * cityid : 132
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "HBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class JBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 1247
             * areaname : 前进
             * cityid : 139
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "JBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class KBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 1175
             * areaname : 鼓楼
             * cityid : 131
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "KBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class LBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 776
             * areaname : 兴宾
             * cityid : 89
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "LBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class MBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 412
             * areaname : 花山
             * cityid : 42
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "MBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class NBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 10403
             * areaname : 东湖
             * cityid : 194
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "NBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class PBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 10584
             * areaname : 兴隆台
             * cityid : 215
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "PBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class QBean {
            private int id;
            private String city;
            private String pinyin;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            @Override
            public String toString() {
                return "QBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + '}';
            }
        }

        public static class RBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 11399
             * areaname : 日喀则市
             * cityid : 305
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "RBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class SBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 1125
             * areaname : 湖滨
             * cityid : 126
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }
        }

        public static class TBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 11521
             * areaname : 塔城市
             * cityid : 320
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "TBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class WBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 10840
             * areaname : 潍城
             * cityid : 249
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "WBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class XBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 514
             * areaname : 思明
             * cityid : 56
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }
        }

        public static class YBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 11312
             * areaname : 雨城
             * cityid : 298
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }
            }

            @Override
            public String toString() {
                return "YBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        public static class ZBean {
            private int id;
            private String city;
            private String pinyin;
            /**
             * areaid : 10815
             * areaname : 市中
             * cityid : 246
             * displayorder : 1
             */

            private List<AreasBean> areas;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public List<AreasBean> getAreas() {
                return areas;
            }

            public void setAreas(List<AreasBean> areas) {
                this.areas = areas;
            }

            public static class AreasBean {
                private int areaid;
                private String areaname;
                private int cityid;
                private int displayorder;

                public int getAreaid() {
                    return areaid;
                }

                public void setAreaid(int areaid) {
                    this.areaid = areaid;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public int getCityid() {
                    return cityid;
                }

                public void setCityid(int cityid) {
                    this.cityid = cityid;
                }

                public int getDisplayorder() {
                    return displayorder;
                }

                public void setDisplayorder(int displayorder) {
                    this.displayorder = displayorder;
                }

                @Override
                public String toString() {
                    return "AreasBean{" + "areaid=" + areaid + ", areaname='" + areaname + '\'' + ", cityid=" + cityid + ", displayorder=" + displayorder + '}';
                }
            }

            @Override
            public String toString() {
                return "ZBean{" + "id=" + id + ", city='" + city + '\'' + ", pinyin='" + pinyin + '\'' + ", areas=" + areas + '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" + "A=" + A + ", B=" + B + ", C=" + C + ", D=" + D + ", E=" + E + ", F=" + F + ", G=" + G + ", H=" + H + ", J=" + J + ", K=" + K + ", L=" + L + ", M=" + M + ", N=" + N
                    + ", P=" + P + ", Q=" + Q + ", R=" + R + ", S=" + S + ", T=" + T + ", W=" + W + ", X=" + X + ", Y=" + Y + ", Z=" + Z + '}';
        }
    }

    @Override
    public String toString() {
        return "GetCityBean{" + "code=" + code + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}
