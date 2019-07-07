package com.example.openmarket

import com.example.openmarket.data.Comment
import com.example.openmarket.data.Product
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class ProductDetailFragmentTest{
    @Test
    fun validCommentFields(){
        var comment=Comment(0,"test comment",Date().toString(),"@testUsername")

        var isValid=ProductDetailFragment.validCommentsFeilds(comment)

        assert(isValid)
    }
}