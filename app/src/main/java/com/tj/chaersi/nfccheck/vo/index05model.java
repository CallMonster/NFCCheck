package com.tj.chaersi.nfccheck.vo;

import java.util.List;

/**
 * Created by Chaersi on 17/2/8.
 */
public class Index05model {
    /**
     * statecode : 1
     * list : [{"id":"2493ec2a-c8b9-42c5-864e-3ccd1077cd07","zuobiao":"116.665205, 39.83475","overTime":"","sendCellphone":"","urls":[],"serviceTime":"20170212","sendUserName":"王五","name":"气站问题","info":"气站问题"},{"id":"c6c7ef35-e6af-4b85-9db2-f58eab0cba20","zuobiao":"116.657005, 39.82475","overTime":"1","sendCellphone":"","urls":["/static/upload/2017/01/20170118102110_980.jpg"],"serviceTime":"20170208","sendUserName":"王五","name":"2号井阀问题","info":"2号井阀问题 请尽快维修 联系方式112332134"}]
     */

    private String statecode;
    /**
     * id : 2493ec2a-c8b9-42c5-864e-3ccd1077cd07
     * zuobiao : 116.665205, 39.83475
     * overTime :
     * sendCellphone :
     * urls : []
     * serviceTime : 20170212
     * sendUserName : 王五
     * name : 气站问题
     * info : 气站问题
     */

    private List<ListBean> list;

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String id;
        private String zuobiao;
        private String overTime;
        private String sendCellphone;
        private String serviceTime;
        private String sendUserName;
        private String name;
        private String info;
        private List<?> urls;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getZuobiao() {
            return zuobiao;
        }

        public void setZuobiao(String zuobiao) {
            this.zuobiao = zuobiao;
        }

        public String getOverTime() {
            return overTime;
        }

        public void setOverTime(String overTime) {
            this.overTime = overTime;
        }

        public String getSendCellphone() {
            return sendCellphone;
        }

        public void setSendCellphone(String sendCellphone) {
            this.sendCellphone = sendCellphone;
        }

        public String getServiceTime() {
            return serviceTime;
        }

        public void setServiceTime(String serviceTime) {
            this.serviceTime = serviceTime;
        }

        public String getSendUserName() {
            return sendUserName;
        }

        public void setSendUserName(String sendUserName) {
            this.sendUserName = sendUserName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public List<?> getUrls() {
            return urls;
        }

        public void setUrls(List<?> urls) {
            this.urls = urls;
        }
    }
}
