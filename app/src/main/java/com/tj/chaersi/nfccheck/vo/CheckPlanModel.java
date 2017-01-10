package com.tj.chaersi.nfccheck.vo;

import java.util.List;

/**
 * Created by Chaersi on 17/1/10.
 */
public class CheckPlanModel {
    /**
     * statecode : 1
     * list : [{"id":"d8f5747e-31e5-4018-800d-b76974ba7333","createDate":1481717194000,"modifyDate":1481717194000,"orgId":"","routeId":"378b12a2-bf8a-49a1-98e3-3b9201fd1af0","name":"东城区路线","userName":"李四","userId":"99db0bc2-f1a5-4106-87ad-1015ebe8ef73","cycle":"日","isLock":true,"delay":"","users":"王五,李四","usersid":"e20cf510-6910-44d4-a650-538ff0eab7a1,99db0bc2-f1a5-4106-87ad-1015ebe8ef73","remark":"","type":"gw","checktype":"0","plantime":"2017110","terriborialId":"f351e85e-a7b3-400d-950b-2649fa8f4d1d","version":3,"state":true,"routeInfos":""}]
     */

    private String statecode;
    /**
     * id : d8f5747e-31e5-4018-800d-b76974ba7333
     * createDate : 1481717194000
     * modifyDate : 1481717194000
     * orgId :
     * routeId : 378b12a2-bf8a-49a1-98e3-3b9201fd1af0
     * name : 东城区路线
     * userName : 李四
     * userId : 99db0bc2-f1a5-4106-87ad-1015ebe8ef73
     * cycle : 日
     * isLock : true
     * delay :
     * users : 王五,李四
     * usersid : e20cf510-6910-44d4-a650-538ff0eab7a1,99db0bc2-f1a5-4106-87ad-1015ebe8ef73
     * remark :
     * type : gw
     * checktype : 0
     * plantime : 2017110
     * terriborialId : f351e85e-a7b3-400d-950b-2649fa8f4d1d
     * version : 3
     * state : true
     * routeInfos :
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
        private String orgId;
        private String routeId;
        private String name;
        private String userName;
        private String userId;
        private String cycle;
        private boolean isLock;
        private String delay;
        private String users;
        private String usersid;
        private String remark;
        private String type;
        private String checktype;
        private String plantime;
        private String terriborialId;
        private int version;
        private boolean state;
        private String routeInfos;

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

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getRouteId() {
            return routeId;
        }

        public void setRouteId(String routeId) {
            this.routeId = routeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCycle() {
            return cycle;
        }

        public void setCycle(String cycle) {
            this.cycle = cycle;
        }

        public boolean isIsLock() {
            return isLock;
        }

        public void setIsLock(boolean isLock) {
            this.isLock = isLock;
        }

        public String getDelay() {
            return delay;
        }

        public void setDelay(String delay) {
            this.delay = delay;
        }

        public String getUsers() {
            return users;
        }

        public void setUsers(String users) {
            this.users = users;
        }

        public String getUsersid() {
            return usersid;
        }

        public void setUsersid(String usersid) {
            this.usersid = usersid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getChecktype() {
            return checktype;
        }

        public void setChecktype(String checktype) {
            this.checktype = checktype;
        }

        public String getPlantime() {
            return plantime;
        }

        public void setPlantime(String plantime) {
            this.plantime = plantime;
        }

        public String getTerriborialId() {
            return terriborialId;
        }

        public void setTerriborialId(String terriborialId) {
            this.terriborialId = terriborialId;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public String getRouteInfos() {
            return routeInfos;
        }

        public void setRouteInfos(String routeInfos) {
            this.routeInfos = routeInfos;
        }
    }
}
