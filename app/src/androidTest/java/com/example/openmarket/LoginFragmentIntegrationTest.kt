package com.example.openmarket

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentIntegrationTest {
    private lateinit var scenario: FragmentScenario<LoginFragment>

    @Before
    fun initFragment() {
        val sampleBundle = Bundle()
        scenario = launchFragmentInContainer<LoginFragment>(sampleBundle)
    }

    @Test
    fun testStaticLabels(){
        onView(withId(R.id.loginscrn)).check(matches(withText("Login")))
        onView(withId(R.id.textInputLayout)).check(matches(withText("Username")))
        onView(withId(R.id.textInputLayout2)).check(matches(withText("Password")))
    }

    @After
    fun destroyFragment(){
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }
}