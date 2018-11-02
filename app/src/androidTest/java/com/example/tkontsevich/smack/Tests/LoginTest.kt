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
import com.example.tkontsevich.smack.Screens.HomeScreen
import com.example.tkontsevich.smack.Screens.LoginScreen
import com.example.tkontsevich.smack.Screens.PasswordValue
import com.example.tkontsevich.smack.Services.IdlingResourceHolder
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class LoginTest : BaseTest() {
    @get:Rule
    val mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private val invalidPassword = "1234567"
    private val invalidUserName = "invalid@username.com"
    private val checkCredentialsToast: ViewInteraction
        get() = onView(withText("Please check your credentials and try again"))
                .inRoot(withDecorView(not(mActivityTestRule.activity.window.decorView)))
    private val enterEmailAndPswToast: ViewInteraction
        get() = onView(withText("Please enter email and password"))
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
    fun testLoginWithValidCredentials() {
        val navigationScreen = loginAsExistingUser()
        navigationScreen.logoutButton.check(matches(isDisplayed()))
        assertEquals(navigationScreen.getUserEmailText(), existingUserEmail)
        navigationScreen.logoutButton.perform(click())
    }

    @Test
    fun testLoginWithInvalidPassword() {
        val homeScreen = HomeScreen()
        val navigationScreen = homeScreen.tapNavigationDrawer()
        val loginScreen = navigationScreen.tapLoginButton()
        loginScreen.enterUserEmail(existingUserEmail)
        loginScreen.enterUserPassword(invalidPassword)
        val loginWithToastMsg = loginScreen.tapLoginButton(PasswordValue.Invalid)
                as LoginScreen
        loginWithToastMsg.emailTextField.check(matches(isDisplayed()))
        checkCredentialsToast.check(matches(isDisplayed()))
    }

    @Test
    fun testLoginWithInvalidUserName() {
        val homeScreen = HomeScreen()
        val navigationScreen = homeScreen.tapNavigationDrawer()
        val loginScreen = navigationScreen.tapLoginButton()
        loginScreen.enterUserEmail(invalidUserName)
        loginScreen.enterUserPassword(existingUserPsw)
        val loginWithToastMsg = loginScreen.tapLoginButton(PasswordValue.Invalid)
                as LoginScreen
        loginWithToastMsg.emailTextField.check(matches(isDisplayed()))
        checkCredentialsToast.check(matches(isDisplayed()))
    }

    @Test
    fun testLoginWithEmptyFields() {
        val homeScreen = HomeScreen()
        val navigationScreen = homeScreen.tapNavigationDrawer()
        val loginScreen = navigationScreen.tapLoginButton()
        val loginWithToastMsg = loginScreen.tapLoginButton(PasswordValue.Invalid)
                as LoginScreen
        loginWithToastMsg.emailTextField.check(matches(isDisplayed()))
        enterEmailAndPswToast.check(matches(isDisplayed()))
    }

    @Test
    fun testTappingLogout() {
        val navigationScreen = loginAsExistingUser()
        navigationScreen.tapLogoutButton()
        navigationScreen.loginButton.check(matches(isDisplayed()))
    }
}