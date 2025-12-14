package Entity

import com.google.gson.annotations.SerializedName

data class Rutina(
    @SerializedName("id") val Id: String,
    @SerializedName("usuarioId") val UsuarioId: String,
    @SerializedName("fecha") val Fecha: String,
    @SerializedName("nombre") val Nombre: String,
    @SerializedName("ejercicios") val Ejercicios: List<String>,
    @SerializedName("completada") var Completada: Boolean = false
)
