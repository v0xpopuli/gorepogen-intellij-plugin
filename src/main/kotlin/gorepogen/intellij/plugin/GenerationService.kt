package gorepogen.intellij.plugin

import com.intellij.openapi.components.ServiceManager
import gorepogen.intellij.plugin.utils.FileUtil
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

object GenerationService {

    val instance: GenerationService
        get() = ServiceManager.getService(GenerationService::class.java)!!

    fun generateFor(entityName: String, path: String): String =
        try {
            val args = listOf(PathResolver.getGorepogenPath()!!, "-n", entityName, "-r", path)
            ProcessExecutor.execute(args)
                .run {
                    val scanner = Scanner(BufferedReader(InputStreamReader(this.inputStream)))
                    this.process?.waitFor()
                    scanner.nextLine()
                }
                .apply {
                    FileUtil.refresh()
                }
        } catch (ex: Exception) {
            throw NotFoundException()
        }

}

