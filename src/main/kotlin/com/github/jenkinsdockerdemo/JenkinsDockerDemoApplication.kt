package com.github.jenkinsdockerdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import com.github.jenkinsdockerdemo.service.ApiController

@SpringBootApplication
class JenkinsDockerDemoApplication
@Bean
fun controller() = ApiController()

fun main(args: Array<String>) {
    runApplication<JenkinsDockerDemoApplication>(*args)
}

@Controller
class MainController {

    @GetMapping(value = [ "/"])
    fun index(request: HttpServletRequest, model: Model): String {
        println("Hello, World")
        return "index"
       
    }
}
