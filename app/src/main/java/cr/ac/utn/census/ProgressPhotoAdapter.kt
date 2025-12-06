package cr.ac.utn.census

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import Entity.FotoProgreso
import java.text.SimpleDateFormat
import java.util.Locale

class ProgressPhotoAdapter(
    private var photoList: List<FotoProgreso>
) : RecyclerView.Adapter<ProgressPhotoAdapter.ProgressPhotoViewHolder>() {

    class ProgressPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.imgPhoto_ItemRecycler)
        val txtNote: TextView = itemView.findViewById(R.id.txtNote_ItemRecycler)
        val txtDate: TextView = itemView.findViewById(R.id.txtDate_ItemRecycler)
        val txtId: TextView = itemView.findViewById(R.id.txtId_ItemRecycler)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressPhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_progress_photo, parent, false)
        return ProgressPhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgressPhotoViewHolder, position: Int) {
        val photo = photoList[position]

        // Set the photo bitmap if available
        if (photo.Photo != null) {
            holder.imgPhoto.setImageBitmap(photo.Photo)
        } else {
            holder.imgPhoto.setImageResource(android.R.drawable.ic_menu_gallery)
        }

        // Set the note
        holder.txtNote.text = if (photo.Nota.isNotEmpty()) photo.Nota else "No note"

        // Format and set the date
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        holder.txtDate.text = dateFormat.format(photo.Fecha)

        // Set the ID (shortened for display)
        holder.txtId.text = photo.Id.take(8)
    }

    override fun getItemCount(): Int = photoList.size

    fun updateData(newList: List<FotoProgreso>) {
        photoList = newList
        notifyDataSetChanged()
    }
}
