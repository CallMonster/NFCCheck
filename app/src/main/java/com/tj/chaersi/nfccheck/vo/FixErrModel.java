package com.tj.chaersi.nfccheck.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chaersi on 17/1/17.
 */
public class FixErrModel {

    /**
     * statecode : 1
     * list : [{"id":"d4aae39c-d5d9-4a7f-a6c5-a342401d4158","zuobiao":"","overTime":"0","sendCellphone":"","urls":["/static/upload/2017/01/20170104104443_836.jpg","/static/upload/2017/01/20170105093631_694.jpg"],"serviceTime":"20170911","sendUserName":"电话","name":"温泉小区2001户","info":"温泉小区2001户燃气表异常"},{"id":"d4aae39c-d5d9-4a7f-a6c5-a342401d4159","zuobiao":"","overTime":"1","sendCellphone":"","urls":[],"serviceTime":"20170107","sendUserName":"李先生 ","name":"小区问题","info":"小区燃气表损坏111"},{"id":"d4aae39c-d5d9-4a7f-a6c5-a342401d4160","zuobiao":"","overTime":"0","sendCellphone":"","urls":[],"serviceTime":"20170801","sendUserName":"李先生","name":"小区问题","info":"温泉小区2001户燃气表异常1"}]
     */

    private String statecode;
    /**
     * id : d4aae39c-d5d9-4a7f-a6c5-a342401d4158
     * zuobiao :
     * overTime : 0
     * sendCellphone :
     * urls : ["/static/upload/2017/01/20170104104443_836.jpg","/static/upload/2017/01/20170105093631_694.jpg"]
     * serviceTime : 20170911
     * sendUserName : 电话
     * name : 温泉小区2001户
     * info : 温泉小区2001户燃气表异常
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

    public static class ListBean implements Serializable {
        private String id;
        private String zuobiao;
        private String overTime;
        private String sendCellphone;
        private String serviceTime;
        private String sendUserName;
        private String name;
        private String info;
        private List<String> urls;

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

        public List<String> getUrls() {
            return urls;
        }

        public void setUrls(List<String> urls) {
            this.urls = urls;
        }
    }
}
