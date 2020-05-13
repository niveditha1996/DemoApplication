package com.demo.demoapp.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demo.demoapp.R
import com.demo.demoapp.commonUtil.CommonUtil
import com.demo.demoapp.model.UserInfoModel
import com.demo.demoapp.viewmodel.UserInfoViewModel
import com.demo.fragments.EmailFragment
import com.google.gson.Gson
import java.util.*


class UserActivity : AppCompatActivity() {

    var userActViewModel: UserInfoViewModel?=null
    var isFirst=true
    var noOfPages=0
    var pageCount=2
    val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        userActViewModel= ViewModelProviders.of(this).get(UserInfoViewModel::class.java)

        if(isFirst==true)
        {
            isFirst=false
            userActViewModel?.apiCallforUserInfo(noOfPages)

        }
        isFirst=false

        userActViewModel?.userInfoLiveData?.observe(this, Observer {
            if(it!=null)
            {
                noOfPages= it.totalPages!!
                isFirst=false

                for (i in it?.data!!){
                    userActViewModel?.insert(i)
                }

                Log.e("noofpagesin",noOfPages.toString())

            }else
                Toast.makeText(applicationContext,"Failure", Toast.LENGTH_SHORT).show()


            while (pageCount <= noOfPages) {
                userActViewModel?.apiCallforUserInfo(pageCount)
                pageCount++
            }

        })
        Log.e("noofpages",noOfPages.toString())


        userActViewModel?.getAllUsers()?.observe(this,
            Observer<List<UserInfoModel>> {
                    t ->
                if(t!=null)
                {
                    var fragment=EmailFragment()
                    val gson= Gson()
                    var list=gson.toJson(t)
                    var arraylist= arrayOf(t)
                    Log.e("emailHere",""+t?.size!!) //pass t to adapter
                    bundle.putSerializable("userdata",t as ArrayList<UserInfoModel>)
                    fragment.arguments=bundle
                    CommonUtil.launchFragment("emailFragment",false,supportFragmentManager,fragment)
                }


            })


    }




}




