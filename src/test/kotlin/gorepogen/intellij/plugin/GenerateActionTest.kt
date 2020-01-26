package gorepogen.intellij.plugin

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import gorepogen.intellij.plugin.utils.MessageUtil
import io.mockk.every
import io.mockk.mockkObject
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import kotlin.test.assertFailsWith

class GenerateActionTest : BasePlatformTestCase() {

    @Test
    fun testEntityWasNotSelected() {
        val actual = assertFailsWith<RuntimeException> {
            myFixture.testAction(GenerateAction())
        }.message
        assertThat(actual).isEqualTo("Please, highlight entity first!")
    }

    @Test
    fun testGorepogenNotFound() {

        mockkObject(GenerationService)
        mockkObject(MessageUtil)

        myFixture.configureByText("user_entity.go", "package entity\n\ntype User struct {}")
        myFixture.editor.selectionModel.setSelection(21, 25)

        every { GenerationService.instance.generateFor(any(), any()) } throws NotFoundException()
        every { MessageUtil.showDownloadAndInstallDialog(any()) } returns Unit

        assertThat(myFixture.testAction(GenerateAction()).isEnabledAndVisible).isTrue()
    }

    @Test
    fun testGenerated() {

        mockkObject(GenerationService)

        myFixture.configureByText("user_entity.go", "package entity\n\ntype User struct {}")
        myFixture.editor.selectionModel.setSelection(21, 25)

        every { GenerationService.instance.generateFor(any(), any()) } returns "Success"

        val actual = assertFailsWith<RuntimeException> {
            myFixture.testAction(GenerateAction())
        }.message

        assertThat(actual).isEqualTo("Success")

    }

}