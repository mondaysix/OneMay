package com.oy.entity;

import java.io.Serializable;

/**
 * Created by Lucky on 2016/10/30.
 */
public class ReadHead implements Serializable {
        /**
         * id : 123
         * title : 你再不来，我就要下雪了
         * cover : Ftgg6J7j3qldjC_qDirU4x-OYFLp.jpg
         * textDes : 拥抱着就能取暖，依偎着便能生存。
         * bgcolor : #f7bfbb
         */

        private int id;
        private String title;
        private String cover;
        private String textDes;
        private String bgcolor;

        public int getId() {
            return id;
        }

        public void setId(int id) {
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
        public String getTextDes() {
            return textDes;
        }
        public String getBgcolor() {
            return bgcolor;
        }
}
