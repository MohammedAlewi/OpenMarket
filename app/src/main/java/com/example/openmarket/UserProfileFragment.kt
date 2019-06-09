package com.example.openmarket


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.openmarket.data.User
import com.example.openmarket.viewmodel.UserViewModel

import kotlinx.android.synthetic.main.fragment_user_profile.view.*
class UserProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)

        val userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        val user : LiveData<User> = userViewModel.getUserById(0)

        user.observe(this, Observer {
            user-> user.let {
            view.profile_title.text = "Profile("+user.username+")"
            view.full_name.text = user.fullName
            view.user_name.text = user.username
            view.email_address.text = user.email
            view.phone_number.text = user.phoneNo
        } })

        view.logout_button?.setOnClickListener {

        }

        view.edit_button?.setOnClickListener {
            
        }



        return view

    }


}
