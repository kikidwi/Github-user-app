package com.kiki.githubuser.ui.main

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kiki.githubuser.R
import com.kiki.githubuser.ui.detail.FollowerFragment
import com.kiki.githubuser.ui.detail.FollowingFragment

class sectionPagerAdapter(private val mCtx : Context, fm : FragmentManager, data : Bundle): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val fragmentBundle : Bundle

    init {
        fragmentBundle = data
    }

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_1, R.string.tab_2)

    override fun getCount(): Int =2

    override fun getItem(position: Int) : Fragment {
        var fragment : Fragment? = null
        when(position){
            0 -> fragment = FollowerFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence{
        return mCtx.resources.getString(TAB_TITLES[position])
    }

}