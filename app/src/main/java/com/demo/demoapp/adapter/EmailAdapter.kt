package com.demo.demoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.R
import com.demo.demoapp.model.UserInfoModel
import kotlinx.android.synthetic.main.row_email.view.*

class EmailAdapter(val list : ArrayList<UserInfoModel>, val context: Context) :
    RecyclerView.Adapter<EmailAdapter.ViewHolder>() {

    var listener1:EmailClickListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_email, parent, false))
    }

    override fun getItemCount(): Int {
       return  list.size
    }

    override fun onBindViewHolder(holder: EmailAdapter.ViewHolder, position: Int) {

        holder.apply {

            list.get(position).let {

                email.text=it.email
            }

            itemView.setOnClickListener {
             listener1?.EmailClick(position)
            }
        }
    }

    fun setEmailListener(listener: EmailClickListener)
    {
        listener1=listener
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val email=view.tvIdofUser


    }

    interface EmailClickListener
    {
        fun EmailClick(position: Int)
    }

}