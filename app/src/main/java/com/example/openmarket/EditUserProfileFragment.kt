package com.example.openmarket


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
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
            fragmentEditUserProfileBinding.action=UserButtonAction(userViewModel,usr,view)}
        })
        fragmentEditUserProfileBinding.lifecycleOwner = this
        return view
    }

    companion object{
        fun checkPasswordMatch(password:String,repassword:String):Boolean{
            return password==repassword
        }
    }

}

class UserButtonAction(private var userViewModel: UserViewModel,var user:User,var view: View){
    fun save_edited_changes(){
        user.fullName=view.full_name_box.text.toString()
        user.email=view.email_box_text.text.toString()
        user.phoneNo=view.phone_no_text.text.toString()
        user.password=view.pass_box_text.text.toString()
        var repasssword=view.repass_box_text.text.toString()
        if (EditUserProfileFragment.checkPasswordMatch(user.password,repasssword)){
            userViewModel.updateUser(user)
            var arg=Bundle()
            arg.putSerializable("user",user)
            view.findNavController().navigate(R.id.userProfileFragment,arg)
        }else{
            Toast.makeText(view.context,"Please Enter a matching password.",Toast.LENGTH_SHORT).show()
        }
    }
}