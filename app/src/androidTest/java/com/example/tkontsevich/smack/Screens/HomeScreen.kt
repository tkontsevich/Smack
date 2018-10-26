package com.example.tkontsevich.smack.Screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.tkontsevich.smack.R
import com.example.tkontsevich.smack.Untilities.getText
import java.util.concurrent.atomic.AtomicReference

class HomeScreen : BaseScreen() {

    private val mainChannelName: ViewInteraction
        get() = onView(withId(R.id.mainChannelName))

    override val uniqueView: ViewInteraction
        get() = mainChannelName

    private val sendMessageBtn: ViewInteraction
        get() = onView(withId(R.id.sendMessageBtn))

    private val messageTextField: ViewInteraction
        get() = onView(withId(R.id.messageTextField))

    private val homeNavigationBtn: ViewInteraction
        get() = onView(withContentDescription("Open navigation drawer"))

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun tapSendMessageBtn() {
        sendMessageBtn.perform(click())
    }

    fun enterMessageText(messageText: String) {
        messageTextField.perform(replaceText(messageText))
    }

    fun tapNavigationDrawer(): NavigationScreen {
        homeNavigationBtn.perform(click())
        return NavigationScreen()
    }

    fun getChannelName(): String {

        val holder = AtomicReference<String>()
        onView(withId(R.id.mainChannelName)).perform(getText(holder))
        return holder.get()
    }
}