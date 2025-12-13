package Entity

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Membresia(
    @SerializedName("id") val Id: String,
    @SerializedName("usuarioId") val UsuarioId: String,
    @SerializedName("tipo") var Tipo: String,
    @SerializedName("fechaInicio") var FechaInicio: Date,
    @SerializedName("fechaVencimiento") var FechaVencimiento: Date,
    @SerializedName("activa") var Activa: Boolean = true,
    @SerializedName("monto") var Monto: Int
)
