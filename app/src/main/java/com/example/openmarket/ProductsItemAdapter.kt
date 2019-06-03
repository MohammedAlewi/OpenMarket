package com.example.openmarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.openmarket.data.Product
import kotlinx.android.synthetic.main.product_items_view.view.*

class ProductsItemAdapter(mainActivity: MainActivity,items_val: Array<Product>)
    :RecyclerView.Adapter<ProductsItemAdapter.ProductViewHolder>(){

    val products=items_val
    val main=mainActivity


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var inflator=LayoutInflater.from(parent.context);
        val recyclerView=inflator.inflate(R.layout.product_items_view, parent,false)

        recyclerView.setOnClickListener{
            var product:Product?=products.find{it.name==recyclerView.name_of_product.text.toString() }
//            var productDetailFragment=ProductDetailFragment.getInstance(product as Product)
//            handler.childFragmentManager.beginTransaction().replace(R.id.home_frame,productDetailFragment).commit()
            main.onItemClicked(product as Product)

        }

        return ProductViewHolder(recyclerView)
    }


    override fun getItemCount():Int{
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product=products[position]
        holder.itemView.name_of_product.text=product.name

    }


    inner class ProductViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    interface ContentListener {
        fun onItemClicked(item: Product)
    }
}

