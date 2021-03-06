package com.demo.demoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.demoapp.adapter.EmailAdapter
import com.demo.demoapp.commonUtil.CommonUtil
import com.demo.demoapp.R
import com.demo.demoapp.databinding.FragmentForEmailIdBinding
import com.demo.demoapp.roomdatabase.model.UserInfoModel


class EmailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding: FragmentForEmailIdBinding? = null
        var dataList = ArrayList<UserInfoModel>()
        var bundle = Bundle()

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_for_email_id, container, false)

        if (arguments != null) {
            dataList = arguments!!.getSerializable("userdata") as ArrayList<UserInfoModel>
        }

        val recyclerView = binding?.rvEmail
        val adapter = EmailAdapter(dataList, context!!)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = adapter

        binding?.tvUserCount?.text = dataList.size.toString()

        adapter.setEmailListener(object : EmailAdapter.EmailClickListener {
            override fun EmailClick(position: Int) {
                var userInfoFragment = UserInfoFragment()

                bundle.putString("avatar", dataList[position].avatar)
                bundle.putString("email", dataList[position].email)
                bundle.putString("firstname", dataList[position].firstName)
                bundle.putString("lastname", dataList[position].lastName)
                bundle.putInt("id", dataList[position].id)
                var list = dataList!![position]
                bundle.putSerializable("datalist", list as UserInfoModel)

                userInfoFragment.arguments = bundle
                CommonUtil.launchFragment(
                    "UserInfoFragment",
                    true,
                    fragmentManager!!,
                    userInfoFragment
                )

            }

        })

        return binding?.root

    }

}
