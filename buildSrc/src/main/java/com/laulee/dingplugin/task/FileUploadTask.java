package com.laulee.dingplugin.task;

import com.android.build.gradle.api.ApplicationVariant;
import com.android.build.gradle.api.BaseVariantOutput;
import com.laulee.dingplugin.DingExtension;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 上传apk任务
 */
public class FileUploadTask extends DefaultTask {

    private Project project;
    private ApplicationVariant applicationVariant;

    /**
     * 初始化
     *
     * @param appVariant
     * @param project
     */
    public void init(ApplicationVariant appVariant, Project project) {
        this.project = project;
        this.applicationVariant = appVariant;
        setDescription("upload apk");
        setGroup("dingPlugin");
    }

    @TaskAction
    public void uploadApk() {
        System.out.println("============================");
        System.out.println("====上传apk");
        for (BaseVariantOutput output : applicationVariant.getOutputs()) {
            File file = output.getOutputFile();
            if (file != null && file.exists()) {
                String versionName = applicationVariant.getVersionName();
                System.out.println("====output apk versionName " + versionName);
                System.out.println("====output apk versionCode " + applicationVariant.getVersionCode());
                System.out.println("====output apk name " + file.getName());
                System.out.println("====output apk path " + file.getAbsolutePath());

                DingExtension dingExtension = project.getExtensions().getByType(DingExtension.class);

                if (dingExtension != null && dingExtension.getHttpUrl() != null) {
                    //上传文件
                    String result = upload(dingExtension.getHttpUrl(), dingExtension.getToken(), dingExtension.getContent(), versionName, file);
                    System.out.println("====上传完毕");
                }
            }
        }
        System.out.println("============================");
    }

    /**
     * 上传文件
     *
     * @param content
     * @param bashUrl
     * @param token
     * @param versionName
     * @param file
     */
    private String upload(String bashUrl, String token, String content, String versionName, File file) {

        String result = null;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("*/*"), file));

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(bashUrl).post(builder.build()).build();
        try {
            Response execute = okHttpClient.newCall(request).execute();
            result = execute.body().toString();
            System.out.println("====上传apk result=" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
