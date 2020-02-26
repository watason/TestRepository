package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class RowAdapter(private val data: MutableList<String>,val itemClickListener : RowAdapter.ViewHolder.itemClickListener) : RecyclerView.Adapter<RowAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return data.count()
    }


    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        holder.mText.text = data[position]
        holder.v.setOnClickListener {
            itemClickListener.OnItemClick(it,position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.my_text_view,parent,false)
        v.setOnClickListener {

        }
        return ViewHolder(v)
    }

    class ViewHolder(val v : View) : RecyclerView.ViewHolder(v){
        interface itemClickListener{
            fun OnItemClick(v : View,pos : Int)
        }
        var mText : TextView = v.findViewById(R.id.text_view)
    }
}

