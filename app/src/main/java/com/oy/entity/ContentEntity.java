package com.oy.entity;

/**
 * Created by Lucky on 2016/10/30.
 */
public class ContentEntity {
        /**
         * hpcontent_id : 1701
         * hp_title : VOL.1701
         * author_id : -1
         * hp_img_url : e5e95e0d7d6bdadc.jpg
         * hp_author : 绘画&Vallotton 作品
         * hp_content : 要记得，生命极短。短到，用来做自己喜欢的事情，都不够；更不要说用来犹豫，以及妥协——尤其是，妥协给那些——你自己都清楚“这只是妥协”的东西。 by 七堇年
         * hp_maketime : 2017-03-17T22:00:00.000Z
         * last_update_date : 2017-03-09T06:49:23.000Z
         * praisenum : 19355
         * sharenum : 8366
         * commentnum : 0
         */

        private int hpcontent_id;
        private String hp_title;
        private String author_id;
        private String hp_img_url;
        private String hp_author;
        private String hp_content;
        private String hp_maketime;
        private String last_update_date;
        private int praisenum;
        private int sharenum;
        private int commentnum;

    public ContentEntity(int hpcontent_id, String hp_title, String author_id, String hp_img_url, String hp_author, String hp_content, String hp_maketime, String last_update_date, int praisenum, int sharenum, int commentnum) {
        this.hpcontent_id = hpcontent_id;
        this.hp_title = hp_title;
        this.author_id = author_id;
        this.hp_img_url = hp_img_url;
        this.hp_author = hp_author;
        this.hp_content = hp_content;
        this.hp_maketime = hp_maketime;
        this.last_update_date = last_update_date;
        this.praisenum = praisenum;
        this.sharenum = sharenum;
        this.commentnum = commentnum;
    }

        public int getHpcontent_id() {
            return hpcontent_id;
        }
        public String getHp_title() {
            return hp_title;
        }
        public String getAuthor_id() {
            return author_id;
        }
        public String getHp_img_url() {
            return hp_img_url;
        }
        public String getHp_author() {
            return hp_author;
        }
        public String getHp_content() {
            return hp_content;
        }
        public String getHp_maketime() {
            return hp_maketime;
        }
        public String getLast_update_date() {
            return last_update_date;
        }
        public int getPraisenum() {
            return praisenum;
        }
        public int getSharenum() {
            return sharenum;
        }

        public void setSharenum(int sharenum) {
            this.sharenum = sharenum;
        }

        public int getCommentnum() {
            return commentnum;
        }
}
