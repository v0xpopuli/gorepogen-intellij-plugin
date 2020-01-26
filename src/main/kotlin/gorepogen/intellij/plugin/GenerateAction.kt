package gorepogen.intellij.plugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import gorepogen.intellij.plugin.utils.FileUtil
import gorepogen.intellij.plugin.utils.MessageUtil

class GenerateAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {

        val entityName = event.getData(PlatformDataKeys.EDITOR)?.selectionModel?.selectedText
        if (entityName == null) {
            MessageUtil.showHighlightEntityErrorMessage(event.project!!)
        }

        try {

            FileUtil.save(event.getData(PlatformDataKeys.EDITOR)!!.document)

            MessageUtil.showSuccessMessage(
                event.project!!,
                GenerationService.instance.generateFor(entityName!!, event.project!!.basePath!!)
            )
        } catch (ex: NotFoundException) {
            MessageUtil.showDownloadAndInstallDialog(event.project!!)
        }

    }
}