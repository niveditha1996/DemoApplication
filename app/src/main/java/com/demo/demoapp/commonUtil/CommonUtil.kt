package com.demo.demoapp.commonUtil

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.demo.demoapp.R

class CommonUtil {

    companion object{

        var activeFragmentTag = ""


        fun launchFragment(tag: String, isBackActive: Boolean, fragmentManager: FragmentManager, activeFragment: Fragment) {
            try {
                // App.homeActivity?.isBackStack=isBackActive
                Log.e("launchedFragment: ", tag)
                if (fragmentManager.findFragmentByTag(tag) != null) {
                    activeFragmentTag = tag
                    if (fragmentManager.findFragmentByTag(tag)!!.isVisible) {
                        Log.e("launchedFragment: ", "returned")
                        return
                    }
                }
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragmentContainer, activeFragment, tag)
                if (isBackActive)
                {

                    fragmentTransaction.addToBackStack(null)

                }
                else{

                }
                fragmentTransaction.commit()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }

    }


}