package com.example.mscrecord.sqlitedatahelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SeriesSQLiteDH(context :Context) : SQLiteOpenHelper(context,"Serie",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE serie(names TEXT,years TEXT,posters TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db!!.execSQL("DROP TABLE İF EXISTS serie")
        onCreate(db)
    }
}