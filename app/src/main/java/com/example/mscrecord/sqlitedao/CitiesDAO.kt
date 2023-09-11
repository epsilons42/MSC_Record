package com.example.mscrecord.sqlitedao

import android.annotation.SuppressLint
import android.content.ContentValues
import com.example.mscrecord.sqlitedatahelper.CitiesSQLiteDH
import com.example.mscrecord.models.SQLiteCitiesModel

class CitiesDAO {
    fun addCities(vt : CitiesSQLiteDH, name : String, region : String, image : String){
        val db = vt.writableDatabase
        val values = ContentValues()

        values.put("name",name)
        values.put("region",region)
        values.put("image",image)

        db.insertOrThrow("cities",null ,values)
        db.close()

    }
    @SuppressLint("Range")
    fun readCities(vt : CitiesSQLiteDH) : ArrayList<SQLiteCitiesModel>{
        val citiesArrayList = ArrayList<SQLiteCitiesModel>()
        val db =vt.writableDatabase

        val cursor = db.rawQuery("SELECT * FROM cities",null)
        while(cursor.moveToNext()){
            val city = SQLiteCitiesModel(cursor.getString(cursor.getColumnIndex("name"))
                ,cursor.getString(cursor.getColumnIndex("region"))
                ,cursor.getString(cursor.getColumnIndex("image")))
            citiesArrayList.add(city)
        }
        return citiesArrayList

    }
    fun deleteCities(vt : CitiesSQLiteDH,name : String){
        val db = vt.writableDatabase
        db.delete("cities","name=?", arrayOf(name))
        db.close()
    }
    @SuppressLint("Range")
    fun controlCities(vt:CitiesSQLiteDH, name :String) : Int {

        var sonuc = 0

        val db = vt.writableDatabase

        val cursor = db.rawQuery("SELECT count(*) AS sonuc FROM cities WHERE name='$name'",null)

        while(cursor.moveToNext()){
            sonuc = cursor.getInt(cursor.getColumnIndex("sonuc"))
        }

        return sonuc
    }
    // kontrol veril kaydının birden fazla aynı veri içermemsi için yapılan işlem
}