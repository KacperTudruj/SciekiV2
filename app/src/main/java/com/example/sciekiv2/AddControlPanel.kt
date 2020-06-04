package com.example.sciekiv2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.sciekiv2.model.CommunityData
import com.example.sciekiv2.model.TypeOfSewageData
import io.realm.Realm
import io.realm.kotlin.where

class AddControlPanel : AppCompatActivity() {

    private lateinit var storeSewageData: Button
    private lateinit var communityButton: Button
    private lateinit var typeOfSewageButton: Button

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_control_panel)

        realm = Realm.getDefaultInstance()

        storeSewageData = findViewById(R.id.add_control_panel_id_button_sewage_disposal)
        communityButton = findViewById(R.id.add_control_panel_id_button_community_list)
        typeOfSewageButton = findViewById(R.id.add_control_panel_id_button_type_of_sewage_list)

        storeSewageData.setOnClickListener {
            if(realm.where<CommunityData>().findAll().isEmpty() && realm.where<TypeOfSewageData>().findAll().isEmpty())
                Toast.makeText(this, "Najpierw musisz dodać gminę i rodzaj ścieków", Toast.LENGTH_SHORT).show()
            else if (realm.where<CommunityData>().findAll().isEmpty())
                Toast.makeText(this, "Najpierw musisz dodać Gminę", Toast.LENGTH_SHORT).show()
            else if (realm.where<TypeOfSewageData>().findAll().isEmpty())
                Toast.makeText(this, "Najpierw musisz dodać rodzaj ścieków", Toast.LENGTH_SHORT).show()
            else{
                val intent = Intent(this, SewageDisposal::class.java)
                startActivity(intent);
            }
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
}
