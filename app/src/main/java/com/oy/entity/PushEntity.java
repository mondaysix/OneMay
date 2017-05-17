package com.oy.entity;

/**
 * Created by Meyki on 2017/5/15.
 */

public class PushEntity {
            /**
         * id : 1
         * img : ximalaya.jpg
         * title : 日本的科技到底比中国高出多少？
         * contentInfo : 只有冷静，才能说出一个真实的日本
         */

        private String id;
        private String img;
        private String title;
        private String contentInfo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContentInfo() {
            return contentInfo;
        }

        public void setContentInfo(String contentInfo) {
            this.contentInfo = contentInfo;
        }

    public PushEntity() {
    }

    public PushEntity(String id, String img, String title, String contentInfo) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.contentInfo = contentInfo;
    }
}
