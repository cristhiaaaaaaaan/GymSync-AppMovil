package cr.ac.utn.census

import Entity.Ejercicio
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter(
    private var exercises: MutableList<Ejercicio>,
    private val onEditClick: (Ejercicio) -> Unit,
    private val onDeleteClick: (Ejercicio) -> Unit
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private var filteredExercises: MutableList<Ejercicio> = exercises.toMutableList()

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewExerciseName: TextView = itemView.findViewById(R.id.textViewExerciseName)
        val textViewSets: TextView = itemView.findViewById(R.id.textViewSets)
        val textViewReps: TextView = itemView.findViewById(R.id.textViewReps)
        val textViewWeight: TextView = itemView.findViewById(R.id.textViewWeight)
        val textViewNotes: TextView = itemView.findViewById(R.id.textViewNotes)
        val buttonEdit: Button = itemView.findViewById(R.id.buttonEdit)
        val buttonDelete: Button = itemView.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = filteredExercises[position]

        holder.textViewExerciseName.text = exercise.Nombre
        holder.textViewSets.text = "Sets: ${exercise.Series}"
        holder.textViewReps.text = "Reps: ${exercise.Repeticiones}"
        holder.textViewWeight.text = "Weight: ${exercise.PesoRecomendado} kg"
        holder.textViewNotes.text = exercise.Notas

        holder.buttonEdit.setOnClickListener {
            onEditClick(exercise)
        }

        holder.buttonDelete.setOnClickListener {
            onDeleteClick(exercise)
        }
    }

    override fun getItemCount(): Int = filteredExercises.size

    fun updateExercises(newExercises: List<Ejercicio>) {
        exercises.clear()
        exercises.addAll(newExercises)
        filteredExercises.clear()
        filteredExercises.addAll(newExercises)
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredExercises.clear()
        if (query.isEmpty()) {
            filteredExercises.addAll(exercises)
        } else {
            val searchQuery = query.lowercase()
            filteredExercises.addAll(exercises.filter { exercise ->
                exercise.Nombre.lowercase().contains(searchQuery) ||
                exercise.Notas.lowercase().contains(searchQuery)
            })
        }
        notifyDataSetChanged()
    }
}
