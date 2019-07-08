package com.example.openmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
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
import org.junit.runner.RunWith

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class ProductUploadFragmentIntegrationTest{
    private lateinit var scenario:FragmentScenario<HomeFragment>

    @Before
    @Test
    fun initFragment(){
        val sampleBundle = Bundle()
        scenario = launchFragmentInContainer<HomeFragment>(sampleBundle)
    }

    @Test
    fun testStaticLabels(){
        Espresso.onView(ViewMatchers.withId(R.id.productDescription))
            .check(ViewAssertions.matches(ViewMatchers.withText("Description")))
        Espresso.onView(ViewMatchers.withId(R.id.amount))
            .check(ViewAssertions.matches(ViewMatchers.withText("Amount")))
        Espresso.onView(ViewMatchers.withId(R.id.price))
            .check(ViewAssertions.matches(ViewMatchers.withText("Price Per")))
        Espresso.onView(ViewMatchers.withId(R.id.uploadBtn))
            .check(ViewAssertions.matches(ViewMatchers.withText("Upload")))
    }

    @After
    fun destroyFragment(){
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }
}