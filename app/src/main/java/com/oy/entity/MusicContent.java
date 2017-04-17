package com.oy.entity;

/**
 * Created by Lucky on 2016/10/31.
 */
public class MusicContent {
    public String id;
    public String title;
    public String cover;
    public String story_title;
    public String story;
    public String lyric;
    public String info;
    public String music_id;
    public String charge_edt;
    public String maketime;
    public String praisenum;
    public String read_num;
    public String sharenum;
    public String commentnum;

    public String getMaketime() {
        return maketime;
    }

    public void setMaketime(String maketime) {
        this.maketime = maketime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getStory_title() {
        return story_title;
    }

    public void setStory_title(String story_title) {
        this.story_title = story_title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }

    public String getCharge_edt() {
        return charge_edt;
    }

    public void setCharge_edt(String charge_edt) {
        this.charge_edt = charge_edt;
    }

    public String getPraisenum() {
        return praisenum;
    }

    public void setPraisenum(String praisenum) {
        this.praisenum = praisenum;
    }

    public String getRead_num() {
        return read_num;
    }

    public void setRead_num(String read_num) {
        this.read_num = read_num;
    }

    public String getSharenum() {
        return sharenum;
    }

    public void setSharenum(String sharenum) {
        this.sharenum = sharenum;
    }

    public String getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(String commentnum) {
        this.commentnum = commentnum;
    }

    private AuthorBean author;
    /**
     * user_id : 7431838
     * user_name : 汤川
     * web_url : http://image.wufazhuce.com/Fg4aCPJKHW8hMpXSPiT1qneXYc4N
     */

    private StoryAuthorBean story_author;

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public StoryAuthorBean getStory_author() {
        return story_author;
    }

    public void setStory_author(StoryAuthorBean story_author) {
        this.story_author = story_author;
    }

    public static class AuthorBean {
        private String user_id;
        private String user_name;
        private String web_url;
        private String desc;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static class StoryAuthorBean {
        private String user_id;
        private String user_name;
        private String web_url;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }
    }
}
