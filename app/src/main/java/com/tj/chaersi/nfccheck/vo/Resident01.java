package com.tj.chaersi.nfccheck.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chaersi on 17/2/9.
 */
public class Resident01 {
    /**
     * statecode : 1
     * list : [{"id":"f5b737b2-ef22-4ead-b560-5ef56405fd06","createDate":1481197571000,"modifyDate":1486114407000,"companyID":null,"name":"管网1号井","distingguishType":"1","isLock":true,"latitudeAndLongitude":"116.647365, 39.828279","labelNum":"0000001","num":"gw00001","terriborialId":"f351e85e-a7b3-400d-950b-2649fa8f4d1d","version":null,"xqId":null,"type":"gw","listOrder":null,"state":"未巡检","plantime":"20161219","username":"王五","checktime":null},{"id":"7bce468b-044c-4820-8de2-92c87223dd34","createDate":1481197606000,"modifyDate":1486114382000,"companyID":null,"name":"管网2号井","distingguishType":"2","isLock":true,"latitudeAndLongitude":"116.645365, 39.830279","labelNum":"000002","num":"gw00002","terriborialId":"f351e85e-a7b3-400d-950b-2649fa8f4d1d","version":null,"xqId":null,"type":"gw","listOrder":null,"state":null,"plantime":"20170130","username":null,"checktime":null}]
     */

    private String statecode;
    /**
     * id : f5b737b2-ef22-4ead-b560-5ef56405fd06
     * createDate : 1481197571000
     * modifyDate : 1486114407000
     * companyID : null
     * name : 管网1号井
     * distingguishType : 1
     * isLock : true
     * latitudeAndLongitude : 116.647365, 39.828279
     * labelNum : 0000001
     * num : gw00001
     * terriborialId : f351e85e-a7b3-400d-950b-2649fa8f4d1d
     * version : null
     * xqId : null
     * type : gw
     * listOrder : null
     * state : 未巡检
     * plantime : 20161219
     * username : 王五
     * checktime : null
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
        private long createDate;
        private long modifyDate;
        private String companyID;
        private String name;
        private String distingguishType;
        private boolean isLock;
        private String latitudeAndLongitude;
        private String labelNum;
        private String num;
        private String terriborialId;
        private String version;
        private String xqId;
        private String type;
        private String listOrder;
        private String state;
        private String plantime;
        private String username;
        private String checktime;

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

        public String getCompanyID() {
            return companyID;
        }

        public void setCompanyID(String companyID) {
            this.companyID = companyID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDistingguishType() {
            return distingguishType;
        }

        public void setDistingguishType(String distingguishType) {
            this.distingguishType = distingguishType;
        }

        public boolean isIsLock() {
            return isLock;
        }

        public void setIsLock(boolean isLock) {
            this.isLock = isLock;
        }

        public String getLatitudeAndLongitude() {
            return latitudeAndLongitude;
        }

        public void setLatitudeAndLongitude(String latitudeAndLongitude) {
            this.latitudeAndLongitude = latitudeAndLongitude;
        }

        public String getLabelNum() {
            return labelNum;
        }

        public void setLabelNum(String labelNum) {
            this.labelNum = labelNum;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTerriborialId() {
            return terriborialId;
        }

        public void setTerriborialId(String terriborialId) {
            this.terriborialId = terriborialId;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getXqId() {
            return xqId;
        }

        public void setXqId(String xqId) {
            this.xqId = xqId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getListOrder() {
            return listOrder;
        }

        public void setListOrder(String listOrder) {
            this.listOrder = listOrder;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPlantime() {
            return plantime;
        }

        public void setPlantime(String plantime) {
            this.plantime = plantime;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getChecktime() {
            return checktime;
        }

        public void setChecktime(String checktime) {
            this.checktime = checktime;
        }
    }
}
