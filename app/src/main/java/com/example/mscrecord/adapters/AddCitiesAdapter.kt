package com.example.mscrecord.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mscrecord.sqlitedao.CitiesDAO
import com.example.mscrecord.sqlitedatahelper.CitiesSQLiteDH
import com.example.mscrecord.databinding.AddCitiesRowBinding

class AddCitiesAdapter(val context : Context,val citiesNameList : ArrayList<String>,val citiesImageList : ArrayList<String>,val citiesRegionList : ArrayList<String>): RecyclerView.Adapter<AddCitiesAdapter.AddCitiesVH>() {
    class AddCitiesVH(var vB : AddCitiesRowBinding) : RecyclerView.ViewHolder(vB.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddCitiesVH {
        val view = AddCitiesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddCitiesVH(view)
    }

    override fun getItemCount(): Int {
        return citiesNameList.size
    }

    override fun onBindViewHolder(holder: AddCitiesVH, position: Int) {
        Glide.with(context).load(citiesImageList.get(position)).into(holder.vB.imageViews)
        holder.vB.textViewName.text = citiesNameList.get(position)
        holder.vB.textViewRegion.text = citiesRegionList.get(position)
        // mainde oluşturulan sınıfları burada çağırıp recyclerview'e veriler eklendi
        holder.vB.citiesCardView.setOnClickListener {
            val vt = CitiesSQLiteDH(context)
            if(CitiesDAO().controlCities(vt,citiesNameList.get(position)).toInt() == 0){
                CitiesDAO().addCities(vt,citiesNameList.get(position),citiesRegionList.get(position),citiesImageList.get(position))
                Toast.makeText(context,"${citiesNameList.get(position)} eklendi",Toast.LENGTH_SHORT).show()
                //eklenen veri kaç tane tespit edilip eğer eklendiyse tekrar eklememk için kod
            }else{
                Toast.makeText(context,"${citiesNameList.get(position)} zaten eklendi",Toast.LENGTH_SHORT).show()
            }

        }

    }
}