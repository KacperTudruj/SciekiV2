package com.example.sciekiv2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.sciekiv2.model.CommunityData
import com.example.sciekiv2.model.SewageDisposalData
import com.example.sciekiv2.model.TypeOfSewageData
import io.realm.Realm
import io.realm.kotlin.where

class ControlPanelActivity : AppCompatActivity() {

    private lateinit var intenToAddPanel: Button
    private lateinit var showInformation: Button
    private lateinit var realm: Realm

    //TESTOWE
    private lateinit var dousuwaniaelemtowWbazie: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_panel)
        realm = Realm.getDefaultInstance()

        intenToAddPanel = findViewById(R.id.control_panel_id_button_add_pannel)
        showInformation = findViewById(R.id.control_panel_id_button_show)

        //TESTOWE
        dousuwaniaelemtowWbazie = findViewById(R.id.dousuwaniaelemtowWbazie)

        intenToAddPanel.setOnClickListener {
            val intent = Intent(this, AddControlPanel::class.java)
            startActivity(intent);
        }
        showInformation.setOnClickListener {
            val intent = Intent(this, ShowControlPanel::class.java)
            startActivity(intent)
        }

        //TESTOWE
        dousuwaniaelemtowWbazie.setOnClickListener {
            funkcjaktoraWyjebieKiedys()
        }
    }

    //TESTOWE
    private fun readRecordFromDatabase() {
        //realm.beginTransaction()

        //val result = realm.where<SewageDisposalData>().findAll()
        //val test = result.average("quantity_of_sewage")
        //communityDataResult.makeText(this, result.average("quantity_of_sewage").toString(), Toast.LENGTH_SHORT).show()
        //Toast.makeText(this, result[0]?.quantity_of_sewage.toString(), Toast.LENGTH_SHORT).show()

        //realm.commitTransaction()
//        val intent = Intent(this, StoredSewageView::class.java)
//        startActivity(intent)
    }

    //TESTOWE
    private fun testoweAddRecordToDatabase() {
        realm.beginTransaction()
        val sawageDB = SewageDisposalData()
        sawageDB.adress = "Losowe jakies"
        sawageDB.quantity_of_sewage = 10.5f
        sawageDB.type_of_sewage = "Losowa jakies"
        sawageDB.community = "TEST"
        realm.copyToRealmOrUpdate(sawageDB)

        realm.commitTransaction()
    }

    //TESTOWE
    private fun funkcjaktoraWyjebieKiedys() {

        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()
    }
}
