package com.laulee.plugin.task;

import com.android.build.gradle.AppExtension;
import com.android.build.gradle.api.ApplicationVariant;
import com.android.build.gradle.api.BaseVariantOutput;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

import java.io.File;

/**
 * 上传apk任务
 */
public class FileUploadTask extends DefaultTask {

    private Project project;

    /**
     * 初始化
     *
     * @param appVariant
     * @param project
     */
    public void init(ApplicationVariant appVariant, Project project) {
        this.project = project;
    }

    @TaskAction
    public void uploadApk() {
        System.out.println("上传apk");
        AppExtension appExtension = (AppExtension) project.getExtensions().findByName("android");


        for (BaseVariantOutput buildOutput : appExtension.getBuildOutputs()) {
            File file = buildOutput.getOutputFile();
            if (file != null && file.exists()) {
                System.out.println("output apk name " + file.getName());
                System.out.println("output apk path " + file.getAbsolutePath());
            }
        }
    }
}
