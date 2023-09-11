package com.example.mscrecord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mscrecord.adapters.MyCitiesAdapter
import com.example.mscrecord.adapters.MyMoviesAdapter
import com.example.mscrecord.databinding.ActivityMyMoviesBinding

class MyMovies : AppCompatActivity() {
    private lateinit var vB : ActivityMyMoviesBinding
    val myMovies = MyMoviesAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        vB = ActivityMyMoviesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(vB.root)


            vB.myMoviesReyclerView.adapter = MyMoviesAdapter(this@MyMovies)
            vB.myMoviesReyclerView.layoutManager = LinearLayoutManager(this@MyMovies)
            // silme işleminde sonra sayfa yenilenmesi ve silinen verinin görünümden gitmesi içim
            if(myMovies.itemCount == 0){
                vB.textViewEmty2.text = "İzlenen Film Yok"
            }
            // yenileme yapıldığında veri sayısı 0'sa yazıyı yazdırmak için


         //Intentions
        vB.buttonToAddMovies.setOnClickListener {
            val intent = Intent(this@MyMovies,AddMovies :: class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
         super.onStart()
        vB.myMoviesReyclerView.adapter = MyMoviesAdapter(this@MyMovies)
        vB.myMoviesReyclerView.layoutManager = LinearLayoutManager(this@MyMovies)

        if(myMovies.itemCount >= 1){
            vB.textViewEmty2.text = ""
        }
        // veri girilince izlenen film yok yazısı boş değer alıyor
        // on start film eklendiğinde anlık eklensin tekrar en başa dönüp geri gelinmemesi için
    }
}