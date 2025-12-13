package cr.ac.utn.census

import Controller.EjercicioController
import Controller.UserController
import Controller.RutinaController
import Controller.RegistroAvanceController
import Entity.Ejercicio
import Entity.RegistroAvance
import Util.SessionManager
import Util.Util
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class WorkoutActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var rutinaController: RutinaController
    private lateinit var ejercicioController: EjercicioController
    private lateinit var personController: UserController
    private lateinit var registroAvanceController: RegistroAvanceController

    private lateinit var textViewGreeting: TextView
    private lateinit var textViewStreak: TextView
    private lateinit var textViewProgressCount: TextView
    private lateinit var progressBarWorkout: ProgressBar
    private lateinit var linearLayoutExercises: LinearLayout

    private var ejerciciosDeLaRutina: List<Ejercicio> = emptyList()
    private var ejerciciosCompletados = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_workout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize controllers
        initControllers()

        // Initialize views
        initViews()

        // Load workout data
        loadWorkoutData()
    }

    private fun initControllers() {
        sessionManager = SessionManager(this)
        rutinaController = RutinaController(this)
        ejercicioController = EjercicioController(this)
        personController = UserController(this)
        registroAvanceController = RegistroAvanceController(this)
    }

    private fun initViews() {
        textViewGreeting = findViewById(R.id.textViewGreeting)
        textViewStreak = findViewById(R.id.textViewStreak)
        textViewProgressCount = findViewById(R.id.textViewProgressCount)
        progressBarWorkout = findViewById(R.id.progressBarWorkout)
        linearLayoutExercises = findViewById(R.id.linearLayoutExercises)
    }

    private fun loadWorkoutData() {
        // Get current user from session
        val userId = sessionManager.getUserId()
        val userName = sessionManager.getUserName()

        if (userId == null || userName == null) {
            Toast.makeText(this, "No user found. Please login again.", Toast.LENGTH_SHORT).show()
            return
        }

        // Set greeting
        textViewGreeting.text = getString(R.string.greeting_hello, userName)

        // Set streak (example value, could be calculated from user data)
        textViewStreak.text = "7 🔥"

        // Launch coroutine for network operations
        lifecycleScope.launch {
            try {
                // Get today's routine (in background thread)
                val rutinas = withContext(Dispatchers.IO) {
                    rutinaController.getRutinas()
                }

                val hoy = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

                val rutinaDeHoy = rutinas.firstOrNull { rutina ->
                    // Extract date portion from ISO string (YYYY-MM-DD)
                    val rutinaFecha = rutina.Fecha.take(10)
                    rutinaFecha == hoy && rutina.UsuarioId == userId
                }

                if (rutinaDeHoy != null) {
                    // Load exercises for this routine
                    loadExercisesForRoutine(rutinaDeHoy.Ejercicios)
                } else {
                    // No routine for today, load user's exercises
                    val ejercicios = withContext(Dispatchers.IO) {
                        ejercicioController.getEjerciciosByUsuario(userId)
                    }
                    ejerciciosDeLaRutina = ejercicios
                    displayExercises()
                }

            } catch (e: Exception) {
                Toast.makeText(this@WorkoutActivity, "Error loading workout: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadExercisesForRoutine(ejercicioIds: List<String>) {
        lifecycleScope.launch {
            try {
                ejerciciosDeLaRutina = withContext(Dispatchers.IO) {
                    ejercicioIds.mapNotNull { id ->
                        try {
                            ejercicioController.getEjercicioById(id)
                        } catch (e: Exception) {
                            null
                        }
                    }
                }
                displayExercises()
            } catch (e: Exception) {
                Toast.makeText(this@WorkoutActivity, "Error loading exercises: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayExercises() {
        linearLayoutExercises.removeAllViews()

        if (ejerciciosDeLaRutina.isEmpty()) {
            val emptyView = TextView(this).apply {
                text = "No exercises for today. Add exercises to your routine!"
                textSize = 16f
                setTextColor(Color.parseColor("#B0B0B0"))
                setPadding(16, 32, 16, 32)
            }
            linearLayoutExercises.addView(emptyView)
            updateProgress()
            return
        }

        ejerciciosDeLaRutina.forEachIndexed { index, ejercicio ->
            val exerciseCard = createExerciseCard(index + 1, ejercicio)
            linearLayoutExercises.addView(exerciseCard)
        }

        updateProgress()
    }

    private fun createExerciseCard(position: Int, ejercicio: Ejercicio): CardView {
        val cardView = CardView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = 24
            }
            radius = 24f
            cardElevation = 4f
            setCardBackgroundColor(Color.parseColor("#2A2A2A"))
        }

        val inflater = LayoutInflater.from(this)
        val contentLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(32, 32, 32, 32)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        // Number badge
        val numberBadge = TextView(this).apply {
            text = position.toString()
            textSize = 18f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#7C3AED"))
            gravity = android.view.Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(80, 80)
        }

        // Exercise info layout
        val infoLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            ).apply {
                marginStart = 24
            }
        }

        val exerciseName = TextView(this).apply {
            text = ejercicio.Nombre
            textSize = 16f
            setTextColor(Color.parseColor("#E5E5E5"))
            setTypeface(null, android.graphics.Typeface.BOLD)
        }

        val exerciseDetails = TextView(this).apply {
            val peso = if (ejercicio.PesoRecomendado > 0) " - ${ejercicio.PesoRecomendado}kg" else ""
            text = "${ejercicio.Series} series x ${ejercicio.Repeticiones} reps$peso"
            textSize = 14f
            setTextColor(Color.parseColor("#B0B0B0"))
            setPadding(0, 8, 0, 0)
        }

        infoLayout.addView(exerciseName)
        infoLayout.addView(exerciseDetails)

        contentLayout.addView(numberBadge)
        contentLayout.addView(infoLayout)

        cardView.addView(contentLayout)

        // Add click listener to record progress
        cardView.setOnClickListener {
            showRecordProgressDialog(ejercicio)
        }

        return cardView
    }

    private fun showRecordProgressDialog(ejercicio: Ejercicio) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_exercise, null)
        val editTextSets = dialogView.findViewById<TextInputEditText>(R.id.editTextSets)
        val editTextReps = dialogView.findViewById<TextInputEditText>(R.id.editTextReps)
        val editTextWeight = dialogView.findViewById<TextInputEditText>(R.id.editTextWeight)
        val editTextNotes = dialogView.findViewById<TextInputEditText>(R.id.editTextNotes)
        val editTextName = dialogView.findViewById<TextInputEditText>(R.id.editTextExerciseName)

        // Pre-fill with recommended values
        editTextName?.apply {
            setText(ejercicio.Nombre)
            isEnabled = false
        }
        editTextSets?.setText(ejercicio.Series.toString())
        editTextReps?.setText(ejercicio.Repeticiones.toString())
        editTextWeight?.setText(ejercicio.PesoRecomendado.toString())

        AlertDialog.Builder(this)
            .setTitle("Record Progress: ${ejercicio.Nombre}")
            .setView(dialogView)
            .setPositiveButton("Save") { dialog, _ ->
                val userId = sessionManager.getUserId()
                if (userId == null) {
                    Toast.makeText(this, "No user session found", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                val sets = editTextSets?.text.toString().trim()
                val reps = editTextReps?.text.toString().trim()
                val weight = editTextWeight?.text.toString().trim()
                val notes = editTextNotes?.text.toString().trim()

                // Validate input
                if (sets.isEmpty() || reps.isEmpty() || weight.isEmpty()) {
                    Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                // Create RegistroAvance object
                val registro = RegistroAvance(
                    Id = Util.generateId(),
                    EjercicioId = ejercicio.Id,
                    UsuarioId = userId,
                    Fecha = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).format(Date()),
                    PesoUtilizado = weight.toDoubleOrNull() ?: 0.0,
                    RepeticionesRealizadas = reps.toIntOrNull() ?: 0,
                    SeriesCompletadas = sets.toIntOrNull() ?: 0,
                    Notas = notes
                )

                // Save to API
                lifecycleScope.launch {
                    try {
                        withContext(Dispatchers.IO) {
                            registroAvanceController.addRegistroAvance(registro)
                        }
                        ejerciciosCompletados++
                        updateProgress()
                        Toast.makeText(this@WorkoutActivity, "Progress saved successfully!", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Toast.makeText(this@WorkoutActivity, "Error saving progress: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun updateProgress() {
        val total = ejerciciosDeLaRutina.size
        val completed = ejerciciosCompletados

        textViewProgressCount.text = "$completed/$total"

        if (total > 0) {
            val progressPercent = (completed * 100) / total
            progressBarWorkout.progress = progressPercent
        } else {
            progressBarWorkout.progress = 0
        }
    }
}
