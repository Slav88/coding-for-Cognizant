package com.example.jsonplaceholderalbums

import org.junit.*

import org.junit.Assert.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @get:Rule
    val ruleFirst = MyRule("@Rule 1")

    @get:Rule
    val ruleSecond = MyRule("@Rule 2")

    @Before
    fun setup() {
        println("@Before")
    }

    @Test
    @Throws(Exception::class)
    fun test1() {
        println("@Test 1")
    }

    @Test
    @Throws(Exception::class)
    fun test2() {
        println("@Test 2")
    }

    @After
    fun tearDown() {
        println("@After")
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupClass() {
            println("@BeforeClass")
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
            println("@AfterClass")
        }

        @ClassRule
        @JvmField
        val classFirst = MyRule("@ClassRule 1")

        @ClassRule
        @JvmField
        val classSecond = MyRule("@ClassRule 2")
    }

    class MyRule(private val name: String) : TestRule {
        override fun apply(base: Statement, description: Description?)
                = MyStatement(base)

        inner class MyStatement(private val base: Statement) : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                println("$name Before TestRule")
                try {
                    base.evaluate()
                } finally {
                    println("$name After TestRule")
                }
            }
        }
    }
}
