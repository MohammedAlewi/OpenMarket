package com.example.openmarket

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.opengl.Visibility
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.openmarket.data.Product
import com.example.openmarket.data.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.util.*


class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,ProductsItemAdapter.ContentListener{
    var currentUser:User?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_entry2_to_productUploadFragment , null))

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        var navController=Navigation.findNavController(this,R.id.main_content)

        if (currentUser!=null){
            var arg=Bundle()
            arg.putSerializable("user",currentUser)
            userProfile.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_entry2_to_userProfileFragment, arg))
        }

        setupNavigationMenu(navController)


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
            .replace(R.id.home_frame,ProductDetailFragment.getInstance(item))
            .addToBackStack(null)
            .commit()

    }

    fun isConnected():Boolean {

        var connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)  as  ConnectivityManager
        var networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected

    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_upload -> {
            }
            R.id.nav_subscription -> {

            }
            R.id.nav_setting -> {

            }
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
