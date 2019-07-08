package com.example.openmarket


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SignUpEspressoTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun signUpEspressoTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val appCompatButton = onView(
            allOf(
                withId(R.id.signup), withText("SignUp"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main_content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        pressBack()

        val appCompatEditText = onView(
            allOf(
                withId(R.id.fullNameEdit),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.main_content),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("Natnael Mulugeta"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.usernameEdit),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.main_content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("natnael"), closeSoftKeyboard())

        pressBack()

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.emailEdit),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.main_content),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("natnaelmu.07@gmail.com"), closeSoftKeyboard())

        pressBack()

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.passwordEdit),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.main_content),
                            0
                        )
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(replaceText("2019"), closeSoftKeyboard())

        pressBack()

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.confirmPasswordEdit),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.main_content),
                            0
                        )
                    ),
                    10
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(replaceText("2019"), closeSoftKeyboard())

        pressBack()

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.phoneNoEdit),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.main_content),
                            0
                        )
                    ),
                    12
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(replaceText("0923363942"), closeSoftKeyboard())

        pressBack()

        val button = onView(
            allOf(
                withId(R.id.signupBtn),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.main_content),
                            0
                        )
                    ),
                    14
                ),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val editText = onView(
            allOf(
                withId(R.id.fullNameEdit), withText("Natnael Mulugeta"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.main_content),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        editText.check(matches(withText("Natnael Mulugeta")))

        val editText2 = onView(
            allOf(
                withId(R.id.usernameEdit), withText("natnael"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.main_content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        editText2.check(matches(withText("natnael")))

        val editText3 = onView(
            allOf(
                withId(R.id.passwordEdit), withText("••••"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.main_content),
                            0
                        )
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        editText3.check(matches(withText("••••")))

        val editText4 = onView(
            allOf(
                withId(R.id.confirmPasswordEdit), withText("••••"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.main_content),
                            0
                        )
                    ),
                    10
                ),
                isDisplayed()
            )
        )
        editText4.check(matches(withText("••••")))

        val editText5 = onView(
            allOf(
                withId(R.id.phoneNoEdit), withText("0923363942"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.main_content),
                            0
                        )
                    ),
                    12
                ),
                isDisplayed()
            )
        )
        editText5.check(matches(withText("0923363942")))

        val editText6 = onView(
            allOf(
                withId(R.id.phoneNoEdit), withText("0923363942"),
                childAtPosition(
                    allOf(
                        withId(R.id.constraintLayout),
                        childAtPosition(
                            withId(R.id.main_content),
                            0
                        )
                    ),
                    12
                ),
                isDisplayed()
            )
        )
        editText6.check(matches(withText("0923363942")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
