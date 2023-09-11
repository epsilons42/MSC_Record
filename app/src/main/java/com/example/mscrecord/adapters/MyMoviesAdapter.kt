package com.example.mscrecord.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mscrecord.sqlitedao.MoviesDAO
import com.example.mscrecord.sqlitedatahelper.MoviesSQLiteDH
import com.example.mscrecord.databinding.MyMoviesRowBinding
import com.example.mscrecord.sqlitedao.CitiesDAO

class MyMoviesAdapter(val context : Context) : RecyclerView.Adapter<MyMoviesAdapter.MyMoviesVH>(){
    class MyMoviesVH(val vB : MyMoviesRowBinding) : RecyclerView.ViewHolder(vB.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMoviesVH {
        val view = MyMoviesRowBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MyMoviesVH(view)
    }

    override fun getItemCount(): Int {
        val vt = MoviesSQLiteDH(context)
        val movieList = MoviesDAO().readMovies(vt)
        return movieList.size
        // SQLite kaydedilen verilerin sayısına göre item sayısı belirlendi
    }

    override fun onBindViewHolder(holder: MyMoviesVH, position: Int) {
        val vt = MoviesSQLiteDH(context)
        val movieList = MoviesDAO().readMovies(vt)
        holder.vB.textViewTitles.text = movieList.get(position).name
        holder.vB.textViewYears.text = movieList.get(position).year
        Glide.with(context).load(movieList.get(position).poster).into(holder.vB.imageViews)
        // SQLite kaydedilen verileri okuyarak recyclerview'e eklendi
        holder.vB.imageButtonDeleteIcon.setOnClickListener {
            val alerd = AlertDialog.Builder(context)
            alerd.setTitle("Silme İşlemi")
            alerd.setMessage("Silmek istediğinize emin misiniz")
            alerd.setPositiveButton("evet"){dialogInterface, which ->
                MoviesDAO().deleteMovies(vt,movieList.get(position).name)
                notifyItemRemoved(position)
                Toast.makeText(context,movieList.get(position).name + " Silindi", Toast.LENGTH_SHORT).show()
            }
            alerd.setNeutralButton("iptal"){dialogInterface , which ->  }

            val alertDialog: AlertDialog = alerd.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
        //istenilen verileri silmek için

    }
}