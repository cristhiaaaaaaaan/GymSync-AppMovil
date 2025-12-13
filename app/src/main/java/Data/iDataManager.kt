package Data

import Entity.*

interface iDataManager {
    // Métodos para Usuario (User)
    suspend fun addUsuario(user: User)
    suspend fun updateUsuario(user: User)
    suspend fun removeUsuario(id: String)
    suspend fun getAllUsuarios(): List<User>
    suspend fun getUsuarioById(id: String): User?

    // Métodos para Rutina
    suspend fun addRutina(rutina: Rutina)
    suspend fun updateRutina(rutina: Rutina)
    suspend fun removeRutina(id: String)
    suspend fun getAllRutinas(): List<Rutina>
    suspend fun getRutinaById(id: String): Rutina?
    suspend fun getRutinasByUsuario(usuarioId: String): List<Rutina>

    // Métodos para Ejercicio
    suspend fun addEjercicio(ejercicio: Ejercicio)
    suspend fun updateEjercicio(ejercicio: Ejercicio)
    suspend fun removeEjercicio(id: String)
    suspend fun getAllEjercicios(): List<Ejercicio>
    suspend fun getEjercicioById(id: String): Ejercicio?
    suspend fun getEjerciciosByUsuario(usuarioId: String): List<Ejercicio>

    // Métodos para RegistroAvance
    suspend fun addRegistroAvance(registro: RegistroAvance)
    suspend fun updateRegistroAvance(registro: RegistroAvance)
    suspend fun removeRegistroAvance(id: String)
    suspend fun getAllRegistrosAvance(): List<RegistroAvance>
    suspend fun getRegistroAvanceById(id: String): RegistroAvance?
    suspend fun getRegistrosAvanceByUsuario(usuarioId: String): List<RegistroAvance>
    suspend fun getRegistrosAvanceByEjercicio(ejercicioId: String): List<RegistroAvance>

    // Métodos para FotoProgreso
    fun addFotoProgreso(foto: FotoProgreso)
    fun updateFotoProgreso(foto: FotoProgreso)
    fun removeFotoProgreso(id: String)
    fun getAllFotosProgreso(): List<FotoProgreso>
    fun getFotoProgresoById(id: String): FotoProgreso?
    fun getFotosProgresoByUsuario(usuarioId: String): List<FotoProgreso>

    // Métodos para Membresia
    suspend fun addMembresia(membresia: Membresia)
    suspend fun updateMembresia(membresia: Membresia)
    suspend fun removeMembresia(id: String)
    suspend fun getAllMembresias(): List<Membresia>
    suspend fun getMembresiaById(id: String): Membresia?
    suspend fun getMembresiaByUsuario(usuarioId: String): Membresia?

    // Métodos para Logro
    fun addLogro(logro: Logro)
    fun updateLogro(logro: Logro)
    fun removeLogro(id: String)
    fun getAllLogros(): List<Logro>
    fun getLogroById(id: String): Logro?
    fun getLogrosByUsuario(usuarioId: String): List<Logro>
}