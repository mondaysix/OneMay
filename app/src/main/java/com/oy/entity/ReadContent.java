package com.oy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lucky on 2016/10/31.
 */
public class ReadContent implements Serializable{
        private List<EssayContent> essay;
        private List<Serial> serial;
        private List<Question> question;

    public List<EssayContent> getEssay() {
        return essay;
    }

    public void setEssay(List<EssayContent> essay) {
        this.essay = essay;
    }

    public List<Serial> getSerial() {
        return serial;
    }

    public void setSerial(List<Serial> serial) {
        this.serial = serial;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    public static class EssayContent implements Serializable {
            /**
             * contentId : 2151
             * contentType : essay
             * contentTitle : 软糖| “雨” _ rain
             * guideWord : 我们每周会选择一个主题，由七个作者绘制不同风格的短篇漫画，每天一幅。
             * authorName : 那狗达
             */

            private int contentId;
            private String contentType;
            private String contentTitle;
            private String guideWord;
            private String authorName;

            public int getContentId() {
                return contentId;
            }

            public void setContentId(int contentId) {
                this.contentId = contentId;
            }

            public String getContentType() {
                return contentType;
            }

            public void setContentType(String contentType) {
                this.contentType = contentType;
            }

            public String getContentTitle() {
                return contentTitle;
            }

            public void setContentTitle(String contentTitle) {
                this.contentTitle = contentTitle;
            }

            public String getGuideWord() {
                return guideWord;
            }

            public void setGuideWord(String guideWord) {
                this.guideWord = guideWord;
            }

            public String getAuthorName() {
                return authorName;
            }

            public void setAuthorName(String authorName) {
                this.authorName = authorName;
            }
        }

        public static class Serial implements Serializable {
            /**
             * contentId : 276
             * contentType : serial
             * contentTitle : 暖气 · 第十八章
             * guideWord : 他喜欢看一些犯罪电影，那里面唯一教给他的只有一点：冷静。
             * authorName : 慢三
             */

            private int contentId;
            private String contentType;
            private String contentTitle;
            private String guideWord;
            private String authorName;

            public int getContentId() {
                return contentId;
            }

            public void setContentId(int contentId) {
                this.contentId = contentId;
            }

            public String getContentType() {
                return contentType;
            }

            public void setContentType(String contentType) {
                this.contentType = contentType;
            }

            public String getContentTitle() {
                return contentTitle;
            }

            public void setContentTitle(String contentTitle) {
                this.contentTitle = contentTitle;
            }

            public String getGuideWord() {
                return guideWord;
            }

            public void setGuideWord(String guideWord) {
                this.guideWord = guideWord;
            }

            public String getAuthorName() {
                return authorName;
            }

            public void setAuthorName(String authorName) {
                this.authorName = authorName;
            }
        }

        public static class Question implements Serializable {
            /**
             * contentId : 1674
             * contentType : question
             * contentTitle : 为什么有人会想成全自己爱的人去爱别人？
             * guideWord : 所谓成全不爱我的人，其实只是在一段不属于自己的感情里找存在感。
             * authorName :
             */

            private int contentId;
            private String contentType;
            private String contentTitle;
            private String guideWord;
            private String authorName;

            public int getContentId() {
                return contentId;
            }

            public void setContentId(int contentId) {
                this.contentId = contentId;
            }

            public String getContentType() {
                return contentType;
            }

            public void setContentType(String contentType) {
                this.contentType = contentType;
            }

            public String getContentTitle() {
                return contentTitle;
            }

            public void setContentTitle(String contentTitle) {
                this.contentTitle = contentTitle;
            }

            public String getGuideWord() {
                return guideWord;
            }

            public void setGuideWord(String guideWord) {
                this.guideWord = guideWord;
            }

            public String getAuthorName() {
                return authorName;
            }

            public void setAuthorName(String authorName) {
                this.authorName = authorName;
            }
        }
}
