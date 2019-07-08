package com.example.openmarket


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openmarket.data.Comment
import com.example.openmarket.data.Product
import java.util.*
import com.example.openmarket.databinding.FragmentProductDetailBinding
import com.example.openmarket.viewmodel.CommentViewModel
import com.example.openmarket.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.comments_lists.view.*
import okhttp3.internal.userAgent

class ProductDetailFragment : Fragment() {
    private lateinit var commentViewModel: CommentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        commentViewModel = ViewModelProviders.of(this).get(CommentViewModel::class.java)
        commentViewModel.setActivtiy(activity as MainActivity)

        var fragmentProductDetailBinding=
           DataBindingUtil.inflate<FragmentProductDetailBinding>(inflater, R.layout.fragment_product_detail, container, false)
        var product=arguments?.getSerializable("product") as Product
        var view=fragmentProductDetailBinding.root
        fragmentProductDetailBinding.product=product
        fragmentProductDetailBinding.comLst=CommentListener(this.context as Context,product,view,commentViewModel,activity as MainActivity)


        var commentViews=view.findViewById(R.id.comment_items) as RecyclerView
        commentViews.layoutManager=LinearLayoutManager(this.context)
        commentViewModel.getCommentByUsername(product.userName).observe(this,androidx.lifecycle.Observer {
            comments-> commentViews.adapter=CommentItemAdapter(comments)
        })

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

class CommentListener(private var context: Context,private var product: Product,
                      private var view: View,private var  commentViewModel: CommentViewModel,private val activity: MainActivity){
    fun buttonClicked() {
        var usrname=activity.currentUser?.username
        if (usrname==null)
            usrname="unknown"
        var comment=Comment(0,view.comment_body.text.toString(),view.comment_date.text.toString(),usrname)
        commentViewModel.insertComment(comment,product_id = product.id)
        Toast.makeText(context,"comment is saved",Toast.LENGTH_LONG).show()
    }
}