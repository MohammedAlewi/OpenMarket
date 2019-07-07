package com.example.openmarket

import com.example.openmarket.data.Product
import com.example.openmarket.data.User
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class EditProductFragmentTest{
    @Test
    fun validEditedProductFields(){
        var product=Product(0,"http://test_path","name","test type","test desc",12,
            12.12,Date().toString(),"@testUsername")

        var isValid=EditProductFragment.editedProductFields(product)

        assert(isValid)
    }
}