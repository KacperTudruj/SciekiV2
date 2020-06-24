package com.example.sciekiv2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.sciekiv2.R
import com.example.sciekiv2.model.SewageDisposalData
import io.realm.Realm
import io.realm.RealmResults
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class StoredSewageData(context: Context, sewageDisposalData: RealmResults<SewageDisposalData>) :
    BaseAdapter() {

    private lateinit var realm: Realm
    private val context: Context
    private val sewageDisposalData: RealmResults<SewageDisposalData>

    init {
        this.context = context
        this.sewageDisposalData = sewageDisposalData
    }

    //responsible for rendering out each row
    @SuppressLint("SimpleDateFormat")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        realm = Realm.getDefaultInstance()

        val layoutInflater = LayoutInflater.from(this.context)
        val row = layoutInflater.inflate(R.layout.adapter_stored_sewage_data, parent, false)

        val storedAdress = row.findViewById<TextView>(R.id.adapter_stored_adress)
        val storedCommunity = row.findViewById<TextView>(R.id.adapter_stored_community)
        val storedSewage = row.findViewById<TextView>(R.id.adapter_stored_sewage_type)
        val storedQuantityOfSewage = row.findViewById<TextView>(R.id.adapter_stored_quantity_of_sewage)
        val storedDate = row.findViewById<TextView>(R.id.adapter_stored_date)
        val adapterDeleteSewageButton = row.findViewById<Button>(R.id.adapter_delete_sewage_button)

        adapterDeleteSewageButton.setOnClickListener {
            realm.beginTransaction()
            this.sewageDisposalData[position]?.deleteFromRealm()
            realm.commitTransaction()
            notifyDataSetChanged()
        }

        storedAdress.text = this.sewageDisposalData[position]?.adress
        storedCommunity.text = this.sewageDisposalData[position]?.community
        storedSewage.text = this.sewageDisposalData[position]?.type_of_sewage
        storedQuantityOfSewage.text = this.sewageDisposalData[position]?.quantity_of_sewage.toString()

        val date = this.sewageDisposalData[position]?.createdAt
        storedDate.text = "${date?.dayOfMonth}.${date?.monthValue}.${date?.year}"

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
        return this.sewageDisposalData.size
    }


}
