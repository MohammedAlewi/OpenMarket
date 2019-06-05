package com.example.openmarket


import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.example.openmarket.data.Product
import com.google.android.material.tabs.TabLayout
import java.util.*


class HomeFragment : Fragment() {

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
    private var categories= arrayOf("Watch","TV","Mobiles")



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view= inflater.inflate(R.layout.fragment_home, container, false)

        var viewPager:ViewPager=view.findViewById(R.id.view_pager_frg)
        var tabLayout:TabLayout=view.findViewById(R.id.tab_layout_frg)
        AsyncTask.execute {
            var productsPagerAdapter=ProductsPagerAdapter(activity?.supportFragmentManager as FragmentManager,products,categories)
            viewPager.adapter=productsPagerAdapter

            tabLayout.setupWithViewPager(viewPager)
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
