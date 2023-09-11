package com.example.mscrecord.sqlitedatahelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CitiesSQLiteDH(val context : Context)  : SQLiteOpenHelper(context,"Cities",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE cities(name  TEXT, region TEXT,image TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE İF EXISTS cities")
        onCreate(db!!)
    }
}