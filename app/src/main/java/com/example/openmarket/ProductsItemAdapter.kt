package com.example.openmarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.openmarket.databinding.ProductItemsViewBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.openmarket.data.Product
import kotlinx.android.synthetic.main.product_items_view.view.*

class ProductsItemAdapter(mainActivity: MainActivity,items_val: List<Product>)
    :RecyclerView.Adapter<ProductsItemAdapter.ProductViewHolder>(){

    val products=items_val
    val main=mainActivity


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var inflator=LayoutInflater.from(parent.context);
        var binding=DataBindingUtil.inflate<ProductItemsViewBinding>(inflator, R.layout.product_items_view, parent, false)
        val recyclerView=binding.root

        recyclerView.setOnClickListener{
            var product:Product?=products.find{it.name==recyclerView.name_of_product.text.toString() }
            var arg=Bundle()
            arg.putSerializable("product",product)
            recyclerView.findNavController().navigate(R.id.productDetailFragment,arg)

            notifyDataSetChanged()
        }
        
        return ProductViewHolder(recyclerView,binding)
    }


    override fun getItemCount():Int{
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product=products[position]
       holder.binding.productName=product.name

    }


    inner class ProductViewHolder(itemView:View, val binding:ProductItemsViewBinding):RecyclerView.ViewHolder(itemView)

    interface ContentListener {
        fun onItemClicked(item: Product)
    }
}
