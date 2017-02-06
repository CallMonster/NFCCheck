package com.tj.chaersi.nfccheck.vo;

import java.util.List;

/**
 * Created by Chaersi on 17/1/20.
 */
public class ImageUploadModel {
    /**
     * statecode : 1
     * list : [{"name":"grzx03","fileExt":"png","url":"/static/upload/2017/01/20170119191823_304.png"},{"name":"grzx03","fileExt":"png","url":"/static/upload/2017/01/20170119191823_304.png"},{"name":"grzx03","fileExt":"png","url":"/static/upload/2017/01/20170119191823_304.png"},{"name":"grzx03","fileExt":"png","url":"/static/upload/2017/01/20170119191823_304.png"},{"name":"grzx03","fileExt":"png","url":"/static/upload/2017/01/20170119191823_304.png"}]
     */

    private String statecode;
    /**
     * name : grzx03
     * fileExt : png
     * url : /static/upload/2017/01/20170119191823_304.png
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
        private String name;
        private String fileExt;
        private String url;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
