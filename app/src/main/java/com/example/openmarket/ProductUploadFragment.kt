package com.example.openmarket

import android.content.ClipDescription
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.openmarket.data.Product
import com.example.openmarket.data.User
import com.example.openmarket.viewmodel.ProductViewModel
import com.example.openmarket.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_product_upload.*
import kotlinx.android.synthetic.main.fragment_product_upload.view.*
import kotlinx.android.synthetic.main.fragment_signup.*


class ProductUploadFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel

    private lateinit var productName: EditText
    private lateinit var description: EditText
    private lateinit var amount: EditText
    private lateinit var price: EditText
    private lateinit var uploadBtn: Button

    private var listener: OnFragmentInteractionListener? = null

    var product_types = arrayOf("Electronics", "Cloth", "Car", "House")
    private lateinit var type: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_upload, container, false)

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)

        productName = view.productNameEdit
        description = view.descriptionEdit
        amount = view.amountEdit
        price = view.priceEdit
        uploadBtn = view.uploadBtn

        uploadBtn.setOnClickListener {
            val product = readFeilds()
            productViewModel.insertProduct(product,0)
            val bundle = Bundle()
            bundle.putSerializable("product", product)
            Navigation.createNavigateOnClickListener(R.id.action_signupFragment_to_loginFragment, bundle)
        }

        return view

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

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
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

}
