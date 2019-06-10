package com.example.openmarket


import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.openmarket.data.Product
import kotlinx.android.synthetic.main.fragment_products_view.view.*
import java.util.*


class ProductsView : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var listitems:View = inflater.inflate(R.layout.fragment_products_view,container,false)
        recyclerView=listitems.findViewById(R.id.recycler_view_items) as RecyclerView

        @Suppress("UNCHECKED_CAST")
        var products=arguments?.getSerializable("products") as Array<Product>

        recyclerView.layoutManager= GridLayoutManager(this.context,2) as RecyclerView.LayoutManager?

        if (resources.configuration.orientation==Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager= GridLayoutManager(this.context,3) as RecyclerView.LayoutManager?
        }

        recyclerView.adapter= ProductsItemAdapter(activity as MainActivity,products)

        return listitems
    }


    companion object{

        fun newInstance(products:Array<Product>): ProductsView {
            var productsview= ProductsView()
            var arg=Bundle()
            arg.putSerializable("products",products)

            productsview.arguments=arg

            return productsview;
        }
    }


}
