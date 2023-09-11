package com.example.mscrecord.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mscrecord.databinding.AddSeriesRowBinding
import com.example.mscrecord.sqlitedao.SeriesDAO
import com.example.mscrecord.sqlitedatahelper.SeriesSQLiteDH

class AddSeriesAdapter(val context : Context, val seriesNameList : ArrayList<String>, val seriesYearList : ArrayList<String>, val seriesPosterList  : ArrayList<String>) : RecyclerView.Adapter<AddSeriesAdapter.AddSeriesVH>(){
    class AddSeriesVH(val vB : AddSeriesRowBinding) : RecyclerView.ViewHolder(vB.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddSeriesVH {
        val view = AddSeriesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddSeriesVH(view)
    }

    override fun getItemCount(): Int {
        return seriesNameList.size
    }

    override fun onBindViewHolder(holder: AddSeriesVH, position: Int) {
        holder.vB.textViewTitles.text = seriesNameList.get(position)
        holder.vB.textViewYears.text = seriesYearList.get(position)
        Glide.with(context).load(seriesPosterList.get(position)).into(holder.vB.imageViews)
        // mainde oluşturulan sınıfları burada çağırıp recyclerview'e veriler eklendi
        holder.vB.seriesCardView.setOnClickListener {
            val vt = SeriesSQLiteDH(context)
            if(SeriesDAO().controlSeries(vt,seriesNameList.get(position)) == 0){
                SeriesDAO().addSerie(vt,seriesNameList.get(position),seriesYearList.get(position),seriesPosterList.get(position))
                Toast.makeText(context,"${seriesNameList.get(position)} eklendi", Toast.LENGTH_SHORT).show()
                //eklenen veri kaç tane tespit edilip eğer eklendiyse tekrar eklememk için kod
            }else{
                Toast.makeText(context,"${seriesNameList.get(position)} zaten eklendi",Toast.LENGTH_SHORT).show()
            }

        }
    }
}