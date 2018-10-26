package com.example.tkontsevich.smack.Screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.example.tkontsevich.smack.R

enum class PasswordValue {
    Valid,
    Invalid
}

class LoginScreen : BaseScreen() {

    val emailTextField: ViewInteraction
        get() = onView(withId(R.id.loginEmailText))

    override val uniqueView: ViewInteraction
        get() = emailTextField

    private val passwordTextField: ViewInteraction
        get() = onView(withId(R.id.loginPasswordText))

    private val loginButton: ViewInteraction
        get() = onView(withId(R.id.loginLoginBtn))

    private val signUpButton: ViewInteraction
        get() = onView(withId(R.id.loginCreateUserBtn))

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun enterUserEmail(email: String) {
        emailTextField.perform(replaceText(email))
    }

    fun enterUserPassword(password: String) {
        passwordTextField.perform(replaceText(password))
    }

    fun tapLoginButton(passwordType: PasswordValue): BaseScreen {
        loginButton.perform(click())
        when (passwordType) {
            PasswordValue.Invalid -> return LoginScreen()
            PasswordValue.Valid -> return NavigationScreen()
        }
    }

    fun tapSignUpButton(): CreateUserScreen {
        signUpButton.perform(click())
        return CreateUserScreen()
    }

    fun login(userEmail: String, password: String): NavigationScreen {
        enterUserEmail(userEmail)
        enterUserPassword(password)
        loginButton.perform(click())
        return NavigationScreen()
    }
}