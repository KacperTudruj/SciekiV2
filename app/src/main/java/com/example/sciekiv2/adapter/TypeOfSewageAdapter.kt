package com.example.sciekiv2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.sciekiv2.R
import com.example.sciekiv2.Rename
import com.example.sciekiv2.model.TypeOfSewage
import io.realm.Realm
import io.realm.RealmResults

class TypeOfSewageAdapter(context: Context, typeOfSewageDataResult: RealmResults<TypeOfSewage>) :
    BaseAdapter() {
    private lateinit var realm: Realm
    private val context: Context
    private val typeOfSewageDataResult: RealmResults<TypeOfSewage>

    init {
        this.context = context
        this.typeOfSewageDataResult = typeOfSewageDataResult
    }

    //responsible for rendering out each row
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        realm = Realm.getDefaultInstance()

        val layoutInflater = LayoutInflater.from(this.context)
        val row = layoutInflater.inflate(R.layout.adapter_view_community, parent, false)

        val commmunityName = row.findViewById<TextView>(R.id.adapter_name)
        val editResultButton = row.findViewById<Button>(R.id.edit_result_button)
        val deleteResultButton = row.findViewById<Button>(R.id.delete_result_button)

        editResultButton.setOnClickListener {
            val intent = Intent(this.context, Rename()::class.java)

            //sending communityname to edit
            intent.putExtra("fieldNameToEdit", "community")
            intent.putExtra("toEdit", this.typeOfSewageDataResult[position]?.typeOfSewageName)
            intent.putExtra("queryId", this.typeOfSewageDataResult[position]?.id)
            context.startActivity(intent)
        }

        deleteResultButton.setOnClickListener {

            realm.beginTransaction()
            //val oneCommunityResult = communityDataResult
            this.typeOfSewageDataResult[position]?.deleteFromRealm()
            realm.commitTransaction()
            notifyDataSetChanged()
        }

        commmunityName.text = this.typeOfSewageDataResult[position]?.typeOfSewageName
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
        return this.typeOfSewageDataResult.size
    }
}