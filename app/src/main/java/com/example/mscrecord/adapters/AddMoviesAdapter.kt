package com.example.mscrecord.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mscrecord.sqlitedao.MoviesDAO
import com.example.mscrecord.sqlitedatahelper.MoviesSQLiteDH
import com.example.mscrecord.databinding.AddMoviesRowBinding

class AddMoviesAdapter(val context : Context,val moviesNameList : ArrayList<String>,val moviesYearList : ArrayList<String>,val moviesPosterList  : ArrayList<String>) : RecyclerView.Adapter<AddMoviesAdapter.AddMoviesVH>() {
    class AddMoviesVH(val vB : AddMoviesRowBinding)  : RecyclerView.ViewHolder(vB.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddMoviesVH {
        val view = AddMoviesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddMoviesVH(view)
    }

    override fun getItemCount(): Int {
        return moviesNameList.size
    }

    override fun onBindViewHolder(holder: AddMoviesVH, position: Int) {
        holder.vB.textViewTitles.text = moviesNameList.get(position)
        holder.vB.textViewYears.text = moviesYearList.get(position)
        Glide.with(context).load(moviesPosterList.get(position)).into(holder.vB.imageViews)
        // mainde oluşturulan sınıfları burada çağırıp recyclerview'e veriler eklendi
        holder.vB.moviesCardView.setOnClickListener {
            val vt = MoviesSQLiteDH(context)
            if(MoviesDAO().controlMovies(vt,moviesNameList.get(position)) == 0){
                MoviesDAO().addMovies(vt,moviesNameList.get(position),moviesYearList.get(position),moviesPosterList.get(position) )
                Toast.makeText(context,"${moviesNameList.get(position)} eklendi", Toast.LENGTH_SHORT).show()
                //eklenen veri kaç tane tespit edilip eğer eklendiyse tekrar eklememk için kod

            }else{
                Toast.makeText(context,"${moviesNameList.get(position)} zaten eklendi",Toast.LENGTH_SHORT).show()
            }
        }

    }
}