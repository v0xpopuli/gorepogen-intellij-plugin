package gorepogen.intellij.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys

class GenerateAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {

        val editor = e.getData(PlatformDataKeys.EDITOR)
        val entityName = editor?.selectionModel?.selectedText

        GenerationService
            .getInstance(e.project!!)
            .generateFor(entityName!!, e.project!!.basePath!!)

    }
}