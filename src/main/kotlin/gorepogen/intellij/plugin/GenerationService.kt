package gorepogen.intellij.plugin

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project


object GenerationService {

    fun getInstance(project: Project): GenerationService {
        return ServiceManager.getService(project, GenerationService::class.java)
    }

    fun generateFor(entityName: String, basePath: String) {

       GeneralCommandLine(System.getenv("GOPATH").plus("\\bin\\gorepogen.exe"))
            .apply {
                this.addParameters("-n", entityName, "-r", basePath)
            }
            .createProcess()

    }

}

