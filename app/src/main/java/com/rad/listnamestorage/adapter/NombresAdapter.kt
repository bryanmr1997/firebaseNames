package com.rad.listnamestorage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rad.listnamestorage.Archivo
import com.rad.listnamestorage.R

class NombresAdapter(private val archivo: List<Archivo>) : RecyclerView.Adapter<NombresViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NombresViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return NombresViewHolder(layoutInflater.inflate(R.layout.item_nombre,parent,false))

    }

    override fun onBindViewHolder(holder: NombresViewHolder, position: Int) {
            val item =archivo[position]
            holder.render(item)
    }

    override fun getItemCount(): Int = archivo.size
}