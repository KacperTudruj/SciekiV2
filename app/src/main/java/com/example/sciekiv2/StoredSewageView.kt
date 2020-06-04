package com.example.sciekiv2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.sciekiv2.adapter.StoredSewageData
import com.example.sciekiv2.model.SewageDisposalData
import io.realm.Realm
import io.realm.kotlin.where

class StoredSewageView : AppCompatActivity() {

    private lateinit var realm: Realm
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stored_sewage_view)

        realm = Realm.getDefaultInstance()
        val sewawgeData = realm.where<SewageDisposalData>().findAll()
        listView = findViewById(R.id.list_view_for_stored_sewage_data)
        val adapter = StoredSewageData(this,sewawgeData)
        adapter.notifyDataSetChanged()
        listView.adapter = adapter
    }
}
