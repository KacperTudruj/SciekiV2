package com.example.sciekiv2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.sciekiv2.model.SewageDisposalData
import io.realm.Realm
import io.realm.kotlin.where

class ControlPanelActivity : AppCompatActivity() {

    private lateinit var saveSewageToDB: Button
    private lateinit var readSewageFromDB: Button
    private lateinit var communityButton: Button
    private lateinit var typeOfSewageButton: Button
    private lateinit var realm: Realm

    //TESTOWE
    private lateinit var dousuwaniaelemtowWbazie: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_panel)
        realm = Realm.getDefaultInstance()
        saveSewageToDB = findViewById(R.id.control_panel_id_button_sewage_disposal)
        readSewageFromDB = findViewById(R.id.control_panel_id_button_show_disposal)
        communityButton = findViewById(R.id.control_panel_id_button_community_list)
        typeOfSewageButton = findViewById(R.id.control_panel_id_button_type_of_sewage_list)

        //TESTOWE
        dousuwaniaelemtowWbazie = findViewById(R.id.dousuwaniaelemtowWbazie)

        saveSewageToDB.setOnClickListener {
            if (realm.where<SewageDisposalData>().findAll().isEmpty()) {
                Toast.makeText(this, "Najpierw musisz dodać Gminę", Toast.LENGTH_SHORT).show()
            } else { // if czy jest rodzaj scieków, kolejny if czy jest rodzaj scieków i czy są gminy. Dopiero na koncu pozwoalić wejść do widoku.
                val intent = Intent(this, SewageDisposal::class.java)
                startActivity(intent);
            }
        }
        readSewageFromDB.setOnClickListener {
            readRecordFromDatabase()
        }

        //TESTOWE
        dousuwaniaelemtowWbazie.setOnClickListener {
            funkcjaktoraWyjebieKiedys()
        }
        communityButton.setOnClickListener {
            val intent = Intent(this, CommunityPanel::class.java)
            startActivity(intent);
        }

        typeOfSewageButton.setOnClickListener {
            val intent = Intent(this, TypeOfSewagePanel::class.java)
            startActivity(intent)
        }
    }

    private fun readRecordFromDatabase() {
        realm.beginTransaction()

        val result = realm.where<SewageDisposalData>().findAll()

        //val test = result.average("quantity_of_sewage")
        //communityDataResult.makeText(this, result.average("quantity_of_sewage").toString(), Toast.LENGTH_SHORT).show()
        Toast.makeText(this, result[0]?.quantity_of_sewage.toString(), Toast.LENGTH_SHORT).show()

        realm.commitTransaction()
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
