package gorepogen.intellij.plugin

import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.JPanel


class DownloadGorepogenDialog(private val project: Project) : DialogWrapper(project) {

    init {
        init()
        title = "Ooops..."
    }

    override fun createCenterPanel() =
        JPanel(BorderLayout())
            .apply {
                this.add(createLabel(), BorderLayout.CENTER)
            }

    override fun doOKAction() {
        try {
            this.close(OK_EXIT_CODE)
            ProgressManager.getInstance()
                .run(GorepogenTask(project))
        } catch (ex: Exception) {
            this.close(CLOSE_EXIT_CODE)
        }
    }

    private fun createLabel() =
        // TODO: move text to message bundle
        JLabel("GOREPOGEN was not found. Can i download the latest version?")
            .apply {
                this.preferredSize = Dimension(70, 50)
            }
}