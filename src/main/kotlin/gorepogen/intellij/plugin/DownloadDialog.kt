package gorepogen.intellij.plugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import gorepogen.intellij.plugin.utils.TaskUtil
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.JPanel


class DownloadDialog(private val project: Project) : DialogWrapper(project) {

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
        TaskUtil.downloadAndInstall(this.project)
    } catch (ex: Exception) {
        this.close(CLOSE_EXIT_CODE)
    }

    private fun createLabel() = JLabel(MsgBundle.getMessage("gorepogen.not_found"))
        .apply {
            this.preferredSize = Dimension(70, 50)
        }
}