package com.example.openmarket


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.openmarket.data.Product
import com.example.openmarket.databinding.FragmentEditProductBinding
import com.example.openmarket.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_edit_product.view.*
import java.util.*

class EditProductFragment : Fragment() {
    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        productViewModel.setActivtiy(activity as MainActivity)
        var fragmentEditProductBinding =
            DataBindingUtil.inflate<FragmentEditProductBinding>(
                inflater,
                R.layout.fragment_edit_product,
                container,
                false
            )
        var view = fragmentEditProductBinding.root
        var product = arguments?.getSerializable("product") as Product
        if (!product.userName.startsWith('@')) {
            product.userName = "@" + product.userName
        }
        fragmentEditProductBinding.product = product
        fragmentEditProductBinding.editController = EditController(product, productViewModel, view, this)

        return view
    }

    companion object {
        fun getInstance(product: Product): EditProductFragment {
            var editProductFragment = EditProductFragment()
            var arg = Bundle()
            arg.putSerializable("product", product)
            editProductFragment.arguments = arg
            return editProductFragment
        }

        fun editedProductFields(product: Product): Boolean {
            if (product.name.isNullOrEmpty() || product.amount == 0 || product.price == 0.0 || product.description.isNullOrEmpty())
                return false
            return true
        }
    }

}

class EditController(
    var product: Product,
    var productViewModel: ProductViewModel,
    var view: View,
    var editProductFragment: EditProductFragment
) {
    fun saveChanges() {
        product.name = view.edit_name?.text?.toString() ?: product.name
        product.amount = view.edit_amount?.text?.toString()?.toInt() ?: product.amount
        product.price = view.edit_price?.text?.toString()?.toDouble() ?: product.price
        product.description = view.edit_desc?.text?.toString() ?: product.description
        // update the date .....
        product.date = Date().toString()
        if (EditProductFragment.editedProductFields(product)) {
            productViewModel.updateProduct(product)
            view.findNavController().navigate(R.id.homeFragment)
        } else {
            Toast.makeText(editProductFragment.context, "Please, fill all the required fields", Toast.LENGTH_LONG)
                .show()
        }

    }
}