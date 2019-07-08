package com.example.openmarket

//import com.example.openmarket.databinding.FragmentProductDetailBinding
//import com.example.openmarket.databinding.FragmentProductUploadBinding
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.openmarket.data.Product
import com.example.openmarket.data.User
import com.example.openmarket.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_product_upload.*
import kotlinx.android.synthetic.main.fragment_product_upload.view.*
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
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        productViewModel.setActivtiy(activity as MainActivity)
        // var fragmentProductUploadBinding=
        //   DataBindingUtil.inflate<FragmentProductUploadBinding>(inflater, R.layout.fragment_product_upload, container, false)
        //var view=fragmentProductUploadBinding.root
        var userObject = arguments?.getSerializable("user") as User?
        var view = inflater.inflate(R.layout.fragment_product_upload, container, false)
        // fragmentProductUploadBinding.uploadProduct=UploadProductAction(view,userObject,this,productViewModel)

        productName = view.productNameEdit
        description = view.descriptionEdit
        amount = view.amountEdit
        price = view.priceEdit
        uploadBtn = view.uploadBtn
        imageView = view.productImage


        imageView.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, ProductUploadFragment.PERMISSION_CODE)
                } else {
                    pickImage()
                }
            } else {
                pickImage()
            }
        }
        view.uploadBtn.setOnClickListener {
            val product = this.readFeilds()
            product.date = Date().toString()
            product.type = view.typeSpinner.selectedItem.toString()
            // get user object

            if (userObject != null) {

                if (allProductFieldsSpecified(product)) {
                    var userid = userObject.id
                    product.userName = userObject.username
                    productViewModel.insertProduct(product, userid)

                    this.clearFields()
                    view.findNavController().navigate(R.id.homeFragment)

                    val bundle = Bundle()
                    bundle.putSerializable("product", product)
                    Navigation.createNavigateOnClickListener(R.id.action_signupFragment_to_loginFragment, bundle)
                }else{ Toast.makeText(this.context,"fill all required spaces before uploading ",Toast.LENGTH_SHORT).show()}
            } else {
                view.findNavController().navigate(R.id.loginFragment, null)
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
                    PackageManager.PERMISSION_GRANTED
                ) {
                    pickImage()
                } else {
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

        type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val item = adapter.getItem(position)
            }
        }
    }


    fun readFeilds(): Product {
        val product = Product(
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

    fun clearFields() {
        productName.setText("")
        description.setText("")
        amount.setText("")
        price.setText("")
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001

        fun allProductFieldsSpecified(product: Product):Boolean{
            if (product.price==0.0 ||product.amount==0||product.description.isEmpty()||product.name.isEmpty())
                return false
            return true
        }
    }

}
