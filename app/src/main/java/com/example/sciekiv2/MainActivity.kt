package com.example.sciekiv2

import android.app.Person
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity : AppCompatActivity() {

    private lateinit var wellcomeButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wellcomeButton = findViewById(R.id.wellcome_button)
        Realm.init(this)

        wellcomeButton.setOnClickListener {
            val intent = Intent(this, ControlPanelActivity::class.java)
            startActivity(intent);
        }
    }
}
