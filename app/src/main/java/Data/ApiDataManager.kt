package Data

import Api.RetrofitClient
import Entity.*

object ApiDataManager : iDataManager {

    // Ejercicios
    override suspend fun getAllEjercicios(): List<Ejercicio> {
        return try {
            val response = RetrofitClient.ejercicioService.getAllEjercicios()
            response.data ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getEjerciciosByUsuario(usuarioId: String): List<Ejercicio> {
        return try {
            val response = RetrofitClient.ejercicioService.getAllEjercicios(usuarioId)
            response.data ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getEjercicioById(id: String): Ejercicio? {
        return try {
            val response = RetrofitClient.ejercicioService.getEjercicioById(id)
            response.data
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun addEjercicio(ejercicio: Ejercicio) {
        try {
            RetrofitClient.ejercicioService.createEjercicio(ejercicio)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun updateEjercicio(ejercicio: Ejercicio) {
        try {
            RetrofitClient.ejercicioService.updateEjercicio(ejercicio.Id, ejercicio)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun removeEjercicio(id: String) {
        try {
            RetrofitClient.ejercicioService.deleteEjercicio(id)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    // Rutinas
    override suspend fun getAllRutinas(): List<Rutina> {
        return try {
            val response = RetrofitClient.rutinaService.getAllRutinas()
            response.data ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getRutinaById(id: String): Rutina? {
        return try {
            val response = RetrofitClient.rutinaService.getRutinaById(id)
            response.data
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getRutinasByUsuario(usuarioId: String): List<Rutina> {
        return getAllRutinas().filter { it.UsuarioId == usuarioId }
    }

    override suspend fun addRutina(rutina: Rutina) {
        try {
            RetrofitClient.rutinaService.createRutina(rutina)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun updateRutina(rutina: Rutina) {
        try {
            RetrofitClient.rutinaService.updateRutina(rutina.Id, rutina)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun removeRutina(id: String) {
        try {
            RetrofitClient.rutinaService.deleteRutina(id)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    // Membresias
    override suspend fun getAllMembresias(): List<Membresia> {
        return try {
            val response = RetrofitClient.membresiaService.getAllMembresias()
            response.data ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getMembresiaById(id: String): Membresia? {
        return try {
            val response = RetrofitClient.membresiaService.getMembresiaById(id)
            response.data
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMembresiaByUsuario(usuarioId: String): Membresia? {
        return getAllMembresias().firstOrNull { it.UsuarioId == usuarioId }
    }

    override suspend fun addMembresia(membresia: Membresia) {
        try {
            RetrofitClient.membresiaService.createMembresia(membresia)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun updateMembresia(membresia: Membresia) {
        try {
            RetrofitClient.membresiaService.updateMembresia(membresia.Id, membresia)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun removeMembresia(id: String) {
        try {
            RetrofitClient.membresiaService.deleteMembresia(id)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    // Usuarios - usar API
    override suspend fun getAllUsuarios(): List<User> {
        return try {
            val response = RetrofitClient.userService.getAllUsers()
            response.data ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getUsuarioById(id: String): User? {
        return try {
            val response = RetrofitClient.userService.getUserById(id)
            response.data
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun addUsuario(user: User) {
        try {
            RetrofitClient.userService.createUser(user)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun updateUsuario(user: User) {
        try {
            RetrofitClient.userService.updateUser(user.Id, user)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun removeUsuario(id: String) {
        try {
            RetrofitClient.userService.deleteUser(id)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    // RegistroAvance - usar API
    override suspend fun addRegistroAvance(registro: RegistroAvance) {
        try {
            RetrofitClient.registroAvanceService.createRegistro(registro)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun updateRegistroAvance(registro: RegistroAvance) {
        try {
            RetrofitClient.registroAvanceService.updateRegistro(registro.Id, registro)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun removeRegistroAvance(id: String) {
        try {
            RetrofitClient.registroAvanceService.deleteRegistro(id)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun getAllRegistrosAvance(): List<RegistroAvance> {
        return try {
            val response = RetrofitClient.registroAvanceService.getAllRegistros()
            response.data ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getRegistroAvanceById(id: String): RegistroAvance? {
        return try {
            val response = RetrofitClient.registroAvanceService.getRegistroById(id)
            response.data
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getRegistrosAvanceByUsuario(usuarioId: String): List<RegistroAvance> {
        return try {
            val response = RetrofitClient.registroAvanceService.getAllRegistros(usuarioId = usuarioId)
            response.data ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getRegistrosAvanceByEjercicio(ejercicioId: String): List<RegistroAvance> {
        return try {
            val response = RetrofitClient.registroAvanceService.getAllRegistros(ejercicioId = ejercicioId)
            response.data ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    // FotoProgreso - usar memoria local por ahora
    override fun addFotoProgreso(foto: FotoProgreso) { MemoryDataManager.addFotoProgreso(foto) }
    override fun updateFotoProgreso(foto: FotoProgreso) { MemoryDataManager.updateFotoProgreso(foto) }
    override fun removeFotoProgreso(id: String) { MemoryDataManager.removeFotoProgreso(id) }
    override fun getAllFotosProgreso(): List<FotoProgreso> = MemoryDataManager.getAllFotosProgreso()
    override fun getFotoProgresoById(id: String): FotoProgreso? = MemoryDataManager.getFotoProgresoById(id)
    override fun getFotosProgresoByUsuario(usuarioId: String): List<FotoProgreso> = MemoryDataManager.getFotosProgresoByUsuario(usuarioId)

    // Logro - usar memoria local por ahora
    override fun addLogro(logro: Logro) { MemoryDataManager.addLogro(logro) }
    override fun updateLogro(logro: Logro) { MemoryDataManager.updateLogro(logro) }
    override fun removeLogro(id: String) { MemoryDataManager.removeLogro(id) }
    override fun getAllLogros(): List<Logro> = MemoryDataManager.getAllLogros()
    override fun getLogroById(id: String): Logro? = MemoryDataManager.getLogroById(id)
    override fun getLogrosByUsuario(usuarioId: String): List<Logro> = MemoryDataManager.getLogrosByUsuario(usuarioId)
}
