package Entity

import com.google.gson.annotations.SerializedName

data class Ejercicio(
    @SerializedName("id") var Id: String,
    @SerializedName("nombre") var Nombre: String,
    @SerializedName("series") var Series: Int,
    @SerializedName("repeticiones") var Repeticiones: Int,
    @SerializedName("pesoRecomendado") var PesoRecomendado: Int,
    @SerializedName("notas") var Notas: String = "",
    @SerializedName("usuarioId") var UsuarioId: String = ""
)
