package com.example.openmarket

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class ProductDetailFragmentIntegrationTest {
    private lateinit var scenario: FragmentScenario<ProductDetailFragment>

    @Before
    fun initFragment() {
        scenario = launchFragmentInContainer<ProductDetailFragment>()
    }

    @Test
    fun testStaticLabels(){
        onView(withId(R.id.textView3)).check(matches(withText("Product Type")))
        onView(withId(R.id.textView5)).check(matches(withText("Give us a comment")))
        onView(withId(R.id.textView7)).check(matches(withText("Amount")))
        onView(withId(R.id.textView8)).check(matches(withText("rate us")))
        onView(withId(R.id.textView9)).check(matches(withText("Price")))
        onView(withId(R.id.comment_btn)).check(matches(withText("Send")))
    }

    @After
    fun destroyFragment(){
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }
}