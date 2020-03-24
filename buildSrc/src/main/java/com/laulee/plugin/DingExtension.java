package com.laulee.plugin;

/**
 * 插件配置文件
 */
class DingExtension {

    String bashUrl;//服务器地址
    String userName;//用户名
    String password;//密码

    String appName;//app名称
    String content;//更新文案

    String iconFilePath;//图片

    String apiToken;//dingding token
    String msgTitle;//消息名称
    String msgContent;//消息描述

    String buttonName;//按钮名称
    String buttonUrl;//按钮点开地址

    public DingExtension() {
    }

    public DingExtension(String bashUrl, String userName, String password, String appName, String content, String iconFilePath, String apiToken, String msgTitle, String msgContent, String buttonName, String buttonUrl) {
        this.bashUrl = bashUrl;
        this.userName = userName;
        this.password = password;
        this.appName = appName;
        this.content = content;
        this.iconFilePath = iconFilePath;
        this.apiToken = apiToken;
        this.msgTitle = msgTitle;
        this.msgContent = msgContent;
        this.buttonName = buttonName;
        this.buttonUrl = buttonUrl;
    }

    public String getBashUrl() {
        return bashUrl;
    }

    public void setBashUrl(String bashUrl) {
        this.bashUrl = bashUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIconFilePath() {
        return iconFilePath;
    }

    public void setIconFilePath(String iconFilePath) {
        this.iconFilePath = iconFilePath;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonUrl() {
        return buttonUrl;
    }

    public void setButtonUrl(String buttonUrl) {
        this.buttonUrl = buttonUrl;
    }
}
