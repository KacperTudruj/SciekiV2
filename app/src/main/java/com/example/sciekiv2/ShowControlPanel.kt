package com.example.sciekiv2

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*

class ShowControlPanel : AppCompatActivity() {

    private lateinit var readSewageFromDB: Button
    private lateinit var dateStart: TextView
    private lateinit var dateEnd: TextView
    private lateinit var dataPickerDialog: DatePickerDialog.OnDateSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_control_panel)

        readSewageFromDB = findViewById(R.id.show_control_panel_id_database)

        dateStart = findViewById(R.id.show_control_panel_date_start)
        dateEnd = findViewById(R.id.show_control_panel_date_end)

        val calendar = Calendar.getInstance()
        val dateSetListenerDataStart =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd.MM.yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.UK)
                dateStart.text = sdf.format(calendar.time)
            }

        val dateSetListenerDataEnd =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd.MM.yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.UK)
                dateEnd.text = sdf.format(calendar.time)
            }

        dateStart.setOnClickListener {
            DatePickerDialog(
                this@ShowControlPanel, dateSetListenerDataStart,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        dateEnd.setOnClickListener {
            DatePickerDialog(
                this@ShowControlPanel, dateSetListenerDataEnd,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        readSewageFromDB.setOnClickListener {
            val intent = Intent(this, StoredSewageView::class.java)
            startActivity(intent)
        }
    }
}
