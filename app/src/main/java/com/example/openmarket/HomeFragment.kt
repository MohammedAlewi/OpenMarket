package com.example.openmarket


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.openmarket.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    private lateinit var productViewModel: ProductViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        var navController = Navigation.findNavController(activity as MainActivity, R.id.main_content)
        view.bottom_nav?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        productViewModel.setActivtiy(activity as MainActivity)

        productViewModel.getAllProducts().observe(this, Observer { products ->
            products.let {
                if (products.isEmpty()) {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.home_framelayouts, ProductsView.newInstance("none")).commit()
                } else {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.home_framelayouts, ProductsView.newInstance("any")).commit()
                }
            }
        })


        view.bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_electronics -> {
                    var arg = Bundle()
                    arg.putSerializable("products", "Electronics")
                    navController.navigate(R.id.productsView, arg)
//                    Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_productsView , arg)
                    true
                }
                R.id.navigation_car -> {
                    var arg = Bundle()
                    arg.putSerializable("products", "Car")
                    navController.navigate(R.id.productsView, arg)
//                    Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_productsView , arg)
                    true
                }
                R.id.navigation_cloth -> {
                    var arg = Bundle()
                    arg.putSerializable("products", "Cloth")
                    navController.navigate(R.id.productsView, arg)
//                    Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_productsView , arg)

                    true
                }
                R.id.navigation_house -> {
                    var arg = Bundle()
                    arg.putSerializable("products", "House")
//                    Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_productsView , arg)
                    navController.navigate(R.id.productsView, arg)
                    true
                }
                else -> false
            }

        }

        return view
    }


    companion object {
        fun getInstance(): HomeFragment {
            var homeFragment = HomeFragment()

            return homeFragment
        }
    }

}
