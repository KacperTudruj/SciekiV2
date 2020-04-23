package com.example.sciekiv2

import android.app.ActionBar
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.*
import com.example.sciekiv2.model.CommunityData
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import io.realm.kotlin.where

class CommunityPanel : AppCompatActivity() {

    private lateinit var realm: Realm
    private lateinit var listView: ListView

    //private lateinit var addCommunityToDB: Button
    //Zalezy mi aby Przycisk  dodawania gminy był zawsze na górze, a pod spodem automatycznie dodawały się gminy z przyciskiem usun.
    // Czytanie gmin powinno odbywać się zawsze z bazy. Poczytać jak ludzie dodają tak by pojawiało się jedno pod drugim.
    // oraz scrool najprawtopodobniej.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_panel)

        //listView = findViewById<ListView>(R.id.recipe_list_view)
        realm = Realm.getDefaultInstance()
        addCommunity("Gmina Wina")
//        addCommunity("Commutniy2")
//        addCommunity("Commutniy3")
//        addCommunity("Commutniy4")

        listView = findViewById<ListView>(R.id.community_list_view)
        val layoutV11 = findViewById(R.id.layout_for_community_panel_data) as LinearLayout

        val add_community_button = Button(this)


        add_community_button.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        add_community_button.text = "O cie chuj"
        layoutV11.addView(add_community_button)
        add_community_button.setOnClickListener {

            val numberOfRecords = realm.where<CommunityData>().count()
            val results = realm.where<CommunityData>().findAll()
            //val test = RealmResults<CommunityData>()
//            Toast.makeText(this, results.get(0)?.communityName.toString(), Toast.LENGTH_SHORT).show()

            //val communityList = ArrayList<>
            for (i in 0 until numberOfRecords) {
                listView.addView(textViewTEST(results[i.toInt()]?.communityName.toString()))
            }

        }
    }

    private fun addCommunity(communityName: String) {
        realm.beginTransaction()
        val communityData = CommunityData()
        communityData.communityName = communityName
        realm.copyToRealmOrUpdate(communityData)
        realm.commitTransaction()
    }


    private fun textViewTEST(text: String): TextView {
        val dynamic_textView = TextView(this)
        dynamic_textView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dynamic_textView.setTextColor(Color.parseColor("#FFFFFF"))
        dynamic_textView.text = text
        return dynamic_textView
    }
}
