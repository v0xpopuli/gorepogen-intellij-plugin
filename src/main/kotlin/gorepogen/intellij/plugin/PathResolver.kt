package gorepogen.intellij.plugin

import org.apache.commons.lang.SystemUtils

object PathResolver {

    private const val goPathEnvVarName = "GOPATH"

    private val osName = SystemUtils.OS_NAME.toLowerCase()

    fun getGorepogenPath() =
        when {
            isWindows() -> System.getenv(goPathEnvVarName).plus(MsgBundle.getMessage("gorepogen.windows"))
            isMac() || isUnix() -> System.getenv(goPathEnvVarName).plus(MsgBundle.getMessage("gorepogen.unix"))
            else -> throw UnsupportedOperationException()
        }

    private fun isWindows() = osName.indexOf("win") >= 0

    private fun isMac() = osName.indexOf("mac") >= 0

    private fun isUnix() = osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0

}