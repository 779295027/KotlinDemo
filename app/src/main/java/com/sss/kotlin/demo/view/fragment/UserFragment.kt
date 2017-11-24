package com.sss.kotlin.demo.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sss.kotlin.demo.R
import com.sss.kotlin.demo.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private var binding: FragmentUserBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        return binding!!.root
    }



}
