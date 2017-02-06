package com.tj.chaersi.nfccheck.vo;

import java.util.List;

/**
 * Created by Chaersi on 17/1/20.
 */
public class CheckPointDetailModel {
    /**
     * routeId : 111
     * pointname : 1号井阀
     * planTime : 20170127
     * userId : 323311323
     * userName : 王五
     * sendInspectionDatas : [{"name":"温度","info":"北区一号阀的填入值","id":"3778899-33","isAbnormal":"1","type":"1","sendimages":[{"url":"/static/upload/2017/01/201701234731_598.png","name":"bbb","fileExt":"png"},{"url":"/static/upload/2017/01/20170123456731_567.png","name":"aaa","fileExt":"png"},{"url":"/static/upload/2017/01/20192819093731_567.png","name":"ccc","fileExt":"png"}]}]
     */

    private AlldataBean alldata;

    public AlldataBean getAlldata() {
        return alldata;
    }

    public void setAlldata(AlldataBean alldata) {
        this.alldata = alldata;
    }

    public static class AlldataBean {
        private String routeId;
        private String pointname;
        private String planTime;
        private String userId;
        private String userName;
        private String pointid;
        /**
         * name : 温度
         * info : 北区一号阀的填入值
         * id : 3778899-33
         * isAbnormal : 1
         * type : 1
         * sendimages : [{"url":"/static/upload/2017/01/201701234731_598.png","name":"bbb","fileExt":"png"},{"url":"/static/upload/2017/01/20170123456731_567.png","name":"aaa","fileExt":"png"},{"url":"/static/upload/2017/01/20192819093731_567.png","name":"ccc","fileExt":"png"}]
         */

        private List<SendInspectionDatasBean> sendInspectionDatas;

        public String getRouteId() {
            return routeId;
        }

        public void setRouteId(String routeId) {
            this.routeId = routeId;
        }

        public String getPointname() {
            return pointname;
        }

        public void setPointname(String pointname) {
            this.pointname = pointname;
        }

        public String getPlanTime() {
            return planTime;
        }

        public void setPlanTime(String planTime) {
            this.planTime = planTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPointid() {
            return pointid;
        }

        public void setPointid(String pointid) {
            this.pointid = pointid;
        }

        public List<SendInspectionDatasBean> getSendInspectionDatas() {
            return sendInspectionDatas;
        }

        public void setSendInspectionDatas(List<SendInspectionDatasBean> sendInspectionDatas) {
            this.sendInspectionDatas = sendInspectionDatas;
        }

        public static class SendInspectionDatasBean {
            private String name;
            private String info;
            private String id;
            private String isAbnormal;
            private String type;
            /**
             * url : /static/upload/2017/01/201701234731_598.png
             * name : bbb
             * fileExt : png
             */

            private List<SendimagesBean> sendimages;

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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIsAbnormal() {
                return isAbnormal;
            }

            public void setIsAbnormal(String isAbnormal) {
                this.isAbnormal = isAbnormal;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<SendimagesBean> getSendimages() {
                return sendimages;
            }

            public void setSendimages(List<SendimagesBean> sendimages) {
                this.sendimages = sendimages;
            }

            public static class SendimagesBean {
                private String url;
                private String name;
                private String fileExt;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getFileExt() {
                    return fileExt;
                }

                public void setFileExt(String fileExt) {
                    this.fileExt = fileExt;
                }
            }
        }
    }
}
