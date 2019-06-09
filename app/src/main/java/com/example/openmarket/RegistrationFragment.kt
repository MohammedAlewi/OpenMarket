package com.example.openmarket


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.openmarket.data.User
import com.example.openmarket.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*

class RegistrationFragment : Fragment() {

    private lateinit var userViewModel : UserViewModel

    private lateinit var fullName:EditText
    private lateinit var userName:EditText
    private lateinit var email_addr:EditText
    private lateinit var password:EditText
    private lateinit var confirmPassword:EditText

    private lateinit var signIn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_registration, container, false)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.setActivtiy(activity as MainActivity)
        fullName = view.full_name
        userName = view.user_name
        email_addr = view.email_address
        password = view.password
        confirmPassword = view.confirm_password

        signIn = view.register

        signIn.setOnClickListener {
            val user = readFeilds()
            userViewModel.insertUser(user)
            val bundle = Bundle()
            bundle.putSerializable("user" , user)
            Navigation.createNavigateOnClickListener(R.id.action_registrationFragment_to_loginFragment , bundle)
        }



        return view
    }

    fun readFeilds(): User {
        val user  = User(
                fullName = fullName.text.toString(),
                username = userName.text.toString(),
                email = email_addr.text.toString(),
                password = validPassword(password.text.toString() , confirm_password.text.toString()),
                phoneNo = "",
                pictureId = "",
                locationId = ""
                )
        return user
    }

    private fun validPassword(pass:String , confirmPass:String):String{
        if(pass == confirmPass){
            return pass.hashCode().toString()
        }
        return ""
    }


}
