package gorepogen.intellij.plugin

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*
import kotlin.test.assertFailsWith

class MsgBundleTest {


    @Test
    fun testGetStringByKeySuccess() {
        assertThat(MsgBundle.getMessage("gorepogen.success")).isEqualTo("Success")
    }

    @Test
    fun testGetStringByKeyFail() {
        assertThat(MsgBundle.getMessage("gorepogen.fail")).isEqualTo("Ooops...")
    }

    @Test
    fun testGetStringByKeyNotFound() {
        assertThat(MsgBundle.getMessage("gorepogen.not_found")).isEqualTo("GOREPOGEN was not found. Download the latest version?")
    }

    @Test
    fun testGetStringByKeyHighlightEntity() {
        assertThat(MsgBundle.getMessage("gorepogen.highlight.entity")).isEqualTo("Please, highlight entity first!")
    }

    @Test
    fun testGetStringByKeyWindows() {
        assertThat(MsgBundle.getMessage("gorepogen.windows")).isEqualTo("/bin/gorepogen.exe")
    }

    @Test
    fun testGetStringByKeyUnix() {
        assertThat(MsgBundle.getMessage("gorepogen.unix")).isEqualTo("/bin/gorepogen")
    }

    @Test
    fun testGetStringByKeyReadyToUse() {
        assertThat(MsgBundle.getMessage("gorepogen.ready_to_use")).isEqualTo("Downloaded and installed. Try to generate again.")
    }

    @Test
    fun testGetStringByKeySuccessDownloadAndInstall() {
        assertThat(MsgBundle.getMessage("gorepogen.progress")).isEqualTo("Download and Install")
    }

    @Test
    fun testGetStringByKeyDownload() {
        assertThat(MsgBundle.getMessage("gorepogen.downloading")).isEqualTo("Downloading...")
    }

    @Test
    fun testGetStringByKeyInstall() {
        assertThat(MsgBundle.getMessage("gorepogen.installing")).isEqualTo("Installing...")
    }

    @Test
    fun testGetStringByKeyResult() {
        assertThat(MsgBundle.getMessage("gorepogen.result")).isEqualTo("Result")
    }

    @Test
    fun testGetStringByKeyMessageNotExists() {
        assertFailsWith<MissingResourceException> {
            MsgBundle.getMessage("gorepogen.not_exists")
        }
    }

}