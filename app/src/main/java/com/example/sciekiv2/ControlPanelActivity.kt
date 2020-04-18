package com.example.sciekiv2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class ControlPanelActivity : AppCompatActivity() {

    private lateinit var saveSewageToDB: Button
    private lateinit var readSewageFromDB: Button

    //TESTOWE
    private lateinit var dousuwaniaelemtowWbazie: Button
    private lateinit var testowoDodajeRekord: Button
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_panel)
        realm = Realm.getDefaultInstance()
        saveSewageToDB = findViewById(R.id.sewage_disposal_button)
        readSewageFromDB = findViewById(R.id.show_disposal_button)

        //TESTOWE
        dousuwaniaelemtowWbazie = findViewById(R.id.dousuwaniaelemtowWbazie)
        testowoDodajeRekord = findViewById(R.id.testowoDodajeRekord)

        saveSewageToDB.setOnClickListener {
            val intent = Intent(this, SewageDisposal::class.java)
            startActivity(intent);
        }
        readSewageFromDB.setOnClickListener {
            readRecordFromDatabase()
        }

        //TESTOWE
        dousuwaniaelemtowWbazie.setOnClickListener {
            funkcjaktoraWyjebieKiedys()
        }
        //TESTOWE
        testowoDodajeRekord.setOnClickListener {
            testoweAddRecordToDatabase()
        }
    }

    private fun readRecordFromDatabase() {
        realm.beginTransaction()

        val result = realm.where<SewageDisposalData>()
            .equalTo("quantity_of_sewage", 10.5f).findAll()

        //val test = result.average("quantity_of_sewage")
        //Toast.makeText(this, result.average("quantity_of_sewage").toString(), Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "$result", Toast.LENGTH_SHORT).show()

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
