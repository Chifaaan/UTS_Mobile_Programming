package com.uts_semangat

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uts_semangat.Data.AlumniData
import com.uts_semangat.Database.DatabaseHelper
import java.text.SimpleDateFormat
import java.util.*

class TambahData : AppCompatActivity() {

    private lateinit var editTextNim: EditText
    private lateinit var editTextNamaAlumni: EditText
    private lateinit var editTextTempatLahir: EditText
    private lateinit var editTextTanggalLahir: EditText
    private lateinit var editTextAlamat: EditText
    private lateinit var editTextAgama: EditText
    private lateinit var editTextNoTelepon: EditText
    private lateinit var editTextTahunMasuk: EditText
    private lateinit var editTextTahunLulus: EditText
    private lateinit var editTextPekerjaan: EditText
    private lateinit var editTextJabatan: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_data)

        editTextNim = findViewById(R.id.editTextNim)
        editTextNamaAlumni = findViewById(R.id.editTextNamaAlumni)
        editTextTempatLahir = findViewById(R.id.editTextTempatLahir)
        editTextTanggalLahir = findViewById(R.id.editTextTanggalLahir)
        editTextAlamat = findViewById(R.id.editTextAlamat)
        editTextAgama = findViewById(R.id.editTextAgama)
        editTextNoTelepon = findViewById(R.id.editTextNoTelepon)
        editTextTahunMasuk = findViewById(R.id.editTextTahunMasuk)
        editTextTahunLulus = findViewById(R.id.editTextTahunLulus)
        editTextPekerjaan = findViewById(R.id.editTextPekerjaan)
        editTextJabatan = findViewById(R.id.editTextJabatan)

        editTextTanggalLahir.setOnClickListener {
            showDatePickerDialog()
        }

        val buttonSimpan: Button = findViewById(R.id.buttonSimpan)
        buttonSimpan.setOnClickListener {
            saveDataToSQLite()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)
                val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
                editTextTanggalLahir.setText(dateFormatter.format(newDate.time))
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun saveDataToSQLite() {
        val nim = editTextNim.text.toString().trim()
        val nama = editTextNamaAlumni.text.toString().trim()
        val tempatLahir = editTextTempatLahir.text.toString().trim()
        val tanggalLahir = editTextTanggalLahir.text.toString().trim()
        val alamat = editTextAlamat.text.toString().trim()
        val agama = editTextAgama.text.toString().trim()
        val noTelepon = editTextNoTelepon.text.toString().trim()
        val tahunMasuk = editTextTahunMasuk.text.toString().trim()
        val tahunLulus = editTextTahunLulus.text.toString().trim()
        val pekerjaan = editTextPekerjaan.text.toString().trim()
        val jabatan = editTextJabatan.text.toString().trim()

        if (nim.isEmpty() || nama.isEmpty() || tempatLahir.isEmpty() || tanggalLahir.isEmpty() || alamat.isEmpty() ||
            agama.isEmpty() || noTelepon.isEmpty() || tahunMasuk.isEmpty() || tahunLulus.isEmpty() ||
            pekerjaan.isEmpty() || jabatan.isEmpty()
        ) {
            Toast.makeText(this, "Mohon lengkapi semua kolom", Toast.LENGTH_SHORT).show()
            return
        }

        val alumniData = AlumniData(
            nim,
            nama,
            tempatLahir,
            tanggalLahir,
            alamat,
            agama,
            noTelepon,
            tahunMasuk,
            tahunLulus,
            pekerjaan,
            jabatan
        )

        val databaseHelper = DatabaseHelper(this)
        val result = databaseHelper.addAlumni(alumniData)

        if (result > -1) {
            Toast.makeText(this, "Data alumni berhasil disimpan", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Gagal menyimpan data alumni", Toast.LENGTH_SHORT).show()
        }
    }
}