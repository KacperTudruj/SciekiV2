package com.example.sciekiv2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.example.sciekiv2.adapter.TypeOfSewageAdapter
import com.example.sciekiv2.model.TypeOfSewage
import io.realm.Realm
import io.realm.kotlin.where

import kotlinx.android.synthetic.main.activity_type_of_sewage.*

class TypeOfSewagePanel : AppCompatActivity() {

    private lateinit var realm: Realm
    private lateinit var listView: ListView
    private lateinit var addTypeOfSewageToDBButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_of_sewage)
        realm = Realm.getDefaultInstance()

        val typeOfSewageResults = realm.where<TypeOfSewage>().findAll()
        listView = findViewById(R.id.type_of_sewage_list_view)
        val adapter = TypeOfSewageAdapter(this, typeOfSewageResults)
        adapter.notifyDataSetChanged()
        listView.adapter = adapter

        val editTextTypeOfSewage = findViewById<EditText>(R.id.type_of_sewage_id_edit_text)

        addTypeOfSewageToDBButton = findViewById(R.id.type_of_sewage_id_add_button)
        addTypeOfSewageToDBButton.setOnClickListener {
            if (editTextTypeOfSewage.text.toString() == "") {
                Toast.makeText(this, "Puste pole", Toast.LENGTH_SHORT).show()
            } else {
                addTypeOfSewageData(editTextTypeOfSewage.text.toString())
            }
            //refreshing listView!
            listView.invalidateViews()
        }
    }

    private fun addTypeOfSewageData(typeOfSewageName: String) {
        realm.beginTransaction()
        val typeOfSewage = TypeOfSewage()
        typeOfSewage.typeOfSewageName = typeOfSewageName
        realm.copyToRealmOrUpdate(typeOfSewage)
        realm.commitTransaction()
    }

}
