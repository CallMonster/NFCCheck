package com.tj.chaersi.nfccheck.vo;

import java.util.List;

/**
 * Created by Chaersi on 17/1/17.
 */
public class FixErrModel {
    /**
     * statecode : 1
     * list : [{"id":"6cedab5a-ca4b-416a-92c4-be7c0d608589","createDate":1481269379000,"modifyDate":1481269379000,"name":"123","Type":"小区","state":"待维修","info":"在**小区住户李先生的燃气表发生故障，需要人员及时维修","remark":"","sendUserId":"","sendUserName":"李先生 ","servicemanId":"e20cf510-6910-44d4-a650-538ff0eab7a1","servicemanName":"王五","sendCellphone":"","overTime":"","zuobiao":"","sendTime":1484642192651,"senduserTime":1484642192651,"serviceTime":1484642192651,"khTime":1484642192651,"qrmanId":"","qrmanName":"","type":"小区"}]
     */

    private String statecode;
    /**
     * id : 6cedab5a-ca4b-416a-92c4-be7c0d608589
     * createDate : 1481269379000
     * modifyDate : 1481269379000
     * name : 123
     * Type : 小区
     * state : 待维修
     * info : 在**小区住户李先生的燃气表发生故障，需要人员及时维修
     * remark :
     * sendUserId :
     * sendUserName : 李先生
     * servicemanId : e20cf510-6910-44d4-a650-538ff0eab7a1
     * servicemanName : 王五
     * sendCellphone :
     * overTime :
     * zuobiao :
     * sendTime : 1484642192651
     * senduserTime : 1484642192651
     * serviceTime : 1484642192651
     * khTime : 1484642192651
     * qrmanId :
     * qrmanName :
     * type : 小区
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
        private long createDate;
        private long modifyDate;
        private String name;
        private String Type;
        private String state;
        private String info;
        private String remark;
        private String sendUserId;
        private String sendUserName;
        private String servicemanId;
        private String servicemanName;
        private String sendCellphone;
        private String overTime;
        private String zuobiao;
        private long sendTime;
        private long senduserTime;
        private long serviceTime;
        private long khTime;
        private String qrmanId;
        private String qrmanName;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public long getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(long modifyDate) {
            this.modifyDate = modifyDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSendUserId() {
            return sendUserId;
        }

        public void setSendUserId(String sendUserId) {
            this.sendUserId = sendUserId;
        }

        public String getSendUserName() {
            return sendUserName;
        }

        public void setSendUserName(String sendUserName) {
            this.sendUserName = sendUserName;
        }

        public String getServicemanId() {
            return servicemanId;
        }

        public void setServicemanId(String servicemanId) {
            this.servicemanId = servicemanId;
        }

        public String getServicemanName() {
            return servicemanName;
        }

        public void setServicemanName(String servicemanName) {
            this.servicemanName = servicemanName;
        }

        public String getSendCellphone() {
            return sendCellphone;
        }

        public void setSendCellphone(String sendCellphone) {
            this.sendCellphone = sendCellphone;
        }

        public String getOverTime() {
            return overTime;
        }

        public void setOverTime(String overTime) {
            this.overTime = overTime;
        }

        public String getZuobiao() {
            return zuobiao;
        }

        public void setZuobiao(String zuobiao) {
            this.zuobiao = zuobiao;
        }

        public long getSendTime() {
            return sendTime;
        }

        public void setSendTime(long sendTime) {
            this.sendTime = sendTime;
        }

        public long getSenduserTime() {
            return senduserTime;
        }

        public void setSenduserTime(long senduserTime) {
            this.senduserTime = senduserTime;
        }

        public long getServiceTime() {
            return serviceTime;
        }

        public void setServiceTime(long serviceTime) {
            this.serviceTime = serviceTime;
        }

        public long getKhTime() {
            return khTime;
        }

        public void setKhTime(long khTime) {
            this.khTime = khTime;
        }

        public String getQrmanId() {
            return qrmanId;
        }

        public void setQrmanId(String qrmanId) {
            this.qrmanId = qrmanId;
        }

        public String getQrmanName() {
            return qrmanName;
        }

        public void setQrmanName(String qrmanName) {
            this.qrmanName = qrmanName;
        }
    }
}
