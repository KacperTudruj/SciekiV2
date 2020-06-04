package com.example.sciekiv2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ShowControlPanel : AppCompatActivity() {

    private lateinit var readSewageFromDB: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_control_panel)

        readSewageFromDB = findViewById(R.id.show_control_panel_id_database)

        readSewageFromDB.setOnClickListener {
            val intent = Intent(this, StoredSewageView::class.java)
            startActivity(intent)
        }
    }
}
