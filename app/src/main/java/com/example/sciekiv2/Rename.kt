package com.example.sciekiv2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class Rename() : AppCompatActivity() {

    private lateinit var test: Button

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rename)


        // Przekazyać ID z ralma do edycji i na podstawie tego edytować daną
        //Dodać do intent.Extras nazwę bazy. By edytowac Rodzaj ścieku oraz gminę (bardziej uniwersalniej)
        //Mały refaktor przydałby się bo już zaczya być syf
        //Moze zmiaast dawac id to może warto dać obiekt Realam do edycji? Da się tak w ogóle?
        val id = intent.extras
        val data = id?.getString("id")



        test = findViewById(R.id.edit_button_community_window)
        test.text = data.toString()
        //test.text = "KURWA NO"
        }
    }
