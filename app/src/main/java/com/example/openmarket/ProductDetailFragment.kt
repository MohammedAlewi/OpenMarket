package com.example.openmarket


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openmarket.data.Comment
import com.example.openmarket.data.Product
import java.util.*
import com.example.openmarket.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {
    private var comments= arrayOf(
        Comment(0,"n order to convert the layout to Data Binding, you nee" +
                "d to wrap the root element inn order to convert the layout to Data Binding, you" +
                " need to wrap the root element in a <layout> tag. You'll also have to move the namespace definitions " +
                " tag. You'll also have to move the namespace definitions ",Date().toString(),"@depressed_demons"),
        Comment(0,"ert the layout to Data Binding, you nee" +
                "d to wrap the root element inn order to convert the layout to Data Binding, you" +
                " need to wrap the root element in a <layout> tag. You'll also have to move the namespace definitions " +
                " a <layout> tag. You'll also have to move the namespace definitions ",Date().toString(),"@maroc"),
        Comment(0,"ncnvert the layout to Data Binding, you nee" +
                "d to wrap the root element inn order to convert the layout to Data Binding, you" +
                " need to wrap the root element in a <layout> tag. You'll also have to move the namespace definitions " +
                " a <layout> tag. You'll also have to move the namespace definitions ",Date().toString(),"@xyz"),
        Comment(0," Data Binding, you nee" +
                "d to wrap the root element inn order to convert the layout to Data Binding, you" +
                " need to wrap the root element in a <layout> tag. You'll also have to move the namespace definitions " +
                " a <layout> tag. You'll also have to move the namespace definitions ",Date().toString(),"@XAD")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmentProductDetailBinding=
           DataBindingUtil.inflate<FragmentProductDetailBinding>(inflater, R.layout.fragment_product_detail, container, false)
        var product=arguments?.getSerializable("product") as Product
        var view=fragmentProductDetailBinding.root
        fragmentProductDetailBinding.product=product
        fragmentProductDetailBinding.comLst=CommentListener(this.context as Context)


        var commentViews=view.findViewById(R.id.comment_items) as RecyclerView
        commentViews.layoutManager=LinearLayoutManager(this.context)
        commentViews.adapter=CommentItemAdapter(comments)

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

class CommentListener(private var context: Context){
    fun buttonClicked() {
      Toast.makeText(context,"comment is saved",Toast.LENGTH_LONG).show()
    }
}