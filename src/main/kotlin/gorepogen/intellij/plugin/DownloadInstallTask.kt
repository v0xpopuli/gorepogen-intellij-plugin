package gorepogen.intellij.plugin

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

class DownloadInstallTask(project: Project) :
    Task.Backgroundable(project, MsgBundle.getMessage("gorepogen.progress"), true) {

    override fun run(indicator: ProgressIndicator) = indicator
        .run {
            this.isIndeterminate = true
            this.text = MsgBundle.getMessage("gorepogen.downloading")
            startDownloading()
            this.text = MsgBundle.getMessage("gorepogen.installing")
            this.stop()
        }

    private fun startDownloading() = ProcessExecutor
        .execute(listOf("go", "get", "-u", "-v", "github.com/v0xpopuli/gorepogen/.../"))
        .process
        ?.waitFor()

    override fun onSuccess() = Messages
        .showMessageDialog(
            project!!,
            MsgBundle.getMessage("gorepogen.ready_to_use"),
            MsgBundle.getMessage("gorepogen.success"),
            null
        )

}