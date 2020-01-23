package gorepogen.intellij.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
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

        // TODO: save file on event
        val generationService = GenerationService.getInstance()
        try {
            val result = generationService.generateFor(entityName!!, event.project!!.basePath!!)
            // TODO; maybe show popup, instead of dialog box
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