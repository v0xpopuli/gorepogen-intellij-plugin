package gorepogen.intellij.plugin

import com.intellij.execution.configurations.GeneralCommandLine
import java.io.InputStream

object ProcessExecutor {

    fun execute(command: List<String>) = GeneralCommandLine(command)
        .createProcess()
        .run {
            ProcessWrapper(this, this.inputStream)
        }

}

data class ProcessWrapper(
    val process: Process?,
    val inputStream: InputStream
)