package com.rad.listnamestorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

import com.rad.listnamestorage.adapter.NombresAdapter
import java.util.Collections

class MainActivity : AppCompatActivity() {




    var storage=Firebase.storage

    val listRef=storage.reference.child("files/")


    val nombres = mutableListOf<String>()

    private lateinit var fileAdapter: NombresAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }



    fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerArchivo)

        recyclerView.layoutManager = LinearLayoutManager(this)




        val storageHelper = FirebasePantalla(listRef)
        storageHelper.getFileList { fileList ->
            fileAdapter = NombresAdapter(fileList)
            recyclerView.adapter = fileAdapter



        }


    }

}