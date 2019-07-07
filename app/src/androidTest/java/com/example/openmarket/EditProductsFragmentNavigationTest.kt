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
class EditProductsFragmentNavigationTest {
    @Test
    fun click_saveButton_navigateTo_homeFragment() {
        val scenario = launchFragmentInContainer<EditProductFragment>(Bundle(), R.style.AppTheme)
        val navController = Mockito.mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // When the FAB is clicked
        Espresso.onView(ViewMatchers.withId(R.id.save)).perform(ViewActions.click())

        // Then verify that we navigate to the add screen
      //  Mockito.verify(navController).navigate(
            //.action_loginFragment_to_homeFragment())
    }


}
