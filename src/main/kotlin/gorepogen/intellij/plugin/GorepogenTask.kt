package gorepogen.intellij.plugin

import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

class GorepogenTask(project: Project) : Task.Modal(project, "Download and Install", true) {

    override fun run(indicator: ProgressIndicator) {
        indicator.isIndeterminate = true
        indicator.start()
        indicator.text = "Downloading..."
        startDownloading()
        indicator.text = "Installing..."
        startInstalling()
        indicator.stop()
    }

    // TODO: determine why output doesn't writes to progress bar
    private fun startDownloading() = Runtime
        .getRuntime()
        .exec("go get -v -u github.com/v0xpopuli/gorepogen/.../")
        .waitFor()

    // TODO: build with gorepogen name
    private fun startInstalling() = Runtime
        .getRuntime()
        .exec("go build github.com/v0xpopuli/gorepogen/cmd")
        .waitFor()

    override fun onSuccess() {
        // TODO: move text to message bundle
        Messages.showMessageDialog(
            project!!,
            "Downloaded and installed. Try to generate again.",
            "Success",
            null
        )
    }

}