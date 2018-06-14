package com.qunxianghui.gxh.bean.mine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CompanySetBean implements Serializable {


    /**
     * code : 0
     * message : 查询成功。
     * data : {"id":82,"member_id":1000010,"company_name":"消磨嗯额","description":"","company_trade":"65","tel":"631132692","mobile":"13295815771","qq":"631132692","province_id":2,"city_id":23,"area_id":248,"address":"哈哈","images":"","linkname":"我怎么","content":"哦哦哦","ctime":1528803977,"view_cnt":0,"status":0,"push_id":0,"change_ad_id":0,"company_trade_name":"IT","province_name":"广东","city_name":"潮州","area_name":"湘桥","province_list":[{"provinceid":1,"provincename":"直辖市","displayorder":1},{"provinceid":2,"provincename":"广东","displayorder":0},{"provinceid":3,"provincename":"浙江","displayorder":0},{"provinceid":4,"provincename":"安徽","displayorder":0},{"provinceid":5,"provincename":"福建","displayorder":0},{"provinceid":6,"provincename":"甘肃","displayorder":0},{"provinceid":7,"provincename":"广西","displayorder":0},{"provinceid":8,"provincename":"贵州","displayorder":0},{"provinceid":9,"provincename":"海南","displayorder":0},{"provinceid":10,"provincename":"河北","displayorder":0},{"provinceid":11,"provincename":"河南","displayorder":0},{"provinceid":12,"provincename":"黑龙江","displayorder":0},{"provinceid":13,"provincename":"湖北","displayorder":0},{"provinceid":14,"provincename":"湖南","displayorder":0},{"provinceid":15,"provincename":"吉林","displayorder":0},{"provinceid":16,"provincename":"江苏","displayorder":0},{"provinceid":17,"provincename":"江西","displayorder":0},{"provinceid":18,"provincename":"辽宁","displayorder":0},{"provinceid":19,"provincename":"内蒙古","displayorder":0},{"provinceid":20,"provincename":"宁夏","displayorder":0},{"provinceid":21,"provincename":"青海","displayorder":0},{"provinceid":22,"provincename":"山东","displayorder":0},{"provinceid":23,"provincename":"山西","displayorder":0},{"provinceid":24,"provincename":"陕西","displayorder":0},{"provinceid":25,"provincename":"四川","displayorder":0},{"provinceid":26,"provincename":"西藏","displayorder":0},{"provinceid":28,"provincename":"云南","displayorder":0},{"provinceid":35,"provincename":"新疆","displayorder":1}],"city_list":[{"cityid":5,"provinceid":2,"cityname":"广州","displayorder":0,"citypinyin":"guangzhou"},{"cityid":6,"provinceid":2,"cityname":"深圳","displayorder":0,"citypinyin":"shen"},{"cityid":7,"provinceid":2,"cityname":"珠海","displayorder":0,"citypinyin":"zhuhai"},{"cityid":8,"provinceid":2,"cityname":"汕头","displayorder":0,"citypinyin":"shantou"},{"cityid":9,"provinceid":2,"cityname":"韶关","displayorder":0,"citypinyin":"shaoguan"},{"cityid":10,"provinceid":2,"cityname":"佛山","displayorder":0,"citypinyin":"foshan"},{"cityid":11,"provinceid":2,"cityname":"江门","displayorder":0,"citypinyin":"jiangmen"},{"cityid":12,"provinceid":2,"cityname":"湛江","displayorder":0,"citypinyin":"zhanjiang"},{"cityid":13,"provinceid":2,"cityname":"茂名","displayorder":0,"citypinyin":"maoming"},{"cityid":14,"provinceid":2,"cityname":"肇庆","displayorder":0,"citypinyin":"zhaoqing"},{"cityid":15,"provinceid":2,"cityname":"惠州","displayorder":0,"citypinyin":"huizhou"},{"cityid":16,"provinceid":2,"cityname":"梅州","displayorder":0,"citypinyin":"meizhou"},{"cityid":17,"provinceid":2,"cityname":"汕尾","displayorder":0,"citypinyin":"shanwei"},{"cityid":18,"provinceid":2,"cityname":"河源","displayorder":0,"citypinyin":"heyuan"},{"cityid":19,"provinceid":2,"cityname":"阳江","displayorder":0,"citypinyin":"yangjiang"},{"cityid":20,"provinceid":2,"cityname":"清远","displayorder":0,"citypinyin":"qingyuan"},{"cityid":21,"provinceid":2,"cityname":"东莞","displayorder":0,"citypinyin":"dong"},{"cityid":22,"provinceid":2,"cityname":"中山","displayorder":0,"citypinyin":"zhongshan"},{"cityid":23,"provinceid":2,"cityname":"潮州","displayorder":0,"citypinyin":"chaozhou"},{"cityid":24,"provinceid":2,"cityname":"揭阳","displayorder":0,"citypinyin":"jieyang"},{"cityid":25,"provinceid":2,"cityname":"云浮","displayorder":0,"citypinyin":"yunfu"}],"area_list":[{"areaid":248,"areaname":"湘桥","cityid":23,"displayorder":1},{"areaid":249,"areaname":"枫溪","cityid":23,"displayorder":2},{"areaid":250,"areaname":"潮安","cityid":23,"displayorder":3},{"areaid":251,"areaname":"饶平","cityid":23,"displayorder":4},{"areaid":252,"areaname":"潮州周边","cityid":23,"displayorder":5}],"cate":[{"id":6,"pid":0,"cate_name":"家政服务","listorder":1,"picurl":"","status":1},{"id":7,"pid":0,"cate_name":"房产","listorder":2,"picurl":"","status":1},{"id":8,"pid":0,"cate_name":"汽车服务","listorder":6,"picurl":"","status":1},{"id":13,"pid":6,"cate_name":"家政服务","listorder":128,"picurl":"","status":1},{"id":16,"pid":7,"cate_name":"房产","listorder":128,"picurl":"","status":1},{"id":17,"pid":16,"cate_name":"写字楼","listorder":128,"picurl":"/upload/sys/image/a4/91d1b794aa898ef5c33970ef708ff9.png","status":1},{"id":19,"pid":18,"cate_name":"二手车","listorder":128,"picurl":"/upload/sys/image/89/7f5d584642af62403f3b507e377cb3.png","status":0},{"id":20,"pid":8,"cate_name":"汽车服务","listorder":128,"picurl":"/upload/sys/image/89/7f5d584642af62403f3b507e377cb3.png","status":1},{"id":30,"pid":23,"cate_name":"建筑装修","listorder":128,"picurl":"","status":0},{"id":36,"pid":13,"cate_name":"保洁清洗","listorder":128,"picurl":"/upload/sys/image/19/963ff8a098ef762a6ce1749a6ac4aa.png","status":1},{"id":37,"pid":13,"cate_name":"保姆陪护","listorder":128,"picurl":"/upload/sys/image/b1/502c8a71ce1cf723bfadb59866cad3.png","status":1},{"id":42,"pid":16,"cate_name":"商铺租售","listorder":128,"picurl":"/upload/sys/image/0e/de81301601f13f6398f0daa9d04d45.png","status":1},{"id":43,"pid":16,"cate_name":"小区","listorder":128,"picurl":"/upload/sys/image/e3/ae9859f6b8e61c8d62da9ad7897f6a.png","status":1},{"id":44,"pid":16,"cate_name":"新房","listorder":128,"picurl":"/upload/sys/image/74/2f169a1d485c454a9bf62ad5118e4f.png","status":1},{"id":45,"pid":16,"cate_name":"厂房/土地","listorder":128,"picurl":"/upload/sys/image/ba/5cf061d099e1b8fb68bb12b2a8927b.png","status":1},{"id":46,"pid":20,"cate_name":"租车","listorder":128,"picurl":"/upload/sys/image/ae/d58cf523d6193db62b84045334e202.png","status":1},{"id":49,"pid":20,"cate_name":"二手车","listorder":128,"picurl":"/upload/sys/image/5f/5e3bf661d1481079115505f41b17be.png","status":1},{"id":59,"pid":30,"cate_name":"装修建材","listorder":128,"picurl":"","status":0},{"id":60,"pid":30,"cate_name":"家用电器","listorder":128,"picurl":"","status":0},{"id":63,"pid":0,"cate_name":"教育培训","listorder":5,"picurl":"","status":1},{"id":64,"pid":63,"cate_name":"家教机构","listorder":128,"picurl":"","status":1},{"id":65,"pid":64,"cate_name":"IT","listorder":128,"picurl":"/upload/sys/image/95/882e6b90144077991593a3a1caad9c.png","status":1},{"id":66,"pid":0,"cate_name":"宠物","listorder":128,"picurl":"","status":1},{"id":67,"pid":66,"cate_name":"宠物狗","listorder":128,"picurl":"","status":1},{"id":68,"pid":66,"cate_name":"宠物猫","listorder":128,"picurl":"","status":1},{"id":72,"pid":68,"cate_name":"短毛猫","listorder":128,"picurl":"/upload/sys/image/4c/356813e6cd90af6f2bb41e24b3a5d9.png","status":1},{"id":73,"pid":79,"cate_name":"泰迪犬","listorder":128,"picurl":"/upload/sys/image/c0/5b59cf470ba81c03e2c44d43ed3403.png","status":1},{"id":74,"pid":79,"cate_name":"博美犬","listorder":128,"picurl":"/upload/sys/image/ea/2f48f56c52a741cf7ae45991271c95.png","status":1},{"id":75,"pid":80,"cate_name":"哈士奇","listorder":128,"picurl":"/upload/sys/image/1c/a80a4db0e1fdd206088f20b85f5e92.png","status":1},{"id":76,"pid":80,"cate_name":"萨摩耶","listorder":128,"picurl":"/upload/sys/image/9e/d914a3251f72b42e2f7fcd783eca35.png","status":1},{"id":77,"pid":81,"cate_name":"拉布拉多","listorder":128,"picurl":"/upload/sys/image/5a/89de937b7e876f1ce07f24f606d818.jpg","status":1},{"id":78,"pid":81,"cate_name":"金毛","listorder":128,"picurl":"/upload/sys/image/df/ba3a4b7445ec25ceefd41c14019d5f.png","status":1},{"id":79,"pid":67,"cate_name":"小型犬","listorder":128,"picurl":"/upload/sys/image/ea/6a91e3bfa8f0e716ce394f614457b2.png","status":1},{"id":80,"pid":67,"cate_name":"中型犬","listorder":128,"picurl":"/upload/sys/image/b2/c51b41cac08e6a992a4f8d8f293688.png","status":1},{"id":81,"pid":67,"cate_name":"大型犬","listorder":128,"picurl":"/upload/sys/image/12/3bdbe7c7872b25de01404e5e075c0b.png","status":1},{"id":85,"pid":0,"cate_name":"装修建材","listorder":7,"picurl":"","status":1},{"id":86,"pid":85,"cate_name":"家装服务","listorder":128,"picurl":"/upload/sys/image/40/4ba3a6c03f554b920849eb09d9ca18.jpg","status":1},{"id":87,"pid":85,"cate_name":"店铺楼宇装修","listorder":128,"picurl":"/upload/sys/image/a4/d194a8dbbb51e0930f3468be9e35f9.jpg","status":1},{"id":88,"pid":85,"cate_name":"建材/工具购买","listorder":128,"picurl":"","status":1},{"id":89,"pid":86,"cate_name":"装修公司","listorder":128,"picurl":"/upload/sys/image/48/896a08823ab8e03d3e891bbc0de1ca.png","status":1},{"id":90,"pid":86,"cate_name":"装修队/散工","listorder":128,"picurl":"/upload/sys/image/83/6f59e9556b18d60bcbad046678b09f.png","status":1},{"id":91,"pid":86,"cate_name":"设计公司","listorder":128,"picurl":"/upload/sys/image/5b/e152cd684586185a17cb406acfc633.png","status":1},{"id":92,"pid":87,"cate_name":"办公室","listorder":128,"picurl":"/upload/sys/image/c7/89b40df698f56c2ada8244778efbbc.png","status":1},{"id":93,"pid":87,"cate_name":"写字楼","listorder":128,"picurl":"/upload/sys/image/eb/5b62733f3fd5ce3e711f36f60bfe1d.png","status":1},{"id":94,"pid":87,"cate_name":"服装店","listorder":128,"picurl":"/upload/sys/image/fc/ba0aa471c1d6befbd2689b9484cbfb.png","status":1},{"id":95,"pid":88,"cate_name":"五金工具","listorder":128,"picurl":"/upload/sys/image/c6/849b58343fb862c35e30eee336ec06.png","status":1},{"id":96,"pid":88,"cate_name":"暖气地暖","listorder":128,"picurl":"/upload/sys/image/25/85ac122ae328bd16beebc2b41fc84d.png","status":1},{"id":97,"pid":88,"cate_name":"厨卫电器","listorder":128,"picurl":"/upload/sys/image/03/236afadd172766c45c3d1c954c8dcc.png","status":1},{"id":98,"pid":0,"cate_name":"休闲娱乐","listorder":9,"picurl":"","status":1},{"id":99,"pid":98,"cate_name":"休闲","listorder":128,"picurl":"/upload/sys/image/38/037811310bc3ebbe7dce5009168eba.jpg","status":1},{"id":100,"pid":98,"cate_name":"娱乐","listorder":128,"picurl":"","status":1},{"id":102,"pid":99,"cate_name":"足疗按摩","listorder":128,"picurl":"/upload/sys/image/b1/01d04d85a3b0bd1d9d7f49eeeb4bf8.png","status":1},{"id":103,"pid":99,"cate_name":"洗浴/温泉","listorder":128,"picurl":"/upload/sys/image/41/cb9dc8003d96a0da23db831934f08b.png","status":1},{"id":104,"pid":99,"cate_name":"运动健身","listorder":128,"picurl":"/upload/sys/image/77/f25c35ad97885e642b5fd349add418.png","status":1},{"id":105,"pid":100,"cate_name":"夜店酒吧","listorder":128,"picurl":"/upload/sys/image/36/a85345f110f42b5d7be0a78debfb86.png","status":1},{"id":106,"pid":100,"cate_name":"室内休闲","listorder":128,"picurl":"/upload/sys/image/52/1a7070b90c1b8bdf34f40f448d8ddc.png","status":1},{"id":107,"pid":100,"cate_name":"户外娱乐","listorder":128,"picurl":"/upload/sys/image/90/16bfe018692e995c7848079744c3ff.png","status":1},{"id":108,"pid":100,"cate_name":"DIY手工坊","listorder":128,"picurl":"/upload/sys/image/51/93c20298d4c09a1d873a5856413cfd.png","status":1},{"id":109,"pid":63,"cate_name":"专业技能","listorder":128,"picurl":"","status":1},{"id":110,"pid":63,"cate_name":"教育/考试","listorder":128,"picurl":"","status":1},{"id":111,"pid":63,"cate_name":"艺术/体育","listorder":128,"picurl":"","status":1},{"id":112,"pid":63,"cate_name":"职业发展","listorder":128,"picurl":"","status":1},{"id":113,"pid":109,"cate_name":"IT培训","listorder":128,"picurl":"/upload/sys/image/15/9ea3da34af7f9b95672aa47cbb8fc3.png","status":1},{"id":114,"pid":109,"cate_name":"设计培训","listorder":128,"picurl":"/upload/sys/image/31/39584a0a07ab89060e499ea41070ec.png","status":1},{"id":115,"pid":110,"cate_name":"婴幼儿教育","listorder":128,"picurl":"/upload/sys/image/20/afdf4baf1187c245b6d4283b2a2b6d.png","status":1},{"id":116,"pid":110,"cate_name":"学历教育","listorder":128,"picurl":"/upload/sys/image/6b/a3ab235aecefae560c00a61ff36e87.png","status":1},{"id":117,"pid":111,"cate_name":"艺术培训","listorder":128,"picurl":"/upload/sys/image/0e/366b7658e3eb85683fc4a918d3144f.png","status":1},{"id":118,"pid":111,"cate_name":"体育培训","listorder":128,"picurl":"/upload/sys/image/3c/e2e53f9197d173166df3746da6c71e.png","status":1},{"id":119,"pid":112,"cate_name":"管理培训","listorder":128,"picurl":"/upload/sys/image/2c/57aa9b44a709deaef44d0b02bb0242.png","status":1},{"id":120,"pid":112,"cate_name":"技能培训","listorder":128,"picurl":"/upload/sys/image/5a/c9b1b6422229b0d3ab8532e24c7538.png","status":1},{"id":121,"pid":8,"cate_name":"汽车维修保养","listorder":128,"picurl":"","status":1},{"id":122,"pid":8,"cate_name":"汽车改装/防护","listorder":128,"picurl":"","status":1},{"id":123,"pid":8,"cate_name":"汽车美容装饰","listorder":128,"picurl":"","status":1},{"id":124,"pid":121,"cate_name":"刹车维修","listorder":128,"picurl":"/upload/sys/image/9b/7f2df9542ae427b3da0d49186bc8d6.png","status":1},{"id":125,"pid":121,"cate_name":"发动机维修","listorder":128,"picurl":"/upload/sys/image/a7/0fc95f28f264837567be43fcd616d3.png","status":1},{"id":126,"pid":121,"cate_name":"空调维修","listorder":128,"picurl":"/upload/sys/image/4c/cb83cb6623a1e8ef1f4de41f065522.png","status":1},{"id":127,"pid":121,"cate_name":"变速箱维修","listorder":128,"picurl":"/upload/sys/image/58/e746ca1ddf7fb0362cbf8698e975df.png","status":1},{"id":128,"pid":122,"cate_name":"灯光改装","listorder":128,"picurl":"/upload/sys/image/6b/b325997e25ed34e18dd3f51134d49a.png","status":1},{"id":129,"pid":122,"cate_name":"防盗系统","listorder":128,"picurl":"/upload/sys/image/5d/a3377ddd106dd9c6de8e13bbbc7501.png","status":1},{"id":130,"pid":122,"cate_name":"内饰改装","listorder":128,"picurl":"/upload/sys/image/c9/a317a78e4253f3dd90612761e3ee90.png","status":1},{"id":131,"pid":123,"cate_name":"洗车","listorder":128,"picurl":"/upload/sys/image/6f/a4e21858d05d83745a1cb5341a4dc1.png","status":1},{"id":132,"pid":123,"cate_name":"座椅包真皮","listorder":128,"picurl":"/upload/sys/image/16/da3f11851195ba4ffd7dc7a5d5ec05.png","status":1},{"id":133,"pid":123,"cate_name":"底盘装甲","listorder":128,"picurl":"/upload/sys/image/dd/d5cb2542dc3ed3c86e60cb5a8f3246.png","status":1},{"id":134,"pid":0,"cate_name":"商务服务","listorder":8,"picurl":"","status":1},{"id":135,"pid":134,"cate_name":"物流","listorder":128,"picurl":"","status":1},{"id":136,"pid":134,"cate_name":"宣传支持","listorder":128,"picurl":"","status":1},{"id":137,"pid":134,"cate_name":"制作","listorder":128,"picurl":"","status":1},{"id":138,"pid":134,"cate_name":"维修服务","listorder":128,"picurl":"","status":1},{"id":139,"pid":134,"cate_name":"咨询","listorder":128,"picurl":"","status":1},{"id":140,"pid":135,"cate_name":"货运物流","listorder":128,"picurl":"/upload/sys/image/fc/43e5b3ad24577873e2ff78bcf1d34d.png","status":1},{"id":141,"pid":135,"cate_name":"快递","listorder":128,"picurl":"/upload/sys/image/f2/54c5b6605b65971df8e5843d8c1cd0.png","status":1},{"id":142,"pid":136,"cate_name":"广告传媒","listorder":128,"picurl":"/upload/sys/image/3b/7c1738b7da3bbee477266605c42152.png","status":1},{"id":143,"pid":136,"cate_name":"印刷包装","listorder":128,"picurl":"/upload/sys/image/48/1869f596423bd99f60fc9124f5505a.png","status":1},{"id":144,"pid":136,"cate_name":"展会服务","listorder":128,"picurl":"/upload/sys/image/65/729acd131acabc63c4c106c7fdc2cb.png","status":1},{"id":145,"pid":137,"cate_name":"礼品定制","listorder":128,"picurl":"/upload/sys/image/f3/cf9b09321f4f2c8ad52b1cc4b1549d.png","status":1},{"id":146,"pid":137,"cate_name":"制卡","listorder":128,"picurl":"/upload/sys/image/a0/3b8d87db6ce46f43173183f1394377.png","status":1},{"id":147,"pid":138,"cate_name":"办公设备维修","listorder":128,"picurl":"/upload/sys/image/39/2d3cee1641e501a04b1ebf313f0a05.png","status":1},{"id":148,"pid":138,"cate_name":"机械设备维修","listorder":128,"picurl":"/upload/sys/image/d9/1ff2626e04d9b2a301d0ed854e4eba.png","status":1},{"id":150,"pid":139,"cate_name":"法律咨询","listorder":128,"picurl":"/upload/sys/image/59/bd47ff94b8336e310f2edaa2117373.png","status":1},{"id":151,"pid":139,"cate_name":"其他咨询","listorder":128,"picurl":"/upload/sys/image/2e/6043684a8ce7b747967d5f104fefb9.png","status":1},{"id":152,"pid":0,"cate_name":"旅游酒店","listorder":10,"picurl":"","status":1},{"id":153,"pid":152,"cate_name":"周边游","listorder":128,"picurl":"","status":1},{"id":154,"pid":152,"cate_name":"国内游","listorder":128,"picurl":"","status":1},{"id":155,"pid":152,"cate_name":"出境游","listorder":128,"picurl":"","status":1},{"id":157,"pid":152,"cate_name":"酒店预订","listorder":128,"picurl":"","status":1},{"id":158,"pid":153,"cate_name":"农家乐","listorder":128,"picurl":"/upload/sys/image/c7/891eaddba22fa4994e82a0952ab73f.png","status":1},{"id":159,"pid":153,"cate_name":"真人CS","listorder":128,"picurl":"/upload/sys/image/c5/49ba5764ea817c8523af8bf85a5405.png","status":1},{"id":160,"pid":153,"cate_name":"生态园","listorder":128,"picurl":"/upload/sys/image/f8/42d7bd7166c10972340a2e3e30385f.png","status":1},{"id":161,"pid":154,"cate_name":"杭州","listorder":128,"picurl":"/upload/sys/image/49/c6282463b25072e1448239216aa421.png","status":1},{"id":162,"pid":154,"cate_name":"九寨沟","listorder":128,"picurl":"/upload/sys/image/f6/20590ed188eb695683f9e1044f5050.png","status":1},{"id":163,"pid":154,"cate_name":"乌镇","listorder":128,"picurl":"/upload/sys/image/6d/2b3fbfafc63cd9bf6579b223b1925b.png","status":1},{"id":164,"pid":154,"cate_name":"丽江","listorder":128,"picurl":"/upload/sys/image/53/aea78bccdc4a5b6f7415b5d56c7ecc.png","status":1},{"id":165,"pid":155,"cate_name":"马尔代夫","listorder":128,"picurl":"/upload/sys/image/a4/dcb1826e461ff2305ec895a25325aa.png","status":1},{"id":166,"pid":155,"cate_name":"迪拜","listorder":128,"picurl":"/upload/sys/image/9e/81caa8b0bad4cdf6e52b4d649536d2.png","status":1},{"id":167,"pid":155,"cate_name":"巴厘岛","listorder":128,"picurl":"/upload/sys/image/89/04a49f5a41909cf3d71cc3696d72e5.png","status":1},{"id":168,"pid":157,"cate_name":"星级酒店","listorder":128,"picurl":"/upload/sys/image/66/32a3d50d76a653e15d3f6260d16f45.png","status":1},{"id":169,"pid":157,"cate_name":"经济型酒店","listorder":128,"picurl":"/upload/sys/image/b9/12a9355d1aa1968f9725c1f2e41843.png","status":1},{"id":170,"pid":157,"cate_name":"特价酒店","listorder":128,"picurl":"/upload/sys/image/6d/cf97794cc053e9fd6bb8c0f083d7f6.png","status":1},{"id":171,"pid":0,"cate_name":"招聘求职","listorder":4,"picurl":"","status":1},{"id":172,"pid":171,"cate_name":"招聘","listorder":128,"picurl":"","status":1},{"id":173,"pid":171,"cate_name":"求职","listorder":128,"picurl":"","status":1},{"id":174,"pid":172,"cate_name":"生活|服务业","listorder":128,"picurl":"/upload/sys/image/cb/b31784e164109a34b081a7e3b5757f.png","status":1},{"id":175,"pid":172,"cate_name":"人力|行政","listorder":128,"picurl":"/upload/sys/image/ea/8430671cdad0bfd5e8452eb349d3c9.png","status":1},{"id":176,"pid":172,"cate_name":"销售|客服","listorder":128,"picurl":"/upload/sys/image/92/2394c0ed79d3a1fc5f8088944cbac7.png","status":1},{"id":178,"pid":172,"cate_name":"网络|通信","listorder":128,"picurl":"/upload/sys/image/15/9ea3da34af7f9b95672aa47cbb8fc3.png","status":1},{"id":179,"pid":172,"cate_name":"市场|媒介|设计","listorder":128,"picurl":"/upload/sys/image/3b/7c1738b7da3bbee477266605c42152.png","status":1},{"id":180,"pid":172,"cate_name":"财会|金融","listorder":128,"picurl":"/upload/sys/image/c9/3d0e04c27edf2c00a2bffd81698f4e.png","status":1},{"id":181,"pid":172,"cate_name":"生产|物流","listorder":128,"picurl":"/upload/sys/image/fc/43e5b3ad24577873e2ff78bcf1d34d.png","status":1},{"id":182,"pid":172,"cate_name":"建筑|装修|物业|其他","listorder":128,"picurl":"/upload/sys/image/48/896a08823ab8e03d3e891bbc0de1ca.png","status":1},{"id":183,"pid":173,"cate_name":"销售|客服","listorder":128,"picurl":"/upload/sys/image/2e/6043684a8ce7b747967d5f104fefb9.png","status":1},{"id":184,"pid":173,"cate_name":"人事|行政","listorder":128,"picurl":"/upload/sys/image/88/cf2e6be35474761815128ba668a03b.png","status":1},{"id":185,"pid":173,"cate_name":"计算机|网络|通信","listorder":128,"picurl":"/upload/sys/image/95/882e6b90144077991593a3a1caad9c.png","status":1},{"id":186,"pid":173,"cate_name":"建筑|房产|装修|物业","listorder":128,"picurl":"/upload/sys/image/5b/e152cd684586185a17cb406acfc633.png","status":1},{"id":187,"pid":13,"cate_name":"洗衣护理","listorder":128,"picurl":"/upload/sys/image/b2/13f601752c83597f9792a1f964aa7f.png","status":1},{"id":188,"pid":13,"cate_name":"文印图文","listorder":128,"picurl":"/upload/sys/image/73/d004a2a505624cecf06a600f653d91.png","status":1},{"id":189,"pid":13,"cate_name":"送水送奶","listorder":128,"picurl":"/upload/sys/image/21/140ab6233abdbb97f0c899e8ea7c3c.png","status":1},{"id":191,"pid":13,"cate_name":"物流快递","listorder":128,"picurl":"/upload/sys/image/02/b8ffc4ae5871d491d7629fb0fd094e.png","status":1},{"id":192,"pid":13,"cate_name":"开锁换锁","listorder":128,"picurl":"/upload/sys/image/7f/27f09a91870d1658ff785c2c12ab85.png","status":1},{"id":194,"pid":13,"cate_name":"律师服务","listorder":128,"picurl":"/upload/sys/image/60/ff366827e77a878c6f2dc3fd0b6dee.png","status":1},{"id":195,"pid":13,"cate_name":"手机电脑维修","listorder":128,"picurl":"/upload/sys/image/ae/eea77877724a9269f602609a71b10f.png","status":1}],"images_img":[""],"images_arr":[""]}
     */

    private int code;
    private String message;
    private DataBean data;

    public static CompanySetBean objectFromData(String str) {

        return new Gson().fromJson(str, CompanySetBean.class);
    }

    public static List<CompanySetBean> arrayCompanySetBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<CompanySetBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

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

    public static class DataBean {
        /**
         * id : 82
         * member_id : 1000010
         * company_name : 消磨嗯额
         * description :
         * company_trade : 65
         * tel : 631132692
         * mobile : 13295815771
         * qq : 631132692
         * province_id : 2
         * city_id : 23
         * area_id : 248
         * address : 哈哈
         * images :
         * linkname : 我怎么
         * content : 哦哦哦
         * ctime : 1528803977
         * view_cnt : 0
         * status : 0
         * push_id : 0
         * change_ad_id : 0
         * company_trade_name : IT
         * province_name : 广东
         * city_name : 潮州
         * area_name : 湘桥
         * province_list : [{"provinceid":1,"provincename":"直辖市","displayorder":1},{"provinceid":2,"provincename":"广东","displayorder":0},{"provinceid":3,"provincename":"浙江","displayorder":0},{"provinceid":4,"provincename":"安徽","displayorder":0},{"provinceid":5,"provincename":"福建","displayorder":0},{"provinceid":6,"provincename":"甘肃","displayorder":0},{"provinceid":7,"provincename":"广西","displayorder":0},{"provinceid":8,"provincename":"贵州","displayorder":0},{"provinceid":9,"provincename":"海南","displayorder":0},{"provinceid":10,"provincename":"河北","displayorder":0},{"provinceid":11,"provincename":"河南","displayorder":0},{"provinceid":12,"provincename":"黑龙江","displayorder":0},{"provinceid":13,"provincename":"湖北","displayorder":0},{"provinceid":14,"provincename":"湖南","displayorder":0},{"provinceid":15,"provincename":"吉林","displayorder":0},{"provinceid":16,"provincename":"江苏","displayorder":0},{"provinceid":17,"provincename":"江西","displayorder":0},{"provinceid":18,"provincename":"辽宁","displayorder":0},{"provinceid":19,"provincename":"内蒙古","displayorder":0},{"provinceid":20,"provincename":"宁夏","displayorder":0},{"provinceid":21,"provincename":"青海","displayorder":0},{"provinceid":22,"provincename":"山东","displayorder":0},{"provinceid":23,"provincename":"山西","displayorder":0},{"provinceid":24,"provincename":"陕西","displayorder":0},{"provinceid":25,"provincename":"四川","displayorder":0},{"provinceid":26,"provincename":"西藏","displayorder":0},{"provinceid":28,"provincename":"云南","displayorder":0},{"provinceid":35,"provincename":"新疆","displayorder":1}]
         * city_list : [{"cityid":5,"provinceid":2,"cityname":"广州","displayorder":0,"citypinyin":"guangzhou"},{"cityid":6,"provinceid":2,"cityname":"深圳","displayorder":0,"citypinyin":"shen"},{"cityid":7,"provinceid":2,"cityname":"珠海","displayorder":0,"citypinyin":"zhuhai"},{"cityid":8,"provinceid":2,"cityname":"汕头","displayorder":0,"citypinyin":"shantou"},{"cityid":9,"provinceid":2,"cityname":"韶关","displayorder":0,"citypinyin":"shaoguan"},{"cityid":10,"provinceid":2,"cityname":"佛山","displayorder":0,"citypinyin":"foshan"},{"cityid":11,"provinceid":2,"cityname":"江门","displayorder":0,"citypinyin":"jiangmen"},{"cityid":12,"provinceid":2,"cityname":"湛江","displayorder":0,"citypinyin":"zhanjiang"},{"cityid":13,"provinceid":2,"cityname":"茂名","displayorder":0,"citypinyin":"maoming"},{"cityid":14,"provinceid":2,"cityname":"肇庆","displayorder":0,"citypinyin":"zhaoqing"},{"cityid":15,"provinceid":2,"cityname":"惠州","displayorder":0,"citypinyin":"huizhou"},{"cityid":16,"provinceid":2,"cityname":"梅州","displayorder":0,"citypinyin":"meizhou"},{"cityid":17,"provinceid":2,"cityname":"汕尾","displayorder":0,"citypinyin":"shanwei"},{"cityid":18,"provinceid":2,"cityname":"河源","displayorder":0,"citypinyin":"heyuan"},{"cityid":19,"provinceid":2,"cityname":"阳江","displayorder":0,"citypinyin":"yangjiang"},{"cityid":20,"provinceid":2,"cityname":"清远","displayorder":0,"citypinyin":"qingyuan"},{"cityid":21,"provinceid":2,"cityname":"东莞","displayorder":0,"citypinyin":"dong"},{"cityid":22,"provinceid":2,"cityname":"中山","displayorder":0,"citypinyin":"zhongshan"},{"cityid":23,"provinceid":2,"cityname":"潮州","displayorder":0,"citypinyin":"chaozhou"},{"cityid":24,"provinceid":2,"cityname":"揭阳","displayorder":0,"citypinyin":"jieyang"},{"cityid":25,"provinceid":2,"cityname":"云浮","displayorder":0,"citypinyin":"yunfu"}]
         * area_list : [{"areaid":248,"areaname":"湘桥","cityid":23,"displayorder":1},{"areaid":249,"areaname":"枫溪","cityid":23,"displayorder":2},{"areaid":250,"areaname":"潮安","cityid":23,"displayorder":3},{"areaid":251,"areaname":"饶平","cityid":23,"displayorder":4},{"areaid":252,"areaname":"潮州周边","cityid":23,"displayorder":5}]
         * cate : [{"id":6,"pid":0,"cate_name":"家政服务","listorder":1,"picurl":"","status":1},{"id":7,"pid":0,"cate_name":"房产","listorder":2,"picurl":"","status":1},{"id":8,"pid":0,"cate_name":"汽车服务","listorder":6,"picurl":"","status":1},{"id":13,"pid":6,"cate_name":"家政服务","listorder":128,"picurl":"","status":1},{"id":16,"pid":7,"cate_name":"房产","listorder":128,"picurl":"","status":1},{"id":17,"pid":16,"cate_name":"写字楼","listorder":128,"picurl":"/upload/sys/image/a4/91d1b794aa898ef5c33970ef708ff9.png","status":1},{"id":19,"pid":18,"cate_name":"二手车","listorder":128,"picurl":"/upload/sys/image/89/7f5d584642af62403f3b507e377cb3.png","status":0},{"id":20,"pid":8,"cate_name":"汽车服务","listorder":128,"picurl":"/upload/sys/image/89/7f5d584642af62403f3b507e377cb3.png","status":1},{"id":30,"pid":23,"cate_name":"建筑装修","listorder":128,"picurl":"","status":0},{"id":36,"pid":13,"cate_name":"保洁清洗","listorder":128,"picurl":"/upload/sys/image/19/963ff8a098ef762a6ce1749a6ac4aa.png","status":1},{"id":37,"pid":13,"cate_name":"保姆陪护","listorder":128,"picurl":"/upload/sys/image/b1/502c8a71ce1cf723bfadb59866cad3.png","status":1},{"id":42,"pid":16,"cate_name":"商铺租售","listorder":128,"picurl":"/upload/sys/image/0e/de81301601f13f6398f0daa9d04d45.png","status":1},{"id":43,"pid":16,"cate_name":"小区","listorder":128,"picurl":"/upload/sys/image/e3/ae9859f6b8e61c8d62da9ad7897f6a.png","status":1},{"id":44,"pid":16,"cate_name":"新房","listorder":128,"picurl":"/upload/sys/image/74/2f169a1d485c454a9bf62ad5118e4f.png","status":1},{"id":45,"pid":16,"cate_name":"厂房/土地","listorder":128,"picurl":"/upload/sys/image/ba/5cf061d099e1b8fb68bb12b2a8927b.png","status":1},{"id":46,"pid":20,"cate_name":"租车","listorder":128,"picurl":"/upload/sys/image/ae/d58cf523d6193db62b84045334e202.png","status":1},{"id":49,"pid":20,"cate_name":"二手车","listorder":128,"picurl":"/upload/sys/image/5f/5e3bf661d1481079115505f41b17be.png","status":1},{"id":59,"pid":30,"cate_name":"装修建材","listorder":128,"picurl":"","status":0},{"id":60,"pid":30,"cate_name":"家用电器","listorder":128,"picurl":"","status":0},{"id":63,"pid":0,"cate_name":"教育培训","listorder":5,"picurl":"","status":1},{"id":64,"pid":63,"cate_name":"家教机构","listorder":128,"picurl":"","status":1},{"id":65,"pid":64,"cate_name":"IT","listorder":128,"picurl":"/upload/sys/image/95/882e6b90144077991593a3a1caad9c.png","status":1},{"id":66,"pid":0,"cate_name":"宠物","listorder":128,"picurl":"","status":1},{"id":67,"pid":66,"cate_name":"宠物狗","listorder":128,"picurl":"","status":1},{"id":68,"pid":66,"cate_name":"宠物猫","listorder":128,"picurl":"","status":1},{"id":72,"pid":68,"cate_name":"短毛猫","listorder":128,"picurl":"/upload/sys/image/4c/356813e6cd90af6f2bb41e24b3a5d9.png","status":1},{"id":73,"pid":79,"cate_name":"泰迪犬","listorder":128,"picurl":"/upload/sys/image/c0/5b59cf470ba81c03e2c44d43ed3403.png","status":1},{"id":74,"pid":79,"cate_name":"博美犬","listorder":128,"picurl":"/upload/sys/image/ea/2f48f56c52a741cf7ae45991271c95.png","status":1},{"id":75,"pid":80,"cate_name":"哈士奇","listorder":128,"picurl":"/upload/sys/image/1c/a80a4db0e1fdd206088f20b85f5e92.png","status":1},{"id":76,"pid":80,"cate_name":"萨摩耶","listorder":128,"picurl":"/upload/sys/image/9e/d914a3251f72b42e2f7fcd783eca35.png","status":1},{"id":77,"pid":81,"cate_name":"拉布拉多","listorder":128,"picurl":"/upload/sys/image/5a/89de937b7e876f1ce07f24f606d818.jpg","status":1},{"id":78,"pid":81,"cate_name":"金毛","listorder":128,"picurl":"/upload/sys/image/df/ba3a4b7445ec25ceefd41c14019d5f.png","status":1},{"id":79,"pid":67,"cate_name":"小型犬","listorder":128,"picurl":"/upload/sys/image/ea/6a91e3bfa8f0e716ce394f614457b2.png","status":1},{"id":80,"pid":67,"cate_name":"中型犬","listorder":128,"picurl":"/upload/sys/image/b2/c51b41cac08e6a992a4f8d8f293688.png","status":1},{"id":81,"pid":67,"cate_name":"大型犬","listorder":128,"picurl":"/upload/sys/image/12/3bdbe7c7872b25de01404e5e075c0b.png","status":1},{"id":85,"pid":0,"cate_name":"装修建材","listorder":7,"picurl":"","status":1},{"id":86,"pid":85,"cate_name":"家装服务","listorder":128,"picurl":"/upload/sys/image/40/4ba3a6c03f554b920849eb09d9ca18.jpg","status":1},{"id":87,"pid":85,"cate_name":"店铺楼宇装修","listorder":128,"picurl":"/upload/sys/image/a4/d194a8dbbb51e0930f3468be9e35f9.jpg","status":1},{"id":88,"pid":85,"cate_name":"建材/工具购买","listorder":128,"picurl":"","status":1},{"id":89,"pid":86,"cate_name":"装修公司","listorder":128,"picurl":"/upload/sys/image/48/896a08823ab8e03d3e891bbc0de1ca.png","status":1},{"id":90,"pid":86,"cate_name":"装修队/散工","listorder":128,"picurl":"/upload/sys/image/83/6f59e9556b18d60bcbad046678b09f.png","status":1},{"id":91,"pid":86,"cate_name":"设计公司","listorder":128,"picurl":"/upload/sys/image/5b/e152cd684586185a17cb406acfc633.png","status":1},{"id":92,"pid":87,"cate_name":"办公室","listorder":128,"picurl":"/upload/sys/image/c7/89b40df698f56c2ada8244778efbbc.png","status":1},{"id":93,"pid":87,"cate_name":"写字楼","listorder":128,"picurl":"/upload/sys/image/eb/5b62733f3fd5ce3e711f36f60bfe1d.png","status":1},{"id":94,"pid":87,"cate_name":"服装店","listorder":128,"picurl":"/upload/sys/image/fc/ba0aa471c1d6befbd2689b9484cbfb.png","status":1},{"id":95,"pid":88,"cate_name":"五金工具","listorder":128,"picurl":"/upload/sys/image/c6/849b58343fb862c35e30eee336ec06.png","status":1},{"id":96,"pid":88,"cate_name":"暖气地暖","listorder":128,"picurl":"/upload/sys/image/25/85ac122ae328bd16beebc2b41fc84d.png","status":1},{"id":97,"pid":88,"cate_name":"厨卫电器","listorder":128,"picurl":"/upload/sys/image/03/236afadd172766c45c3d1c954c8dcc.png","status":1},{"id":98,"pid":0,"cate_name":"休闲娱乐","listorder":9,"picurl":"","status":1},{"id":99,"pid":98,"cate_name":"休闲","listorder":128,"picurl":"/upload/sys/image/38/037811310bc3ebbe7dce5009168eba.jpg","status":1},{"id":100,"pid":98,"cate_name":"娱乐","listorder":128,"picurl":"","status":1},{"id":102,"pid":99,"cate_name":"足疗按摩","listorder":128,"picurl":"/upload/sys/image/b1/01d04d85a3b0bd1d9d7f49eeeb4bf8.png","status":1},{"id":103,"pid":99,"cate_name":"洗浴/温泉","listorder":128,"picurl":"/upload/sys/image/41/cb9dc8003d96a0da23db831934f08b.png","status":1},{"id":104,"pid":99,"cate_name":"运动健身","listorder":128,"picurl":"/upload/sys/image/77/f25c35ad97885e642b5fd349add418.png","status":1},{"id":105,"pid":100,"cate_name":"夜店酒吧","listorder":128,"picurl":"/upload/sys/image/36/a85345f110f42b5d7be0a78debfb86.png","status":1},{"id":106,"pid":100,"cate_name":"室内休闲","listorder":128,"picurl":"/upload/sys/image/52/1a7070b90c1b8bdf34f40f448d8ddc.png","status":1},{"id":107,"pid":100,"cate_name":"户外娱乐","listorder":128,"picurl":"/upload/sys/image/90/16bfe018692e995c7848079744c3ff.png","status":1},{"id":108,"pid":100,"cate_name":"DIY手工坊","listorder":128,"picurl":"/upload/sys/image/51/93c20298d4c09a1d873a5856413cfd.png","status":1},{"id":109,"pid":63,"cate_name":"专业技能","listorder":128,"picurl":"","status":1},{"id":110,"pid":63,"cate_name":"教育/考试","listorder":128,"picurl":"","status":1},{"id":111,"pid":63,"cate_name":"艺术/体育","listorder":128,"picurl":"","status":1},{"id":112,"pid":63,"cate_name":"职业发展","listorder":128,"picurl":"","status":1},{"id":113,"pid":109,"cate_name":"IT培训","listorder":128,"picurl":"/upload/sys/image/15/9ea3da34af7f9b95672aa47cbb8fc3.png","status":1},{"id":114,"pid":109,"cate_name":"设计培训","listorder":128,"picurl":"/upload/sys/image/31/39584a0a07ab89060e499ea41070ec.png","status":1},{"id":115,"pid":110,"cate_name":"婴幼儿教育","listorder":128,"picurl":"/upload/sys/image/20/afdf4baf1187c245b6d4283b2a2b6d.png","status":1},{"id":116,"pid":110,"cate_name":"学历教育","listorder":128,"picurl":"/upload/sys/image/6b/a3ab235aecefae560c00a61ff36e87.png","status":1},{"id":117,"pid":111,"cate_name":"艺术培训","listorder":128,"picurl":"/upload/sys/image/0e/366b7658e3eb85683fc4a918d3144f.png","status":1},{"id":118,"pid":111,"cate_name":"体育培训","listorder":128,"picurl":"/upload/sys/image/3c/e2e53f9197d173166df3746da6c71e.png","status":1},{"id":119,"pid":112,"cate_name":"管理培训","listorder":128,"picurl":"/upload/sys/image/2c/57aa9b44a709deaef44d0b02bb0242.png","status":1},{"id":120,"pid":112,"cate_name":"技能培训","listorder":128,"picurl":"/upload/sys/image/5a/c9b1b6422229b0d3ab8532e24c7538.png","status":1},{"id":121,"pid":8,"cate_name":"汽车维修保养","listorder":128,"picurl":"","status":1},{"id":122,"pid":8,"cate_name":"汽车改装/防护","listorder":128,"picurl":"","status":1},{"id":123,"pid":8,"cate_name":"汽车美容装饰","listorder":128,"picurl":"","status":1},{"id":124,"pid":121,"cate_name":"刹车维修","listorder":128,"picurl":"/upload/sys/image/9b/7f2df9542ae427b3da0d49186bc8d6.png","status":1},{"id":125,"pid":121,"cate_name":"发动机维修","listorder":128,"picurl":"/upload/sys/image/a7/0fc95f28f264837567be43fcd616d3.png","status":1},{"id":126,"pid":121,"cate_name":"空调维修","listorder":128,"picurl":"/upload/sys/image/4c/cb83cb6623a1e8ef1f4de41f065522.png","status":1},{"id":127,"pid":121,"cate_name":"变速箱维修","listorder":128,"picurl":"/upload/sys/image/58/e746ca1ddf7fb0362cbf8698e975df.png","status":1},{"id":128,"pid":122,"cate_name":"灯光改装","listorder":128,"picurl":"/upload/sys/image/6b/b325997e25ed34e18dd3f51134d49a.png","status":1},{"id":129,"pid":122,"cate_name":"防盗系统","listorder":128,"picurl":"/upload/sys/image/5d/a3377ddd106dd9c6de8e13bbbc7501.png","status":1},{"id":130,"pid":122,"cate_name":"内饰改装","listorder":128,"picurl":"/upload/sys/image/c9/a317a78e4253f3dd90612761e3ee90.png","status":1},{"id":131,"pid":123,"cate_name":"洗车","listorder":128,"picurl":"/upload/sys/image/6f/a4e21858d05d83745a1cb5341a4dc1.png","status":1},{"id":132,"pid":123,"cate_name":"座椅包真皮","listorder":128,"picurl":"/upload/sys/image/16/da3f11851195ba4ffd7dc7a5d5ec05.png","status":1},{"id":133,"pid":123,"cate_name":"底盘装甲","listorder":128,"picurl":"/upload/sys/image/dd/d5cb2542dc3ed3c86e60cb5a8f3246.png","status":1},{"id":134,"pid":0,"cate_name":"商务服务","listorder":8,"picurl":"","status":1},{"id":135,"pid":134,"cate_name":"物流","listorder":128,"picurl":"","status":1},{"id":136,"pid":134,"cate_name":"宣传支持","listorder":128,"picurl":"","status":1},{"id":137,"pid":134,"cate_name":"制作","listorder":128,"picurl":"","status":1},{"id":138,"pid":134,"cate_name":"维修服务","listorder":128,"picurl":"","status":1},{"id":139,"pid":134,"cate_name":"咨询","listorder":128,"picurl":"","status":1},{"id":140,"pid":135,"cate_name":"货运物流","listorder":128,"picurl":"/upload/sys/image/fc/43e5b3ad24577873e2ff78bcf1d34d.png","status":1},{"id":141,"pid":135,"cate_name":"快递","listorder":128,"picurl":"/upload/sys/image/f2/54c5b6605b65971df8e5843d8c1cd0.png","status":1},{"id":142,"pid":136,"cate_name":"广告传媒","listorder":128,"picurl":"/upload/sys/image/3b/7c1738b7da3bbee477266605c42152.png","status":1},{"id":143,"pid":136,"cate_name":"印刷包装","listorder":128,"picurl":"/upload/sys/image/48/1869f596423bd99f60fc9124f5505a.png","status":1},{"id":144,"pid":136,"cate_name":"展会服务","listorder":128,"picurl":"/upload/sys/image/65/729acd131acabc63c4c106c7fdc2cb.png","status":1},{"id":145,"pid":137,"cate_name":"礼品定制","listorder":128,"picurl":"/upload/sys/image/f3/cf9b09321f4f2c8ad52b1cc4b1549d.png","status":1},{"id":146,"pid":137,"cate_name":"制卡","listorder":128,"picurl":"/upload/sys/image/a0/3b8d87db6ce46f43173183f1394377.png","status":1},{"id":147,"pid":138,"cate_name":"办公设备维修","listorder":128,"picurl":"/upload/sys/image/39/2d3cee1641e501a04b1ebf313f0a05.png","status":1},{"id":148,"pid":138,"cate_name":"机械设备维修","listorder":128,"picurl":"/upload/sys/image/d9/1ff2626e04d9b2a301d0ed854e4eba.png","status":1},{"id":150,"pid":139,"cate_name":"法律咨询","listorder":128,"picurl":"/upload/sys/image/59/bd47ff94b8336e310f2edaa2117373.png","status":1},{"id":151,"pid":139,"cate_name":"其他咨询","listorder":128,"picurl":"/upload/sys/image/2e/6043684a8ce7b747967d5f104fefb9.png","status":1},{"id":152,"pid":0,"cate_name":"旅游酒店","listorder":10,"picurl":"","status":1},{"id":153,"pid":152,"cate_name":"周边游","listorder":128,"picurl":"","status":1},{"id":154,"pid":152,"cate_name":"国内游","listorder":128,"picurl":"","status":1},{"id":155,"pid":152,"cate_name":"出境游","listorder":128,"picurl":"","status":1},{"id":157,"pid":152,"cate_name":"酒店预订","listorder":128,"picurl":"","status":1},{"id":158,"pid":153,"cate_name":"农家乐","listorder":128,"picurl":"/upload/sys/image/c7/891eaddba22fa4994e82a0952ab73f.png","status":1},{"id":159,"pid":153,"cate_name":"真人CS","listorder":128,"picurl":"/upload/sys/image/c5/49ba5764ea817c8523af8bf85a5405.png","status":1},{"id":160,"pid":153,"cate_name":"生态园","listorder":128,"picurl":"/upload/sys/image/f8/42d7bd7166c10972340a2e3e30385f.png","status":1},{"id":161,"pid":154,"cate_name":"杭州","listorder":128,"picurl":"/upload/sys/image/49/c6282463b25072e1448239216aa421.png","status":1},{"id":162,"pid":154,"cate_name":"九寨沟","listorder":128,"picurl":"/upload/sys/image/f6/20590ed188eb695683f9e1044f5050.png","status":1},{"id":163,"pid":154,"cate_name":"乌镇","listorder":128,"picurl":"/upload/sys/image/6d/2b3fbfafc63cd9bf6579b223b1925b.png","status":1},{"id":164,"pid":154,"cate_name":"丽江","listorder":128,"picurl":"/upload/sys/image/53/aea78bccdc4a5b6f7415b5d56c7ecc.png","status":1},{"id":165,"pid":155,"cate_name":"马尔代夫","listorder":128,"picurl":"/upload/sys/image/a4/dcb1826e461ff2305ec895a25325aa.png","status":1},{"id":166,"pid":155,"cate_name":"迪拜","listorder":128,"picurl":"/upload/sys/image/9e/81caa8b0bad4cdf6e52b4d649536d2.png","status":1},{"id":167,"pid":155,"cate_name":"巴厘岛","listorder":128,"picurl":"/upload/sys/image/89/04a49f5a41909cf3d71cc3696d72e5.png","status":1},{"id":168,"pid":157,"cate_name":"星级酒店","listorder":128,"picurl":"/upload/sys/image/66/32a3d50d76a653e15d3f6260d16f45.png","status":1},{"id":169,"pid":157,"cate_name":"经济型酒店","listorder":128,"picurl":"/upload/sys/image/b9/12a9355d1aa1968f9725c1f2e41843.png","status":1},{"id":170,"pid":157,"cate_name":"特价酒店","listorder":128,"picurl":"/upload/sys/image/6d/cf97794cc053e9fd6bb8c0f083d7f6.png","status":1},{"id":171,"pid":0,"cate_name":"招聘求职","listorder":4,"picurl":"","status":1},{"id":172,"pid":171,"cate_name":"招聘","listorder":128,"picurl":"","status":1},{"id":173,"pid":171,"cate_name":"求职","listorder":128,"picurl":"","status":1},{"id":174,"pid":172,"cate_name":"生活|服务业","listorder":128,"picurl":"/upload/sys/image/cb/b31784e164109a34b081a7e3b5757f.png","status":1},{"id":175,"pid":172,"cate_name":"人力|行政","listorder":128,"picurl":"/upload/sys/image/ea/8430671cdad0bfd5e8452eb349d3c9.png","status":1},{"id":176,"pid":172,"cate_name":"销售|客服","listorder":128,"picurl":"/upload/sys/image/92/2394c0ed79d3a1fc5f8088944cbac7.png","status":1},{"id":178,"pid":172,"cate_name":"网络|通信","listorder":128,"picurl":"/upload/sys/image/15/9ea3da34af7f9b95672aa47cbb8fc3.png","status":1},{"id":179,"pid":172,"cate_name":"市场|媒介|设计","listorder":128,"picurl":"/upload/sys/image/3b/7c1738b7da3bbee477266605c42152.png","status":1},{"id":180,"pid":172,"cate_name":"财会|金融","listorder":128,"picurl":"/upload/sys/image/c9/3d0e04c27edf2c00a2bffd81698f4e.png","status":1},{"id":181,"pid":172,"cate_name":"生产|物流","listorder":128,"picurl":"/upload/sys/image/fc/43e5b3ad24577873e2ff78bcf1d34d.png","status":1},{"id":182,"pid":172,"cate_name":"建筑|装修|物业|其他","listorder":128,"picurl":"/upload/sys/image/48/896a08823ab8e03d3e891bbc0de1ca.png","status":1},{"id":183,"pid":173,"cate_name":"销售|客服","listorder":128,"picurl":"/upload/sys/image/2e/6043684a8ce7b747967d5f104fefb9.png","status":1},{"id":184,"pid":173,"cate_name":"人事|行政","listorder":128,"picurl":"/upload/sys/image/88/cf2e6be35474761815128ba668a03b.png","status":1},{"id":185,"pid":173,"cate_name":"计算机|网络|通信","listorder":128,"picurl":"/upload/sys/image/95/882e6b90144077991593a3a1caad9c.png","status":1},{"id":186,"pid":173,"cate_name":"建筑|房产|装修|物业","listorder":128,"picurl":"/upload/sys/image/5b/e152cd684586185a17cb406acfc633.png","status":1},{"id":187,"pid":13,"cate_name":"洗衣护理","listorder":128,"picurl":"/upload/sys/image/b2/13f601752c83597f9792a1f964aa7f.png","status":1},{"id":188,"pid":13,"cate_name":"文印图文","listorder":128,"picurl":"/upload/sys/image/73/d004a2a505624cecf06a600f653d91.png","status":1},{"id":189,"pid":13,"cate_name":"送水送奶","listorder":128,"picurl":"/upload/sys/image/21/140ab6233abdbb97f0c899e8ea7c3c.png","status":1},{"id":191,"pid":13,"cate_name":"物流快递","listorder":128,"picurl":"/upload/sys/image/02/b8ffc4ae5871d491d7629fb0fd094e.png","status":1},{"id":192,"pid":13,"cate_name":"开锁换锁","listorder":128,"picurl":"/upload/sys/image/7f/27f09a91870d1658ff785c2c12ab85.png","status":1},{"id":194,"pid":13,"cate_name":"律师服务","listorder":128,"picurl":"/upload/sys/image/60/ff366827e77a878c6f2dc3fd0b6dee.png","status":1},{"id":195,"pid":13,"cate_name":"手机电脑维修","listorder":128,"picurl":"/upload/sys/image/ae/eea77877724a9269f602609a71b10f.png","status":1}]
         * images_img : [""]
         * images_arr : [""]
         */

        private int id;
        private int member_id;
        private String company_name;
        private String description;
        private String company_trade;
        private String tel;
        private String mobile;
        private String qq;
        private int province_id;
        private int city_id;
        private int area_id;
        private String address;
        private String images;
        private String linkname;
        private String content;
        private int ctime;
        private int view_cnt;
        private int status;
        private int push_id;
        private int change_ad_id;
        private String company_trade_name;
        private String province_name;
        private String city_name;
        private String area_name;
        private List<ProvinceListBean> province_list;
        private List<CityListBean> city_list;
        private List<AreaListBean> area_list;
        private List<CateBean> cate;
        private List<String> images_img;
        private List<String> images_arr;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCompany_trade() {
            return company_trade;
        }

        public void setCompany_trade(String company_trade) {
            this.company_trade = company_trade;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getLinkname() {
            return linkname;
        }

        public void setLinkname(String linkname) {
            this.linkname = linkname;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public int getView_cnt() {
            return view_cnt;
        }

        public void setView_cnt(int view_cnt) {
            this.view_cnt = view_cnt;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPush_id() {
            return push_id;
        }

        public void setPush_id(int push_id) {
            this.push_id = push_id;
        }

        public int getChange_ad_id() {
            return change_ad_id;
        }

        public void setChange_ad_id(int change_ad_id) {
            this.change_ad_id = change_ad_id;
        }

        public String getCompany_trade_name() {
            return company_trade_name;
        }

        public void setCompany_trade_name(String company_trade_name) {
            this.company_trade_name = company_trade_name;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public List<ProvinceListBean> getProvince_list() {
            return province_list;
        }

        public void setProvince_list(List<ProvinceListBean> province_list) {
            this.province_list = province_list;
        }

        public List<CityListBean> getCity_list() {
            return city_list;
        }

        public void setCity_list(List<CityListBean> city_list) {
            this.city_list = city_list;
        }

        public List<AreaListBean> getArea_list() {
            return area_list;
        }

        public void setArea_list(List<AreaListBean> area_list) {
            this.area_list = area_list;
        }

        public List<CateBean> getCate() {
            return cate;
        }

        public void setCate(List<CateBean> cate) {
            this.cate = cate;
        }

        public List<String> getImages_img() {
            return images_img;
        }

        public void setImages_img(List<String> images_img) {
            this.images_img = images_img;
        }

        public List<String> getImages_arr() {
            return images_arr;
        }

        public void setImages_arr(List<String> images_arr) {
            this.images_arr = images_arr;
        }

        public static class ProvinceListBean {
            /**
             * provinceid : 1
             * provincename : 直辖市
             * displayorder : 1
             */

            private int provinceid;
            private String provincename;
            private int displayorder;

            public static ProvinceListBean objectFromData(String str) {

                return new Gson().fromJson(str, ProvinceListBean.class);
            }

            public static List<ProvinceListBean> arrayProvinceListBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<ProvinceListBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public int getProvinceid() {
                return provinceid;
            }

            public void setProvinceid(int provinceid) {
                this.provinceid = provinceid;
            }

            public String getProvincename() {
                return provincename;
            }

            public void setProvincename(String provincename) {
                this.provincename = provincename;
            }

            public int getDisplayorder() {
                return displayorder;
            }

            public void setDisplayorder(int displayorder) {
                this.displayorder = displayorder;
            }
        }

        public static class CityListBean {
            /**
             * cityid : 5
             * provinceid : 2
             * cityname : 广州
             * displayorder : 0
             * citypinyin : guangzhou
             */

            private int cityid;
            private int provinceid;
            private String cityname;
            private int displayorder;
            private String citypinyin;

            public static CityListBean objectFromData(String str) {

                return new Gson().fromJson(str, CityListBean.class);
            }

            public static List<CityListBean> arrayCityListBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<CityListBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public int getCityid() {
                return cityid;
            }

            public void setCityid(int cityid) {
                this.cityid = cityid;
            }

            public int getProvinceid() {
                return provinceid;
            }

            public void setProvinceid(int provinceid) {
                this.provinceid = provinceid;
            }

            public String getCityname() {
                return cityname;
            }

            public void setCityname(String cityname) {
                this.cityname = cityname;
            }

            public int getDisplayorder() {
                return displayorder;
            }

            public void setDisplayorder(int displayorder) {
                this.displayorder = displayorder;
            }

            public String getCitypinyin() {
                return citypinyin;
            }

            public void setCitypinyin(String citypinyin) {
                this.citypinyin = citypinyin;
            }
        }

        public static class AreaListBean {
            /**
             * areaid : 248
             * areaname : 湘桥
             * cityid : 23
             * displayorder : 1
             */

            private int areaid;
            private String areaname;
            private int cityid;
            private int displayorder;

            public static AreaListBean objectFromData(String str) {

                return new Gson().fromJson(str, AreaListBean.class);
            }

            public static List<AreaListBean> arrayAreaListBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<AreaListBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

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

        public static class CateBean {
            /**
             * id : 6
             * pid : 0
             * cate_name : 家政服务
             * listorder : 1
             * picurl :
             * status : 1
             */

            private int id;
            private int pid;
            private String cate_name;
            private int listorder;
            private String picurl;
            private int status;

            public static CateBean objectFromData(String str) {

                return new Gson().fromJson(str, CateBean.class);
            }

            public static List<CateBean> arrayCateBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<CateBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }

            public int getListorder() {
                return listorder;
            }

            public void setListorder(int listorder) {
                this.listorder = listorder;
            }

            public String getPicurl() {
                return picurl;
            }

            public void setPicurl(String picurl) {
                this.picurl = picurl;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
