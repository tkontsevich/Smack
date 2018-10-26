package com.example.tkontsevich.smack.Services

import android.support.test.espresso.idling.CountingIdlingResource

object IdlingResourceHolder {
    val networkIdlingResource = CountingIdlingResource("Network Idling Resource", true)
}