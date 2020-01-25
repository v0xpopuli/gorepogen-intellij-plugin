package gorepogen.intellij.plugin

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.assertj.core.api.Assertions.assertThat

import org.junit.Test
import kotlin.test.assertFailsWith

class GenerateActionTest : BasePlatformTestCase() {

    @Test
    fun testEntityWasNotSelected() {
        val ex = assertFailsWith<RuntimeException> { myFixture.testAction(GenerateAction()) }
        assertThat(ex.message).isEqualTo("Please, highlight entity first!")
    }

}