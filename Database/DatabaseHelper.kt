package com.uts_semangat.Database
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.uts_semangat.Data.AlumniData

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "AlumniDB"
        private const val TABLE_ALUMNI = "alumni"

        private const val KEY_NIM = "nim"
        private const val KEY_NAMA = "nama"
        private const val KEY_TEMPAT_LAHIR = "tempat_lahir"
        private const val KEY_TANGGAL_LAHIR = "tanggal_lahir"
        private const val KEY_ALAMAT = "alamat"
        private const val KEY_AGAMA = "agama"
        private const val KEY_NO_TELEPON = "no_telepon"
        private const val KEY_TAHUN_MASUK = "tahun_masuk"
        private const val KEY_TAHUN_LULUS = "tahun_lulus"
        private const val KEY_PEKERJAAN = "pekerjaan"
        private const val KEY_JABATAN = "jabatan"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_ALUMNI ("
                + "$KEY_NIM TEXT PRIMARY KEY,"
                + "$KEY_NAMA TEXT,"
                + "$KEY_TEMPAT_LAHIR TEXT,"
                + "$KEY_TANGGAL_LAHIR TEXT,"
                + "$KEY_ALAMAT TEXT,"
                + "$KEY_AGAMA TEXT,"
                + "$KEY_NO_TELEPON TEXT,"
                + "$KEY_TAHUN_MASUK TEXT,"
                + "$KEY_TAHUN_LULUS TEXT,"
                + "$KEY_PEKERJAAN TEXT,"
                + "$KEY_JABATAN TEXT)")

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ALUMNI")
        onCreate(db)
    }

    fun addAlumni(alumni: AlumniData): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NIM, alumni.nim)
        contentValues.put(KEY_NAMA, alumni.nama)
        contentValues.put(KEY_TEMPAT_LAHIR, alumni.tempatLahir)
        contentValues.put(KEY_TANGGAL_LAHIR, alumni.tanggalLahir)
        contentValues.put(KEY_ALAMAT, alumni.alamat)
        contentValues.put(KEY_AGAMA, alumni.agama)
        contentValues.put(KEY_NO_TELEPON, alumni.noTelepon)
        contentValues.put(KEY_TAHUN_MASUK, alumni.tahunMasuk)
        contentValues.put(KEY_TAHUN_LULUS, alumni.tahunLulus)
        contentValues.put(KEY_PEKERJAAN, alumni.pekerjaan)
        contentValues.put(KEY_JABATAN, alumni.jabatan)

        val success = db.insert(TABLE_ALUMNI, null, contentValues)
        db.close()
        return success
    }

    // Method untuk mendapatkan semua data alumni
    @SuppressLint("Range")
    fun getAllAlumni(): ArrayList<AlumniData> {
        val alumniList = ArrayList<AlumniData>()
        val selectQuery = "SELECT  * FROM $TABLE_ALUMNI"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val alumni = AlumniData(
                    cursor.getString(cursor.getColumnIndex(KEY_NIM)),
                    cursor.getString(cursor.getColumnIndex(KEY_NAMA)),
                    cursor.getString(cursor.getColumnIndex(KEY_TEMPAT_LAHIR)),
                    cursor.getString(cursor.getColumnIndex(KEY_TANGGAL_LAHIR)),
                    cursor.getString(cursor.getColumnIndex(KEY_ALAMAT)),
                    cursor.getString(cursor.getColumnIndex(KEY_AGAMA)),
                    cursor.getString(cursor.getColumnIndex(KEY_NO_TELEPON)),
                    cursor.getString(cursor.getColumnIndex(KEY_TAHUN_MASUK)),
                    cursor.getString(cursor.getColumnIndex(KEY_TAHUN_LULUS)),
                    cursor.getString(cursor.getColumnIndex(KEY_PEKERJAAN)),
                    cursor.getString(cursor.getColumnIndex(KEY_JABATAN))
                )
                alumniList.add(alumni)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return alumniList
    }
}