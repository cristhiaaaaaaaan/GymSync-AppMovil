package cr.ac.utn.census

import Controller.MembresiaController
import Controller.UserController
import Entity.Membresia
import Entity.User
import Util.SessionManager
import Util.Util
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class ProfileActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var personController: UserController
    private lateinit var membresiaController: MembresiaController

    private lateinit var imageViewAvatar: ImageView
    private lateinit var textViewUsername: TextView
    private lateinit var textViewEmail: TextView
    private lateinit var textViewMembershipStatus: TextView
    private lateinit var textViewExpiresIn: TextView
    private lateinit var textViewExpirationDate: TextView
    private lateinit var buttonEditProfile: Button
    private lateinit var buttonRenewMembership: Button

    private var currentUser: User? = null
    private var currentMembresia: Membresia? = null

    companion object {
        private const val GALLERY_PERMISSION_CODE = 101
    }

    // Activity result launcher for gallery
    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
                imageViewAvatar.setImageBitmap(bitmap)
                Toast.makeText(this, "Profile photo updated!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize controllers
        initControllers()

        // Initialize views
        initViews()

        // Setup listeners
        setupListeners()

        // Load user data
        loadUserData()
    }

    private fun initControllers() {
        sessionManager = SessionManager(this)
        personController = UserController(this)
        membresiaController = MembresiaController(this)
    }

    private fun initViews() {
        imageViewAvatar = findViewById(R.id.imageViewAvatar)
        textViewUsername = findViewById(R.id.textViewUsername)
        textViewEmail = findViewById(R.id.textViewEmail)
        textViewMembershipStatus = findViewById(R.id.textViewMembershipStatus)
        textViewExpiresIn = findViewById(R.id.textViewExpiresIn)
        textViewExpirationDate = findViewById(R.id.textViewExpirationDate)
        buttonEditProfile = findViewById(R.id.buttonEditProfile)
        buttonRenewMembership = findViewById(R.id.buttonRenewMembership)
    }

    private fun setupListeners() {
        // Edit profile photo
        imageViewAvatar.setOnClickListener {
            openGallery()
        }

        buttonEditProfile.setOnClickListener {
            // Could open an edit profile activity or dialog
            Toast.makeText(this, "Edit profile feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        buttonRenewMembership.setOnClickListener {
            showRenewMembershipDialog()
        }
    }

    private fun loadUserData() {
        try {
            // Get current user from session
            val userId = sessionManager.getUserId()
            val userName = sessionManager.getUserName()
            val userEmail = sessionManager.getUserEmail()

            if (userId == null || userName == null || userEmail == null) {
                Toast.makeText(this, "No user found. Please login again.", Toast.LENGTH_SHORT).show()
                return
            }

            // Create User object from session data
            currentUser = User(
                Id = userId,
                Name = userName,
                Email = userEmail,
                FotoPerfil = "",
                FechaRegistro = ""
            )

            // Display user info
            textViewUsername.text = userName
            textViewEmail.text = userEmail

            // Load membership data
            loadMembershipData()

        } catch (e: Exception) {
            Toast.makeText(this, "Error loading user data: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadMembershipData() {
        lifecycleScope.launch {
            try {
                currentUser?.let { user ->
                    currentMembresia = withContext(Dispatchers.IO) {
                        membresiaController.getMembresiaByUsuario(user.Id)
                    }

                    if (currentMembresia != null) {
                        displayMembershipInfo(currentMembresia!!)
                    } else {
                        // No active membership
                        textViewMembershipStatus.text = "No Active Membership"
                        textViewMembershipStatus.setTextColor(getColor(R.color.orange_accent))
                        textViewExpiresIn.text = "Subscribe to access all features"
                        textViewExpirationDate.text = ""
                        buttonRenewMembership.text = "Subscribe Now"
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this@ProfileActivity, "Error loading membership: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayMembershipInfo(membresia: Membresia) {
        // Check if membership is active
        val isActive = membresia.Activa && membresia.FechaVencimiento.after(Date())

        if (isActive) {
            textViewMembershipStatus.text = "${membresia.Tipo} - Active"
            textViewMembershipStatus.setTextColor(getColor(R.color.green_success))

            // Calculate days until expiration
            val daysUntilExpiration = calculateDaysUntilExpiration(membresia.FechaVencimiento)
            textViewExpiresIn.text = "Expires in $daysUntilExpiration days"

            // Format expiration date
            val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
            textViewExpirationDate.text = "Expiration date: ${dateFormat.format(membresia.FechaVencimiento)}"

            buttonRenewMembership.text = getString(R.string.renew_membership)
        } else {
            textViewMembershipStatus.text = "${membresia.Tipo} - Expired"
            textViewMembershipStatus.setTextColor(getColor(R.color.orange_accent))
            textViewExpiresIn.text = "Your membership has expired"

            val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
            textViewExpirationDate.text = "Expired on: ${dateFormat.format(membresia.FechaVencimiento)}"

            buttonRenewMembership.text = "Renew Now"
        }
    }

    private fun calculateDaysUntilExpiration(expirationDate: Date): Long {
        val currentDate = Date()
        val diffInMillis = expirationDate.time - currentDate.time
        return TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
    }

    private fun showRenewMembershipDialog() {
        val membershipTypes = arrayOf("Basic - $30/month", "Premium - $50/month", "VIP - $80/month")
        val prices = arrayOf(30.0, 50.0, 80.0)

        AlertDialog.Builder(this)
            .setTitle("Renew Membership")
            .setItems(membershipTypes) { dialog, which ->
                val selectedType = membershipTypes[which].split(" - ")[0]
                val selectedPrice = prices[which]
                confirmPayment(selectedType, selectedPrice)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun confirmPayment(membershipType: String, amount: Double) {
        AlertDialog.Builder(this)
            .setTitle("Confirm Payment")
            .setMessage("You are about to pay $$amount for $membershipType membership.\n\nDo you want to proceed?")
            .setPositiveButton("Pay Now") { dialog, _ ->
                processPayment(membershipType, amount)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun processPayment(membershipType: String, amount: Double) {
        lifecycleScope.launch {
            try {
                currentUser?.let { user ->
                    // Calculate new expiration date (1 month from now)
                    val calendar = Calendar.getInstance()
                    val fechaInicio = calendar.time
                    calendar.add(Calendar.MONTH, 1)
                    val fechaVencimiento = calendar.time

                    withContext(Dispatchers.IO) {
                        if (currentMembresia != null) {
                            // Update existing membership
                            currentMembresia!!.Tipo = membershipType
                            currentMembresia!!.FechaInicio = fechaInicio
                            currentMembresia!!.FechaVencimiento = fechaVencimiento
                            currentMembresia!!.Activa = true
                            currentMembresia!!.Monto = amount.toInt()

                            membresiaController.updateMembresia(currentMembresia!!)
                        } else {
                            // Create new membership
                            val newMembresia = Membresia(
                                Id = Util.generateId(),
                                UsuarioId = user.Id,
                                Tipo = membershipType,
                                FechaInicio = fechaInicio,
                                FechaVencimiento = fechaVencimiento,
                                Activa = true,
                                Monto = amount.toInt()
                            )
                            membresiaController.addMembresia(newMembresia)
                            currentMembresia = newMembresia
                        }
                    }

                    // Show success message
                    AlertDialog.Builder(this@ProfileActivity)
                        .setTitle("Payment Successful!")
                        .setMessage("Your $membershipType membership has been activated.\n\nThank you for your payment of $$amount!")
                        .setPositiveButton("OK") { dialog, _ ->
                            loadMembershipData() // Reload membership data
                            dialog.dismiss()
                        }
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ProfileActivity, "Payment failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openGallery() {
        if (checkGalleryPermission()) {
            galleryLauncher.launch("image/*")
        } else {
            requestGalleryPermission()
        }
    }

    private fun checkGalleryPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestGalleryPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            GALLERY_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GALLERY_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                Toast.makeText(this, "Gallery permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}