package cr.ac.utn.census

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import Controller.*
import Entity.*
import Util.SessionManager
import Util.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class MainActivity : AppCompatActivity() {

    // Controladores
    private lateinit var sessionManager: SessionManager
    private lateinit var usuarioController: UserController
    private lateinit var rutinaController: RutinaController
    private lateinit var ejercicioController: EjercicioController
    private lateinit var registroAvanceController: RegistroAvanceController
    private lateinit var fotoProgresoController: FotoProgresoController
    private lateinit var membresiaController: MembresiaController
    private lateinit var logroController: LogroController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize SessionManager first
        sessionManager = SessionManager(this)

        // Check if user is logged in
        if (!sessionManager.isLoggedIn()) {
            navigateToLoginActivity()
            return
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar controladores
        initControllers()

        // Update welcome text with user name
        updateWelcomeText()

        // Cargar datos de prueba solo si es necesario
        loadTestDataIfNeeded()

        // Setup navigation buttons
        setupNavigationButtons()
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun updateWelcomeText() {
        val textViewWelcome = findViewById<TextView>(R.id.textViewWelcome)
        val userName = sessionManager.getUserName() ?: "User"
        textViewWelcome.text = "Welcome to GymSync, $userName!"
    }

    private fun setupNavigationButtons() {
        // Exercise Management button
        val buttonManageExercises = findViewById<Button>(R.id.buttonManageExercises)
        buttonManageExercises.setOnClickListener {
            val intent = Intent(this, ExerciseCrudActivity::class.java)
            startActivity(intent)
        }

        // Add Progress Photo button
        val buttonAddProgressPhoto = findViewById<Button>(R.id.buttonAddProgressPhoto)
        buttonAddProgressPhoto.setOnClickListener {
            val intent = Intent(this, ProgressPhotoActivity::class.java)
            startActivity(intent)
        }

        // View Photo List button
        val buttonViewPhotoList = findViewById<Button>(R.id.buttonViewPhotoList)
        buttonViewPhotoList.setOnClickListener {
            val intent = Intent(this, ProgressPhotoListActivity::class.java)
            startActivity(intent)
        }

        // My Workout Plan button
        val buttonMyWorkout = findViewById<Button>(R.id.buttonMyWorkout)
        buttonMyWorkout.setOnClickListener {
            val intent = Intent(this, WorkoutActivity::class.java)
            startActivity(intent)
        }

        // My Profile & Membership button
        val buttonMyProfile = findViewById<Button>(R.id.buttonMyProfile)
        buttonMyProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Logout button
        val buttonLogout = findViewById<Button>(R.id.buttonLogout)
        buttonLogout.setOnClickListener {
            showLogoutConfirmation()
        }
    }

    private fun showLogoutConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { dialog, _ ->
                performLogout()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun performLogout() {
        sessionManager.logout()
        navigateToLoginActivity()
    }

    private fun initControllers() {
        usuarioController = UserController(this)
        rutinaController = RutinaController(this)
        ejercicioController = EjercicioController(this)
        registroAvanceController = RegistroAvanceController(this)
        fotoProgresoController = FotoProgresoController(this)
        membresiaController = MembresiaController(this)
        logroController = LogroController(this)
    }

    private fun loadTestDataIfNeeded() {
        lifecycleScope.launch {
            try {
                // Only load test data if there are no users yet (first time setup)
                val usuarios = withContext(Dispatchers.IO) {
                    usuarioController.getUsuarios()
                }

                // If user is logged in, get their user object
                val userId = sessionManager.getUserId()
                val currentUser = usuarios.firstOrNull { it.Id == userId }

                if (currentUser == null && userId != null) {
                    // User session exists but user data doesn't - recreate from session
                    val usuario = User(
                        Id = userId,
                        Name = sessionManager.getUserName() ?: "User",
                        Email = sessionManager.getUserEmail() ?: "user@gymsync.com",
                        FotoPerfil = "",
                        FechaRegistro = Util.getCurrentDateString()
                    )
                    withContext(Dispatchers.IO) {
                        usuarioController.addUsuario(usuario)
                    }
                    loadExampleDataForUser(usuario)
                    return@launch
                }

                if (currentUser != null && usuarios.size == 1) {
                    // This is a new user, load example data
                    loadExampleDataForUser(currentUser)
                }
            } catch (e: Exception) {
                android.util.Log.e("MainActivity", "Error loading test data", e)
            }
        }
    }

    private fun loadExampleDataForUser(usuario: User) {
        lifecycleScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    // Crear ejercicios de prueba
                    val ejercicio1 = Ejercicio(
                        Id = Util.generateId(),
                        Nombre = "Press de Banca",
                        Series = 4,
                        Repeticiones = 12,
                        PesoRecomendado = 60,
                        Notas = "Ejercicio para pecho"
                    )
                    ejercicioController.addEjercicio(ejercicio1)

                    val ejercicio2 = Ejercicio(
                        Id = Util.generateId(),
                        Nombre = "Sentadillas",
                        Series = 4,
                        Repeticiones = 15,
                        PesoRecomendado = 80,
                        Notas = "Ejercicio para piernas"
                    )
                    ejercicioController.addEjercicio(ejercicio2)

                    val ejercicio3 = Ejercicio(
                        Id = Util.generateId(),
                        Nombre = "Peso Muerto",
                        Series = 3,
                        Repeticiones = 10,
                        PesoRecomendado = 100,
                        Notas = "Ejercicio para espalda baja"
                    )
                    ejercicioController.addEjercicio(ejercicio3)

                    // Crear rutina de prueba
                    val rutina = Rutina(
                        Id = Util.generateId(),
                        UsuarioId = usuario.Id,
                        Fecha = Util.getCurrentDateString(),
                        Nombre = "Rutina de Fuerza",
                        Ejercicios = listOf(ejercicio1.Id, ejercicio2.Id, ejercicio3.Id),
                        Completada = false
                    )
                    rutinaController.addRutina(rutina)

                    // Crear membresía de prueba
                    val fechaInicio = Util.getCurrentDate()
                    val calendar = java.util.Calendar.getInstance()
                    calendar.time = fechaInicio
                    calendar.add(java.util.Calendar.MONTH, 1)
                    val fechaVencimiento = calendar.time

                    val membresia = Membresia(
                        Id = Util.generateId(),
                        UsuarioId = usuario.Id,
                        Tipo = "Premium",
                        FechaInicio = fechaInicio,
                        FechaVencimiento = fechaVencimiento,
                        Activa = true,
                        Monto = 50000
                    )
                    membresiaController.addMembresia(membresia)

                    // Crear registro de avance de prueba
                    val registroAvance = RegistroAvance(
                        Id = Util.generateId(),
                        EjercicioId = ejercicio1.Id,
                        UsuarioId = usuario.Id,
                        Fecha = Util.getCurrentDateString(),
                        PesoUtilizado = 55.0,
                        RepeticionesRealizadas = 12,
                        SeriesCompletadas = 4,
                        Notas = "Registro de prueba"
                    )
                    withContext(Dispatchers.IO) {
                        registroAvanceController.addRegistroAvance(registroAvance)
                    }

                    // Create test progress photo (with null bitmap for now)
                    val fotoProgreso = FotoProgreso(
                        id = Util.generateId(),
                        usuarioId = usuario.Id,
                        fecha = Util.getCurrentDate(),
                        photo = null,
                        nota = "First progress photo"
                    )
                    fotoProgresoController.addFotoProgreso(fotoProgreso)

                    // Crear logro de prueba
                    val logro = Logro(
                        id = Util.generateId(),
                        usuarioId = usuario.Id,
                        titulo = "Primera Rutina",
                        descripcion = "Completaste tu primera rutina en GymSync",
                        fecha = Util.getCurrentDate(),
                        icono = "trophy"
                    )
                    logroController.addLogro(logro)
                }
            } catch (e: Exception) {
                android.util.Log.e("MainActivity", "Error loading example data", e)
            }
        }
    }
}