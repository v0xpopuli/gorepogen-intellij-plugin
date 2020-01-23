package gorepogen.intellij.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.ui.Messages

class GenerateAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {

        val entityName = event.getData(PlatformDataKeys.EDITOR)?.selectionModel?.selectedText
        if (entityName == null) {
            Messages.showErrorDialog(
                event.project!!,
                MsgBundle.getMessage("gorepogen.highlight.entity"),
                MsgBundle.getMessage("gorepogen.fail")
            )
        }

        val generationService = GenerationService.getInstance()
        try {

            FileDocumentManager.getInstance()
                .saveDocument(event.getData(PlatformDataKeys.EDITOR)!!.document)

            val result = generationService.generateFor(entityName!!, event.project!!.basePath!!)

            Messages
                .showMessageDialog(
                    event.project!!,
                    result,
                    MsgBundle.getMessage("gorepogen.result"),
                    null
                )
        } catch (ex: NotFoundException) {
            DownloadDialog(event.project!!, generationService.resolveGorepogenLocation()).show()
        }

    }
}