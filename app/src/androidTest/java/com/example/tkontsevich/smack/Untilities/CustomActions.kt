package com.example.tkontsevich.smack.Untilities

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import android.widget.TextView
import org.hamcrest.Matcher
import java.util.concurrent.atomic.AtomicReference

fun getText(textHolder: AtomicReference<String>): ViewAction {
    class GetTextAction(val textHolder: AtomicReference<String>) : ViewAction {
        override fun getDescription(): String = "Get text on the view"

        override fun getConstraints(): Matcher<View> =
                ViewMatchers.isAssignableFrom(TextView::class.java)

        override fun perform(uiController: UiController?, view: View?) {
            this.textHolder.set((view as TextView).text.toString())
        }
    }
    return GetTextAction(textHolder)
}