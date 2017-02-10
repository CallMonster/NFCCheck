package com.tj.chaersi.nfccheck.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chaersi on 17/2/10.
 */
public class Index_LocalModel {
    /**
     * statecode : 1
     * list : [{"labelnum":"","id":"4f612fa4-3532-4635-bcaf-85f8c87d9f85","zuobiao":"116.657765, 39.838279","name":"东方社区"},{"labelnum":"","id":"4f612fa4-3532-4635-bcaf-85f8c87d9f85","zuobiao":"116.657765, 39.838279","name":"东方社区"}]
     */

    private String statecode;
    /**
     * labelnum :
     * id : 4f612fa4-3532-4635-bcaf-85f8c87d9f85
     * zuobiao : 116.657765, 39.838279
     * name : 东方社区
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
        private String labelnum;
        private String id;
        private String zuobiao;
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

        public String getZuobiao() {
            return zuobiao;
        }

        public void setZuobiao(String zuobiao) {
            this.zuobiao = zuobiao;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
