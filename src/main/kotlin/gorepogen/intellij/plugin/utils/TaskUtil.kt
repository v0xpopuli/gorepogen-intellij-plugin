package gorepogen.intellij.plugin.utils

import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import gorepogen.intellij.plugin.DownloadInstallTask

object TaskUtil {

    fun downloadAndInstall(project: Project) = ProgressManager
        .getInstance()
        .run(DownloadInstallTask(project))

}