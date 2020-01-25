package gorepogen.intellij.plugin

import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.vfs.VirtualFileManager
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

object GenerationService {

    val instance: GenerationService
        get() = ServiceManager.getService(GenerationService::class.java)!!

    fun generateFor(entityName: String, path: String): String =
        try {
            ProcessExecutor.execute("${PathResolver.getGorepogenPath()} -n $entityName -r $path")
                .run {
                    val scanner = Scanner(BufferedReader(InputStreamReader(this.inputStream)))
                    this.process?.waitFor()
                    scanner.nextLine()
                }
                .apply {
                    VirtualFileManager.getInstance().asyncRefresh(null)
                }
        } catch (ex: IOException) {
            throw NotFoundException()
        }

}

