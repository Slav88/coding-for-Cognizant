package com.example.jsonplaceholderalbums.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholderalbums.R
import com.example.jsonplaceholderalbums.model.Album
import com.example.jsonplaceholderalbums.view.MainActivity

class CustomAdapter(private val dataSet: ArrayList<Album>) : RecyclerView.Adapter<CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater
                .from(parent.context).inflate(
                    R.layout.item_layout,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.albumTitle.text =  dataSet[position].title
    }
}