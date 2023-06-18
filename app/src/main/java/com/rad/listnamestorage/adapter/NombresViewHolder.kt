package com.rad.listnamestorage.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.ar.sceneform.rendering.ModelRenderable
import com.rad.listnamestorage.Archivo
import com.rad.listnamestorage.CustomARFragmet
import com.rad.listnamestorage.R

class NombresViewHolder(view:View):RecyclerView.ViewHolder(view){


    val name=view.findViewById<TextView>(R.id.tvNombre)

    val btn_mostrar=view.findViewById<Button>(R.id.btn_ads)

    fun render(model:Archivo,buttonClickListener: (String, String) -> Unit){

        val fileName=model.nombre
        val url=model.fileUrl
        name.text=model.nombre

        btn_mostrar.setOnClickListener {




            buttonClickListener.invoke(fileName, url)
        }

    }
}