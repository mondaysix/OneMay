package com.oy.entity;

import java.io.Serializable;

/**
 * Created by Lucky on 2016/11/1.
 */
public class MovieList implements Serializable{
        /**
         * id : 769
         * title : 权力的游戏 第七季
         * verse :
         * verse_en :
         * score : null
         * revisedscore : 0
         * releasetime : 2017-07-16 00:00:00
         * scoretime : 0000-00-00 00:00:00
         * start_video :
         * cover :
         * author_list : []
         * servertime : 1494580990
         */

        private String id;
        private String title;

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
}
