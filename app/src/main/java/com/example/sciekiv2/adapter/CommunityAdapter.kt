package com.example.sciekiv2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.sciekiv2.R
import com.example.sciekiv2.Rename
import com.example.sciekiv2.model.CommunityData
import io.realm.Realm
import io.realm.RealmResults

class CommunityAdapter(context: Context, communityDataResult: RealmResults<CommunityData>) :
    BaseAdapter() {

    private lateinit var realm: Realm
    private val context: Context
    private val communityDataResult: RealmResults<CommunityData>

    init {
        this.context = context
        this.communityDataResult = communityDataResult
    }

    //responsible for rendering out each row
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        realm = Realm.getDefaultInstance()

        val layoutInflater = LayoutInflater.from(this.context)
        val row = layoutInflater.inflate(R.layout.adapter_view_community, parent, false)

        val commmunityName = row.findViewById<TextView>(R.id.community_name)
        val editResultButton = row.findViewById<Button>(R.id.edit_result_button)
        val deleteResultButton = row.findViewById<Button>(R.id.delete_result_button)

        editResultButton.setOnClickListener {
            val intent = Intent(this.context, Rename()::class.java)

            //sending communityname to edit
            intent.putExtra("fieldNameToEdit", "community")
            intent.putExtra("toEdit", this.communityDataResult[position]?.communityName)
            intent.putExtra("queryId", this.communityDataResult[position]?.id)
            context.startActivity(intent)
        }

        deleteResultButton.setOnClickListener {

            realm.beginTransaction()
            //val oneCommunityResult = communityDataResult
            this.communityDataResult[position]?.deleteFromRealm()
            realm.commitTransaction()
            notifyDataSetChanged()
        }

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
