package com.example.openmarket


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openmarket.data.Comment
import com.example.openmarket.data.Product
import com.example.openmarket.data.Rating
import com.example.openmarket.data.Subscription
import com.example.openmarket.databinding.FragmentProductDetailBinding
import com.example.openmarket.viewmodel.*
import kotlinx.android.synthetic.main.fragment_product_detail.view.*
import java.util.*

class ProductDetailFragment : Fragment() {
    private lateinit var commentViewModel: CommentViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var ratingViewModel: RatingViewmodel
    private lateinit var subscriptionViewmodel: SubscriptionViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        commentViewModel = ViewModelProviders.of(this).get(CommentViewModel::class.java)
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        ratingViewModel = ViewModelProviders.of(this).get(RatingViewmodel::class.java)
        subscriptionViewmodel = ViewModelProviders.of(this).get(SubscriptionViewmodel::class.java)


        commentViewModel.setActivtiy(activity as MainActivity)
        productViewModel.setActivtiy(activity as MainActivity)
        userViewModel.setActivtiy(activity as MainActivity)
        ratingViewModel.setActivtiy(activity as MainActivity)
        subscriptionViewmodel.setActivtiy(activity as MainActivity)


        var fragmentProductDetailBinding =
            DataBindingUtil.inflate<FragmentProductDetailBinding>(
                inflater,
                R.layout.fragment_product_detail,
                container,
                false
            )
        var product = arguments?.getSerializable("product") as Product
        var view = fragmentProductDetailBinding.root
        if (!product.userName.startsWith('@')) {
            product.userName = "@" + product.userName
        }
        fragmentProductDetailBinding.product = product
        fragmentProductDetailBinding
            .comLst = CommentListener(
            this, product, view, commentViewModel, activity as MainActivity,
            userViewModel, productViewModel, ratingViewModel, subscriptionViewmodel
        )


        var commentViews = view.findViewById(R.id.comment_items) as RecyclerView
        commentViews.layoutManager = LinearLayoutManager(this.context)
        productViewModel.getCommentForProduct(product.id).observe(this, androidx.lifecycle.Observer { comments ->
            commentViews.adapter = CommentItemAdapter(comments,activity as MainActivity,commentViewModel,view)
        })


        var username =
            activity?.getSharedPreferences("user_login", Context.MODE_PRIVATE)?.getString("username", "unknown")
        if ("@$username" == product.userName) {
            view.edit_btn.visibility = View.VISIBLE
            view.delete_btn.visibility = View.VISIBLE
        }


        ratingViewModel.getRatingByProductId(product.id).observe(this, androidx.lifecycle.Observer { ratings ->
            ratings.let {
                if (ratings.filter { rating -> rating.username == username }.isEmpty()) {
                    ratingViewModel.saveRating(Rating(username ?: "unknown", 0.0, product.id))
                }
            }
        })


        ratingViewModel.getViewersNumber(product.id).observe(this, androidx.lifecycle.Observer { num ->
            num.let { view.view_no.text = num.toString() }
        })


        ratingViewModel.getRatingValue(product.id).observe(this, androidx.lifecycle.Observer { rating_no ->
            rating_no.let {
                if (!rating_no.isNaN()) {
                    view.product_rating.text = rating_no.toString()
                } else {
                    view.product_rating.text = "0.0"
                }
            }
        })

        return view
    }

    companion object {
        fun getInstance(product: Product): ProductDetailFragment {

            var productDetailFragment = ProductDetailFragment()
            var arg = Bundle()
            arg.putSerializable("product", product)
            productDetailFragment.arguments = arg

            return productDetailFragment
        }

        fun validCommentsFeilds(comment: Comment): Boolean {
            if (comment.commentdata.isNullOrEmpty() || comment.dateOfComment.isNullOrEmpty() || comment.userName.isNullOrEmpty()) {
                return false
            }
            return true
        }
    }
}


class CommentListener(
    private var context: ProductDetailFragment, private var product: Product,
    private var view: View, private var commentViewModel: CommentViewModel,
    private val activity: MainActivity, val userViewModel: UserViewModel,
    private val productViewModel: ProductViewModel, var ratingViewmodel: RatingViewmodel,
    private var subscriptionViewmodel: SubscriptionViewmodel
) {

    val comment_field = ObservableField<String>()

    fun buttonClicked() {
        var username =
            activity.getSharedPreferences("user_login", Context.MODE_PRIVATE).getString("username", "unknown")

        var body = view.comment_box.text.toString()
        var date =
            Date().date.toString() + "/" + Date().month + "/" + (Date().year.toInt() + 1900) + " " + (Date().hours % 12) + ":" + Date().minutes
        view.comment_box.setText("")
        var comment = Comment(0, body, date, "@$username")
        if (ProductDetailFragment.validCommentsFeilds(comment))
            commentViewModel.insertComment(comment, product_id = product.id)
    }

    fun navigateToUser() {
        var username = product.userName
        if (product.userName.contains('@')) {
            username = product.userName.substring(1)
        }
        userViewModel.getUserByUsername(username).observe(context, androidx.lifecycle.Observer { userObj ->
            userObj.let {
                var arg = Bundle()
                arg.putSerializable("user", userObj)
                view.findNavController().navigate(R.id.userProfileFragment, arg)
            }
        })
    }

    fun deleteProduct() {
        productViewModel.deleteProduct(product)
        view.findNavController().navigate(R.id.homeFragment)
    }

    fun editProduct() {
        var arg = Bundle()
        arg.putSerializable("product", product)
        view.findNavController().navigate(R.id.editProductFragment, arg)
    }

    fun addRating() {
        var username =
            activity.getSharedPreferences("user_login", Context.MODE_PRIVATE).getString("username", "unknown")
        ratingViewmodel.getRatingByUsername(username ?: "unknown")
            .observe(context, androidx.lifecycle.Observer { ratings ->
                ratings.forEach { rating ->
                    rating.let {
                        if (rating.product_id == product.id && rating.rateNo < 5) {
                            rating.rateNo += 1
                            ratingViewmodel.saveRating(rating)
                        }
                    }
                }
            })
    }

    fun subscribe() {
        var username =
            activity.getSharedPreferences("user_login", Context.MODE_PRIVATE).getString("username", "unknown")
        var subscription = Subscription(subscribed_to = product.userName, username = username)
        subscriptionViewmodel.saveSubscription(subscription)
        Toast.makeText(context.context, "Your Subscription has been Sent.", Toast.LENGTH_SHORT).show()
    }
}