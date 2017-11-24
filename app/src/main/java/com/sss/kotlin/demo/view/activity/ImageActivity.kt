package com.sss.kotlin.demo.view.activity

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sss.kotlin.demo.R
import com.sss.kotlin.demo.databinding.ActivityImageBinding
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    private var binding: ActivityImageBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityImageBinding>(this, R.layout.activity_image)
        button.setOnClickListener { Glide.with(this@ImageActivity).load("https://i.imgur.com/aZfviZp.jpg").into(img) }

        text_input_edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text!!.length != 6)
                    text_input_edit_text.error = "密码错误"
            }

        })


        edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text!!.length != 3) {
                    text_input_layout.error = "用户名错误"
                } else
                    text_input_layout.isErrorEnabled = false
            }

        })

    }


    fun showToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

}
