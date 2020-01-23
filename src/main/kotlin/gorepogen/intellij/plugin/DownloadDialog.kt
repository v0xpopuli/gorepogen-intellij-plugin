package gorepogen.intellij.plugin

import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.JPanel


class DownloadDialog(private val project: Project, private val gorepogenLocation: String) : DialogWrapper(project) {

    init {
        init()
        title = MsgBundle.getMessage("gorepogen.fail")
    }

    override fun createCenterPanel() = JPanel(BorderLayout())
        .apply {
            this.add(createLabel(), BorderLayout.CENTER)
        }

    override fun doOKAction() = try {
        this.close(OK_EXIT_CODE)
        ProgressManager.getInstance().run(DownloadInstallTask(this.project, this.gorepogenLocation))
    } catch (ex: Exception) {
        this.close(CLOSE_EXIT_CODE)
    }

    private fun createLabel() = JLabel(MsgBundle.getMessage("gorepogen.not_found"))
        .apply {
            this.preferredSize = Dimension(70, 50)
        }
}