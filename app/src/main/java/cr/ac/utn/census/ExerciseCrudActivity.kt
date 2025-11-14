package cr.ac.utn.census

import Controller.EjercicioController
import Entity.Ejercicio
import Util.Util
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import android.view.View
import android.widget.TextView

class ExerciseCrudActivity : AppCompatActivity() {

    private lateinit var exerciseController: EjercicioController
    private lateinit var exerciseAdapter: ExerciseAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var editTextSearch: EditText
    private lateinit var fabAddExercise: FloatingActionButton
    private lateinit var textViewNoExercises: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_crud)

        // Initialize controller
        exerciseController = EjercicioController(this)

        // Initialize views
        initViews()

        // Setup RecyclerView
        setupRecyclerView()

        // Setup search functionality
        setupSearch()

        // Setup FAB
        setupFab()

        // Load exercises
        loadExercises()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerViewExercises)
        editTextSearch = findViewById(R.id.editTextSearch)
        fabAddExercise = findViewById(R.id.fabAddExercise)
        textViewNoExercises = findViewById(R.id.textViewNoExercises)
    }

    private fun setupRecyclerView() {
        exerciseAdapter = ExerciseAdapter(
            mutableListOf(),
            onEditClick = { exercise -> showEditDialog(exercise) },
            onDeleteClick = { exercise -> showDeleteConfirmationDialog(exercise) }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = exerciseAdapter
    }

    private fun setupSearch() {
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                exerciseAdapter.filter(s.toString())
                updateEmptyState()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupFab() {
        fabAddExercise.setOnClickListener {
            showAddDialog()
        }
    }

    private fun loadExercises() {
        try {
            val exercises = exerciseController.getEjercicios()
            exerciseAdapter.updateExercises(exercises)
            updateEmptyState()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateEmptyState() {
        if (exerciseAdapter.itemCount == 0) {
            textViewNoExercises.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            textViewNoExercises.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun showAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_exercise, null)
        val editTextName = dialogView.findViewById<TextInputEditText>(R.id.editTextExerciseName)
        val editTextSets = dialogView.findViewById<TextInputEditText>(R.id.editTextSets)
        val editTextReps = dialogView.findViewById<TextInputEditText>(R.id.editTextReps)
        val editTextWeight = dialogView.findViewById<TextInputEditText>(R.id.editTextWeight)
        val editTextNotes = dialogView.findViewById<TextInputEditText>(R.id.editTextNotes)

        AlertDialog.Builder(this)
            .setTitle(R.string.dialog_add_exercise_title)
            .setView(dialogView)
            .setPositiveButton(R.string.dialog_save) { dialog, _ ->
                val name = editTextName.text.toString().trim()
                val setsStr = editTextSets.text.toString().trim()
                val repsStr = editTextReps.text.toString().trim()
                val weightStr = editTextWeight.text.toString().trim()
                val notes = editTextNotes.text.toString().trim()

                if (validateInput(name, setsStr, repsStr, weightStr)) {
                    val exercise = Ejercicio(
                        id = Util.generateId(),
                        nombre = name,
                        series = setsStr.toInt(),
                        repeticiones = repsStr.toInt(),
                        pesoRecomendado = weightStr.toDouble(),
                        notas = notes
                    )

                    try {
                        exerciseController.addEjercicio(exercise)
                        loadExercises()
                        Toast.makeText(
                            this,
                            R.string.exercise_added_successfully,
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: Exception) {
                        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(
                        this,
                        R.string.exercise_validation_error,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton(R.string.dialog_cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showEditDialog(exercise: Ejercicio) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_exercise, null)
        val editTextName = dialogView.findViewById<TextInputEditText>(R.id.editTextExerciseName)
        val editTextSets = dialogView.findViewById<TextInputEditText>(R.id.editTextSets)
        val editTextReps = dialogView.findViewById<TextInputEditText>(R.id.editTextReps)
        val editTextWeight = dialogView.findViewById<TextInputEditText>(R.id.editTextWeight)
        val editTextNotes = dialogView.findViewById<TextInputEditText>(R.id.editTextNotes)

        // Populate fields with current data
        editTextName.setText(exercise.Nombre)
        editTextSets.setText(exercise.Series.toString())
        editTextReps.setText(exercise.Repeticiones.toString())
        editTextWeight.setText(exercise.PesoRecomendado.toString())
        editTextNotes.setText(exercise.Notas)

        AlertDialog.Builder(this)
            .setTitle(R.string.dialog_edit_exercise_title)
            .setView(dialogView)
            .setPositiveButton(R.string.dialog_save) { dialog, _ ->
                val name = editTextName.text.toString().trim()
                val setsStr = editTextSets.text.toString().trim()
                val repsStr = editTextReps.text.toString().trim()
                val weightStr = editTextWeight.text.toString().trim()
                val notes = editTextNotes.text.toString().trim()

                if (validateInput(name, setsStr, repsStr, weightStr)) {
                    // Show update confirmation dialog
                    showUpdateConfirmationDialog(exercise, name, setsStr, repsStr, weightStr, notes)
                } else {
                    Toast.makeText(
                        this,
                        R.string.exercise_validation_error,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton(R.string.dialog_cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showUpdateConfirmationDialog(
        exercise: Ejercicio,
        name: String,
        setsStr: String,
        repsStr: String,
        weightStr: String,
        notes: String
    ) {
        AlertDialog.Builder(this)
            .setTitle(R.string.dialog_update_exercise_title)
            .setMessage(R.string.dialog_update_exercise_message)
            .setPositiveButton(R.string.dialog_confirm) { dialog, _ ->
                exercise.Nombre = name
                exercise.Series = setsStr.toInt()
                exercise.Repeticiones = repsStr.toInt()
                exercise.PesoRecomendado = weightStr.toDouble()
                exercise.Notas = notes

                try {
                    exerciseController.updateEjercicio(exercise)
                    loadExercises()
                    Toast.makeText(
                        this,
                        R.string.exercise_updated_successfully,
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton(R.string.dialog_cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showDeleteConfirmationDialog(exercise: Ejercicio) {
        AlertDialog.Builder(this)
            .setTitle(R.string.dialog_delete_exercise_title)
            .setMessage(R.string.dialog_delete_exercise_message)
            .setPositiveButton(R.string.dialog_delete) { dialog, _ ->
                try {
                    exerciseController.deleteEjercicio(exercise.Id)
                    loadExercises()
                    Toast.makeText(
                        this,
                        R.string.exercise_deleted_successfully,
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton(R.string.dialog_cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun validateInput(
        name: String,
        setsStr: String,
        repsStr: String,
        weightStr: String
    ): Boolean {
        if (name.isEmpty() || setsStr.isEmpty() || repsStr.isEmpty() || weightStr.isEmpty()) {
            return false
        }

        return try {
            val sets = setsStr.toInt()
            val reps = repsStr.toInt()
            val weight = weightStr.toDouble()
            sets > 0 && reps > 0 && weight > 0
        } catch (e: NumberFormatException) {
            false
        }
    }
}
