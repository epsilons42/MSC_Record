package com.example.mscrecord.sqlitedao

import android.annotation.SuppressLint
import android.content.ContentValues
import com.example.mscrecord.models.SQLiteSeriesModel
import com.example.mscrecord.sqlitedatahelper.CitiesSQLiteDH
import com.example.mscrecord.sqlitedatahelper.MoviesSQLiteDH
import com.example.mscrecord.sqlitedatahelper.SeriesSQLiteDH

class SeriesDAO {
    fun addSerie(vt : SeriesSQLiteDH,names : String,years : String,posters : String){
        val db = vt.writableDatabase
        val values = ContentValues()

        values.put("names",names)
        values.put("years",years)
        values.put("posters",posters)

        db.insertOrThrow("serie",null,values)
        db.close()

    }
    @SuppressLint("Range")
    fun readAllSerie(vt : SeriesSQLiteDH) : ArrayList<SQLiteSeriesModel>{
        val serieArrayList = ArrayList<SQLiteSeriesModel>()
        val db = vt.writableDatabase

        val cursor = db.rawQuery("SELECT * FROM serie",null)

        while(cursor.moveToNext()){
            val serie = SQLiteSeriesModel(cursor.getString(cursor.getColumnIndex("names")),
            cursor.getString(cursor.getColumnIndex("years")),
            cursor.getString(cursor.getColumnIndex("posters")))

            serieArrayList.add(serie)
        }
        return serieArrayList

    }
    fun deleteSeries(vt : SeriesSQLiteDH, names : String){
        val db = vt.writableDatabase
        db.delete("serie","names=?", arrayOf(names))
        db.close()
    }
    @SuppressLint("Range")
    fun controlSeries(vt: SeriesSQLiteDH, names :String) : Int {

        var sonuc = 0

        val db = vt.writableDatabase

        val cursor = db.rawQuery("SELECT count(*) AS sonuc FROM serie WHERE names='$names'",null)

        while(cursor.moveToNext()){
            sonuc = cursor.getInt(cursor.getColumnIndex("sonuc"))
        }

        return sonuc
    }
    // kontrol veril kaydının birden fazla aynı veri içermemsi için yapılan işlem
}