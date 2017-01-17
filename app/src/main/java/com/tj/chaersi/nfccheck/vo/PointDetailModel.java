package com.tj.chaersi.nfccheck.vo;

import java.util.List;

/**
 * Created by Chaersi on 17/1/13.
 */
public class PointDetailModel {

    /**
     * statecode : 1
     * list : [{"id":"08654a81-a3bb-444f-b856-9b96ba43722e","createDate":1481197661000,"modifyDate":1481197661000,"inspectionPointId":"f5b737b2-ef22-4ead-b560-5ef56405fd06","name":"1号井是否正常","eliminateType":"3","isLock":true,"upperLimit":"","lowerLimit":"","attributeUnit":"","remark":"","terriborialId":"","version":"","ifsId":"","ifsName":"","dateType":"1"},{"id":"a469e15c-726e-4c74-a90d-fc5e1ae9c111","createDate":1482110611000,"modifyDate":1482110611000,"inspectionPointId":"f5b737b2-ef22-4ead-b560-5ef56405fd06","name":"气泵温度","eliminateType":"1","isLock":true,"upperLimit":"","lowerLimit":"","attributeUnit":"摄氏度","remark":"","terriborialId":"","version":"","ifsId":"","ifsName":"","dateType":"1"},{"id":"128dc4a5-a140-4bd7-9b2e-14d982795872","createDate":1483493545000,"modifyDate":1483493545000,"inspectionPointId":"f5b737b2-ef22-4ead-b560-5ef56405fd06","name":"1号井阀拍照","eliminateType":"2","isLock":true,"upperLimit":"","lowerLimit":"","attributeUnit":"","remark":"","terriborialId":"","version":"","ifsId":"","ifsName":"","dateType":""}]
     */

    private String statecode;
    /**
     * id : 08654a81-a3bb-444f-b856-9b96ba43722e
     * createDate : 1481197661000
     * modifyDate : 1481197661000
     * inspectionPointId : f5b737b2-ef22-4ead-b560-5ef56405fd06
     * name : 1号井是否正常
     * eliminateType : 3
     * isLock : true
     * upperLimit :
     * lowerLimit :
     * attributeUnit :
     * remark :
     * terriborialId :
     * version :
     * ifsId :
     * ifsName :
     * dateType : 1
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
        private String inspectionPointId;
        private String name;
        private String eliminateType;
        private boolean isLock;
        private String upperLimit;
        private String lowerLimit;
        private String attributeUnit;
        private String remark;
        private String terriborialId;
        private String version;
        private String ifsId;
        private String ifsName;
        private String dateType;

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

        public String getInspectionPointId() {
            return inspectionPointId;
        }

        public void setInspectionPointId(String inspectionPointId) {
            this.inspectionPointId = inspectionPointId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEliminateType() {
            return eliminateType;
        }

        public void setEliminateType(String eliminateType) {
            this.eliminateType = eliminateType;
        }

        public boolean isIsLock() {
            return isLock;
        }

        public void setIsLock(boolean isLock) {
            this.isLock = isLock;
        }

        public String getUpperLimit() {
            return upperLimit;
        }

        public void setUpperLimit(String upperLimit) {
            this.upperLimit = upperLimit;
        }

        public String getLowerLimit() {
            return lowerLimit;
        }

        public void setLowerLimit(String lowerLimit) {
            this.lowerLimit = lowerLimit;
        }

        public String getAttributeUnit() {
            return attributeUnit;
        }

        public void setAttributeUnit(String attributeUnit) {
            this.attributeUnit = attributeUnit;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public String getIfsId() {
            return ifsId;
        }

        public void setIfsId(String ifsId) {
            this.ifsId = ifsId;
        }

        public String getIfsName() {
            return ifsName;
        }

        public void setIfsName(String ifsName) {
            this.ifsName = ifsName;
        }

        public String getDateType() {
            return dateType;
        }

        public void setDateType(String dateType) {
            this.dateType = dateType;
        }
    }
}
