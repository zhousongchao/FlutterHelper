package com.zsc.flutter.help.mark

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.util.Iconable
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.patterns.PlatformPatterns.virtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.IconUtil
import com.intellij.util.ImageLoader
import com.intellij.util.SVGLoader
import com.sun.javafx.scene.CameraHelper.project
import java.io.File
import javax.swing.Icon
import javax.swing.ImageIcon


/**
 * Svgs.dart显示图标在路径左侧
 */
class SvgLineMarkerProvider : LineMarkerProvider {


    /**
     * Fixme 大图性能，添加缓存
     */
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? {
        if (element.containingFile.name.contains("Images.dart", true)
            && (element as LeafPsiElement?)?.elementType?.toString() == "REGULAR_STRING_PART"
        ) {
            val anchor = PsiTreeUtil.getDeepestFirst(element)
            //获取图片
            /*var icon: Icon = ImageIcon(
                ImageLoader.loadFromUrl(File(element.project.basePath + "/" + element.text).toURL(), true)
            )
            if (icon.iconWidth > 16) {
                icon = IconUtil.scale(icon, 16.0 / icon.iconWidth)
            }*/
            val vFile =
                LocalFileSystem.getInstance().findFileByPath(element.project.basePath + "/" + element.text)
            return LineMarkerInfo(
                anchor, anchor.textRange,
                IconUtil.getIcon(vFile!!, Iconable.ICON_FLAG_VISIBILITY,element.project), null,
                { e, t ->
                    //点击
                    println("navHandler:${element.text}  =>$t")
                    val vFile =
                        LocalFileSystem.getInstance().findFileByPath(element.project.basePath + "/" + element.text)
                    //新窗口打开的
//                    LightEdit.openFile(vFile!!)
                    FileEditorManager.getInstance(element.project)
                        .openTextEditor(OpenFileDescriptor(element.project, vFile!!), true)

                }, GutterIconRenderer.Alignment.LEFT
            )
        } else if (element.containingFile.name.contains("Svgs.dart", true)
            && (element as LeafPsiElement?)?.elementType?.toString() == "REGULAR_STRING_PART"
        ) {
            val anchor = PsiTreeUtil.getDeepestFirst(element)
            var icon: Icon = ImageIcon(
                SVGLoader.load(
                    File(element.project.basePath + "/" + element.text).toURL()
                    , 1.0F
                )
            )
            if (icon.iconWidth > 16) {
                icon = IconUtil.scale(icon, 16.0 / icon.iconWidth)
            }
            return LineMarkerInfo(
                anchor, anchor.textRange,
                icon, {
                    //悬停，会多次调用
                    println("tooltipProvider:${element.text}")
                    return@LineMarkerInfo ""
                }, { e, t ->
                    //点击
                    println("navHandler:${element.text}  =>$t")
                    val vFile =
                        LocalFileSystem.getInstance().findFileByPath(element.project.basePath + "/" + element.text)
                    //新窗口打开的
//                    LightEdit.openFile(vFile!!)
                    FileEditorManager.getInstance(element.project)
                        .openTextEditor(OpenFileDescriptor(element.project, vFile!!), true)

                }, GutterIconRenderer.Alignment.LEFT
            )
        }
        return null
    }
}