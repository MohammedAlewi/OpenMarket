package com.example.openmarket

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class EntryFragmentNavigationTest {
    @Test
    fun click_loginButton_navigateTo_loginFragment() {
        val scenario = launchFragmentInContainer<Entry>(Bundle(), R.style.AppTheme)
        val navController = Mockito.mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // When the FAB is clicked
        Espresso.onView(ViewMatchers.withId(R.id.login)).perform(ViewActions.click())

        // Then verify that we navigate to the add screen
        Mockito.verify(navController).navigate(
            EntryDirections.action_entry_to_loginFragment())
    }

    @Test
    fun click_signupButton_navigateTo_signupFragment() {
        val scenario = launchFragmentInContainer<Entry>(Bundle(), R.style.AppTheme)
        val navController = Mockito.mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // When the FAB is clicked
        Espresso.onView(ViewMatchers.withId(R.id.signup)).perform(ViewActions.click())

        // Then verify that we navigate to the add screen
        Mockito.verify(navController).navigate(
            EntryDirections.action_entry2_to_signupFragment())
    }
}
