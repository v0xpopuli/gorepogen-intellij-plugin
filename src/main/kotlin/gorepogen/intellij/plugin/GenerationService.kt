package gorepogen.intellij.plugin

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.ProcessNotCreatedException
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project
import org.apache.commons.lang.SystemUtils

object GenerationService {

    private val osName: String = SystemUtils.OS_NAME.toLowerCase()

    // TODO: move those names to message bundle
    private val unixGorepogenPath: String =
        System.getenv("GOPATH")
            .plus("/bin/cmd")

    private val windowsGorepogenPath: String =
        System.getenv("GOPATH")
            .plus("\\bin\\cmd.exe")


    fun getInstance(project: Project): GenerationService {
        return ServiceManager.getService(project, GenerationService::class.java)
    }

    fun generateFor(entityName: String?, basePath: String) {
        try {
            // TODO: user Runtime.exec to follow common approach
            GeneralCommandLine(resolveGorepogenLocation())
                .run {
                    this.addParameters("-n", entityName, "-r", basePath)
                    this.createProcess()
                }
                .waitFor()
        } catch (ex: ProcessNotCreatedException) {
            throw GorepogenNotFoundException()
        }
    }

    private fun resolveGorepogenLocation() =
        when {
            isWindows() -> windowsGorepogenPath
            isMac() || isUnix() -> unixGorepogenPath
            else -> throw UnsupportedOperationException()
        }

    private fun isWindows() = osName.indexOf("win") >= 0

    private fun isMac() = osName.indexOf("mac") >= 0

    private fun isUnix() = osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0

}

