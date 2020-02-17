package gorepogen.intellij.plugin

import java.util.*


object MsgBundle {

    private var bundle = ResourceBundle.getBundle("messages")

    fun getMessage(title: String) = String(bundle.getString(title).toByteArray(), Charsets.UTF_8)

}