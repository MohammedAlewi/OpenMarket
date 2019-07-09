package com.example.openmarket

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class HomeFragmentNavigationTest {
    @Test
    fun click_bottonNav_navigateTo_loginFragment() {
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
