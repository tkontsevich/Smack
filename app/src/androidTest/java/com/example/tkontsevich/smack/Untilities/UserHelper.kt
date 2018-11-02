package com.example.tkontsevich.smack.Untilities

import com.example.tkontsevich.smack.Services.AuthService

object UserHelper {

    fun createTestUser() {
        val email = "testuser@testsmack.com"
        //val email = RandomStringUtils.randomAlphanumeric(5) + "@smack.com"
        val password = "123456"
        AuthService.registerUser(email, password) { isRegistered ->
            if (isRegistered)
                AuthService.loginUser(email, password) { isLoggedIn ->
                    if (isLoggedIn)
                        AuthService.createUser("Tanya3Test", email, "dark5",
                                "[0.9686274509803922, 0.9647058823529412, 0.9490196078431372, 1]") { isCreated ->
                        }
                }
        }
    }

}