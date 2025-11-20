package Entity

import android.graphics.Bitmap
import java.util.Date

// Rutina de ejercicios
class Province {
    private var id: String = ""
    private var usuarioId: String = ""
    private lateinit var fecha: Date
    private var nombre: String = ""
    private var ejercicios: MutableList<String> = mutableListOf()
    private var completada: Boolean = false

    constructor()

    constructor(id: String, usuarioId: String, fecha: Date, nombre: String, ejercicios: MutableList<String>, completada: Boolean) {
        this.id = id
        this.usuarioId = usuarioId
        this.fecha = fecha
        this.nombre = nombre
        this.ejercicios = ejercicios
        this.completada = completada
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

    var Nombre: String
        get() = this.nombre
        set(value) { this.nombre = value }

    var Ejercicios: MutableList<String>
        get() = this.ejercicios
        set(value) { this.ejercicios = value }

    var Completada: Boolean
        get() = this.completada
        set(value) { this.completada = value }
}

// Ejercicio
class Ejercicio {
    private var id: String = ""
    private var nombre: String = ""
    private var series: Int = 0
    private var repeticiones: Int = 0
    private var pesoRecomendado: Double = 0.0
    private var notas: String = ""

    constructor()

    constructor(id: String, nombre: String, series: Int, repeticiones: Int, pesoRecomendado: Double, notas: String) {
        this.id = id
        this.nombre = nombre
        this.series = series
        this.repeticiones = repeticiones
        this.pesoRecomendado = pesoRecomendado
        this.notas = notas
    }

    var Id: String
        get() = this.id
        set(value) { this.id = value }

    var Nombre: String
        get() = this.nombre
        set(value) { this.nombre = value }

    var Series: Int
        get() = this.series
        set(value) { this.series = value }

    var Repeticiones: Int
        get() = this.repeticiones
        set(value) { this.repeticiones = value }

    var PesoRecomendado: Double
        get() = this.pesoRecomendado
        set(value) { this.pesoRecomendado = value }

    var Notas: String
        get() = this.notas
        set(value) { this.notas = value }
}

// Registro de Avance
class RegistroAvance {
    private var id: String = ""
    private var ejercicioId: String = ""
    private var usuarioId: String = ""
    private lateinit var fecha: Date
    private var pesoUtilizado: Double = 0.0
    private var repeticionesRealizadas: Int = 0
    private var seriesCompletadas: Int = 0

    constructor()

    constructor(id: String, ejercicioId: String, usuarioId: String, fecha: Date, pesoUtilizado: Double, repeticionesRealizadas: Int, seriesCompletadas: Int) {
        this.id = id
        this.ejercicioId = ejercicioId
        this.usuarioId = usuarioId
        this.fecha = fecha
        this.pesoUtilizado = pesoUtilizado
        this.repeticionesRealizadas = repeticionesRealizadas
        this.seriesCompletadas = seriesCompletadas
    }

    var Id: String
        get() = this.id
        set(value) { this.id = value }

    var EjercicioId: String
        get() = this.ejercicioId
        set(value) { this.ejercicioId = value }

    var UsuarioId: String
        get() = this.usuarioId
        set(value) { this.usuarioId = value }

    var Fecha: Date
        get() = this.fecha
        set(value) { this.fecha = value }

    var PesoUtilizado: Double
        get() = this.pesoUtilizado
        set(value) { this.pesoUtilizado = value }

    var RepeticionesRealizadas: Int
        get() = this.repeticionesRealizadas
        set(value) { this.repeticionesRealizadas = value }

    var SeriesCompletadas: Int
        get() = this.seriesCompletadas
        set(value) { this.seriesCompletadas = value }
}

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

// Membresía
class Membresia {
    private var id: String = ""
    private var usuarioId: String = ""
    private var tipo: String = ""
    private lateinit var fechaInicio: Date
    private lateinit var fechaVencimiento: Date
    private var activa: Boolean = false
    private var monto: Double = 0.0

    constructor()

    constructor(id: String, usuarioId: String, tipo: String, fechaInicio: Date, fechaVencimiento: Date, activa: Boolean, monto: Double) {
        this.id = id
        this.usuarioId = usuarioId
        this.tipo = tipo
        this.fechaInicio = fechaInicio
        this.fechaVencimiento = fechaVencimiento
        this.activa = activa
        this.monto = monto
    }

    var Id: String
        get() = this.id
        set(value) { this.id = value }

    var UsuarioId: String
        get() = this.usuarioId
        set(value) { this.usuarioId = value }

    var Tipo: String
        get() = this.tipo
        set(value) { this.tipo = value }

    var FechaInicio: Date
        get() = this.fechaInicio
        set(value) { this.fechaInicio = value }

    var FechaVencimiento: Date
        get() = this.fechaVencimiento
        set(value) { this.fechaVencimiento = value }

    var Activa: Boolean
        get() = this.activa
        set(value) { this.activa = value }

    var Monto: Double
        get() = this.monto
        set(value) { this.monto = value }
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