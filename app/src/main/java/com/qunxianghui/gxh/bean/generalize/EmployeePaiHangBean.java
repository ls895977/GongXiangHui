package com.qunxianghui.gxh.bean.generalize;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EmployeePaiHangBean implements Serializable {

    /**
     * code : 0
     * message :
     * data : [{"id":978,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100098","staff_id":1000012,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"朱总","active_time":1525948017,"member_name":"无邪","member_avatar":"","cnt":"3742"},{"id":883,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100003","staff_id":1000013,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"lu","active_time":1526351606,"member_name":"宸翰","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5ace080883a5b.jpeg","cnt":"1506"},{"id":894,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100014","staff_id":1000009,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1527590770,"member_name":"陈公子_璐","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180525/37eb7d67dbc1b5b4b70f5b789002b62c.png","cnt":"1094"},{"id":909,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100029","staff_id":1000055,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525917954,"member_name":"考拉","member_avatar":"","cnt":"932"},{"id":882,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100002","staff_id":1000025,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525934382,"member_name":"群享汇","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5aca11fe1b58a.jpeg","cnt":"786"},{"id":898,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100018","staff_id":1000049,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526305427,"member_name":"龙","member_avatar":"","cnt":"421"},{"id":899,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100019","staff_id":1000053,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526374628,"member_name":"钱朵朵","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5adafd9c51a02.jpg","cnt":"414"},{"id":971,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100091","staff_id":1000211,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"卢总","active_time":1527473760,"member_name":"183****4848","member_avatar":"","cnt":"370"},{"id":884,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100004","staff_id":1000095,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526885554,"member_name":"无奈","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180521/45ff6f724452f3fdfb408c617365529b.png","cnt":"299"},{"id":916,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100036","staff_id":1000043,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525871103,"member_name":"蔡╓〞＝㈠o(*￣▽￣*)ブ","member_avatar":"","cnt":"252"},{"id":918,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100038","staff_id":1000065,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525872311,"member_name":"碗碗","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180605/939b86a9e5b35c1fea4be302d114a6df.png","cnt":"185"},{"id":922,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100042","staff_id":1000035,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525995360,"member_name":"逍遥","member_avatar":"","cnt":"166"},{"id":972,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100092","staff_id":1000180,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"刘老师","active_time":1526707554,"member_name":"171****4444","member_avatar":"","cnt":"159"},{"id":900,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100020","staff_id":1000108,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1527843976,"member_name":"我狂故我拽","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180516/cfc1b7c745701142a1d9d09c7b3be475.png","cnt":"132"},{"id":887,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100007","staff_id":1000160,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526296666,"member_name":"某时某刻某人","member_avatar":"","cnt":"126"},{"id":917,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100037","staff_id":1000041,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1527759407,"member_name":"钟灵","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180513/3da9d4d4e7ad33cb14526b3fd3017fed.png","cnt":"113"},{"id":910,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100030","staff_id":1000120,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525871515,"member_name":"悟空弥陀","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180510/2e5e76f41d5dabfbbb9ef78b0c3db03f.png","cnt":"95"},{"id":902,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100022","staff_id":1000099,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525919814,"member_name":"玄黄子","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180508/72c9c177b015cc362f8e01642fafbde6.png","cnt":"81"},{"id":919,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100039","staff_id":1000036,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525871062,"member_name":"陌上花开","member_avatar":"","cnt":"77"},{"id":885,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100005","staff_id":1000113,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526885606,"member_name":"萤火虫","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180521/43dc11dda46b7340c1d495bb8cea57dd.png","cnt":"63"},{"id":914,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100034","staff_id":1000033,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525871974,"member_name":"中国好吃货","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180519/de960748b264dfe49d7c0586721c27f7.png","cnt":"56"},{"id":915,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100035","staff_id":1000190,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526956081,"member_name":"生人勿近520","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180522/a631c6aee6a201cea4797afad1edeb2c.png","cnt":"47"},{"id":892,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100012","staff_id":1000050,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526351573,"member_name":"周丶阿虎","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180515/a196ddbd7a81a077ca5d6eef69842ae9.png","cnt":"46"},{"id":976,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100096","staff_id":1000038,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"蒋慧燕测试账号","active_time":1527734372,"member_name":"151****1929","member_avatar":"","cnt":"43"},{"id":886,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100006","staff_id":1000052,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526296673,"member_name":"彼岸花","member_avatar":"","cnt":"39"},{"id":893,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100013","staff_id":1000027,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526349059,"member_name":"盖世嘤雄","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5b14a2f9598ad.jpeg","cnt":"37"},{"id":923,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100043","staff_id":1000047,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525870988,"member_name":"王志豪","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180509/2e48b3cb9b70bbbdf5f6e3154fda1eb5.png","cnt":"36"},{"id":889,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100009","staff_id":1000126,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526297132,"member_name":"不忘初心","member_avatar":"","cnt":"33"},{"id":924,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100044","staff_id":1000051,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525871117,"member_name":"不将就丶","member_avatar":"","cnt":"28"},{"id":921,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100041","staff_id":1000116,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525871103,"member_name":"阿紫","member_avatar":"","cnt":"26"},{"id":888,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100008","staff_id":1000054,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526297078,"member_name":"神仙姐姐ah","member_avatar":"","cnt":"24"},{"id":907,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100027","staff_id":1000127,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525921394,"member_name":"183****2923","member_avatar":"","cnt":"24"},{"id":913,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100033","staff_id":1000037,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525871888,"member_name":"犟犟","member_avatar":"","cnt":"24"},{"id":891,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100011","staff_id":1000010,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526349464,"member_name":"臭居居的归来","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180602/acf33e81d668333be5e7269607521940.png","cnt":"22"},{"id":890,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100010","staff_id":1000028,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525870289,"member_name":"金铁锋","member_avatar":"","cnt":"20"},{"id":975,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100095","staff_id":1000178,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"刘老师","active_time":1526697068,"member_name":"186****9333","member_avatar":"","cnt":"19"},{"id":973,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100093","staff_id":1000179,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"刘老师","active_time":1526697323,"member_name":"186****1863","member_avatar":"","cnt":"17"},{"id":904,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100024","staff_id":1000089,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525871134,"member_name":"浊酒丶独饮","member_avatar":"","cnt":"15"},{"id":974,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100094","staff_id":1000174,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"刘老师","active_time":1526703107,"member_name":"131****2267","member_avatar":"","cnt":"14"},{"id":911,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100031","staff_id":1000098,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525917912,"member_name":"emmm","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180519/ad80250fbc37d3931c4f7d1ae1f53ce6.png","cnt":"13"},{"id":903,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100023","staff_id":1000202,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1527486422,"member_name":"不努力不行君","member_avatar":"","cnt":"11"},{"id":896,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100016","staff_id":1000030,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526886428,"member_name":"张壮-可爱多","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180525/008d309c677f88cbd6e4ebebcf63f8f8.png","cnt":"8"},{"id":926,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100046","staff_id":1000192,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526890735,"member_name":"哼0850","member_avatar":"","cnt":"8"},{"id":897,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100017","staff_id":1000057,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526886308,"member_name":"阿碧","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180522/d444c7f730b4edc10edb9a44c2000160.png","cnt":"7"},{"id":906,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100026","staff_id":1000032,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1527040837,"member_name":"越八","member_avatar":"","cnt":"2"},{"id":895,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100015","staff_id":1000029,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526547224,"member_name":"飙分狂人","member_avatar":"http://api.qunxianghui.com.cn/upload/posts/5ad597cd5ac2e.jpg","cnt":"0"},{"id":908,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100028","staff_id":1000096,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525871821,"member_name":"上善若水","member_avatar":"","cnt":"0"},{"id":912,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100032","staff_id":1000110,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525918038,"member_name":"星火","member_avatar":"","cnt":"0"},{"id":920,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100040","staff_id":1000117,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1525871010,"member_name":"莫然呀i","member_avatar":"","cnt":"0"},{"id":925,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100045","staff_id":1000101,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1528104236,"member_name":"134****0390","member_avatar":"","cnt":"0"},{"id":927,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100047","staff_id":1000162,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1527940407,"member_name":"乌云漏光是希望","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180521/6ff529836bfef8fad2020ded0eb55edf.png","cnt":"0"},{"id":928,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100048","staff_id":1000181,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"","active_time":1526956129,"member_name":"杜杜东","member_avatar":"","cnt":"0"},{"id":968,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100088","staff_id":1000175,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"赵龙涛测试用","active_time":1528192194,"member_name":"臭居居的归来","member_avatar":"http://api.qunxianghui.com.cn/upload/images/20180524/f746158014f1c6361431e3347316d42a.png","cnt":"0"},{"id":969,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100089","staff_id":1000142,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"金总测试","active_time":1527579317,"member_name":"186****4448","member_avatar":"","cnt":"0"},{"id":970,"member_id":1000121,"company_id":1000122,"activecode":"50057126001100090","staff_id":1000224,"ctime":1525867180,"status":1,"expires_in":1746720000,"remark":"金总测试","active_time":1527732785,"member_name":"177****5213","member_avatar":"","cnt":"0"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public static EmployeePaiHangBean objectFromData(String str) {

        return new Gson().fromJson(str, EmployeePaiHangBean.class);
    }

    public static List<EmployeePaiHangBean> arrayEmployeePaiHangBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<EmployeePaiHangBean>>() {
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 978
         * member_id : 1000121
         * company_id : 1000122
         * activecode : 50057126001100098
         * staff_id : 1000012
         * ctime : 1525867180
         * status : 1
         * expires_in : 1746720000
         * remark : 朱总
         * active_time : 1525948017
         * member_name : 无邪
         * member_avatar :
         * cnt : 3742
         */

        private int id;
        private int member_id;
        private int company_id;
        private String activecode;
        private int staff_id;
        private int ctime;
        private int status;
        private int expires_in;
        private String remark;
        private int active_time;
        private String member_name;
        private String member_avatar;
        private String cnt;

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

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public String getActivecode() {
            return activecode;
        }

        public void setActivecode(String activecode) {
            this.activecode = activecode;
        }

        public int getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(int staff_id) {
            this.staff_id = staff_id;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getActive_time() {
            return active_time;
        }

        public void setActive_time(int active_time) {
            this.active_time = active_time;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getMember_avatar() {
            return member_avatar;
        }

        public void setMember_avatar(String member_avatar) {
            this.member_avatar = member_avatar;
        }

        public String getCnt() {
            return cnt;
        }

        public void setCnt(String cnt) {
            this.cnt = cnt;
        }
    }
}
