package com.example.tkontsevich.smack.Screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.tkontsevich.smack.R

enum class UserData {
    Valid,
    Invalid
}
class CreateUserScreen : BaseScreen() {

    private val userName: ViewInteraction
        get() = onView(withId(R.id.createUserNameText))

    override val uniqueView: ViewInteraction
        get() = userName

    private val userEmail: ViewInteraction
        get() = onView(withId(R.id.createEmailText))

    private val userPassword: ViewInteraction
        get() = onView(withId(R.id.createPasswordText))

    private val avatarImage: ViewInteraction
        get() = onView(withId(R.id.createAvatarImageView))

    private val backgroundColor: ViewInteraction
        get() = onView(withId(R.id.backgroundColorBtn))

    private val createUserButton: ViewInteraction
        get() = onView(withId(R.id.createUserBtn))

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun enterUserName(name: String) {
        userName.perform(replaceText(name))
    }

    fun enterUserEmail(email: String) {
        userEmail.perform(replaceText(email))
    }

    fun enterUserPassword(password: String) {
        userPassword.perform(replaceText(password))
    }

    fun setAvatarImage() {
        avatarImage.perform(click())
    }

    fun setBackgroundAvatarColor() {
        backgroundColor.perform(click())
    }

    fun tapCreateUserBtn(userData: UserData): BaseScreen {
        createUserButton.perform(click())
        when (userData) {
            UserData.Invalid -> return CreateUserScreen()
            UserData.Valid -> return NavigationScreen()
        }
    }
}
