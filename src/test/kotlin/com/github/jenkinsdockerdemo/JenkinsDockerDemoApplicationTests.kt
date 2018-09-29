package com.github.jenkinsdockerdemo

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class JenkinsDockerDemoApplicationTests {

    @Test
    fun simpleTest() {
        assertEquals("failure - strings are not equal", "string1", "string2")
    }
}
