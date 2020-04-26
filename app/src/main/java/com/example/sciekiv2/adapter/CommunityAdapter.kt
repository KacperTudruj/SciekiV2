package com.example.sciekiv2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.sciekiv2.R
import com.example.sciekiv2.model.CommunityData
import io.realm.RealmResults

class CommunityAdapter(context: Context, communityDataResult: RealmResults<CommunityData>) : BaseAdapter() {

    private val context: Context
    private val communityDataResult: RealmResults<CommunityData>

    init {
        this.context = context
        this.communityDataResult = communityDataResult
    }

    //responsible for rendering out each row
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(this.context)
        val row = layoutInflater.inflate(R.layout.adapter_view_community, parent, false)

        val commmunityName = row.findViewById<TextView>(R.id.community_name)
        commmunityName.text = this.communityDataResult[position]?.communityName
        return row
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //responsable for how many  rows in my list
    override fun getCount(): Int {
        return this.communityDataResult.size
    }

}
