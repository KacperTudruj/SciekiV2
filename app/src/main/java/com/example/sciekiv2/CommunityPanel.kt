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
        addCommunity("Gdzieś tam daleko w lublinie")
        addCommunity("No a jak gmina może się nazywać?")
        addCommunity("Jastków")

        val numberOfRecords = realm.where<CommunityData>().count()
        val communityResults = realm.where<CommunityData>().findAll()
        addCommunityToDB = findViewById(R.id.add_community_button)

        //Array list for Addapter
        val communityList = ArrayList<String>()
        for(i in 0 until numberOfRecords){
            communityList.add(communityResults.get(i.toInt())?.communityName.toString())
        }
        listView = findViewById<ListView>(R.id.community_list_view)
        listView.adapter = CommunityAdapter(this, communityList)
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
