package com.rad.listnamestorage.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.rad.listnamestorage.Archivo
import com.rad.listnamestorage.CustomARFragmet
import com.rad.listnamestorage.R

class NombresAdapter(
    private val context: Context?,
    private val archivo: List<Archivo>,
    private val buttonClickListener: (String, String) -> Unit,
    private var arFragment: CustomARFragmet
    ) : RecyclerView.Adapter<NombresViewHolder>() {
    private var isFragmentVisible: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NombresViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NombresViewHolder(layoutInflater.inflate(R.layout.item_nombre, parent, false))
    }

    override fun onBindViewHolder(holder: NombresViewHolder, position: Int) {


        val item = archivo[position]


        holder.render(item, buttonClickListener)
        Log.e("RADELOZO_adapter", " paso todo")


    }

    override fun getItemCount(): Int = archivo.size


    fun setFragmentVisibility(visible: Boolean) {
        isFragmentVisible = visible
        notifyDataSetChanged()
    }

}