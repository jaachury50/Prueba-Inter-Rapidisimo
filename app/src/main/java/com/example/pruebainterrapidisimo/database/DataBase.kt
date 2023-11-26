package com.example.pruebainterrapidisimo.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.pruebainterrapidisimo.model.TableModel

var BD = "pruebaInterRapidisimo"

class DataBase(context: Context): SQLiteOpenHelper(context,BD,null,1) {


    override fun onCreate(p0: SQLiteDatabase?) {

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }


    fun createTables(queries: List<TableModel>) {
        writableDatabase.apply {
            beginTransaction()
            try {
                for (query in queries) {
                    if (!tableExists(this, query.nombreTabla)) {
                        execSQL(query.queryCreacion)
                    }
                }
                setTransactionSuccessful()
            } catch (e: Exception) {
                Log.e("Error", "Error en la transacción de la base de datos", e)
            } finally {
                endTransaction()
                close()
            }
        }
    }

    private fun tableExists(db: SQLiteDatabase?, tableName: String): Boolean {
        val cursor = db?.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name=?", arrayOf(tableName))
        val tableExists = cursor?.count!! > 0
        cursor?.close()
        return tableExists
    }



}