package gorepogen.intellij.plugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel


class DownloadGorepogenDialog(project: Project) : DialogWrapper(project) {

    init {
        init()
        title = "Ooops..."
    }

    override fun createCenterPanel(): JComponent? {
        val dialogPanel = JPanel(BorderLayout())

        val label = JLabel("Can i download and install latest version of GOREPOGEN?")
        label.preferredSize = Dimension(200, 100)
        dialogPanel.add(label, BorderLayout.CENTER)

        return dialogPanel
    }

    override fun doOKAction() {
        // download and install logic goes here
        this.close(0)
    }
}