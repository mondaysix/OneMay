package com.oy.entity;

import android.net.http.SslError;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lucky on 2016/10/31.
 */
public class ReadContent implements Serializable{
    List<EssayContent> essayContents;
    List<Serial> serials;
    List<Question> questions;

    public List<EssayContent> getEssayContents() {
        return essayContents;
    }

    public void setEssayContents(List<EssayContent> essayContents) {
        this.essayContents = essayContents;
    }

    public List<Serial> getSerials() {
        return serials;
    }

    public void setSerials(List<Serial> serials) {
        this.serials = serials;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public static class EssayContent implements Serializable{
        private String content_id;
        private String hp_title;
        private String hp_makettime;
        private String guide_word;
        private boolean has_audio;


        private List<AuthorBean> author;

        public String getContent_id() {
            return content_id;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }

        public String getHp_title() {
            return hp_title;
        }

        public void setHp_title(String hp_title) {
            this.hp_title = hp_title;
        }

        public String getHp_makettime() {
            return hp_makettime;
        }

        public void setHp_makettime(String hp_makettime) {
            this.hp_makettime = hp_makettime;
        }

        public String getGuide_word() {
            return guide_word;
        }

        public void setGuide_word(String guide_word) {
            this.guide_word = guide_word;
        }

        public boolean isHas_audio() {
            return has_audio;
        }

        public void setHas_audio(boolean has_audio) {
            this.has_audio = has_audio;
        }

        public List<AuthorBean> getAuthor() {
            return author;
        }

        public void setAuthor(List<AuthorBean> author) {
            this.author = author;
        }

        public static class AuthorBean implements Serializable{
            private String user_id;
            private String user_name;
            private String web_url;
            private String desc;
            private String wb_name;

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

            public String getWb_name() {
                return wb_name;
            }

            public void setWb_name(String wb_name) {
                this.wb_name = wb_name;
            }
        }
    }
    public static class Serial implements Serializable{

        /**
         * id : 195
         * serial_id : 34
         * number : 1
         * title : 昨日重现·第一章：刺客
         * excerpt : 这是记忆时代，可以分享记忆的时代。
         * read_num : 24100
         * maketime : 2016-10-29 21:00:00
         * author : {"user_id":"4814747","user_name":"张寒寺","web_url":"http://image.wufazhuce.com/FlJ2uKL9qGrmdsyWZr_CmOwg_kjt","desc":"小说作者，编剧。新书《不正常人类症候群》现已上市。"}
         * has_audio : false
         */

        private String id;
        private String serial_id;
        private String number;
        private String title;
        private String excerpt;
        private String read_num;
        private String maketime;
        /**
         * user_id : 4814747
         * user_name : 张寒寺
         * web_url : http://image.wufazhuce.com/FlJ2uKL9qGrmdsyWZr_CmOwg_kjt
         * desc : 小说作者，编剧。新书《不正常人类症候群》现已上市。
         */

        private AuthorBean author;
        private boolean has_audio;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSerial_id() {
            return serial_id;
        }

        public void setSerial_id(String serial_id) {
            this.serial_id = serial_id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(String excerpt) {
            this.excerpt = excerpt;
        }

        public String getRead_num() {
            return read_num;
        }

        public void setRead_num(String read_num) {
            this.read_num = read_num;
        }

        public String getMaketime() {
            return maketime;
        }

        public void setMaketime(String maketime) {
            this.maketime = maketime;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public boolean isHas_audio() {
            return has_audio;
        }

        public void setHas_audio(boolean has_audio) {
            this.has_audio = has_audio;
        }

        public static class AuthorBean implements Serializable {
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
    }
    public class Question implements Serializable{

        /**
         * question_id : 1518
         * question_title : 为什么会“害怕跟别人一样，又害怕跟别人不一样”？
         * answer_title : 达达令答croli：
         * answer_content : 不要刻意奢望他人能为你感同身受，否则那就变成了一种廉价的感动。
         * question_makettime : 2016-10-30 21:00:00
         */

        private String question_id;
        private String question_title;
        private String answer_title;
        private String answer_content;
        private String question_makettime;

        public String getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(String question_id) {
            this.question_id = question_id;
        }

        public String getQuestion_title() {
            return question_title;
        }

        public void setQuestion_title(String question_title) {
            this.question_title = question_title;
        }

        public String getAnswer_title() {
            return answer_title;
        }

        public void setAnswer_title(String answer_title) {
            this.answer_title = answer_title;
        }

        public String getAnswer_content() {
            return answer_content;
        }

        public void setAnswer_content(String answer_content) {
            this.answer_content = answer_content;
        }

        public String getQuestion_makettime() {
            return question_makettime;
        }

        public void setQuestion_makettime(String question_makettime) {
            this.question_makettime = question_makettime;
        }
    }
}
