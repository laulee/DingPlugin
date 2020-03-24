package com.laulee.plugin.task;

import com.android.build.gradle.api.ApplicationVariant;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;

/**
 * 发送钉钉任务
 */
public class SendDingMessageTask extends DefaultTask {


    /**
     * 初始化
     *
     * @param appVariant
     * @param project
     */
    public void init(ApplicationVariant appVariant, Project project) {

    }

    @TaskAction
    public void sendMessage() {
        System.out.println("执行发送钉钉任务");
    }
}
