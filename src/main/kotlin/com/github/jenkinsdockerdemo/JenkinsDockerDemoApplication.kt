package com.github.jenkinsdockerdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpServletRequest

@SpringBootApplication
class JenkinsDockerDemoApplication

fun main(args: Array<String>) {
    runApplication<JenkinsDockerDemoApplication>(*args)
}

@Controller
class MainController {

    @GetMapping(value = ["/"])
    fun index(request: HttpServletRequest, model: Model): String {
        return "index"
    }
}
