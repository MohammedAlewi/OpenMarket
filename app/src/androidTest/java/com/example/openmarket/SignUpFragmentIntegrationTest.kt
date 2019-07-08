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
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignUpFragmentIntegrationTest {
    private lateinit var scenario: FragmentScenario<SignupFragment>

    @Before
    fun initialize(){
        val bundle = Bundle()
        scenario = launchFragmentInContainer<SignupFragment>(bundle)
    }
    fun testLabels(){
        onView(withId(R.id.fullName)).check(matches((withText("Full Name"))))
        onView(withId(R.id.usernameTxt)).check(matches(withText("User Name")))
        onView(withId(R.id.emailTxt)).check(matches(withText("Email")))
        onView(withId(R.id.lnkLogin)).check(matches(withText("Already Registered? Login here")))
        onView(withId(R.id.phoneNo)).check(matches(withText("Phone Number")))
        onView(withId(R.id.amount)).check(matches(withText("Confirm Password")))
        onView(withId(R.id.password)).check(matches(withText("Password")))

    }
    @After
    fun destroyFrag(){
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }
}