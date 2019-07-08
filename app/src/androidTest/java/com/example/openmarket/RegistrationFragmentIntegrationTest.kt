package com.example.openmarket

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RegistrationFragmentIntegrationTest{
    private lateinit var scenario: FragmentScenario<RegistrationFragment>


    @Before
    fun initFragment(){
        val sampleBundle = Bundle()
        scenario = launchFragmentInContainer<RegistrationFragment>(sampleBundle)
    }

    @Test
    fun testStaticLabel(){
        Espresso.onView(ViewMatchers.withId(R.id.textView6))
            .check(ViewAssertions.matches(ViewMatchers.withText("Be Family")))
        Espresso.onView(ViewMatchers.withId(R.id.register))
            .check(ViewAssertions.matches(ViewMatchers.withText("SignUp")))
        Espresso.onView(ViewMatchers.withId(R.id.add_image))
            .check(ViewAssertions.matches(ViewMatchers.withText("+")))
    }

    @After
    fun destroyFragment(){
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }
}