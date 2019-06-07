package com.example.openmarket


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.openmarket.data.Product
import java.util.*


class ProductsView : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var default=arrayOf(
        Product(17,"sdjfhg erer","dsfjg dsfgj","sdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd"),
        Product(18,"fdslkgs ioeroiew","sdfg dfs","dsfgsdfgkl sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd"),
        Product(19,"klre ewrwer","sdflkfd sdf","sdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd"),
        Product(20,"sldfkg sdfjg","fdsjgds sdf","sdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var listitems:View = inflater.inflate(R.layout.fragment_products_view,container,false)
        recyclerView=listitems.findViewById(R.id.recycler_view_items) as RecyclerView
        if(arguments==null || arguments?.isEmpty() as Boolean){
            recyclerView.layoutManager= GridLayoutManager(this.context,2) as RecyclerView.LayoutManager?
            recyclerView.adapter= ProductsItemAdapter(activity as MainActivity,default)
            return listitems
        }
        @Suppress("UNCHECKED_CAST")
        var products=arguments?.getSerializable("products") as Array<Product>
        recyclerView.layoutManager= GridLayoutManager(this.context,2) as RecyclerView.LayoutManager?
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
