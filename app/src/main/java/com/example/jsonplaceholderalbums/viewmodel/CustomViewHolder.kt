package com.example.jsonplaceholderalbums.viewmodel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholderalbums.R
import kotlinx.android.synthetic.main.item_layout.view.*

class CustomViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    val albumTitle: TextView = itemView.findViewById(R.id.tv_album_title)
}