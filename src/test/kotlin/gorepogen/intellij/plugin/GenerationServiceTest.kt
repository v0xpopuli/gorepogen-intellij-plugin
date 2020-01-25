package gorepogen.intellij.plugin

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import io.mockk.every
import io.mockk.mockkObject
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.IOException
import kotlin.test.assertFailsWith


class GenerationServiceTest : BasePlatformTestCase() {

    @Before
    public override fun setUp() {
        super.setUp()
    }

    @Test
    fun testGetInstance() {
        assertThat(GenerationService.instance).isNotNull
    }

    @Test
    fun testGenerateFor() {

        mockkObject(PathResolver)
        mockkObject(ProcessExecutor)

        val gorepogenPath = "path/to/gorepogen"
        val entityName = "entityName"
        val entityPath = "entityPath"
        val command = "$gorepogenPath -n $entityName -r $entityPath"
        val result = "Repository successfully generated".toByteArray()
        val processWrapper = ProcessWrapper(null, ByteArrayInputStream(result))

        every { PathResolver.getGorepogenPath() } returns gorepogenPath
        every { ProcessExecutor.execute(eq(command)) } returns processWrapper

        val actual = GenerationService.generateFor(entityName, entityPath)
        assertThat(actual).isEqualTo("Repository successfully generated")
    }

    @Test
    fun testGenerateForThrowException() {

        mockkObject(PathResolver)
        mockkObject(ProcessExecutor)

        val gorepogenPath = "path/to/gorepogen"
        val entityName = "entityName"
        val entityPath = "entityPath"
        val command = "$gorepogenPath -n $entityName -r $entityPath"

        every { PathResolver.getGorepogenPath() } returns gorepogenPath
        every { ProcessExecutor.execute(eq(command)) } throws IOException()

        assertFailsWith<NotFoundException> {
            GenerationService.generateFor(entityName, entityPath)
        }
    }

}