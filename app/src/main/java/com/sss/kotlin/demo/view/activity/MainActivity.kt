package com.sss.kotlin.demo.view.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sss.kotlin.demo.R
import com.sss.kotlin.demo.databinding.ActivityMainBinding
import com.sss.kotlin.demo.db.entity.UserEntity
import com.sss.kotlin.demo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        button.setOnClickListener { startActivity(Intent(applicationContext, ImageActivity::class.java)) }
        button2.setOnClickListener { startActivity(Intent(applicationContext, TabActivity::class.java)) }
        binding!!.sdkVer = 1
        val model = UserViewModel(application)
        model.userLiveData!!.observe(this, Observer<UserEntity> { userEntity ->
            binding!!.user = userEntity
            binding!!.invalidateAll()
        })


    }
}
