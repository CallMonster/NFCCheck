package com.tj.chaersi.nfccheck.vo;

/**
 * Created by Chaersi on 17/1/9.
 */
public class LoginModel {


    /**
     * statecode : 1
     * list : {"id":"99db0bc2-f1a5-4106-87ad-1015ebe8ef73","createDate":1481101887000,"modifyDate":1481101887000,"username":"15100111111","realname":"李四","staffnum":"00001","station":"管理员","sortnum":1,"password":"670b14728ad9902aecba32e22fa4f6bd","status":true,"cellphone":"15100111111","roleId":null,"intro":"职工","orgID":"e18302c5-a92f-4c60-a894-21585fdf6567"}
     */

    private String statecode;
    /**
     * id : 99db0bc2-f1a5-4106-87ad-1015ebe8ef73
     * createDate : 1481101887000
     * modifyDate : 1481101887000
     * username : 15100111111
     * realname : 李四
     * staffnum : 00001
     * station : 管理员
     * sortnum : 1
     * password : 670b14728ad9902aecba32e22fa4f6bd
     * status : true
     * cellphone : 15100111111
     * roleId : null
     * intro : 职工
     * orgID : e18302c5-a92f-4c60-a894-21585fdf6567
     */

    private ListBean list;

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public static class ListBean {
        private String id;
        private long createDate;
        private long modifyDate;
        private String username;
        private String realname;
        private String staffnum;
        private String station;
        private int sortnum;
        private String password;
        private boolean status;
        private String cellphone;
        private Integer roleId;
        private String intro;
        private String orgID;

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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getStaffnum() {
            return staffnum;
        }

        public void setStaffnum(String staffnum) {
            this.staffnum = staffnum;
        }

        public String getStation() {
            return station;
        }

        public void setStation(String station) {
            this.station = station;
        }

        public int getSortnum() {
            return sortnum;
        }

        public void setSortnum(int sortnum) {
            this.sortnum = sortnum;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getCellphone() {
            return cellphone;
        }

        public void setCellphone(String cellphone) {
            this.cellphone = cellphone;
        }

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getOrgID() {
            return orgID;
        }

        public void setOrgID(String orgID) {
            this.orgID = orgID;
        }
    }
}
