package com.zsc.flutter.help

import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.VFileEvent
import com.zsc.flutter.help.utils.AssertFileGenerate
import com.zsc.flutter.help.utils.Constants
import com.zsc.flutter.help.utils.project

class AssertFileListener : BulkFileListener {
    override fun after(events: List<VFileEvent>) {
        val project = events.firstOrNull {
            it.project != null
        }?.project ?: return
        println("fileChange:project:${events.firstOrNull()?.project}\n path: ${events.firstOrNull()?.path}")
        events.firstOrNull {
            Constants.isTargetImage(it.project, it.file)
        }?.runCatching {
            print("doAssert:${path}")
            AssertFileGenerate.doCreateAssertFile(project)
        }

        events.firstOrNull {
            Constants.isTargetSvg(it.project, it.file)
        }?.runCatching {
            AssertFileGenerate.doCreateSvgFile(project)
        }

        events.firstOrNull {
            Constants.isTargetLangJson(it.project, it.file)
        }?.runCatching {
            AssertFileGenerate.doCreateKeysFile(project)
        }


    }

}
