package gorepogen.intellij.plugin.utils

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import gorepogen.intellij.plugin.DownloadDialog
import gorepogen.intellij.plugin.MsgBundle

object MessageUtil {

    fun showHighlightEntityErrorMessage(project: Project) = Messages.showErrorDialog(
        project,
        MsgBundle.getMessage("gorepogen.highlight.entity"),
        MsgBundle.getMessage("gorepogen.fail")
    )

    fun showSuccessMessage(project: Project, result: String) = Messages.showMessageDialog(
        project,
        result,
        MsgBundle.getMessage("gorepogen.result"),
        null
    )

    fun showDownloadAndInstallDialog(project: Project) = DownloadDialog(project).show()

}