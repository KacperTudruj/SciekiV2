package com.example.sciekiv2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.example.sciekiv2.model.CommunityData
import com.example.sciekiv2.model.SewageDisposalData
import com.example.sciekiv2.model.TypeOfSewageData
import io.realm.Realm
import io.realm.kotlin.where
import kotlin.collections.ArrayList

class SewageDisposal : AppCompatActivity() {
    private lateinit var saveSewageButton: Button
    private lateinit var spinnerCommunity: Spinner
    private lateinit var adressEditText: EditText
    private lateinit var spinnerTypeOfSewage: Spinner
    private lateinit var quantityOfSewageEditText: EditText
    private lateinit var realm: Realm

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sewage_disposal)

        realm = Realm.getDefaultInstance()

        adressEditText = findViewById(R.id.sewage_disposal_id_adress_edit_text)
        spinnerCommunity = findViewById(R.id.sewage_disposal_id_community_spinner)
        spinnerTypeOfSewage = findViewById(R.id.sewage_disposal_id_type_edit_text)
        quantityOfSewageEditText = findViewById(R.id.sewage_disposal_id_quantity_edit_text)
        saveSewageButton = findViewById(R.id.sewage_disposal_id_add_sewage_disposal_button)

        //Spinner for community
        val communityList = ArrayList<String>()
            for (community in realm.where<CommunityData>().findAll()) {
                communityList.add(community.communityName.toString())
            }
        createSpinner(communityList, spinnerCommunity)

        //Spinner for Sewage
        val sewageList = ArrayList<String>()
            for (sewage in realm.where<TypeOfSewageData>().findAll()) {
                sewageList.add(sewage.typeOfSewageName)
            }
        //Toast.makeText(this, "$communityList", Toast.LENGTH_SHORT).show()
        createSpinner(sewageList, spinnerTypeOfSewage)


        saveSewageButton.setOnClickListener {
            addSewage(
                adressEditText.text.toString(),
                spinnerCommunity.selectedItem.toString(),
                spinnerTypeOfSewage.selectedItem.toString(),
                quantityOfSewageEditText.text.toString().toFloat()
            )
            val intent = Intent(this, ControlPanelActivity::class.java)
            startActivity(intent);
        }

    }

    private fun addSewage(
        adress: String,
        community: String,
        type_of_sewage: String,
        quantity_of_sewage: Float
    ) {

        realm.beginTransaction()

        val sewageDisposalData = SewageDisposalData()
        sewageDisposalData.adress = adress
        sewageDisposalData.community = community
        sewageDisposalData.type_of_sewage = type_of_sewage
        sewageDisposalData.quantity_of_sewage = quantity_of_sewage

        realm.copyToRealmOrUpdate(sewageDisposalData)
        realm.commitTransaction()

    }

    private fun createSpinner(arrayList: ArrayList<String>, spinner: Spinner) {
        spinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList)

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
            }

        }
    }
}
