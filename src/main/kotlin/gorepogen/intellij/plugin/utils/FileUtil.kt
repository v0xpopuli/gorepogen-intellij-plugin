package gorepogen.intellij.plugin.utils

import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.vfs.VirtualFileManager

object FileUtil {

    fun save(document: Document) = FileDocumentManager.getInstance().saveDocument(document)

    fun refresh() = VirtualFileManager.getInstance().asyncRefresh(null)

}