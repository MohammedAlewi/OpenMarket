package com.example.openmarket


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.openmarket.data.User
import com.example.openmarket.databinding.FragmentUserProfileBinding
import kotlinx.android.synthetic.main.fragment_user_profile.view.*

class UserProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = DataBindingUtil.inflate<FragmentUserProfileBinding>(
            inflater,
            R.layout.fragment_user_profile,
            container,
            false
        )
        var user = arguments?.getSerializable("user") as User?
        var view = binding.root
        binding.user = user

        view.usres_profile_image.setOnClickListener {
            view.findNavController().navigate(R.id.editUserProfileFragment)
        }

        return view
    }

    companion object {
        fun getInstance(user: User): UserProfileFragment {
            val usr = UserProfileFragment()
            var arg = Bundle()
            arg.putSerializable("user", user)
            usr.arguments = arg
            return usr
        }
    }

}
