package com.example.openmarket

import android.opengl.Visibility
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.openmarket.data.Product
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*


class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,ProductsItemAdapter.ContentListener{
    private var products= arrayOf(
        arrayOf(
            Product(1,"sdfsdf ","sdasd","sdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfgserwe k",23,90.7, Date().toString(),"sdasd"),
            Product(2,"asda","sdfgsdfg ","adsadds sdfgserwe dfg wer sdfsdf","hjsdfgserwe ",232,23.2, Date().toString(),"sdasd"),
            Product(3,"sdfgjkls ","sfdgnkljsdf ","sdfgsdf sdfgsdfg sdfgsdfg sdfsdf","nmm sdfgserwe",232,23.2, Date().toString(),"sdasd"),
            Product(4,"sdfkglsdf fdsg","sdsdfg asd","sdfdf sdfghsldk orehj","sdfgserwe mhk",232,23.2, Date().toString(),"sdasd"),
            Product(5,"fdre","sdfgsdfg ","adsadds sdfgserwe dfg wer sdfsdf","sdfdf sdfgh sdfgserw esldk orehj",232,23.2, Date().toString(),"sdasd"),
            Product(6,"fsreijo ","sfdgnkljsdf ","sdfgsdf sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd"),
            Product(7,"ieroitowe fdsg","sdsdfg asd","sdfdf sdfghsldk orehj","sdfdf sdfghsldk orehj",232,23.2, Date().toString(),"sdasd")
        ),arrayOf(
            Product(8,"sdfjgnsdf sdf","sdfg sdf","sdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,23.2, Date().toString(),"sdasd"),
            Product(9,"fdsgjksdf, sdf","fg  fds","sdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd"),
            Product(10,"sldkfgsdf sdfg","dsfg sdfg","sdffdsgjksdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,23.2,Date().toString(),"sdasd"),
            Product(11,"sdkf fdsgjs","fsdgjkj fsdg","sdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd"),
            Product(12,"fdsg sdf","sdfg sdf","sdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,23.2, Date().toString(),"sdasd"),
            Product(13,"we, sdf","fg  fds","sdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd"),
            Product(14,"ioup sdfg","dsfg sdfg","sdffdsgjksdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd"),
            Product(15,"wer fdsgjs","fsdgjkj fsdg","sdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd"),
            Product(16,"olwe fdsgjs","fsdgjkj fsdg","sdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd")

        ),arrayOf(
            Product(17,"sdjfhg erer","dsfjg dsfgj","sdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd"),
            Product(18,"fdslkgs ioeroiew","sdfg dfs","dsfgsdfgkl sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd"),
            Product(19,"klre ewrwer","sdflkfd sdf","sdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd"),
            Product(20,"sldfkg sdfjg","fdsjgds sdf","sdfsdg sdfgsdfg sdfgsdfg sdfsdf","sdfdf sdfghsldk orehj",232,2.0, Date().toString(),"sdasd")
        ))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        var navController=Navigation.findNavController(this,R.id.main_content)

        bottom_nav?.let {
            NavigationUI.setupWithNavController(it,navController)
        }


        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_electronics -> {
                    var arg=Bundle()
                    arg.putSerializable("products",products[1])
                    navController.navigate(R.id.productsView,arg)
                    true
                }
                R.id.navigation_car ->{
                    var arg=Bundle()
                    arg.putSerializable("products",products[2])
                    navController.navigate(R.id.productsView,arg)
                    true
                }
                R.id.navigation_cloth ->{
                    var arg=Bundle()
                    arg.putSerializable("products",products[0])
                    navController.navigate(R.id.productsView,arg)
                    true
                }
                R.id.navigation_house ->{
                    var arg=Bundle()
                    arg.putSerializable("products",products[0])
                    navController.navigate(R.id.productsView,arg)
                    true
                }else -> false
            }
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
            .replace(R.id.home_frame,ProductDetailFragment.getInstance(item))
            .addToBackStack(null)
            .commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_upload -> {
                // Handle the camera action
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

}
