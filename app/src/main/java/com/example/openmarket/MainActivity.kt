package com.example.openmarket

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.openmarket.R.layout.activity_main
import com.example.openmarket.data.Product
import com.example.openmarket.data.User
import com.example.openmarket.viewmodel.UserViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener, ProductsItemAdapter.ContentListener {
    var currentUser: User? = null
    private lateinit var controller: NavController
    private lateinit var sheardPref: SharedPreferences
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        var navController = Navigation.findNavController(this, R.id.main_content)

        setupNavigationMenu(navController)
        Navigation.setViewNavController(fab, navController)
        fab.setOnClickListener {
            navController.navigate(R.id.productUploadFragment)
            //it.findNavController().navigate(R.id.productUploadFragment)
            //supportFragmentManager.beginTransaction().replace(R.id.home_framelayout,ProductUploadFragment()).commit()
        }
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.setActivtiy(this)

        sheardPref = getSharedPreferences("user_login", Context.MODE_PRIVATE)
        var username = sheardPref.getString("username", null)

        if (username != null) {

            var user: User? = null// as User
            userViewModel.getUserByUsername(username).observe(this, androidx.lifecycle.Observer { userObj ->
                userObj.let {
                    user = userObj
                    nav_view.getHeaderView(0).findViewById<TextView>(R.id.main_username).text = userObj.username
                    nav_view.getHeaderView(0).findViewById<TextView>(R.id.main_fullname).text = userObj.fullName
                }
            })

            currentUser = user

            controller = navController
            var profile = nav_view.getHeaderView(0).findViewById<ImageView>(R.id.userProfile)
            profile.setOnClickListener {
                var arg = Bundle()
                arg.putSerializable("user", user)
                navController.navigate(R.id.userProfileFragment, arg)
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            fab.setOnClickListener {
                var arg = Bundle()
                arg.putSerializable("user", user)
                navController.navigate(R.id.productUploadFragment, arg)
            }
            navController.navigate(R.id.homeFragment, null)
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }


    override fun onItemClicked(item: Product) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.home_frame, ProductDetailFragment.getInstance(item))
            .addToBackStack(null)
            .commit()

    }

    fun isConnected(): Boolean {

        var connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_upload -> {
            }
            R.id.nav_subscription -> {
                println("--------------subscription hit")
                Toast.makeText(this, "Subscriptions has been clicked", Toast.LENGTH_LONG).show()
                var arg = Bundle()
                arg.putSerializable("products", "subscriptions")
                controller.navigate(R.id.productsView, arg)
            }
            R.id.nav_setting -> {

            }
            // description
            R.id.nav_share -> {

            }
            R.id.nav_about -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setupNavigationMenu(navController: NavController) {
        val sideNavView = findViewById<NavigationView>(R.id.nav_view)
        sideNavView?.setupWithNavController(navController)

    }


}
