package com.example.clubdeportivo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MainDatabaseHelper(context:Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        companion object{
            private val DATABASE_NAME = "CLUBDEPORTIVO.db"
            private val DATABASE_VERSION = 1

            private val TABLE_ADMIN = "Administradores"
            private val COLUMN_ADMIN_ID = "id_admin"
            private val COLUMN_ADMIN_USER = "usuario"
            private val COLUMN_ADMIN_PASS = "pass"

            private val TABLE_SOCIO = "Socio"
            private val COLUMN_SOCIO_ID = "id_socio"
            private val COLUMN_SOCIO_NAME = "nombre"
            private val COLUMN_SOCIO_SURNAME = "apellido"
            private val COLUMN_SOCIO_DNI = "dni"
            private val COLUMN_SOCIO_APTO_FISICO = "apto_fisico"

            private val TABLE_NO_SOCIO = "NoSocio"
            private val COLUMN_NO_SOCIO_ID = "id_no_socio"
            private val COLUMN_NO_SOCIO_NAME = "nombre"
            private val COLUMN_NO_SOCIO_SURNAME = "apellido"
            private val COLUMN_NO_SOCIO_DNI = "dni"
            private val COLUMN_NO_SOCIO_APTO_FISICO = "apto_fisico"

            private val TABLE_MONTHLY_CUOTES = "Cuotas_mensuales"
            private val COLUMN_CUOTES_ID = "id_cuota"
            private val COLUMN_CUOTES_AMOUNT = "monto"
            private val COLUMN_CUOTES_PAGO = "pago"
            private val COLUMN_CUOTES_EXPIRATION_DATE = "vencimiento"
            private val COLUMN_CUOTES_SOCIO_ID = "socio_id"

            private val TABLE_DAILY_ACTIVITIES = "Actividades_diarias"
            private val COLUMN_ACTIVITIES_ID = "id_actividad"
            private val COLUMN_ACTIVITIES_NAME = "nombre_actividad"
            private val COLUMN_ACTIVITIES_AMOUNT =  "monto"
            private val COLUMN_ACTIVITIES_NO_SOCIO_ID = "no_socio_id"
        }

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("PRAGMA foreign_keys = ON;")

            val createTableAdmin = ("CREATE TABLE " + TABLE_ADMIN + " (" + COLUMN_ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_ADMIN_USER + " VARCHAR(30), " + COLUMN_ADMIN_PASS + " VARCHAR(30))")
            val createTableSocio = ("CREATE TABLE " + TABLE_SOCIO + " (" + COLUMN_SOCIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_SOCIO_NAME + " VARCHAR(50), " + COLUMN_SOCIO_SURNAME + " VARCHAR(50), " + COLUMN_SOCIO_DNI + " INTEGER, "
                    + COLUMN_SOCIO_APTO_FISICO + " INTEGER)")
            val createTableNoSocio = ("CREATE TABLE " + TABLE_NO_SOCIO + " (" + COLUMN_NO_SOCIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NO_SOCIO_NAME + " VARCHAR(50), " + COLUMN_NO_SOCIO_SURNAME + " VARCHAR(50), " + COLUMN_NO_SOCIO_DNI + " INTEGER, "
                    + COLUMN_NO_SOCIO_APTO_FISICO + " INTEGER)")
            val createTableCuotas = ("CREATE TABLE " + TABLE_MONTHLY_CUOTES + " (" + COLUMN_CUOTES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_CUOTES_AMOUNT + " INTEGER, " + COLUMN_CUOTES_PAGO + " VARCHAR(5), " + COLUMN_CUOTES_EXPIRATION_DATE + " VARCHAR(20), "
                    + COLUMN_CUOTES_SOCIO_ID + " INTEGER, FOREIGN KEY (" + COLUMN_CUOTES_SOCIO_ID + ") REFERENCES "+ TABLE_SOCIO
                    + "(" + COLUMN_SOCIO_ID + ") ON DELETE CASCADE)")
            val createTableActivities = ("CREATE TABLE " + TABLE_DAILY_ACTIVITIES + " (" + COLUMN_ACTIVITIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_ACTIVITIES_NAME + " VARCHAR(30), " + COLUMN_ACTIVITIES_AMOUNT + " INTEGER, "
                    + COLUMN_ACTIVITIES_NO_SOCIO_ID + " INTEGER, FOREIGN KEY (" + COLUMN_ACTIVITIES_NO_SOCIO_ID + ") REFERENCES "+ TABLE_NO_SOCIO
                    + "(" + COLUMN_NO_SOCIO_ID + ") ON DELETE CASCADE)")

            db.execSQL(createTableAdmin)
            db.execSQL(createTableSocio)
            db.execSQL(createTableNoSocio)
            db.execSQL(createTableCuotas)
            db.execSQL(createTableActivities)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN)
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOCIO)
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NO_SOCIO)
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONTHLY_CUOTES)
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAILY_ACTIVITIES)
            onCreate(db)
        }

        fun addAdmin(user : String, password : String) : Long{
            val db = this.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_ADMIN_USER, user)
                put(COLUMN_ADMIN_PASS, password)
            }
            db.execSQL("DELETE FROM " + TABLE_ADMIN)
            val success = db.insert(TABLE_ADMIN, null, values)
            return success
        }

        fun addSocio(name : String, surname : String, dni : String, aptoFisico : Boolean) : Long{
            var aptoFisicoInBit = 0
            if(aptoFisico){
                 aptoFisicoInBit = 1
            }
            val db = this.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_SOCIO_NAME, name)
                put(COLUMN_SOCIO_SURNAME, surname)
                put(COLUMN_SOCIO_DNI, dni)
                put(COLUMN_SOCIO_APTO_FISICO, aptoFisicoInBit)
            }
            val success = db.insert(TABLE_SOCIO, null, values)
            return success
        }

        fun addNoSocio(name : String, surname : String, dni : String, aptoFisico: Boolean) : Long{
            var aptoFisicoInBit = 0
            if(aptoFisico){
                aptoFisicoInBit = 1
            }
            val db = this.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_NO_SOCIO_NAME, name)
                put(COLUMN_NO_SOCIO_SURNAME, surname)
                put(COLUMN_NO_SOCIO_DNI, dni)
                put(COLUMN_SOCIO_APTO_FISICO, aptoFisicoInBit)
            }
            val success = db.insert(TABLE_NO_SOCIO, null, values)
            return success
        }

        fun updateSocio(id : String?, name : String, surname : String, dni : String, aptoFisico : Boolean): Int{ //Edita los datos de un miembro registrado del club
            var aptoFisicoInBit = 0
            if(aptoFisico){
                aptoFisicoInBit = 1
            }
            val db = this.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_SOCIO_NAME, name)
                put(COLUMN_SOCIO_SURNAME, surname)
                put(COLUMN_SOCIO_DNI, dni)
                put(COLUMN_SOCIO_APTO_FISICO, aptoFisicoInBit)
            }
            val success = db.update(TABLE_SOCIO, values, "$COLUMN_SOCIO_ID = ?", arrayOf(id))
            return success
        }
        fun updateNoSocio(id : String?, name : String, surname : String, dni : String, aptoFisico : Boolean): Int{
            var aptoFisicoInBit = 0
            if(aptoFisico){
                aptoFisicoInBit = 1
            }
            val db = this.writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_NO_SOCIO_NAME, name)
                put(COLUMN_NO_SOCIO_SURNAME, surname)
                put(COLUMN_NO_SOCIO_DNI, dni)
                put(COLUMN_NO_SOCIO_APTO_FISICO, aptoFisicoInBit)
            }
            val success = db.update(TABLE_NO_SOCIO, values, "$COLUMN_NO_SOCIO_ID = ?", arrayOf(id))
            return success
        }

        fun addCuota(dni: String, monto: String, vencimiento: String): Long{
            val db = this.writableDatabase
            val member = getMemberByDni(dni)
            if(member[3] == "Socio"){
                val values = ContentValues().apply {
                    put(COLUMN_CUOTES_AMOUNT, monto)
                    put(COLUMN_CUOTES_EXPIRATION_DATE, vencimiento)
                    put(COLUMN_CUOTES_PAGO, "NO")
                    put(COLUMN_CUOTES_SOCIO_ID, member[0])
                }
                val success = db.insert(TABLE_MONTHLY_CUOTES, null, values)
                return success
            }else {return -1}

        }

        fun getAdmin(username: String): String { //Recive el admin para el login
            val db = this.readableDatabase
            val cursor = db.rawQuery("SELECT * FROM " + TABLE_ADMIN + " WHERE " + COLUMN_ADMIN_USER + " = '" + username + "'", null)
            if (cursor.moveToFirst()){
                val result = cursor.getString(cursor.getColumnIndex(COLUMN_ADMIN_PASS))
                cursor.close()
                return result
            }else {
                cursor.close()
                return "-1"
            }
        }
        fun getMemberByDni(dni: String): List<String>{ //Busca un miembro del club en base a su dni y devuelve sus datos para luego poder editarlos
            val member = mutableListOf<String>()
            val db = this.readableDatabase

            var cursor = db.rawQuery("SELECT * FROM " + TABLE_SOCIO + " WHERE " + COLUMN_SOCIO_DNI + " = " + dni,null)

            if(cursor.moveToFirst()){
                member.add(cursor.getString(cursor.getColumnIndex(COLUMN_SOCIO_ID)))
                member.add(cursor.getString(cursor.getColumnIndex(COLUMN_SOCIO_NAME)))
                member.add(cursor.getString(cursor.getColumnIndex(COLUMN_SOCIO_SURNAME)))
                member.add("Socio")

            }else {
                cursor = db.rawQuery("SELECT * FROM " + TABLE_NO_SOCIO + " WHERE " + COLUMN_NO_SOCIO_DNI + " = " + dni,null)
                if (cursor.moveToFirst()) {
                    member.add(cursor.getString(cursor.getColumnIndex(COLUMN_NO_SOCIO_ID)))
                    member.add(cursor.getString(cursor.getColumnIndex(COLUMN_NO_SOCIO_NAME)))
                    member.add(cursor.getString(cursor.getColumnIndex(COLUMN_NO_SOCIO_SURNAME)))
                    member.add("NoSocio")
                }
            }
            cursor.close()
            return member
        }
        fun getCuotas(dni: String): MutableList<String>{ //Busca las cuotas en base a un dni
            val db = this.readableDatabase
            val member = getMemberByDni(dni)
            val cuotas = mutableListOf<String>()

            val cursor = db.rawQuery("SELECT * FROM " + TABLE_MONTHLY_CUOTES + " WHERE " + COLUMN_CUOTES_SOCIO_ID + " = " + member[0]
                + " AND " + COLUMN_CUOTES_PAGO + " = 'NO'", null)

            if (cursor.moveToFirst()){
                do {
                    val id = cursor.getString(cursor.getColumnIndex(COLUMN_CUOTES_ID))
                    val monto = cursor.getString(cursor.getColumnIndex(COLUMN_CUOTES_AMOUNT))
                    val vencimiento = cursor.getString(cursor.getColumnIndex(COLUMN_CUOTES_EXPIRATION_DATE))
                    cuotas.add("ID: $id - Monto: $monto - Vencimiento: $vencimiento")
                }while (cursor.moveToNext())
            }
            cursor.close()
            return cuotas
        }
        fun getCuotasVencimiento(fecha: String): MutableList<String>{ //Busca las cuotas que vencen en el día
            val db = this.readableDatabase
            val cuotas = mutableListOf<String>()

            val cursor = db.rawQuery("SELECT * FROM " + TABLE_MONTHLY_CUOTES + " WHERE " + COLUMN_CUOTES_EXPIRATION_DATE + " = '" + fecha
                    + "' AND " + COLUMN_CUOTES_PAGO + " = 'NO'", null)

            if (cursor.moveToFirst()){
                do {
                    val cursor2 = db.rawQuery("SELECT * FROM " + TABLE_SOCIO + " WHERE " + COLUMN_SOCIO_ID + " = "
                            + cursor.getString(cursor.getColumnIndex(COLUMN_CUOTES_SOCIO_ID)),null)
                    if (cursor2.moveToFirst()){
                        val id = cursor.getString(cursor.getColumnIndex(COLUMN_CUOTES_ID))
                        val dni = cursor2.getString(cursor2.getColumnIndex(COLUMN_SOCIO_DNI))
                        val monto = cursor.getString(cursor.getColumnIndex(COLUMN_CUOTES_AMOUNT))
                        val vencimiento = cursor.getString(cursor.getColumnIndex(COLUMN_CUOTES_EXPIRATION_DATE))
                        cuotas.add("ID: $id - Monto: $monto - Vencimiento: $vencimiento - DNI: $dni")
                        cursor2.close()
                    }
                }while (cursor.moveToNext())
            }
            cursor.close()
            return cuotas
        }
        fun pagarCuota(id: String): Int{
            val db = this.writableDatabase
            val values = ContentValues().apply{
                put(COLUMN_CUOTES_PAGO, "SI")
            }
            val success = db.update(TABLE_MONTHLY_CUOTES, values, "$COLUMN_CUOTES_ID = ?", arrayOf(id))
            return success
        }
        fun pagarActividad(dni: String, actividad: String, monto: String): Long{
            val db = this.writableDatabase
            val member = getMemberByDni(dni)
            if(member[3] == "NoSocio"){
                val values = ContentValues().apply {
                    put(COLUMN_ACTIVITIES_NAME, actividad)
                    put(COLUMN_ACTIVITIES_AMOUNT, monto)
                    put(COLUMN_ACTIVITIES_NO_SOCIO_ID, member[0])
                }
                val success = db.insert(TABLE_DAILY_ACTIVITIES, null, values)
                return success

            }else {return -1}
        }
    }