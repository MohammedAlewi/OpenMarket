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
class SignupFragmentNavigationTest{
    @Test
    fun click_registerButton_navigateTo_loginFragment() {
        val scenario = launchFragmentInContainer<SignupFragment>(Bundle(), R.style.AppTheme)
        val navController = Mockito.mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }


        Espresso.onView(ViewMatchers.withId(R.id.signupBtn)).perform(ViewActions.click())

        Mockito.verify(navController).navigate(
            SignupFragmentDirections.action_signupFragment_to_loginFragment())
    }
}