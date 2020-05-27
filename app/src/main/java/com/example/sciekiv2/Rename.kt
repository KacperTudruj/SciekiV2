package com.example.sciekiv2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sciekiv2.model.CommunityData
import io.realm.Realm
import io.realm.kotlin.where

class Rename() : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var editText: EditText
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rename)

        val intentExtras = intent.extras
        realm = Realm.getDefaultInstance()

        editText = findViewById(R.id.rename_id_edit_text_view)
        button = findViewById(R.id.rename_id_button)


        if (intentExtras?.getString("fieldNameToEdit") == "community") {
            val communityResults =
                realm.where<CommunityData>().equalTo("id", intentExtras?.getString("queryId"))
                    .findFirst()
            editText.setText(communityResults?.communityName)
            button.setOnClickListener {
                if (editText.text.toString() == "")
                    Toast.makeText(this, "Puste pole", Toast.LENGTH_SHORT).show()
                else {
                    realm.beginTransaction()
                    communityResults?.communityName = editText.text.toString()
                    realm.copyToRealmOrUpdate(communityResults)
                    realm.commitTransaction()
                    this.finish()
                }
            }

        }
        //Czas to dobrze zrobić. WALIDACJA, Na to samą nazwę, oraz na puste pole. Zamiast wykonać ackcję to wyswietlić Tosta :D I nie tylko ten erkan
    }
}
