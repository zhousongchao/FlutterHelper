package com.zsc.flutter.help.utils

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

object FileContent {

    private const val TAB = "\t"

    private fun VirtualFile.isPicture(): Boolean {
        extension ?: return false
        return !isDirectory && name.isNotBlank()
                && ((extension.equals("png", true)
                || extension.equals("jpg", true)
                || extension.equals("jepg", true)))
    }

    private fun List<VirtualFile>.toConstStrings(project: Project): String {
        return joinToString(separator = "") {
            val path = it.path.replace(project.basePath + "/", "")
            val variableName = it.nameWithoutExtension.lineToHump()
            "\n\tstatic const String $variableName = '$path';"
        }
    }

    fun imagesContent(project: Project, assertDir: VirtualFile): String {
        val content = assertDir.children.filter {
            it.isPicture()
        }.toConstStrings(project)
        return """
// 此文件自动生成，用来简化asset的图片的调用
class Images {
${TAB}Images._();
$content
  
}"""
    }

    fun svgContent(project: Project, assertDir: VirtualFile): String {
        val content = assertDir.children.filter {
            !it.isDirectory && it.extension?.equals("svg", true) ?: false
        }.toConstStrings(project)
        return """
// 此文件自动生成，用来简化asset中svg的调用
class Svgs {
${TAB}Svgs._();
$content
}"""
    }


    private fun Map<String, String>.toConstStrings(): String {
        return keys.joinToString(separator = "") {
            "\n\tstatic const String $it = '$it';"
        }
    }

    /*fun keysContent(jsonFile: VirtualFile): String? {
        runCatching {
            val jsonString = jsonFile.contentsToByteArray().toString(Charset.defaultCharset())
            val map = jsonString.toObject<LinkedHashMap<String, String>>() ?: return null
            println("解析的数量：${map.size}")
            val content = map.toConstStrings()
            return """// 此文件自动生成，用来简化多语言的调用
abstract class LocaleKeys {$content
}"""
        }
        return null
    }*/

}