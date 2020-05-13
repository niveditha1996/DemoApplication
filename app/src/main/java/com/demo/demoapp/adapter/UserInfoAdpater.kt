package com.demo.demoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.demoapp.R
import com.demo.demoapp.model.UserInfoModel
import kotlinx.android.synthetic.main.row_user_info.view.*
import kotlinx.android.synthetic.main.row_user_info.view.tvIdofUser

class UserInfoAdpater(val list : ArrayList<UserInfoModel>, val context: Context) :
    RecyclerView.Adapter<UserInfoAdpater.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInfoAdpater.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_user_info, parent, false))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: UserInfoAdpater.ViewHolder, position: Int) {

        holder.apply {

            list.get(position).let {

             Glide.with(context)
                 .asBitmap()
                 .load(it.avatar)
                 .into(avatar)

                fname.text=it.firstName
                lname.text=it.lastName
                id.text=it.id.toString()
                email.text=it.email

            }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val avatar=view.ivAvatar
        val fname=view.tvFname
        val lname=view.tvLname
        val id=view.tvIdofUser
        val email=view.tvEmail



    }
}