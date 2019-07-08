package com.example.openmarket

import com.example.openmarket.data.User
import org.junit.Test
import org.junit.runner.RunWith

//@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
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

    @Test
    fun validUserFields(){
        var user=User(0,"test name","test username","email ",
            "pssW@#1!","0912345","http://localhost/id_pic_test","12.54.23.56")

        var isValid=SignupFragment.validUserFields(user)

        assert(isValid)
    }
}
