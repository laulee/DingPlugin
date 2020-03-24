package com.laulee.plugin;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.AppPlugin;
import com.android.build.gradle.api.ApplicationVariant;
import com.laulee.plugin.task.FileUploadTask;
import com.laulee.plugin.task.SendDingMessageTask;

import org.gradle.api.Action;
import org.gradle.api.DomainObjectSet;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/***
 * 插件
 */
public class DingPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        System.out.println("DingPlugin start");

        if (!project.getPlugins().hasPlugin(AppPlugin.class)) {
            throw new GradleException("无法在非Android Applicaiton插件中使用插件");
        }
        project.getExtensions().create("dingMessager", DingExtension.class);

        //afterEvalutate表示在解析完成之后再执行我们的代码
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                DomainObjectSet<ApplicationVariant> appVariants = ((AppExtension)
                        project.getExtensions().findByName("android")).getApplicationVariants();
                for (ApplicationVariant appVariant : appVariants) {
                    System.out.println("appVariant buildType " + appVariant.getBuildType().getName());
                    if (appVariant.getBuildType().getName().equalsIgnoreCase("release")) {
                        String appVariantName = appVariant.getName();
                        //创建上传任务
                        FileUploadTask fileUploadTask = project.getTasks().create("fileApkUpload", FileUploadTask.class);
                        fileUploadTask.init(appVariant, project);
                        System.out.println("fileUploadTask create success");

                        //创建发送钉钉消息任务
                        SendDingMessageTask sendDingMessageTask = project.getTasks().create("sendDingMessage", SendDingMessageTask.class);
                        sendDingMessageTask.init(appVariant, project);
                        System.out.println("sendDingMessageTask create success ");

                        appVariant.getAssembleProvider().get().dependsOn(project.getTasks().findByName("clean"));
                        fileUploadTask.dependsOn(appVariant.getAssembleProvider().get());
                        sendDingMessageTask.dependsOn(fileUploadTask);
                    }
                }
            }
        });
    }

    /**
     * 首字母大写
     *
     * @param s
     * @return
     */
    public static String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
