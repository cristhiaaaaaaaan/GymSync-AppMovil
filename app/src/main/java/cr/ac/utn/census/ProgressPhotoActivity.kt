package cr.ac.utn.census

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import Controller.FotoProgresoController
import Controller.UserController
import Entity.FotoProgreso
import Util.SessionManager
import Util.Util

class ProgressPhotoActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var fotoProgresoController: FotoProgresoController
    private lateinit var personController: UserController

    private lateinit var imgPhoto: ImageView
    private lateinit var editTextNote: EditText
    private lateinit var btnSelectGallery: ImageButton
    private lateinit var btnTakePhoto: ImageButton
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    private var selectedBitmap: Bitmap? = null

    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
    }

    // Activity result launcher for gallery
    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
                selectedBitmap = bitmap
                imgPhoto.setImageBitmap(bitmap)
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.error_load_image), Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Activity result launcher for camera
    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val bitmap = result.data?.extras?.get("data") as? Bitmap
            bitmap?.let {
                selectedBitmap = it
                imgPhoto.setImageBitmap(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_progress_photo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize controllers
        initControllers()

        // Initialize views
        initViews()

        // Setup button listeners
        setupListeners()
    }

    private fun initControllers() {
        sessionManager = SessionManager(this)
        fotoProgresoController = FotoProgresoController(this)
        personController = UserController(this)
    }

    private fun initViews() {
        imgPhoto = findViewById(R.id.imgPhoto)
        editTextNote = findViewById(R.id.editTextNote)
        btnSelectGallery = findViewById(R.id.btnSelectGallery)
        btnTakePhoto = findViewById(R.id.btnTakePhoto)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)
    }

    private fun setupListeners() {
        // Gallery button click
        btnSelectGallery.setOnClickListener {
            openGallery()
        }

        // Camera button click
        btnTakePhoto.setOnClickListener {
            if (checkCameraPermission()) {
                openCamera()
            } else {
                requestCameraPermission()
            }
        }

        // Save button click
        btnSave.setOnClickListener {
            saveProgressPhoto()
        }

        // Cancel button click
        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun openGallery() {
        galleryLauncher.launch("image/*")
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(intent)
    }

    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                Toast.makeText(this, getString(R.string.error_camera_permission), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveProgressPhoto() {
        // Validate that an image has been selected
        if (selectedBitmap == null) {
            Toast.makeText(this, getString(R.string.progress_photo_select_image), Toast.LENGTH_SHORT).show()
            return
        }

        // Get current user from session
        val usuarioId = sessionManager.getUserId()
        if (usuarioId == null) {
            Toast.makeText(this, "No user found. Please login again.", Toast.LENGTH_SHORT).show()
            return
        }

        val note = editTextNote.text.toString()

        // Create and save the progress photo with bitmap
        val fotoProgreso = FotoProgreso(
            id = Util.generateId(),
            usuarioId = usuarioId,
            fecha = Util.getCurrentDate(),
            photo = selectedBitmap,
            nota = note
        )

        try {
            fotoProgresoController.addFotoProgreso(fotoProgreso)
            Toast.makeText(this, getString(R.string.progress_photo_saved), Toast.LENGTH_SHORT).show()
            finish()
        } catch (e: Exception) {
            Toast.makeText(this, getString(R.string.error_add_data), Toast.LENGTH_SHORT).show()
        }
    }
}
