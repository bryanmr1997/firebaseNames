package com.rad.listnamestorage.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rad.listnamestorage.Archivo
import com.rad.listnamestorage.R

class NombresViewHolder(view:View):RecyclerView.ViewHolder(view){

    val name=view.findViewById<TextView>(R.id.tvNombre)

    val btn_mostrar=view.findViewById<Button>(R.id.btn_ads)

    fun render(model:Archivo){
        name.text=model.nombre
    }
}