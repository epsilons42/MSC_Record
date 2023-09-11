package com.example.mscrecord.sqlitedatahelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MoviesSQLiteDH(val context : Context) : SQLiteOpenHelper(context,"Movies" ,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE movies(name TEXT,year TEXT ,poster TEXT);")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE İF EXISTS movies")
        onCreate(db!!)
    }
}