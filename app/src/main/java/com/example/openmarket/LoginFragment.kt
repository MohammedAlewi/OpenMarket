package com.example.openmarket

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import android.text.Editable
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.openmarket.data.User
import com.example.openmarket.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_registration.view.*


class LoginFragment : Fragment() {
    private lateinit var userViewModel : UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        if (arguments!=null){
            var user=arguments?.getSerializable("user") as User
            view.txtEmail.setText(user.username)
            view.txtPwd.setText(user.password)
        }
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.setActivtiy(activity as MainActivity)
        view.btnLogin.setOnClickListener {
            val username=view.txtEmail.text.toString()
            val password=view.txtPwd.text.toString()
            val user=userViewModel.getUserByUsername(username).value as User
            if (user!=null && (user.password==password)) {
                view.findNavController().navigate(R.id.homeFragment,null)
            } else {
                Toast.makeText(this.context,"Password or Username is incorrect",Toast.LENGTH_LONG).show()
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
