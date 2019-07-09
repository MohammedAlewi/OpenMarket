package com.example.openmarket


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.openmarket.data.User
import com.example.openmarket.databinding.FragmentLoginBinding
import com.example.openmarket.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.setActivtiy(activity as MainActivity)

        var fragmentLoginBinding =
            DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        var view = fragmentLoginBinding.root
        fragmentLoginBinding.loginObject = LoginBinding(this, view, userViewModel)

        if (arguments != null) {
            var user = arguments?.getSerializable("user") as User
            view.txtEmail.setText(user.username)
            view.txtPwd.setText(user.password)

//            with((activity?.getSharedPreferences("user_login", Context.MODE_PRIVATE) as SharedPreferences).edit()) {
//                putString("username", user.username)
//                apply()
//            }

        }



        return view
    }

    companion object {
        fun getInstance(): LoginFragment {
            var loginFragment = LoginFragment()

            return loginFragment
        }

        fun isPasswordValid(username: String, password: String): Boolean {
            if (username.isEmpty() || password.isEmpty())
                return false
            return true
        }
    }
}

class LoginBinding(var loginFragment: LoginFragment, var view: View, var userViewModel: UserViewModel) {
    fun login() {
        val username = view.txtEmail.text.toString()
        val password = view.txtPwd.text.toString()
        if (LoginFragment.isPasswordValid(username, password)) {
            val login = userViewModel.login(username, password)
            if (login == null) {
                Toast.makeText(loginFragment.context, "There is No Connection", Toast.LENGTH_LONG).show()
            } else {
                if (login == true) {
//                    val user = userViewModel.getUserByUsername(username).value as User
//
//                    val arg = Bundle()
//                    arg.putSerializable("user", user)
//
//                    (loginFragment.activity as MainActivity).currentUser = user

                    with(
                        (loginFragment.activity?.getSharedPreferences(
                            "user_login",
                            Context.MODE_PRIVATE
                        ) as SharedPreferences).edit()
                    ) {
                        putString("username", username)
                        apply()
                    }
                    view.findNavController().navigate(R.id.homeFragment, null)
                } else {
                    Toast.makeText(loginFragment.activity, "Password or Username is incorrect", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

    }
}
