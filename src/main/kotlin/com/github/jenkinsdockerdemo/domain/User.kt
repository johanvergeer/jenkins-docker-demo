package com.github.jenkinsdockerdemo.domain

/**
 * Created by floris on 29-9-2018.
 */

/**
 * Model of a user
 * @property firstname
 * @property lastname
 * @property email
 */
data class User(
        val firstname: String,
        val lastname: String,
        val email: String
)