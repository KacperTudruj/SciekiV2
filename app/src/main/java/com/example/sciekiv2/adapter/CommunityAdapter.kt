package com.example.sciekiv2.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CommunityAdapter(context: Context) : BaseAdapter() {

    private val mContext:Context
    init {
        mContext = context
    }

    //responsible for rendering out each row
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView = TextView(mContext)
        textView.text = "Testowy tekst Adaptera"
        return textView
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    //responsable for how many  rows in my list
    override fun getCount(): Int {
        return 3
    }

}
