package com.purplepotato.gmccareadmin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(supportFragmentManager : FragmentManager) : FragmentPagerAdapter(supportFragmentManager){

    private var mFragmentList = ArrayList<Fragment>()
    private var mFragmentListTitle = ArrayList<String>()

    override fun getCount(): Int {
        return mFragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentListTitle[position]
    }

    fun addFragment(fragment : Fragment, title:String){
        mFragmentList.add(fragment)
        mFragmentListTitle.add(title)
    }

}