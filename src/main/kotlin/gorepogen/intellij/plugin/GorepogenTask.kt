package gorepogen.intellij.plugin

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.JBPopupFactory

class GorepogenTask(project: Project) : Task.Modal(project, "Download and Install", true) {

    override fun run(indicator: ProgressIndicator) {
        indicator.isIndeterminate = true
        indicator.start()
        indicator.text = "Downloading..."
        startDownloading(indicator)
        indicator.text = "Installing..."
        startInstalling()
        indicator.stop()
    }

    private fun startDownloading(indicator: ProgressIndicator) = Runtime.getRuntime()
        .exec("go get -u github.com/v0xpopuli/gorepogen/.../")
        .inputStream


    private fun startInstalling() =
        Runtime.getRuntime().exec("go build github.com/v0xpopuli/gorepogen/cmd")
            .inputStream

    override fun onSuccess() {
        JBPopupFactory.getInstance()
            .createMessage("Downloaded and installed. Try to generate again.")
            .showCenteredInCurrentWindow(project)
    }

}