package gorepogen.intellij.plugin.utils

import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFileManager
import gorepogen.intellij.plugin.DownloadDialog
import gorepogen.intellij.plugin.DownloadInstallTask
import gorepogen.intellij.plugin.MsgBundle
import gorepogen.intellij.plugin.PathResolver

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

    fun showDownloadAndInstallDialog(project: Project) = DownloadDialog(
        project,
        PathResolver.getGorepogenPath()
    ).show()

}