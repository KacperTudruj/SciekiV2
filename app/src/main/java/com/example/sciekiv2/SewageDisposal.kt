package com.example.sciekiv2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.sciekiv2.model.SewageDisposalData
import io.realm.Realm

class SewageDisposal : AppCompatActivity() {
    private lateinit var saveSewageToDB: Button
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sewage_disposal)

        saveSewageToDB = findViewById(R.id.add_sewage_disposal_data_button)
        realm = Realm.getDefaultInstance()

        saveSewageToDB.setOnClickListener {
            addSewage("Adres", "Gmina", "Rodzaj", 10.5f)
            val intent = Intent(this, ControlPanelActivity::class.java)
            startActivity(intent);
        }

    }
    private fun addSewage(adress: String, community: String, type_of_sewage: String, quantity_of_sewage: Float){

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
