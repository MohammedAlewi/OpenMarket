package com.example.openmarket


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openmarket.data.Comment
import com.example.openmarket.data.Product
import kotlinx.android.synthetic.main.fragment_product_detail.view.*
import java.util.*


class ProductDetailFragment : Fragment() {
    private var comments= arrayOf(
        Comment(0,"n order to convert the layout to Data Binding, you nee" +
                "d to wrap the root element inn order to convert the layout to Data Binding, you" +
                " need to wrap the root element in a <layout> tag. You'll also have to move the namespace definitions " +
                " a <layout> tag. You'll also have to move the namespace definitions ",Date().toString(),"@depressed_demons"),
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
        var view=inflater.inflate(R.layout.fragment_product_detail, container, false)
        var product=arguments?.getSerializable("product") as Product

        view.product_name.text=product.name
        view.product__type.text=product.type
        view.product_amount.text=product.amount.toString()
        view.product_date.text=product.date
        view.product_description.text=product.description
        view.product_price.text=product.price.toString()

        view.comment_btn.setOnClickListener {
            var commentText=view.comment_box.text.toString()
            var commentdate=Date().toString()
            var commentusername="@maroc"

            var comment=Comment(0,commentText,commentdate,commentusername)
        }

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

