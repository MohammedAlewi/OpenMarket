package com.example.openmarket


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_entry.view.*

class Entry : Fragment() {

    private lateinit var loginButton : Button
    private lateinit var signupButton : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_entry, container, false)

        loginButton = view.login
        signupButton = view.signup

        loginButton.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_entry_to_loginFragment)
        }

        signupButton.setOnClickListener {
            Navigation.createNavigateOnClickListener(R.id.action_entry_to_registrationFragment)
        }

        return view
    }


}
