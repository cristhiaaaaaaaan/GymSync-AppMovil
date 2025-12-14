package Entity

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val Id: String,
    @SerializedName("name") val Name: String,
    @SerializedName("email") val Email: String,
    @SerializedName("fotoPerfil") val FotoPerfil: String = "",
    @SerializedName("fechaRegistro") val FechaRegistro: String = ""
)
