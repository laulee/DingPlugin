package com.laulee.dingplugin.task;

import java.util.List;

/**
 * Created by laulee on 2020-03-25.
 *
 * {
 *      "msgtype": "markdown",
 *      "markdown": {
 *          "title":"杭州天气",
 *          "text": "#### 杭州天气 @156xxxx8827\n" +
 *                  "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
 *                  "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n"  +
 *                  "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n"
 *      },
 *     "at": {
 *         "atMobiles": [
 *             "156xxxx8827",
 *             "189xxxx8325"
 *         ],
 *         "isAtAll": false
 *     }
 *  }
 */
public class DingRequestModel {
    private String msgtype = "markdown";
    private DingTalkModel markdown;
    private DingAtModel at;

    public DingAtModel getAt() {
        return at;
    }

    public void setAt(DingAtModel at) {
        this.at = at;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public DingTalkModel getMarkdown() {
        return markdown;
    }

    public void setMarkdown(DingTalkModel markdown) {
        this.markdown = markdown;
    }

    /**
     * 消息体
     */
    public static class DingTalkModel {

        private String title;//消息标题

        private String text;//消息内容。如果太长只会部分展示

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    /**
     * at用户
     */
    public static class DingAtModel {

        /**
         * "atMobiles": [
         * "156xxxx8827",
         * "189xxxx8325"
         * ],
         * "isAtAll": false
         */

        private List<String> atMobiles;
        private boolean isAtAll;

        public List<String> getAtMobiles() {
            return atMobiles;
        }

        public void setAtMobiles(List<String> atMobiles) {
            this.atMobiles = atMobiles;
        }

        public boolean isAtAll() {
            return isAtAll;
        }

        public void setAtAll(boolean atAll) {
            isAtAll = atAll;
        }
    }
}
