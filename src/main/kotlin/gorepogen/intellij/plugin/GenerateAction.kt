package gorepogen.intellij.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.Messages

class GenerateAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {

        val entityName = event.getData(PlatformDataKeys.EDITOR)?.selectionModel?.selectedText
        if (entityName == null) {
            Messages.showErrorDialog(event.project!!, "Please, highlight entity first!", "Ooops...")
        }

        try {
            GenerationService
                .getInstance(event.project!!)
                .generateFor(entityName, event.project!!.basePath!!)
        } catch (ex: GorepogenNotFoundException) {
            DownloadGorepogenDialog(event.project!!).show()
        }

    }
}