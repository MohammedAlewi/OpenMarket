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
class LoginFragmentNavigationTest {
    @Test
    fun click_loginButton_navigateTo_homeFragment() {
        val scenario = launchFragmentInContainer<LoginFragment>(Bundle(), R.style.AppTheme)
        val navController = Mockito.mock(NavController::class.java)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // When the FAB is clicked
        Espresso.onView(ViewMatchers.withId(R.id.btnLogin)).perform(ViewActions.click())

        // Then verify that we navigate to the add screen
        Mockito.verify(navController).navigate(
            LoginFragmentDirections.action_loginFragment_to_homeFragment())
    }


}
