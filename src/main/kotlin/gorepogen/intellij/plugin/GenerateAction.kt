package gorepogen.intellij.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys

class GenerateAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {

        val editor = event.getData(PlatformDataKeys.EDITOR)
        val entityName = editor?.selectionModel?.selectedText

        try {
            GenerationService
                .getInstance(event.project!!)
                .generateFor(entityName!!, event.project!!.basePath!!)
        } catch (ex: GorepogenNotFoundException) {
            DownloadGorepogenDialog(event.project!!).show()
        }

    }
}