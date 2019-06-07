package com.example.openmarket


import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.openmarket.data.User
import com.example.openmarket.repository.UserRepository
import com.example.openmarket.viewmodel.UserViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*
import kotlinx.android.synthetic.main.fragment_signup.view.*
import java.util.jar.Manifest
import kotlinx.android.synthetic.main.fragment_registration.view.user_profile_image as user_profile_image1

class SignupFragment : Fragment() {

    private lateinit var userViewModel : UserViewModel

    private lateinit var fullName:EditText
    private lateinit var userName:EditText
    private lateinit var email_addr:EditText
    private lateinit var password:EditText
    private lateinit var confirmPassword:EditText
    private lateinit var phoneNumber: EditText

    private lateinit var imageView: ImageView

    private lateinit var signUp: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_signup, container, false)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        fullName = view.fullNameEdit
        userName = view.usernameEdit
        email_addr = view.emailEdit
        password = view.passwordEdit
        confirmPassword = view.confirmPasswordEdit
        phoneNumber = view.phoneNoEdit

        imageView = view.user_profile_image

        imageView.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(requireContext(),
                        android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                }else{
                    pickImage()
                }
            }else{
                pickImage()
            }
        }



        signUp = view.signupBtn

        signUp.setOnClickListener {
            val user = readFeilds()
            userViewModel.insertUser(user)
            val bundle = Bundle()
            bundle.putSerializable("user" , user)
            Navigation.createNavigateOnClickListener(R.id.action_registrationFragment_to_loginFragment , bundle)
        }



        return view
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    pickImage()
                }
                else {
                    Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageView.setImageURI(data?.data)
        }
    }

    fun readFeilds(): User {
        val user  = User(
            fullName = fullName.text.toString(),
            username = userName.text.toString(),
            email = email_addr.text.toString(),
            password = validPassword(password.text.toString() , confirm_password.text.toString()),
            phoneNo = phoneNumber.text.toString(),
            pictureId = "",
            locationId = ""
        )
        return user
    }

    private fun validPassword(pass:String , confirmPass:String):String {
        if (pass == confirmPass) {
            return pass.hashCode().toString()
        }
        return ""

    }

    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }


}
