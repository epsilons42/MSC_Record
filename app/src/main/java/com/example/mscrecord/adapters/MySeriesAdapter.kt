package com.example.mscrecord.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mscrecord.databinding.MySeriesRowBinding
import com.example.mscrecord.sqlitedao.SeriesDAO
import com.example.mscrecord.sqlitedatahelper.SeriesSQLiteDH

class MySeriesAdapter(val context : Context) : RecyclerView.Adapter<MySeriesAdapter.MySeriesVH>() {
    class MySeriesVH(val vB : MySeriesRowBinding) : RecyclerView.ViewHolder(vB.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySeriesVH {
        val view = MySeriesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MySeriesVH(view)
    }

    override fun getItemCount(): Int {
        val vt = SeriesSQLiteDH(context)
        val seriesList = SeriesDAO().readAllSerie(vt)
        return seriesList.size
        // SQLite kaydedilen verilerin sayısına göre item sayısı belirlendi
    }

    override fun onBindViewHolder(holder: MySeriesVH, position: Int) {
        val vt = SeriesSQLiteDH(context)
        val seriesList = SeriesDAO().readAllSerie(vt)
        holder.vB.textViewSeriesTitle.text = seriesList.get(position).names
        holder.vB.textViewSeriesYear.text = seriesList.get(position).years
        Glide.with(context).load(seriesList.get(position).posters).into(holder.vB.seriesImageView)
        // SQLite kaydedilen verileri okuyarak recyclerview'e eklendi
        holder.vB.imageButtonDeleteIcon.setOnClickListener {
            val alerd = AlertDialog.Builder(context)
            alerd.setTitle("Silme İşlemi")
            alerd.setMessage("Silmek istediğinize emin misiniz")
            alerd.setPositiveButton("evet"){dialogInterface, which ->
                SeriesDAO().deleteSeries(vt,seriesList.get(position).names)
                notifyItemRemoved(position)
                Toast.makeText(context,seriesList.get(position).names + " Silindi", Toast.LENGTH_SHORT).show()
            }
            alerd.setNeutralButton("iptal"){dialogInterface , which ->  }

            val alertDialog: AlertDialog = alerd.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
        //istenilen verileri silmek ve alerd mesaj için
    }
}