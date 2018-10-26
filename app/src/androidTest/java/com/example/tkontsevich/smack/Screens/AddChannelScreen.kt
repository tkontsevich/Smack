package com.example.tkontsevich.smack.Screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.tkontsevich.smack.R

class AddChannelScreen : BaseScreen() {

    private val channelNameTxtField: ViewInteraction
        get() = onView(withId(R.id.addChannelNameText))

    override val uniqueView: ViewInteraction
        get() = channelNameTxtField

    private val channelDescTxtField: ViewInteraction
        get() = onView(withId(R.id.addChannelDescText))

    private val addButton: ViewInteraction
        get() = onView(withText("ADD"))

    private val cancelButton: ViewInteraction
        get() = onView(withText("CANCEL"))

    init {
        uniqueView.check(matches(isDisplayed()))
    }

    fun enterChannelName(channelName: String) {
        channelNameTxtField.perform(replaceText(channelName))
    }

    fun enterChannelDesc(channelDesc: String) {
        channelDescTxtField.perform(replaceText(channelDesc))
    }

    fun tapAddButton(): NavigationScreen {
        addButton.perform(click())
        return NavigationScreen()
    }

    fun tapCancelButton(): NavigationScreen {
        cancelButton.perform(click())
        return NavigationScreen()
    }
}
