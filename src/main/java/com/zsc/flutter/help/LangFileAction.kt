package com.zsc.flutter.help

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.Project
import com.zsc.flutter.help.utils.AssertFileGenerate

class LangFileAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        //获取当前在操作的工程上下文
        val project = e.getData<Project>(PlatformDataKeys.PROJECT) ?: return
        runCatching {
            AssertFileGenerate.doCreateKeysFile(project)
        }
    }

}