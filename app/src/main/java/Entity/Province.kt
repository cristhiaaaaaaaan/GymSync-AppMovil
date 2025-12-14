package Entity

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName
import java.util.Date

// Registro de Avance
data class RegistroAvance(
    @SerializedName("id") var Id: String = "",
    @SerializedName("ejercicioId") var EjercicioId: String = "",
    @SerializedName("usuarioId") var UsuarioId: String = "",
    @SerializedName("fecha") var Fecha: String = "",
    @SerializedName("pesoUtilizado") var PesoUtilizado: Double = 0.0,
    @SerializedName("repeticionesRealizadas") var RepeticionesRealizadas: Int = 0,
    @SerializedName("seriesCompletadas") var SeriesCompletadas: Int = 0,
    @SerializedName("notas") var Notas: String = ""
)

// Progress Photo
class FotoProgreso {
    private var id: String = ""
    private var usuarioId: String = ""
    private lateinit var fecha: Date
    private var photo: Bitmap? = null
    private var nota: String = ""

    constructor()

    constructor(id: String, usuarioId: String, fecha: Date, photo: Bitmap?, nota: String) {
        this.id = id
        this.usuarioId = usuarioId
        this.fecha = fecha
        this.photo = photo
        this.nota = nota
    }

    var Id: String
        get() = this.id
        set(value) { this.id = value }

    var UsuarioId: String
        get() = this.usuarioId
        set(value) { this.usuarioId = value }

    var Fecha: Date
        get() = this.fecha
        set(value) { this.fecha = value }

    var Photo: Bitmap?
        get() = this.photo
        set(value) { this.photo = value }

    var Nota: String
        get() = this.nota
        set(value) { this.nota = value }
}


// Logro
class Logro {
    private var id: String = ""
    private var usuarioId: String = ""
    private var titulo: String = ""
    private var descripcion: String = ""
    private lateinit var fecha: Date
    private var icono: String = ""

    constructor()

    constructor(id: String, usuarioId: String, titulo: String, descripcion: String, fecha: Date, icono: String) {
        this.id = id
        this.usuarioId = usuarioId
        this.titulo = titulo
        this.descripcion = descripcion
        this.fecha = fecha
        this.icono = icono
    }

    var Id: String
        get() = this.id
        set(value) { this.id = value }

    var UsuarioId: String
        get() = this.usuarioId
        set(value) { this.usuarioId = value }

    var Titulo: String
        get() = this.titulo
        set(value) { this.titulo = value }

    var Descripcion: String
        get() = this.descripcion
        set(value) { this.descripcion = value }

    var Fecha: Date
        get() = this.fecha
        set(value) { this.fecha = value }

    var Icono: String
        get() = this.icono
        set(value) { this.icono = value }
}