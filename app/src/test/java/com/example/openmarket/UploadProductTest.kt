package com.example.openmarket

import com.example.openmarket.data.Product
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class UploadProductTest{

    @Test
    fun validUploadProductFields(){
        var product= Product(0,"http://test_path","name","test type","test desc",12,
            12.12, Date().toString(),"@testUsername")

        var isValid=ProductUploadFragment.allProductFieldsSpecified(product)

        assert(isValid)
    }
}