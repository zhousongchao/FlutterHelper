/*
package com.zsc.flutter.help

import com.intellij.json.psi.JsonFile
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileEvent
import com.intellij.openapi.vfs.VirtualFileListener
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiManager
import com.zsc.flutter.help.utils.AssertFileGenerate
import com.zsc.flutter.help.utils.Constants

class MyListener : FileEditorManagerListener {


    private val listener = object : VirtualFileListener {
        var project: Project? = null

        override fun contentsChanged(event: VirtualFileEvent) {
            project?:return
            val children=(PsiManager.getInstance(project!!)
                .findFile(event.file) as? JsonFile)?.children?.first()?.children?:return
            if(children.none { it is PsiErrorElement }){
                return
            }
            AssertFileGenerate.doCreateKeysFile(project!!)
        }
    }

    override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
        if (Constants.isTargetLangJson(source.project,file)) {
            println("=============>>> fileOpened: ${file.path}}")
            file.fileSystem.addVirtualFileListener(listener)
            listener.project = listener.project  ?: source.project
        }
    }

    override fun fileClosed(source: FileEditorManager, file: VirtualFile) {
        if (file.extension == "json") {
            println("=============>>> fileClosed: ${file.path}}")
            file.fileSystem.removeVirtualFileListener(listener)
        }
    }

}*/
