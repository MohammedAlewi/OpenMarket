package com.example.openmarket

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import android.text.Editable
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*



class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Set an error if the password is less than 8 characters.
        view.btnLogin.setOnClickListener {
            if (!isPasswordValid(txtPwd.text)) {
               // txtPwd.error = getString(R.string.shr_error_password)
            } else {
                txtPwd.error = null // Clear the error
               // (activity as NavigationHost).navigateTo(ProductGridFragment(), false) // Navigate to the next Fragment
            }
        }

        // Clear the error once more than 8 characters are typed.
        view.txtPwd.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(txtPwd.text)) {
                txtPwd.error = null //Clear the error
            }
            false
        }
        return view
    }

    companion object{
        fun getInstance():LoginFragment{
            var loginFragment=LoginFragment()

            return loginFragment;
        }
    }


    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }


}
