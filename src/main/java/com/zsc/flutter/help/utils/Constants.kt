package com.zsc.flutter.help.utils

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

object Constants {

    const val SRC_PATH = "generated"

    fun isTargetImage(project: Project?, file: VirtualFile?): Boolean {
        project?:return false
        file?:return false
        return file.parent.path.contains("${project.basePath}/assets/images",ignoreCase = true).apply {
            println("\n\nfileCheck:$this \n${file.parent.path}\n${project.basePath}/assets/images\n\n")
        }
    }

    fun isTargetSvg(project: Project?, file: VirtualFile?): Boolean {
        project?:return false
        file?:return false
        return file.parent.path.contains("${project.basePath}/assets/svgs",ignoreCase = true).apply {
            println("\n\nfileCheck:$this \n${file.parent.path}\n${project.basePath}/assets/images\n\n")
        }
    }

    fun isTargetLangJson(project: Project?, file: VirtualFile?): Boolean {
        project?:return false
        file?:return false
        return file.parent.path.equals("${project.basePath}/resources/langs/zh-CH.json",ignoreCase = true)
    }
}