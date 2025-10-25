package Entity

import java.util.Date

// Usuario de GymSync
class Person {

    private var id: String = ""
    private var name: String = ""
    private var email: String = ""
    private var fotoPerfil: String = ""
    private lateinit var fechaRegistro: Date

    constructor(id: String, name: String, email: String, fotoPerfil: String, fechaRegistro: Date) {
        this.id = id
        this.name = name
        this.email = email
        this.fotoPerfil = fotoPerfil
        this.fechaRegistro = fechaRegistro
    }

    var Id: String
        get() = this.id
        set(value) { this.id = value }

    var Name: String
        get() = this.name
        set(value) { this.name = value }

    var Email: String
        get() = this.email
        set(value) { this.email = value }

    var FotoPerfil: String
        get() = this.fotoPerfil
        set(value) { this.fotoPerfil = value }

    var FechaRegistro: Date
        get() = this.fechaRegistro
        set(value) { this.fechaRegistro = value }
}




