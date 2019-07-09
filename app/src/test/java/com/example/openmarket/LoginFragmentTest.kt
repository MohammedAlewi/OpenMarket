package com.example.openmarket

import com.example.openmarket.data.Product
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class LoginFragmentTest{
    @Test
    fun validEntryOfLoginFields(){
        var username="@testUsername"
        var password="pas#12@!word!"

        var isValid=LoginFragment.isPasswordValid(username,password)

        assert(isValid)
    }
}