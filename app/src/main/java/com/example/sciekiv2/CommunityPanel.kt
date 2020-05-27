package com.example.sciekiv2

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import com.example.sciekiv2.adapter.CommunityAdapter
import com.example.sciekiv2.model.CommunityData
import io.realm.Realm
import io.realm.kotlin.where

class CommunityPanel : AppCompatActivity() {

    private lateinit var realm: Realm
    private lateinit var listView: ListView
    private lateinit var addCommunityToDBButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_panel)
        realm = Realm.getDefaultInstance()

        val communityResults = realm.where<CommunityData>().findAll()

        listView = findViewById(R.id.community_panel_id_list_view)
        val adapter = CommunityAdapter(this, communityResults)
        adapter.notifyDataSetChanged()
        listView.adapter = adapter

        val editTextCommunity = findViewById<EditText>(R.id.community_panel_id_edit_text)

        addCommunityToDBButton = findViewById(R.id.community_panel_id_add_button)
        addCommunityToDBButton.setOnClickListener {
            if (editTextCommunity.text.toString() == "")
                Toast.makeText(this, "Puste pole", Toast.LENGTH_SHORT).show()
            else {
                addCommunityData(editTextCommunity.text.toString())
            }
            //refreshing listView!
            listView.invalidateViews()
        }
    }

    private fun addCommunityData(communityName: String) {
        realm.beginTransaction()
        val communityData = CommunityData()
        communityData.communityName = communityName
        realm.copyToRealmOrUpdate(communityData)
        realm.commitTransaction()
    }
}
