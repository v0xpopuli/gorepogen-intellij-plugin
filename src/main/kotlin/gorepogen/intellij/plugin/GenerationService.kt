package gorepogen.intellij.plugin

import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.vfs.VirtualFileManager
import org.apache.commons.lang.SystemUtils
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

object GenerationService {

    private const val goPath = "GOPATH"

    private val osName = SystemUtils.OS_NAME.toLowerCase()

    fun getInstance() = ServiceManager.getService(GenerationService::class.java)!!

    fun generateFor(entityName: String, path: String): String =
        try {
            Runtime.getRuntime()
                .exec("${resolveGorepogenLocation()} -n $entityName -r $path")
                .run {
                    val scanner = Scanner(BufferedReader(InputStreamReader(this.inputStream)))
                    this.waitFor()
                    scanner.nextLine()
                }
                .apply {
                    VirtualFileManager.getInstance().asyncRefresh(null)
                }
        } catch (ex: IOException) {
            throw NotFoundException()
        }


    fun resolveGorepogenLocation() =
        when {
            isWindows() -> System.getenv(goPath).plus(MsgBundle.getMessage("gorepogen.windows"))
            isMac() || isUnix() -> System.getenv(goPath).plus(MsgBundle.getMessage("gorepogen.unix"))
            else -> throw UnsupportedOperationException()
        }

    private fun isWindows() = osName.indexOf("win") >= 0

    private fun isMac() = osName.indexOf("mac") >= 0

    private fun isUnix() = osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0

}

