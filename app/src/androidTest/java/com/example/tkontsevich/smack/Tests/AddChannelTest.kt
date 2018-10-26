package com.example.tkontsevich.smack.Tests

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.ViewMatchers.withParent
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.tkontsevich.smack.Controller.MainActivity
import com.example.tkontsevich.smack.R
import com.example.tkontsevich.smack.Services.IdlingResourceHolder
import com.example.tkontsevich.smack.Untilities.CustomFailureHandler
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.After
import org.junit.Test
import org.junit.Assert.assertEquals

@RunWith(AndroidJUnit4::class)
class AddChannelTest : BaseTest() {
    @get:Rule
    val mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun beforeTestIdlingRegistry() {
        IdlingRegistry.getInstance().register(IdlingResourceHolder.networkIdlingResource)
    }
    @After
    fun afterTestIdlingRegistry() {
        IdlingRegistry.getInstance().unregister(IdlingResourceHolder.networkIdlingResource)
    }

    @Test
    fun testAddChannel() {
        val navigationScreen = loginAsExistingUser()
        val addChannelScreen = navigationScreen.tapAddChannelButton()
        addChannelScreen.enterChannelName("Channel$channelName")
        addChannelScreen.enterChannelDesc("Testestest")
        addChannelScreen.tapAddButton()
        val homeScreen = navigationScreen.tapChannelName("#Channel$channelName")
        assertEquals("#Channel$channelName", homeScreen.getChannelName())
        homeScreen.tapNavigationDrawer().logoutButton.perform(click())
    }

    @Test
    fun testCancelAddChannel() {
        val navigationScreen = loginAsExistingUser()
        val addChannelScreen = navigationScreen.tapAddChannelButton()
        addChannelScreen.enterChannelName("Cancel$channelName")
        addChannelScreen.enterChannelDesc("Testestest")
        addChannelScreen.tapCancelButton()
        verifyNoChannelNameExists("#Cancel$channelName")
        navigationScreen.logoutButton.perform(click())
    }

    private fun verifyNoChannelNameExists(channelName: String) {
        onView(
                allOf(
                        withText(channelName),
                        withParent(withId(R.id.channel_list))
                )
        ).withFailureHandler(CustomFailureHandler)
                .check(doesNotExist())
    }
}