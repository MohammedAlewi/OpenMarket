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

    private var products= arrayOf(arrayOf(
            Product(1,"sdfsdf ","sdasd","sdfsdg sdfgsdfg sdfgsdfg sdfsdf",232,23.2, Date().toString()),
            Product(2,"asda","sdfgsdfg ","adsadds sdfgserwe dfg wer sdfsdf",232,23.2, Date().toString()),
            Product(3,"sdfgjkls ","sfdgnkljsdf ","sdfgsdf sdfgsdfg sdfgsdfg sdfsdf",232,23.2, Date().toString()),
            Product(4,"sdfkglsdf fdsg","sdsdfg asd","sdfdf sdfghsldk orehj",232,23.2, Date().toString()),
            Product(5,"fdre","sdfgsdfg ","adsadds sdfgserwe dfg wer sdfsdf",232,23.2, Date().toString()),
            Product(6,"fsreijo ","sfdgnkljsdf ","sdfgsdf sdfgsdfg sdfgsdfg sdfsdf",232,2.0, Date().toString()),
            Product(7,"ieroitowe fdsg","sdsdfg asd","sdfdf sdfghsldk orehj",232,23.2, Date().toString())
    ),arrayOf(
            Product(8,"sdfjgnsdf sdf","sdfg sdf","sdfsdg sdfgsdfg sdfgsdfg sdfsdf",232,23.2, Date().toString()),
            Product(9,"fdsgjksdf, sdf","fg  fds","sdfsdg sdfgsdfg sdfgsdfg sdfsdf",232,2.0, Date().toString()),
            Product(10,"sldkfgsdf sdfg","dsfg sdfg","sdffdsgjksdfsdg sdfgsdfg sdfgsdfg sdfsdf",232,23.2,Date().toString()),
            Product(11,"sdkf fdsgjs","fsdgjkj fsdg","sdfsdg sdfgsdfg sdfgsdfg sdfsdf",232,2.0, Date().toString()),
            Product(12,"fdsg sdf","sdfg sdf","sdfsdg sdfgsdfg sdfgsdfg sdfsdf",232,23.2, Date().toString()),
            Product(13,"we, sdf","fg  fds","sdfsdg sdfgsdfg sdfgsdfg sdfsdf",232,2.0, Date().toString()),
            Product(14,"ioup sdfg","dsfg sdfg","sdffdsgjksdfsdg sdfgsdfg sdfgsdfg sdfsdf",232,2.0, Date().toString()),
            Product(15,"wer fdsgjs","fsdgjkj fsdg","sdfsdg sdfgsdfg sdfgsdfg sdfsdf",232,2.0, Date().toString()),
            Product(16,"olwe fdsgjs","fsdgjkj fsdg","sdfsdg sdfgsdfg sdfgsdfg sdfsdf",232,2.0, Date().toString())

    ),arrayOf(
            Product(17,"sdjfhg erer","dsfjg dsfgj","sdfsdg sdfgsdfg sdfgsdfg sdfsdf",232,2.0, Date().toString()),
            Product(18,"fdslkgs ioeroiew","sdfg dfs","dsfgsdfgkl sdfgsdfg sdfgsdfg sdfsdf",232,2.0, Date().toString()),
            Product(19,"klre ewrwer","sdflkfd sdf","sdfsdg sdfgsdfg sdfgsdfg sdfsdf",232,2.0, Date().toString()),
            Product(20,"sldfkg sdfjg","fdsjgds sdf","sdfsdg sdfgsdfg sdfgsdfg sdfsdf",232,2.0, Date().toString())
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
