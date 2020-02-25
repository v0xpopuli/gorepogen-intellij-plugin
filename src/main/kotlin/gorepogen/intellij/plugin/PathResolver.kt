package gorepogen.intellij.plugin

import org.apache.commons.lang.SystemUtils

object PathResolver {

    private const val goPathEnvVarName = "GOPATH"
    private const val unixHomeVarName = "HOME"
    private const val windowsUserProfileVarName = "USERPROFILE"

    private val osName = SystemUtils.OS_NAME.toLowerCase()

    fun getGorepogenPath() =
        when {
            isWindows() -> resolveGorepogenPath(windowsUserProfileVarName, "/bin/gorepogen.exe")
            isMac() || isUnix() -> resolveGorepogenPath(unixHomeVarName, "/bin/gorepogen")
            else -> throw UnsupportedOperationException()
        }

    private fun resolveGorepogenPath(fallback: String, executableName: String) =
        System.getenv(goPathEnvVarName)?.plus(executableName)
            ?: System.getenv(fallback).plus("/go$executableName")

    private fun isWindows() = osName.indexOf("win") >= 0

    private fun isMac() = osName.indexOf("mac") >= 0

    private fun isUnix() = osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0

}