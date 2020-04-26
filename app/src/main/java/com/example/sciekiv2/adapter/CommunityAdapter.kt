package com.example.sciekiv2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sciekiv2.R

class CommunityAdapter(context: Context, arrayList: ArrayList<String>) : BaseAdapter() {

    private val context: Context
    private val arrayList: ArrayList<String>
    private var arrayListSize: Int = 0

    init {
        this.context = context
        this.arrayList = arrayList
        this.arrayListSize = arrayList.size
    }

    //responsible for rendering out each row
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(this.context)
        val row =layoutInflater.inflate(R.layout.adapter_view_community, parent, false)

        val commmunityName = row.findViewById<TextView>(R.id.community_name)
        commmunityName.text = "${arrayList[position]}"
        return row
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    //responsable for how many  rows in my list
    override fun getCount(): Int {
        return arrayListSize
    }

}
