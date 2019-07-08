package com.example.openmarket

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserProfileTest {
    private lateinit var scenario: FragmentScenario<UserProfileFragment>

    @Before
    fun initFrag(){
        val bundle = Bundle()
        scenario = launchFragmentInContainer <UserProfileFragment>(bundle)
    }
    @Test
    fun testLabels(){
        Espresso.onView(ViewMatchers.withId(R.id.fullName))
            .check(ViewAssertions.matches((ViewMatchers.withText("Full Name"))))
        Espresso.onView(ViewMatchers.withId(R.id.usernameTxt))
            .check(ViewAssertions.matches(ViewMatchers.withText("User Name")))
        Espresso.onView(ViewMatchers.withId(R.id.emailTxt))
            .check(ViewAssertions.matches(ViewMatchers.withText("Email")))
        Espresso.onView(ViewMatchers.withId(R.id.lnkLogin))
            .check(ViewAssertions.matches(ViewMatchers.withText("Already Registered? Login here")))
        Espresso.onView(ViewMatchers.withId(R.id.phoneNo))
            .check(ViewAssertions.matches(ViewMatchers.withText("Phone Number")))
        Espresso.onView(ViewMatchers.withId(R.id.amount))
            .check(ViewAssertions.matches(ViewMatchers.withText("Confirm Password")))
        Espresso.onView(ViewMatchers.withId(R.id.password))
            .check(ViewAssertions.matches(ViewMatchers.withText("Password")))

    }
    @After
    fun destroyFrag(){
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }


}