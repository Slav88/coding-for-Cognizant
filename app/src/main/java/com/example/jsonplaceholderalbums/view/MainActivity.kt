package com.example.jsonplaceholderalbums.view

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.jsonplaceholderalbums.R
import com.example.jsonplaceholderalbums.model.Album
import com.example.jsonplaceholderalbums.model.AppDatabase
import com.example.jsonplaceholderalbums.viewmodel.CustomAdapter
import com.example.jsonplaceholderalbums.viewmodel.CustomViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var albums: ArrayList<Album> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager =
            LinearLayoutManager(this)

        val connection = isConnectedToNetwork(this)
        //checks connection
        if (connection) {
            //With network connection
            val viewModel = ViewModelProvider(this)
                .get(CustomViewModel::class.java)

            viewModel.getData().observe(this,
                Observer {

                    //sorts titles alphabetically
                    val reponse = it
                    reponse.sortWith(compareBy { album -> album.title })

                    val adapter = CustomAdapter(reponse)
                    recycler_view.adapter = adapter

                    //stores into room database
                    val dB = AppDatabase.invoke(this).albumDao()
                    GlobalScope.launch {
                        for (i in it.indices)
                            dB.delete(Album(it[i].id, it[i].title))

                        for (i in it.indices)
                            dB.insert(Album(it[i].id, it[i].title))
                    }
                })
        } else {
            //Without network connection
            val db = AppDatabase(this).albumDao()
            //Foreground thread
            GlobalScope.launch(Dispatchers.Main) {
                //Background threading
                withContext(Dispatchers.IO) {
                    db.getAll().forEach {
                        albums.add(it)
                    }
                }
                //sorts titles alphabetically
                val reponse = albums
                reponse.sortWith(compareBy { album -> album.title })

                val adapter = CustomAdapter(albums)
                recycler_view.adapter = adapter
            }

        }
    }


    //function checks for connectivity
    private fun isConnectedToNetwork(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isConnected = false
        val activeNetwork = connectivityManager.getActiveNetworkInfo()
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting()
        return isConnected
    }
}
