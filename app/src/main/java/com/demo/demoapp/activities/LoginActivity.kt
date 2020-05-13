package com.demo.demoapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demo.demoapp.R
import com.demo.demoapp.databinding.ActivityMainBinding
import com.demo.demoapp.viewmodel.LoginViewModel
import org.json.JSONObject



class LoginActivity : AppCompatActivity() {
    var binding:ActivityMainBinding?=null
    var email:String=""
    var pwd:String=""
    var loginActVm: LoginViewModel?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        loginActVm=ViewModelProviders.of(this).get(LoginViewModel::class.java)


        binding!!.btnLogin.setOnClickListener( View.OnClickListener {

            email=binding?.etEmail?.text.toString()
            pwd=binding?.etPassword?.text.toString()

            if(email.equals("") ||pwd.equals(""))
            {
                Toast.makeText(applicationContext,"Please enter the details",Toast.LENGTH_SHORT).show()
            }

            else
            {

                val paramObject = JSONObject()
                paramObject.put("email",email ) //email  //"eve.holt@reqres.in"
                paramObject.put("password",pwd ) //pwd //"cityslicka"

                Log.e("email"+"nksdnksn",email)
                binding?.uploadProgress?.visibility=View.VISIBLE
                loginActVm?.loginApiCall(paramObject)

            }


        })


        loginActVm?.loginLiveData?.observe(this, Observer {
            binding?.uploadProgress?.visibility=View.GONE
if(it!=null)
{
    if(it.token.equals("QpwL5tke4Pnpja7X4"))
    {
        Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
    }

    else if(it.error.equals("user not found"))
    {
        Toast.makeText(applicationContext,"Login Failed",Toast.LENGTH_SHORT).show()
    }

    else
        Toast.makeText(applicationContext,"Failed",Toast.LENGTH_SHORT).show()

}
else

    Toast.makeText(applicationContext,"Login Failed",Toast.LENGTH_SHORT).show()

        })


    }
}
