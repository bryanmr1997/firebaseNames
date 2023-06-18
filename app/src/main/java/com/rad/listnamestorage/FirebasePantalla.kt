package com.rad.listnamestorage

import com.google.firebase.storage.StorageReference

class FirebasePantalla (private val storageRef:StorageReference) {

    fun getFileList(onSuccess:(List<Archivo>) -> Unit){
        val fileList: MutableList<Archivo> = mutableListOf()



        storageRef.listAll().addOnSuccessListener { listResult ->
            listResult.items.forEach { item ->
                val fileName = item.name
                item.downloadUrl.addOnSuccessListener { uri ->
                    val fileUrl = uri.toString()
                    fileList.add(Archivo(fileName,fileUrl))

                    // Verificar si se ha agregado todos los archivos
                    if (fileList.size == listResult.items.size) {
                        onSuccess(fileList)
                    }
                }

            }
        }
    }
}