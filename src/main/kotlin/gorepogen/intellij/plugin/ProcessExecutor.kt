package gorepogen.intellij.plugin

import java.io.InputStream

object ProcessExecutor {

    fun execute(command: String) = Runtime.getRuntime()
        .exec(command)
        .run {
            ProcessWrapper(this, this.inputStream)
        }

}

data class ProcessWrapper(
    val process: Process?,
    val inputStream: InputStream
)