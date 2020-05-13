package com.demo.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.demo.demoapp.R
import com.demo.demoapp.databinding.FragmentUserInfoBinding
import com.demo.demoapp.model.UserInfoModel
import java.io.InputStream
import java.net.URL


class UserInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        var avatar=""
        var email=""
        var fname=""
        var lname=""
        var id=0
        var binding:FragmentUserInfoBinding?=null

        binding= DataBindingUtil.inflate( inflater , R.layout.fragment_user_info, container, false)

        if(arguments!=null)
        {
            avatar= arguments?.getString("avatar") as String
            fname= arguments?.getString("firstname") as String
            lname= arguments?.getString("lastname") as String
            id= arguments?.getInt("id")!!
            email= arguments?.getString("email") as String

        }

        Glide.with(context!!)
            .asBitmap()
            .load(avatar)
            .into(binding.ivAvatar)

        binding?.tvEmail?.text=email
        binding?.tvFname?.text=fname
        binding?.tvLname?.text=lname
        binding?.tvIdofUser?.text=id.toString()

        return binding!!.root
    }

}
