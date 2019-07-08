package com.example.openmarket


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.openmarket.data.User
import com.example.openmarket.databinding.FragmentEditUserProfileBinding
import com.example.openmarket.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_edit_user_profile.view.*

class EditUserProfileFragment : Fragment() {
    private val userViewModel by lazy { ViewModelProviders.of(this).get(UserViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel.setActivtiy(activity as MainActivity)
        var fragmentEditUserProfileBinding =
            DataBindingUtil.inflate<FragmentEditUserProfileBinding>( inflater,  R.layout.fragment_edit_user_profile, container,false)
        var view = fragmentEditUserProfileBinding.root
        var username =
            activity?.getSharedPreferences("user_login", Context.MODE_PRIVATE)?.getString("username", "unknown") ?:"username"
        var user=userViewModel.getUserByUsername(username)
        fragmentEditUserProfileBinding.userViewModel=userViewModel
        user.observe(this, Observer {
            usr-> usr.let { fragmentEditUserProfileBinding.user=usr ;
            fragmentEditUserProfileBinding.action=UserButtonAction(userViewModel,usr)}
        })
        fragmentEditUserProfileBinding.lifecycleOwner = this
        return view
    }


}

class UserButtonAction(private var userViewModel: UserViewModel,var user:User){
    fun save_edited_changes(){
        println("$-----------------${user.fullName}")
        println("$-----------------${user.email}")
        userViewModel.updateUser(user)
    }
}