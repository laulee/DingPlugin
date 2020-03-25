package com.laulee.dingplugin.task;

import com.android.build.gradle.api.ApplicationVariant;
import com.google.gson.Gson;
import com.laulee.dingplugin.DingExtension;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 发送钉钉任务
 */
public class SendDingMessageTask extends DefaultTask {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private Project project;
    private ApplicationVariant appVariant;

    /**
     * 初始化
     *
     * @param appVariant
     * @param project
     */
    public void init(ApplicationVariant appVariant, Project project) {
        this.project = project;
        this.appVariant = appVariant;
        setDescription("send message");
        setGroup("dingPlugin");
    }

    @TaskAction
    public void sendMessage() {
        System.out.println("============================");
        System.out.println("====执行发送钉钉任务");

        DingExtension dingExtension = project.getExtensions().getByType(DingExtension.class);

        if (dingExtension != null) {

            DingRequestModel dingRequest = new DingRequestModel();
            //https://oapi.dingtalk.com/robot/send?access_token=
            DingRequestModel.DingTalkModel dingTalkModel = new DingRequestModel.DingTalkModel();

            StringBuilder text = new StringBuilder();
            text.append("## Android");
            text.append(appVariant.getVersionName());
            text.append("版本\n");
            text.append("### ");
            text.append(getBuildType(appVariant.getBuildType().getName()));
            text.append("\n");
            text.append(dingExtension.getContent());
            text.append("\n");
            text.append("![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)");
            dingTalkModel.setText(text.toString());
            dingTalkModel.setTitle("Android安装包");

            //是否需要at
            if (dingExtension.getAtReceiver() != null) {
                String[] mobiles = dingExtension.getAtReceiver().split(",");
                DingRequestModel.DingAtModel dingAtModel = new DingRequestModel.DingAtModel();
                dingAtModel.setAtMobiles(Arrays.asList(mobiles));
                dingAtModel.setAtAll(true);
                dingRequest.setAt(dingAtModel);
            }

            dingRequest.setMarkdown(dingTalkModel);

            String json = new Gson().toJson(dingRequest);
            System.out.println("====json=" + json);

            RequestBody requestBody = RequestBody.create(JSON
                    , json);
            String url;
            StringBuilder builder = new StringBuilder();
            builder.append("https://oapi.dingtalk.com/robot/send?access_token=");
            builder.append(dingExtension.getDingToken());
            url = builder.toString();

            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .header("Content-Type", "application/json")
                    .build();
            OkHttpClient client = new OkHttpClient.Builder().build();

            try (Response response = client.newCall(request).execute()) {
                String result = response.body().string();
                System.out.println("====执行发送钉钉任务完成result=" + result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("============================");
        }
    }

    /**
     * 版本类型
     *
     * @param name
     * @return
     */
    private String getBuildType(String name) {
        if (name != null) {
            switch (name) {
                case "release":
                    return "线上包";
                case "debug":
                    return "测试包";
                case "preview":
                    return "预发包";
            }
        }
        return name;
    }
}
