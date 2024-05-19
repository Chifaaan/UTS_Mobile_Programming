package com.uts_semangat

import AlumniAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uts_semangat.Data.AlumniData
import com.uts_semangat.Database.DatabaseHelper

class Alumni : AppCompatActivity() {

    private lateinit var listViewAlumni: ListView
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var alumniList: ArrayList<AlumniData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alumni)

        listViewAlumni = findViewById(R.id.listViewAlumni)
        databaseHelper = DatabaseHelper(this)
        alumniList = ArrayList()

        loadAlumniList()

        listViewAlumni.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // Handle item click event if needed
            val selectedAlumni = alumniList[position]
            Toast.makeText(this, "Clicked: ${selectedAlumni.nama}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadAlumniList() {
        val alumniDataList = databaseHelper.getAllAlumni()
        alumniList.clear()
        alumniList.addAll(alumniDataList)

        val adapter = AlumniAdapter(this, alumniList)
        listViewAlumni.adapter = adapter
    }
}