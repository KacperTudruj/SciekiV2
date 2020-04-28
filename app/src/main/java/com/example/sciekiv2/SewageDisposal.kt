package com.example.sciekiv2

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.*
import com.example.sciekiv2.model.CommunityData
import com.example.sciekiv2.model.SewageDisposalData
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where

class SewageDisposal : AppCompatActivity() {
    private lateinit var saveSewageButton: Button
    private lateinit var spinnerCommunity: Spinner
    private lateinit var realm: Realm

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sewage_disposal)

        realm = Realm.getDefaultInstance()

        saveSewageButton = findViewById(R.id.add_sewage_disposal_data_button)
        spinnerCommunity = findViewById(R.id.community_spinner)

        val communityDataBaseResults = realm.where<CommunityData>().findAll()
        val communityList = ArrayList<String>()

        if (communityDataBaseResults.isEmpty()) {
            saveSewageButton.isEnabled = false
            saveSewageButton.text = "X"
            communityList.add(getText(R.string.community_list_is_empty) as String)
        } else {
            for (community in realm.where<CommunityData>().findAll()) {
                communityList.add(community?.communityName.toString())
            }
        }

        spinnerCommunity.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, communityList)

        saveSewageButton.setOnClickListener {
            addSewage("Adres", "Gmina", "Rodzaj", 10.5f)
            val intent = Intent(this, ControlPanelActivity::class.java)
            startActivity(intent);
        }

    }

    private fun addSewage(
        adress: String,
        community: String,
        type_of_sewage: String,
        quantity_of_sewage: Float
    ) {

        realm.beginTransaction()

        val sewageDisposalData = SewageDisposalData()
        sewageDisposalData.adress = adress
        sewageDisposalData.community = community
        sewageDisposalData.type_of_sewage = type_of_sewage
        sewageDisposalData.quantity_of_sewage = quantity_of_sewage

        realm.copyToRealmOrUpdate(sewageDisposalData)
        realm.commitTransaction()

    }
}
