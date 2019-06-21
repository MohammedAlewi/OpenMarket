package com.example.openmarket


import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.openmarket.data.Product
import com.example.openmarket.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_products_view.view.*
import java.util.*


class ProductsView : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productViewModel:ProductViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var listitems:View = inflater.inflate(R.layout.fragment_products_view,container,false)
        recyclerView=listitems.findViewById(R.id.recycler_view_items) as RecyclerView

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        productViewModel.setActivtiy(activity as MainActivity)

        @Suppress("UNCHECKED_CAST")
        var type=arguments?.getSerializable("products") as String

        productViewModel.getAllProducts().observe(this,androidx.lifecycle.Observer {
            prod->prod.let { Toast.makeText(this.context," all product size ${prod.size}", Toast.LENGTH_LONG).show() }
        })

        recyclerView.layoutManager= GridLayoutManager(this.context,2) as RecyclerView.LayoutManager?

        if (resources.configuration.orientation==Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager= GridLayoutManager(this.context,3) as RecyclerView.LayoutManager?
        }
        when(type){
            "none" -> {recyclerView.adapter= ProductsItemAdapter(activity as MainActivity, emptyList()) }
            "any" -> {
                var main_products:List<Product> = emptyList()
                productViewModel.getAllProducts().observe(this,androidx.lifecycle.Observer {
                    products -> products.let { recyclerView.adapter= ProductsItemAdapter(activity as MainActivity,products) }
                })
            }
            "electronics" -> {
                var main_products:List<Product> = emptyList()
                productViewModel.getAllProducts().observe(this,androidx.lifecycle.Observer {
                        products -> products.let {
                            recyclerView.adapter= ProductsItemAdapter(activity as MainActivity,
                                products.filter { product -> product.type=="electronics"  })
                        }
                })

            }
            "car" -> {
                var main_products:List<Product> = emptyList()
                productViewModel.getAllProducts().observe(this,androidx.lifecycle.Observer {
                        products -> products.let {
                            recyclerView.adapter= ProductsItemAdapter(activity as MainActivity,
                            products.filter { product -> product.type=="car"})
                        }

                })

            }
            "cloth" -> {
                var main_products:List<Product> = emptyList()
                productViewModel.getAllProducts().observe(this,androidx.lifecycle.Observer {
                        products -> products.let {
                            recyclerView.adapter= ProductsItemAdapter(activity as MainActivity,
                            products.filter { product -> product.type=="cloth"  })
                        }
                })
            }
            "house" ->{
                var main_products:List<Product> = emptyList()
                productViewModel.getAllProducts().observe(this,androidx.lifecycle.Observer {
                        products -> products.let {
                            recyclerView.adapter= ProductsItemAdapter(activity as MainActivity,
                                products.filter { product -> product.type=="house"  } )
                        }
                })
            }
        }

        return listitems
    }


    companion object{

        fun newInstance(products:String): ProductsView {
            var productsview= ProductsView()
            var arg=Bundle()
            arg.putSerializable("products",products)

            productsview.arguments=arg

            return productsview;
        }
    }


}
