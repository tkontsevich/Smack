package com.example.tkontsevich.smack.Tests

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.support.test.espresso.matcher.ViewMatchers.hasSibling
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.tkontsevich.smack.Controller.MainActivity
import com.example.tkontsevich.smack.R
import com.example.tkontsevich.smack.Services.IdlingResourceHolder
import com.example.tkontsevich.smack.Untilities.getText
import org.apache.commons.lang3.RandomStringUtils
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.atomic.AtomicReference

@RunWith(AndroidJUnit4::class)
class MessageTest : BaseTest() {

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
    fun testMessagePostedInChannel() {
        val messageText = "message: " + RandomStringUtils.randomAlphabetic(12)
        var navigationScreen = loginAsExistingUser()
        // val expectedSenderName = navigationScreen.getUserName()
        val addChannelScreen = navigationScreen.tapAddChannelButton()
        addChannelScreen.enterChannelName("Msg$channelName")
        navigationScreen = addChannelScreen.tapAddButton()
        val homeScreen = navigationScreen.tapChannelName("#Msg$channelName")
        homeScreen.enterMessageText(messageText)
        homeScreen.tapSendMessageBtn()
        verifyMessageIsDisplayed(messageText)
        // val actualSenderName = getSenderByMessageText(messageText)
        // Assert.assertEquals(expectedSenderName, actualSenderName)
        homeScreen.tapNavigationDrawer().logoutButton.perform(click())
    }

    private fun verifyMessageIsDisplayed(messageText: String) {
        onView(
                allOf(
                        withText(messageText), withId(R.id.messageBodyLabel)
                )
        ).check(matches(isDisplayed()))
    }

    private fun getSenderByMessageText(messageText: String): String {
        val holder = AtomicReference<String>()
        onView(
                allOf(
                        withId(R.id.messageUserNameLabel),
                isDescendantOfA(hasSibling(withText(messageText)))
                )
        ).perform(getText(holder))
        return holder.get()
    }
}