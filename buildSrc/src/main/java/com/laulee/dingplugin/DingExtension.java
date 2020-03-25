package com.laulee.dingplugin;

/**
 * 插件配置文件
 */
public class DingExtension {

    String httpUrl;//服务器地址
    String token;//用户token
    String content;//更新文案
    String dingToken;//dingding token
    String atReceiver;//接收者 逗号隔开

    public DingExtension() {

    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDingToken() {
        return dingToken;
    }

    public void setDingToken(String dingToken) {
        this.dingToken = dingToken;
    }

    public String getAtReceiver() {
        return atReceiver;
    }

    public void setAtReceiver(String atReceiver) {
        this.atReceiver = atReceiver;
    }

    public DingExtension(String httpUrl, String token, String content, String dingToken, String atReceiver) {
        this.httpUrl = httpUrl;
        this.token = token;
        this.content = content;
        this.dingToken = dingToken;
        this.atReceiver = atReceiver;
    }
}
