package com.example.mscrecord.sqlitedao

import android.annotation.SuppressLint
import android.content.ContentValues
import com.example.mscrecord.sqlitedatahelper.MoviesSQLiteDH
import com.example.mscrecord.models.SQLiteMoviesModel
import com.example.mscrecord.sqlitedatahelper.CitiesSQLiteDH

class MoviesDAO {
    fun addMovies(vt : MoviesSQLiteDH, name : String, year : String, poster : String){
        val db = vt.writableDatabase
        val values = ContentValues()

        values.put("name",name)
        values.put("year",year)
        values.put("poster",poster)

        db.insertOrThrow("movies",null,values)
        db.close()
    }
    @SuppressLint("Range")
    fun readMovies(vt: MoviesSQLiteDH) : ArrayList<SQLiteMoviesModel> {

        val moviesArrayList = ArrayList<SQLiteMoviesModel>()

        val db = vt.writableDatabase

        val cursor = db.rawQuery("SELECT * FROM movies",null)

        while(cursor.moveToNext()){

            val movie = SQLiteMoviesModel(cursor.getString(cursor.getColumnIndex("name"))
                ,cursor.getString(cursor.getColumnIndex("year"))
                ,cursor.getString(cursor.getColumnIndex("poster")))

            moviesArrayList.add(movie)
        }

        return moviesArrayList

    }
    fun deleteMovies(vt : MoviesSQLiteDH, name : String){
        val db = vt.writableDatabase
        db.delete("movies","name=?", arrayOf(name))
        db.close()
    }
    @SuppressLint("Range")
    fun controlMovies(vt:MoviesSQLiteDH, name :String) : Int {

        var sonuc = 0

        val db = vt.writableDatabase

        val cursor = db.rawQuery("SELECT count(*) AS sonuc FROM movies WHERE name='$name'",null)

        while(cursor.moveToNext()){
            sonuc = cursor.getInt(cursor.getColumnIndex("sonuc"))
        }

        return sonuc
    }
    // kontrol veril kaydının birden fazla aynı veri içermemsi için yapılan işlem
}