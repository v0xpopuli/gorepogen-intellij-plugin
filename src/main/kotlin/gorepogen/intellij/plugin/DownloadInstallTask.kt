package gorepogen.intellij.plugin

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

class DownloadInstallTask(project: Project, private val gorepogenLocation: String) :
    Task.Backgroundable(project, MsgBundle.getMessage("gorepogen.progress"), true) {

    override fun run(indicator: ProgressIndicator) = indicator
        .run {
            this.isIndeterminate = true
            this.text = MsgBundle.getMessage("gorepogen.downloading")
            startDownloading()
            this.text = MsgBundle.getMessage("gorepogen.installing")
            startInstalling()
            this.stop()
        }


    private fun startDownloading() = Runtime.getRuntime()
        .exec("go get -v -u github.com/v0xpopuli/gorepogen/.../")
        .waitFor()

    private fun startInstalling() = Runtime.getRuntime()
        .exec("go build -o ${this.gorepogenLocation} github.com/v0xpopuli/gorepogen/cmd")
        .waitFor()

    override fun onSuccess() = Messages
        .showMessageDialog(
            project!!,
            MsgBundle.getMessage("gorepogen.ready_to_use"),
            MsgBundle.getMessage("gorepogen.success"),
            null
        )

}