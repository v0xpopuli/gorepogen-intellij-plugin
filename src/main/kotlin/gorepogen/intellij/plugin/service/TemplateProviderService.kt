package gorepogen.intellij.plugin.service

import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project
import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import freemarker.template.Version


object TemplateProviderService {

    private var cfg: Configuration = Configuration(Version("2.3.29"))

    init {
        cfg.setClassForTemplateLoading(this::class.java, "/templates/")
        cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER;
    }

    fun getInstance(project: Project): TemplateProviderService {
        return ServiceManager.getService(project, TemplateProviderService::class.java)
    }

    fun getTemplate(templateName: String): Template? {
        return cfg.getTemplate(templateName)
    }

}

