package com.sss.kotlin.demo.view.activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.sss.kotlin.demo.R
import com.sss.kotlin.demo.databinding.ActivityTabBinding
import com.sss.kotlin.demo.view.fragment.UserFragment

class TabActivity : AppCompatActivity() {
    private var fragmentone: Fragment? = null;
    private var binding: ActivityTabBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityTabBinding>(this, R.layout.activity_tab)
        fragmentone = UserFragment()
        changeFragment(fragmentone!!)
    }


    fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame_layout, fragment, fragment.javaClass.simpleName)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
