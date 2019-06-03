package com.example.openmarket


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.openmarket.data.Product
import kotlinx.android.synthetic.main.fragment_product_detail.view.*


class ProductDetailFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view=inflater.inflate(R.layout.fragment_product_detail, container, false)
        var product=arguments?.getSerializable("product") as Product

        view.product_name.text=product.name
        view.product__type.text=product.type
        view.product_amount.text=product.amount.toString()
        view.product_date.text=product.date
        view.product_description.text=product.description
        view.product_price.text=product.price.toString()


        return view
    }



    companion object {
        fun getInstance(product:Product):ProductDetailFragment{

            var productDetailFragment=ProductDetailFragment()
            var arg=Bundle()
            arg.putSerializable("product",product)
            productDetailFragment.arguments=arg

            return productDetailFragment;
        }
    }
}
