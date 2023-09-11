package com.example.mscrecord.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mscrecord.sqlitedao.CitiesDAO
import com.example.mscrecord.sqlitedatahelper.CitiesSQLiteDH
import com.example.mscrecord.databinding.MyCitiesRowBinding

class MyCitiesAdapter(val context : Context) : RecyclerView.Adapter<MyCitiesAdapter.MyCitiesVH>() {
    class MyCitiesVH(val vB : MyCitiesRowBinding) : RecyclerView.ViewHolder(vB.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCitiesVH {
        val view = MyCitiesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyCitiesVH(view)
    }

    override fun getItemCount(): Int {
        val vt = CitiesSQLiteDH(context)
        val citiesList = CitiesDAO().readCities(vt)
        return citiesList.size
        // SQLite kaydedilen verilerin sayısına göre item sayısı belirlendi
    }

    override fun onBindViewHolder(holder: MyCitiesVH, position: Int) {
        val vt = CitiesSQLiteDH(context)
        val citiesList = CitiesDAO().readCities(vt)
        holder.vB.textViewName.text = citiesList.get(position).Title
        holder.vB.textViewRegion.text = citiesList.get(position).region
        Glide.with(context).load(citiesList.get(position).image).into(holder.vB.imageViews)
        // SQLite kaydedilen verileri okuyarak recyclerview'e eklendi
        holder.vB.imageButtonDeleteIcon.setOnClickListener {
            val alerd = AlertDialog.Builder(context)
            alerd.setTitle("Silme İşlemi")
            alerd.setMessage("Silmek istediğinize emin misiniz")
            alerd.setPositiveButton("evet"){dialogInterface, which ->
                CitiesDAO().deleteCities(vt,citiesList.get(position).Title)
                notifyItemRemoved(position)
                Toast.makeText(context,citiesList.get(position).Title + " Silindi",Toast.LENGTH_SHORT).show()
            }
            alerd.setNeutralButton("iptal"){dialogInterface , which ->  }

            val alertDialog: AlertDialog = alerd.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
        //istenilen verileri silmek için

    }
}