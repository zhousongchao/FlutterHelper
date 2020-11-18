package com.zsc.flutter.help.utils

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.psi.PsiTreeChangeListener

object AssertFileGenerate {

    fun doCreateAssertFile(project: Project) {
        WriteCommandAction.runWriteCommandAction(project) {
            val srcFile = LocalFileSystem.getInstance().findFileByPath(project.basePath + "/" + "lib")
                ?: return@runWriteCommandAction
            val assertDir = LocalFileSystem.getInstance().findFileByPath(project.basePath + "/assets/images")
                ?: return@runWriteCommandAction
            val resourceFileDir = srcFile.findChild(Constants.SRC_PATH)
                ?: srcFile.createChildDirectory(null, Constants.SRC_PATH)
            val rDartFile = resourceFileDir.findOrCreateChildData(null, "images.dart")
            val dartFileContent = FileContent.imagesContent(project, assertDir)
            rDartFile.setBinaryContent(dartFileContent.toByteArray())
        }
    }

    fun doCreateSvgFile(project: Project) {
//        PsiTreeChangeListener
        WriteCommandAction.runWriteCommandAction(project) {
            val srcFile = LocalFileSystem.getInstance().findFileByPath(project.basePath + "/" + "lib")
                ?: return@runWriteCommandAction
            val assertDir = LocalFileSystem.getInstance().findFileByPath(project.basePath + "/assets/svgs")
                ?: return@runWriteCommandAction
            val resourceFileDir = srcFile.findChild(Constants.SRC_PATH)
                ?: srcFile.createChildDirectory(null, Constants.SRC_PATH)
            val rDartFile = resourceFileDir.findOrCreateChildData(null, "svgs.dart")
            val dartFileContent = FileContent.svgContent(project, assertDir)
            rDartFile.setBinaryContent(dartFileContent.toByteArray())
        }
    }


    fun doCreateKeysFile(project: Project) {
        /*WriteCommandAction.runWriteCommandAction(project) {
            val srcFile = LocalFileSystem.getInstance().findFileByPath(project.basePath + "/" + "lib")
                ?: return@runWriteCommandAction
            val jsonFile =
                LocalFileSystem.getInstance().findFileByPath(project.basePath + "/resources/langs/zh-CH.json")
                    ?: return@runWriteCommandAction
            val resourceFileDir =
                srcFile.findChild(Constants.SRC_PATH) ?: srcFile.createChildDirectory(null, Constants.SRC_PATH)
            val dartFileContent = FileContent.keysContent(jsonFile) ?: return@runWriteCommandAction
            val rDartFile = resourceFileDir.findOrCreateChildData(null, "locale_keys.g.dart")
            rDartFile.setBinaryContent(dartFileContent.toByteArray())
        }*/
    }
}