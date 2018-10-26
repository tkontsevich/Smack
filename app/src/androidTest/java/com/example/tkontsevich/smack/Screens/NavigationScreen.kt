package com.example.tkontsevich.smack.Screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withParent
import com.example.tkontsevich.smack.R
import com.example.tkontsevich.smack.Untilities.getText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import java.util.concurrent.atomic.AtomicReference

class NavigationScreen : BaseScreen() {

    private val addChannelButton: ViewInteraction
        get() = onView(withId(R.id.addChannelBtn))

    override val uniqueView: ViewInteraction
        get() = addChannelButton

    val loginButton: ViewInteraction
        get() = onView(withText("LOGIN"))

    val logoutButton: ViewInteraction
        get() = onView(withText("LOGOUT"))

    val userName: ViewInteraction
        get() = onView(withId(R.id.userNameNavHeader))

    private val userEmail: ViewInteraction
        get() = onView(withId(R.id.userEmailNavHeader))

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun tapAddChannelButton(): AddChannelScreen {
        addChannelButton.perform(click())
        return AddChannelScreen()
    }

    fun tapLoginButton(): LoginScreen {
        loginButton.perform(click())
        return LoginScreen()
    }

    fun tapLogoutButton(): NavigationScreen {
        logoutButton.perform(click())
        return NavigationScreen()
    }

    fun getUserEmailText(): String {
        val holder = AtomicReference<String>()
        userEmail.perform(getText(holder))
        return holder.get()
    }

    fun tapChannelName(channelName: String): HomeScreen {
        onView(
                allOf(
                        withText(channelName),
                        withParent(withId(R.id.channel_list))
                )
        ).perform(click())
        return HomeScreen()
    }

    fun getUserName(): String {
        val holder = AtomicReference<String>()
        onView(withId(R.id.userNameNavHeader)).perform(getText(holder))
        return holder.get()
    }
}