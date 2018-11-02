package com.example.tkontsevich.smack.Tests

import com.example.tkontsevich.smack.Screens.HomeScreen
import com.example.tkontsevich.smack.Screens.NavigationScreen
import com.example.tkontsevich.smack.Services.AuthService.createUser
import com.example.tkontsevich.smack.Services.AuthService.loginUser
import com.example.tkontsevich.smack.Services.AuthService.registerUser
import org.apache.commons.lang3.RandomStringUtils

open class BaseTest {

    val existingUserEmail = "testuser@testsmack.com"
    val existingUserPsw = "123456"
    val channelName = "" + RandomStringUtils.randomAlphanumeric(5)


    fun loginAsExistingUser(): NavigationScreen {
        val homeScreen = HomeScreen()
        val navigationScreen = homeScreen.tapNavigationDrawer()
        val loginScreen = navigationScreen.tapLoginButton()
        return loginScreen.login(existingUserEmail, existingUserPsw)
    }
}
