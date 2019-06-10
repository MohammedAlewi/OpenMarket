package com.example.openmarket

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class SignUpFragmentUnitTest{

    @Test
    fun validPassword(){
        val password="asdfgh"
        val validReenteredPassword="asdfgh"
        val invalidRenteredPassword="qwerrt"
        val shouldBeEmptyString=SignupFragment.validPassword(password,invalidRenteredPassword)
        val shouldBeEqualTopassword=SignupFragment.validPassword(password,validReenteredPassword)

       assert(shouldBeEmptyString.equals(""))
        assert(shouldBeEqualTopassword.equals(password))
    }
}