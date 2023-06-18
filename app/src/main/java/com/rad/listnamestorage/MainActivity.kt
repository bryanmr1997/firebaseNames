package com.rad.listnamestorage

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.TransformableNode
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

import com.rad.listnamestorage.adapter.NombresAdapter
import com.rad.listnamestorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var uxFragment: CustomARFragmet
    private lateinit var binding: ActivityMainBinding
    var renderable: ModelRenderable? = null

    private var isFragmentVisible = false

    private lateinit var recyclerView: RecyclerView

    private val storage = Firebase.storage
    private val listRef = storage.reference.child("files/")

    private lateinit var fileAdapter: NombresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar el fragmento AR
        uxFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment) as CustomARFragmet

        // Desactivar renderizado de planos
       // uxFragment.arSceneView.planeRenderer.isEnabled = true

        // Ocultar el fragmento AR
        supportFragmentManager.beginTransaction()
            .hide(uxFragment)
            .commit()

        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerArchivo)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val storageHelper = FirebasePantalla(listRef)
        storageHelper.getFileList { fileList ->
            fileAdapter = NombresAdapter(this, fileList,::onButtonClick ,uxFragment)
            recyclerView.adapter = fileAdapter
        }
        Log.e("RADELOZO15", " $uxFragment")



    }
    private fun onButtonClick(fileName: String, fileUrl: String) {


        toggleFragmentVisibility()


        Log.e("RADELOZO50", " $fileUrl")
        downloadModel(fileUrl)

        uxFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            if (renderable == null) return@setOnTapArPlaneListener

            val anchor: Anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(uxFragment.arSceneView.scene)

            val node = TransformableNode(uxFragment.transformationSystem)
            node.renderable = renderable
            node.scaleController.minScale = 0.06f
            node.scaleController.maxScale = 1.0f
            node.worldScale = Vector3(0.5f, 0.5f, 0.5f)
            node.setParent(anchorNode)
            node.select()
        }





        // Realiza las acciones adicionales según sea necesario
    }
    private fun toggleFragmentVisibility() {
        isFragmentVisible = !isFragmentVisible
        if (isFragmentVisible) {
            supportFragmentManager.beginTransaction()
                .show(uxFragment)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .hide(uxFragment)
                .commit()
        }
        fileAdapter.setFragmentVisibility(isFragmentVisible)
    }

    private fun downloadModel(url: String?) {


        val renderableSource = RenderableSource.builder()
            .setSource(this, Uri.parse(url), RenderableSource.SourceType.GLB)
            .setRecenterMode(RenderableSource.RecenterMode.CENTER)
            .build()

        ModelRenderable.builder()
            .setSource(this, renderableSource)
            .build()
            .thenAccept { modelRenderable ->
                renderable=modelRenderable
                val toast = Toast.makeText(
                    this,
                    "Descarga completa",
                    Toast.LENGTH_LONG
                )
                toast.show()
            }
            .exceptionally { throwable ->
                val toast = Toast.makeText(
                    this,
                    "No se pudo descargar el modelo 3D, comprueba la conexión a internet",
                    Toast.LENGTH_LONG
                )
                toast.show()
                return@exceptionally null
            }
    }
}
