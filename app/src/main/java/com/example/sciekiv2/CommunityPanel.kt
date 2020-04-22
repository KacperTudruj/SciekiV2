package com.example.sciekiv2

import android.app.ActionBar
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

class CommunityPanel : AppCompatActivity() {

    //private lateinit var addCommunityToDB: Button
    //Zalezy mi aby Przycisk  dodawania gminy był zawsze na górze, a pod spodem automatycznie dodawały się gminy z przyciskiem usun.
    // Czytanie gmin powinno odbywać się zawsze z bazy. Poczytać jak ludzie dodają tak by pojawiało się jedno pod drugim.
    // oraz scrool najprawtopodobniej.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_panel)

        var layout = findViewById(R.id.rel_layout_community_list) as LinearLayout
        val add_community_button = Button(this)
        val dynamic_textView = TextView(this)
        layout.addView(dynamic_textView)
        //addCommunityToDB = findViewById(R.id.add_community)


        add_community_button.layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        add_community_button.text = "O cie chuj"
        add_community_button.setBackgroundColor(Color.parseColor("#CA7A04"))
        add_community_button.setTextColor(Color.parseColor("#FFFFFF"))
        add_community_button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36f)

//        <Button
//        android:id="@+id/add_community"
//        android:layout_width="320dp"
//        android:layout_height="wrap_content"
//        android:layout_centerHorizontal="true"
//        android:layout_marginTop="30dp"
//        android:backgroundTint="#CA7A04"
//        android:text="Dodaj Gminę (Testowo button)"
//        android:textColor="#FFFFFF"
//        android:textSize="36sp"
//        tools:layout_conversion_absoluteHeight="48dp"
//        tools:layout_conversion_absoluteWidth="411dp" />

        dynamic_textView.layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        dynamic_textView.text = "O CHUJ CI CHODZI!"
        dynamic_textView.setTextColor(Color.parseColor("#FFFFFF"))
        dynamic_textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36f)
        //textView.setBackgroundColor(Color.parseColor("#f44336"));
//        android:layout_centerHorizontal="true"
//        android:gravity=
//        android:orientation="vertical"
        add_community_button.setOnClickListener {
            layout.addView(add_community_button)
            //layout.addView(dynamic_textView)
        }

    }
}
