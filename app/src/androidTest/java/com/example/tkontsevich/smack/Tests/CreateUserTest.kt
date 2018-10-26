package com.example.tkontsevich.smack.Tests

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.tkontsevich.smack.Controller.MainActivity
import com.example.tkontsevich.smack.Screens.CreateUserScreen
import com.example.tkontsevich.smack.Screens.HomeScreen
import com.example.tkontsevich.smack.Screens.NavigationScreen
import com.example.tkontsevich.smack.Screens.UserData
import com.example.tkontsevich.smack.Services.IdlingResourceHolder
import org.apache.commons.lang3.RandomStringUtils
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateUserTest : BaseTest() {

    @get:Rule
    val mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val userName = "User" + RandomStringUtils.randomAlphanumeric(6)
    private val userEmail = RandomStringUtils.randomAlphanumeric(6) + "@mail.com"
    private val userPassword = "123456"
    private val serverErrorToast: ViewInteraction
        get() = onView(withText("Something went wrong, please try again later"))
                .inRoot(withDecorView(not(mActivityTestRule.activity.window.decorView)))

    private val fillOutAllRequiredFieldsToast: ViewInteraction
        get() = onView(withText("Make sure username, email, password are filled in"))
                .inRoot(withDecorView(not(mActivityTestRule.activity.window.decorView)))

    @Before
    fun beforeTestIdlingRegistry() {
        IdlingRegistry.getInstance().register(IdlingResourceHolder.networkIdlingResource)
    }
    @After
    fun afterTestIdlingRegistry() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHolder.networkIdlingResource)
    }

    @Test
    fun testCreateUserWithEmptyFieldsThrowsError() {
        val homeScreen = HomeScreen()
        val navigationScreen = homeScreen.tapNavigationDrawer()
        val loginScreen = navigationScreen.tapLoginButton()
        val createUserScreen = loginScreen.tapSignUpButton()
        createUserScreen.tapCreateUserBtn(UserData.Invalid)
        fillOutAllRequiredFieldsToast.check(matches(isDisplayed()))
    }

    @Test
    fun testCreateUserWithAllFieldsFilledInSuccessful() {
        val homeScreen = HomeScreen()
        var navigationScreen = homeScreen.tapNavigationDrawer()
        val loginScreen = navigationScreen.tapLoginButton()
        val createUserScreen = loginScreen.tapSignUpButton()
        createUserScreen.enterUserName(userName)
        createUserScreen.enterUserEmail(userEmail)
        createUserScreen.enterUserPassword(userPassword)
        createUserScreen.setAvatarImage()
        createUserScreen.setBackgroundAvatarColor()
        navigationScreen = createUserScreen.tapCreateUserBtn(UserData.Valid)
                as NavigationScreen
        assertEquals(navigationScreen.getUserEmailText(), userEmail)
        navigationScreen.logoutButton.perform(click())
    }

    @Test
    fun testCreateUserWithRegisteredEmailForbidden() {
        val homeScreen = HomeScreen()
        val navigationScreen = homeScreen.tapNavigationDrawer()
        val loginScreen = navigationScreen.tapLoginButton()
        var createUserScreen = loginScreen.tapSignUpButton()
        createUserScreen.enterUserName(userName)
        createUserScreen.enterUserEmail(existingUserEmail)
        createUserScreen.enterUserPassword(userPassword)
        createUserScreen.tapCreateUserBtn(UserData.Invalid)
                as CreateUserScreen
        serverErrorToast.check(matches(isDisplayed()))
    }
}