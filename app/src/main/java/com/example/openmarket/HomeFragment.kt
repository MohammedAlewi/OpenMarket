package com.example.openmarket


import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager
import com.example.openmarket.data.Product
import com.example.openmarket.viewmodel.ProductViewModel
import com.example.openmarket.viewmodel.UserViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var productViewModel:ProductViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view= inflater.inflate(R.layout.fragment_home, container, false)
        var navController= Navigation.findNavController(activity as MainActivity,R.id.main_content)
        view.bottom_nav?.let {
            NavigationUI.setupWithNavController(it,navController)
        }
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        productViewModel.setActivtiy(activity as MainActivity)
        var empty=productViewModel.products.value?.isEmpty() as Boolean
        if (empty){
            childFragmentManager.beginTransaction().replace(R.id.home_framelayout,ProductsView.newInstance("none")).commit()
        }else{
            childFragmentManager.beginTransaction().replace(R.id.home_framelayout,ProductsView.newInstance("all")).commit()
        }

        view.bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_electronics -> {
                    var arg=Bundle()
                    var electornics=productViewModel.products.value?.filter { product -> product.type=="electronics"  }
                    arg.putSerializable("products","electronics")
                    navController.navigate(R.id.productsView,arg)
//                    Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_productsView , arg)
                    true
                }
                R.id.navigation_car ->{
                    var arg=Bundle()
                    arg.putSerializable("products","car")
                    navController.navigate(R.id.productsView,arg)
//                    Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_productsView , arg)
                    true
                }
                R.id.navigation_cloth ->{
                    var arg=Bundle()
                    arg.putSerializable("products","cloth")
                    navController.navigate(R.id.productsView,arg)
//                    Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_productsView , arg)

                    true
                }
                R.id.navigation_house ->{
                    var arg=Bundle()
                    arg.putSerializable("products","house")
//                    Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_productsView , arg)
                    navController.navigate(R.id.productsView,arg)
                    true
                }else -> false
            }

        }

        return view
    }


    companion object{
        fun getInstance():HomeFragment{
            var homeFragment=HomeFragment()

            return homeFragment;
        }
    }

}
