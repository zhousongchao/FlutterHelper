package com.zsc.flutter.help.utils

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.newvfs.events.VFileEvent
import com.intellij.psi.PsiManager

val VFileEvent.project:Project?
    get() = (requestor as? PsiManager)?.project

