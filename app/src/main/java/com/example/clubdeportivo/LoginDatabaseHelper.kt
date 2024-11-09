package com.example.clubdeportivo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class LoginDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
    companion object{
        private val DATABASE_NAME = "LOGIN.db"
        private val DATABASE_VERSION = 1
        private val TABLE_LOGIN = "administrador"
        private val COLUMN_ID = "id_adm"
        private val COLUMN_US = "usuario"
        private val COLUMN_PASS = "pass"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = (
                "CREATE TABLE " + TABLE_LOGIN + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_US + " VARCHAR(100), "
                + COLUMN_PASS + " VARCHAR(100)"
                )
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN)
        onCreate(db)
    }

    // si devuelve -1 no se pudo insertar
    fun addUsuario(us: String): Long
    {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_US, us)
        }
        val success = db.insert(TABLE_LOGIN, null, values)
        return success
    }

    fun addPass(pass: String): Long
    {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_PASS, pass)
        }
        val success = db.insert(TABLE_LOGIN, null, values)
        return success
    }
}