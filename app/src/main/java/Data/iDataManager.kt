package Data

import Entity.*

interface iDataManager {
    // Métodos para Usuario (Person)
    fun addUsuario(person: Person)
    fun updateUsuario(person: Person)
    fun removeUsuario(id: String)
    fun getAllUsuarios(): List<Person>
    fun getUsuarioById(id: String): Person?

    // Métodos para Rutina (Province)
    fun addRutina(rutina: Province)
    fun updateRutina(rutina: Province)
    fun removeRutina(id: String)
    fun getAllRutinas(): List<Province>
    fun getRutinaById(id: String): Province?
    fun getRutinasByUsuario(usuarioId: String): List<Province>

    // Métodos para Ejercicio
    fun addEjercicio(ejercicio: Ejercicio)
    fun updateEjercicio(ejercicio: Ejercicio)
    fun removeEjercicio(id: String)
    fun getAllEjercicios(): List<Ejercicio>
    fun getEjercicioById(id: String): Ejercicio?

    // Métodos para RegistroAvance
    fun addRegistroAvance(registro: RegistroAvance)
    fun updateRegistroAvance(registro: RegistroAvance)
    fun removeRegistroAvance(id: String)
    fun getAllRegistrosAvance(): List<RegistroAvance>
    fun getRegistroAvanceById(id: String): RegistroAvance?
    fun getRegistrosAvanceByUsuario(usuarioId: String): List<RegistroAvance>
    fun getRegistrosAvanceByEjercicio(ejercicioId: String): List<RegistroAvance>

    // Métodos para FotoProgreso
    fun addFotoProgreso(foto: FotoProgreso)
    fun updateFotoProgreso(foto: FotoProgreso)
    fun removeFotoProgreso(id: String)
    fun getAllFotosProgreso(): List<FotoProgreso>
    fun getFotoProgresoById(id: String): FotoProgreso?
    fun getFotosProgresoByUsuario(usuarioId: String): List<FotoProgreso>

    // Métodos para Membresia
    fun addMembresia(membresia: Membresia)
    fun updateMembresia(membresia: Membresia)
    fun removeMembresia(id: String)
    fun getAllMembresias(): List<Membresia>
    fun getMembresiaById(id: String): Membresia?
    fun getMembresiaByUsuario(usuarioId: String): Membresia?

    // Métodos para Logro
    fun addLogro(logro: Logro)
    fun updateLogro(logro: Logro)
    fun removeLogro(id: String)
    fun getAllLogros(): List<Logro>
    fun getLogroById(id: String): Logro?
    fun getLogrosByUsuario(usuarioId: String): List<Logro>
}