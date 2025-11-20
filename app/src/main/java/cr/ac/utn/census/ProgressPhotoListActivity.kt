package cr.ac.utn.census

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import Controller.FotoProgresoController

class ProgressPhotoListActivity : AppCompatActivity() {

    private lateinit var fotoProgresoController: FotoProgresoController
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProgressPhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_progress_photo_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize controller
        fotoProgresoController = FotoProgresoController(this)

        // Setup RecyclerView
        setupRecyclerView()

        // Load data
        loadProgressPhotos()
    }

    override fun onResume() {
        super.onResume()
        // Reload data when returning to this activity
        loadProgressPhotos()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.rvProgressPhotos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProgressPhotoAdapter(emptyList())
        recyclerView.adapter = adapter
    }

    private fun loadProgressPhotos() {
        val photos = fotoProgresoController.getFotosProgreso()
        adapter.updateData(photos)
    }
}
