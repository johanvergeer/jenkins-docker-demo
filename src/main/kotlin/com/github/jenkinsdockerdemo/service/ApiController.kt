package com.github.jenkinsdockerdemo.service

import org.springframework.web.bind.annotation.GetMapping
import com.github.jenkinsdockerdemo.domain.User
import org.springframework.web.bind.annotation.RestController

/**
 * Created by floris on 29-9-2018.
 */

@RestController
class ApiController {

    @GetMapping(value = ["/api/user"])
    fun getUser() : User {
        val user = User(
                firstname = "Floris",
                lastname = "Bosch",
                email = "floris.bosch@student.fontys.nl"
        )
        return user
    }
}
