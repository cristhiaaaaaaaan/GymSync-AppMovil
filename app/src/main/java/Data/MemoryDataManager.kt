package Data

import Entity.*

object MemoryDataManager: iDataManager {
    private var usuarioList = mutableListOf<Person>()
    private var rutinaList = mutableListOf<Province>()
    private var ejercicioList = mutableListOf<Ejercicio>()
    private var registroAvanceList = mutableListOf<RegistroAvance>()
    private var fotoProgresoList = mutableListOf<FotoProgreso>()
    private var membresiaList = mutableListOf<Membresia>()
    private var logroList = mutableListOf<Logro>()

    // Implementación para Usuario (Person)
    override fun addUsuario(person: Person) {
        usuarioList.add(person)
    }

    override fun removeUsuario(id: String) {
        usuarioList.removeIf { it.Id.trim() == id.trim() }
    }

    override fun updateUsuario(person: Person) {
        removeUsuario(person.Id)
        addUsuario(person)
    }

    override fun getAllUsuarios() = usuarioList

    override fun getUsuarioById(id: String): Person? {
        try {
            val result = usuarioList.filter { it.Id.trim() == id.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    // Implementación para Rutina (Province)
    override fun addRutina(rutina: Province) {
        rutinaList.add(rutina)
    }

    override fun removeRutina(id: String) {
        rutinaList.removeIf { it.Id.trim() == id.trim() }
    }

    override fun updateRutina(rutina: Province) {
        removeRutina(rutina.Id)
        addRutina(rutina)
    }

    override fun getAllRutinas() = rutinaList

    override fun getRutinaById(id: String): Province? {
        try {
            val result = rutinaList.filter { it.Id.trim() == id.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getRutinasByUsuario(usuarioId: String): List<Province> {
        try {
            return rutinaList.filter { it.UsuarioId.trim() == usuarioId.trim() }
        } catch (e: Exception) {
            throw e
        }
    }

    // Implementación para Ejercicio
    override fun addEjercicio(ejercicio: Ejercicio) {
        ejercicioList.add(ejercicio)
    }

    override fun removeEjercicio(id: String) {
        ejercicioList.removeIf { it.Id.trim() == id.trim() }
    }

    override fun updateEjercicio(ejercicio: Ejercicio) {
        removeEjercicio(ejercicio.Id)
        addEjercicio(ejercicio)
    }

    override fun getAllEjercicios() = ejercicioList

    override fun getEjercicioById(id: String): Ejercicio? {
        try {
            val result = ejercicioList.filter { it.Id.trim() == id.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    // Implementación para RegistroAvance
    override fun addRegistroAvance(registro: RegistroAvance) {
        registroAvanceList.add(registro)
    }

    override fun removeRegistroAvance(id: String) {
        registroAvanceList.removeIf { it.Id.trim() == id.trim() }
    }

    override fun updateRegistroAvance(registro: RegistroAvance) {
        removeRegistroAvance(registro.Id)
        addRegistroAvance(registro)
    }

    override fun getAllRegistrosAvance() = registroAvanceList

    override fun getRegistroAvanceById(id: String): RegistroAvance? {
        try {
            val result = registroAvanceList.filter { it.Id.trim() == id.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getRegistrosAvanceByUsuario(usuarioId: String): List<RegistroAvance> {
        try {
            return registroAvanceList.filter { it.UsuarioId.trim() == usuarioId.trim() }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getRegistrosAvanceByEjercicio(ejercicioId: String): List<RegistroAvance> {
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
    override fun addMembresia(membresia: Membresia) {
        membresiaList.add(membresia)
    }

    override fun removeMembresia(id: String) {
        membresiaList.removeIf { it.Id.trim() == id.trim() }
    }

    override fun updateMembresia(membresia: Membresia) {
        removeMembresia(membresia.Id)
        addMembresia(membresia)
    }

    override fun getAllMembresias() = membresiaList

    override fun getMembresiaById(id: String): Membresia? {
        try {
            val result = membresiaList.filter { it.Id.trim() == id.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getMembresiaByUsuario(usuarioId: String): Membresia? {
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
