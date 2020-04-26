package com.example.sciekiv2

import android.app.ActionBar
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.*
import com.example.sciekiv2.adapter.CommunityAdapter
import com.example.sciekiv2.model.CommunityData
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import io.realm.kotlin.where

class CommunityPanel : AppCompatActivity() {

    private lateinit var realm: Realm
    private lateinit var listView: ListView
    private lateinit var addCommunityToDB: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_panel)

        realm = Realm.getDefaultInstance()
        addCommunity("Gmina Wina")

        addCommunityToDB = findViewById(R.id.add_community_button)

        listView = findViewById<ListView>(R.id.community_list_view)
        listView.setBackgroundColor(Color.parseColor("#FF0000")) //testowy kolorek
        //Customer Adapter
        listView.adapter = CommunityAdapter(this)



        val layoutV11 = findViewById<LinearLayout>(R.id.layout_for_community_panel_data)

        val add_community_button = Button(this)
        add_community_button.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        add_community_button.text = "O cie chuj"
        layoutV11.addView(add_community_button)
        add_community_button.setOnClickListener {
            val numberOfRecords = realm.where<CommunityData>().count()
            val results = realm.where<CommunityData>().findAll()
            //val communityAdapter =
            //val test = RealmResults<CommunityData>()
//            Toast.makeText(this, results.get(0)?.communityName.toString(), Toast.LENGTH_SHORT).show()

            //val communityList = ArrayList<>
            for (i in 0 until numberOfRecords) {
                listView.addView(textViewTEST(results[i.toInt()]?.communityName.toString()))
            }

        }
    }

    private fun addCommunity(communityName: String) {
        realm.beginTransaction()
        val communityData = CommunityData()
        communityData.communityName = communityName
        realm.copyToRealmOrUpdate(communityData)
        realm.commitTransaction()
    }


    private fun textViewTEST(text: String): TextView {
        val dynamic_textView = TextView(this)
        dynamic_textView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dynamic_textView.setTextColor(Color.parseColor("#FFFFFF"))
        dynamic_textView.text = text
        return dynamic_textView
    }
}
