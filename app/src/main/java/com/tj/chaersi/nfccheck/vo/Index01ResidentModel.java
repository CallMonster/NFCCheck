package com.tj.chaersi.nfccheck.vo;

import java.util.List;

/**
 * Created by Chaersi on 17/2/10.
 */
public class Index01ResidentModel {
    /**
     * statecode : 1
     * list : [{"labelnum":"2","id":"d5b5f5ea-b09c-4145-92eb-bfced060fb63","num":"1111","name":"住户1"}]
     */

    private String statecode;
    /**
     * labelnum : 2
     * id : d5b5f5ea-b09c-4145-92eb-bfced060fb63
     * num : 1111
     * name : 住户1
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
        private String labelnum;
        private String id;
        private String num;
        private String name;

        public String getLabelnum() {
            return labelnum;
        }

        public void setLabelnum(String labelnum) {
            this.labelnum = labelnum;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
