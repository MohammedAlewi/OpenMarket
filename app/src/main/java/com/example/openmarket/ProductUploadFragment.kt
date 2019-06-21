package com.example.openmarket

import android.app.Activity
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.openmarket.data.Product
import com.example.openmarket.data.User
import com.example.openmarket.viewmodel.ProductViewModel
import com.example.openmarket.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_product_upload.*
import kotlinx.android.synthetic.main.fragment_product_upload.view.*
import kotlinx.android.synthetic.main.fragment_signup.*
import java.util.*


class ProductUploadFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel

    private lateinit var productName: EditText
    private lateinit var description: EditText
    private lateinit var amount: EditText
    private lateinit var price: EditText
    private lateinit var uploadBtn: Button
    private lateinit var imageView: ImageView


    var product_types = arrayOf("Electronics", "Cloth", "Car", "House")
    private lateinit var type: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_upload, container, false)

        var userObject=arguments?.getSerializable("user") as User?

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)

        productViewModel.setActivtiy(activity as MainActivity)

        productName = view.productNameEdit
        description = view.descriptionEdit
        amount = view.amountEdit
        price = view.priceEdit
        uploadBtn = view.uploadBtn
        imageView = view.productImage

        uploadBtn.setOnClickListener {

            val product = readFeilds()
            product.date=Date().toString()
            product.type="car"
            // get user object

            if (userObject !=null){
                var userid=userObject.id
                product.userName=userObject.username

                productViewModel.insertProduct(product,userid)

                val bundle = Bundle()
                bundle.putSerializable("product", product)
                Navigation.createNavigateOnClickListener(R.id.action_signupFragment_to_loginFragment, bundle)
            }else{  view.findNavController().navigate(R.id.loginFragment,null)}

        }

        imageView.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(requireContext(),
                        android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, ProductUploadFragment.PERMISSION_CODE)
                }else{
                    pickImage()
                }
            }else{
                pickImage()
            }
        }

        return view

    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, ProductUploadFragment.IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            ProductUploadFragment.PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    pickImage()
                }
                else {
                    Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == ProductUploadFragment.IMAGE_PICK_CODE) {
            imageView.setImageURI(data?.data)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        type = view.typeSpinner


        val adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, product_types)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        typeSpinner!!.adapter = adapter

        type.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val item = adapter.getItem(position)
            }
        }
    }


    fun readFeilds(): Product {
        val product  = Product(
            name = productName.text.toString(),
            description = description.text.toString(),
            amount = amount.text.toString().toInt(),
            price = price.text.toString().toDouble(),
            date = "",
            userName = "",
            imagePath = "",
            type = ""
        )
        return product
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

}
