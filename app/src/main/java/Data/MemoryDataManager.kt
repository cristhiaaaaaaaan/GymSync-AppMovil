package Data

import Entity.*

object MemoryDataManager: iDataManager {
    private var usuarioList = mutableListOf<User>()
    private var rutinaList = mutableListOf<Rutina>()
    private var ejercicioList = mutableListOf<Ejercicio>()
    private var registroAvanceList = mutableListOf<RegistroAvance>()
    private var fotoProgresoList = mutableListOf<FotoProgreso>()
    private var membresiaList = mutableListOf<Membresia>()
    private var logroList = mutableListOf<Logro>()

    // Implementación para Usuario (User)
    override suspend fun addUsuario(user: User) {
        usuarioList.add(user)
    }

    override suspend fun removeUsuario(id: String) {
        usuarioList.removeIf { it.Id.trim() == id.trim() }
    }

    override suspend fun updateUsuario(user: User) {
        removeUsuario(user.Id)
        addUsuario(user)
    }

    override suspend fun getAllUsuarios() = usuarioList

    override suspend fun getUsuarioById(id: String): User? {
        try {
            val result = usuarioList.filter { it.Id.trim() == id.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    // Implementación para Rutina (Rutina)
    override suspend fun addRutina(rutina: Rutina) {
        rutinaList.add(rutina)
    }

    override suspend fun removeRutina(id: String) {
        rutinaList.removeIf { it.Id.trim() == id.trim() }
    }

    override suspend fun updateRutina(rutina: Rutina) {
        removeRutina(rutina.Id)
        addRutina(rutina)
    }

    override suspend fun getAllRutinas() = rutinaList

    override suspend fun getRutinaById(id: String): Rutina? {
        try {
            val result = rutinaList.filter { it.Id.trim() == id.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getRutinasByUsuario(usuarioId: String): List<Rutina> {
        try {
            return rutinaList.filter { it.UsuarioId.trim() == usuarioId.trim() }
        } catch (e: Exception) {
            throw e
        }
    }

    // Implementación para Ejercicio
    override suspend fun addEjercicio(ejercicio: Ejercicio) {
        ejercicioList.add(ejercicio)
    }

    override suspend fun removeEjercicio(id: String) {
        ejercicioList.removeIf { it.Id.trim() == id.trim() }
    }

    override suspend fun updateEjercicio(ejercicio: Ejercicio) {
        removeEjercicio(ejercicio.Id)
        addEjercicio(ejercicio)
    }

    override suspend fun getAllEjercicios() = ejercicioList

    override suspend fun getEjerciciosByUsuario(usuarioId: String): List<Ejercicio> {
        try {
            return ejercicioList.filter { it.UsuarioId.trim() == usuarioId.trim() }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getEjercicioById(id: String): Ejercicio? {
        try {
            val result = ejercicioList.filter { it.Id.trim() == id.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    // Implementación para RegistroAvance
    override suspend fun addRegistroAvance(registro: RegistroAvance) {
        registroAvanceList.add(registro)
    }

    override suspend fun removeRegistroAvance(id: String) {
        registroAvanceList.removeIf { it.Id.trim() == id.trim() }
    }

    override suspend fun updateRegistroAvance(registro: RegistroAvance) {
        removeRegistroAvance(registro.Id)
        addRegistroAvance(registro)
    }

    override suspend fun getAllRegistrosAvance() = registroAvanceList

    override suspend fun getRegistroAvanceById(id: String): RegistroAvance? {
        try {
            val result = registroAvanceList.filter { it.Id.trim() == id.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getRegistrosAvanceByUsuario(usuarioId: String): List<RegistroAvance> {
        try {
            return registroAvanceList.filter { it.UsuarioId.trim() == usuarioId.trim() }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getRegistrosAvanceByEjercicio(ejercicioId: String): List<RegistroAvance> {
        try {
            return registroAvanceList.filter { it.EjercicioId.trim() == ejercicioId.trim() }
        } catch (e: Exception) {
            throw e
        }
    }

    // Implementación para FotoProgreso
    override fun addFotoProgreso(foto: FotoProgreso) {
        fotoProgresoList.add(foto)
    }

    override fun removeFotoProgreso(id: String) {
        fotoProgresoList.removeIf { it.Id.trim() == id.trim() }
    }

    override fun updateFotoProgreso(foto: FotoProgreso) {
        removeFotoProgreso(foto.Id)
        addFotoProgreso(foto)
    }

    override fun getAllFotosProgreso() = fotoProgresoList

    override fun getFotoProgresoById(id: String): FotoProgreso? {
        try {
            val result = fotoProgresoList.filter { it.Id.trim() == id.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getFotosProgresoByUsuario(usuarioId: String): List<FotoProgreso> {
        try {
            return fotoProgresoList.filter { it.UsuarioId.trim() == usuarioId.trim() }
        } catch (e: Exception) {
            throw e
        }
    }

    // Implementación para Membresia
    override suspend fun addMembresia(membresia: Membresia) {
        membresiaList.add(membresia)
    }

    override suspend fun removeMembresia(id: String) {
        membresiaList.removeIf { it.Id.trim() == id.trim() }
    }

    override suspend fun updateMembresia(membresia: Membresia) {
        removeMembresia(membresia.Id)
        addMembresia(membresia)
    }

    override suspend fun getAllMembresias() = membresiaList

    override suspend fun getMembresiaById(id: String): Membresia? {
        try {
            val result = membresiaList.filter { it.Id.trim() == id.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getMembresiaByUsuario(usuarioId: String): Membresia? {
        try {
            val result = membresiaList.filter { it.UsuarioId.trim() == usuarioId.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    // Implementación para Logro
    override fun addLogro(logro: Logro) {
        logroList.add(logro)
    }

    override fun removeLogro(id: String) {
        logroList.removeIf { it.Id.trim() == id.trim() }
    }

    override fun updateLogro(logro: Logro) {
        removeLogro(logro.Id)
        addLogro(logro)
    }

    override fun getAllLogros() = logroList

    override fun getLogroById(id: String): Logro? {
        try {
            val result = logroList.filter { it.Id.trim() == id.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getLogrosByUsuario(usuarioId: String): List<Logro> {
        try {
            return logroList.filter { it.UsuarioId.trim() == usuarioId.trim() }
        } catch (e: Exception) {
            throw e
        }
    }
}
