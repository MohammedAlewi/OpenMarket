package com.example.openmarket

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.openmarket.data.Product

class ProductsPagerAdapter(fragmentManager: FragmentManager,val products:Array<Array<Product>>,val categories:Array<String>)
    :FragmentStatePagerAdapter(fragmentManager){

    override fun getItem(position: Int): Fragment {

        return ProductsView.newInstance(products[position])
    }

    override fun getCount(): Int {
        return products.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return categories[position]
    }
}