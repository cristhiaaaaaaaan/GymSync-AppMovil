package Data

import Api.RetrofitClient
import Entity.*

object ApiDataManager : iDataManager {

    // Ejercicios
    override fun getAllEjercicios(): List<Ejercicio> {
        return try {
            val response = RetrofitClient.ejercicioService.getAllEjercicios().execute()
            if (response.isSuccessful) {
                response.body()?.data ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun getEjercicioById(id: String): Ejercicio? {
        return try {
            val response = RetrofitClient.ejercicioService.getEjercicioById(id).execute()
            if (response.isSuccessful) {
                response.body()?.data
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override fun addEjercicio(ejercicio: Ejercicio) {
        try {
            RetrofitClient.ejercicioService.createEjercicio(ejercicio).execute()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun updateEjercicio(ejercicio: Ejercicio) {
        try {
            RetrofitClient.ejercicioService.updateEjercicio(ejercicio.Id, ejercicio).execute()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun removeEjercicio(id: String) {
        try {
            RetrofitClient.ejercicioService.deleteEjercicio(id).execute()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Rutinas
    override fun getAllRutinas(): List<Province> {
        return try {
            val response = RetrofitClient.rutinaService.getAllRutinas().execute()
            if (response.isSuccessful) {
                response.body()?.data ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun getRutinaById(id: String): Province? {
        return try {
            val response = RetrofitClient.rutinaService.getRutinaById(id).execute()
            if (response.isSuccessful) {
                response.body()?.data
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override fun getRutinasByUsuario(usuarioId: String): List<Province> {
        return getAllRutinas().filter { it.UsuarioId == usuarioId }
    }

    override fun addRutina(rutina: Province) {
        try {
            RetrofitClient.rutinaService.createRutina(rutina).execute()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun updateRutina(rutina: Province) {
        try {
            RetrofitClient.rutinaService.updateRutina(rutina.Id, rutina).execute()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun removeRutina(id: String) {
        try {
            RetrofitClient.rutinaService.deleteRutina(id).execute()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Membresias
    override fun getAllMembresias(): List<Membresia> {
        return try {
            val response = RetrofitClient.membresiaService.getAllMembresias().execute()
            if (response.isSuccessful) {
                response.body()?.data ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun getMembresiaById(id: String): Membresia? {
        return try {
            val response = RetrofitClient.membresiaService.getMembresiaById(id).execute()
            if (response.isSuccessful) {
                response.body()?.data
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override fun getMembresiaByUsuario(usuarioId: String): Membresia? {
        return getAllMembresias().firstOrNull { it.UsuarioId == usuarioId }
    }

    override fun addMembresia(membresia: Membresia) {
        try {
            RetrofitClient.membresiaService.createMembresia(membresia).execute()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun updateMembresia(membresia: Membresia) {
        try {
            RetrofitClient.membresiaService.updateMembresia(membresia.Id, membresia).execute()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun removeMembresia(id: String) {
        try {
            RetrofitClient.membresiaService.deleteMembresia(id).execute()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // Usuarios - usar memoria local por ahora
    override fun addUsuario(person: Person) { MemoryDataManager.addUsuario(person) }
    override fun updateUsuario(person: Person) { MemoryDataManager.updateUsuario(person) }
    override fun removeUsuario(id: String) { MemoryDataManager.removeUsuario(id) }
    override fun getAllUsuarios(): List<Person> = MemoryDataManager.getAllUsuarios()
    override fun getUsuarioById(id: String): Person? = MemoryDataManager.getUsuarioById(id)

    // RegistroAvance - usar memoria local por ahora
    override fun addRegistroAvance(registro: RegistroAvance) { MemoryDataManager.addRegistroAvance(registro) }
    override fun updateRegistroAvance(registro: RegistroAvance) { MemoryDataManager.updateRegistroAvance(registro) }
    override fun removeRegistroAvance(id: String) { MemoryDataManager.removeRegistroAvance(id) }
    override fun getAllRegistrosAvance(): List<RegistroAvance> = MemoryDataManager.getAllRegistrosAvance()
    override fun getRegistroAvanceById(id: String): RegistroAvance? = MemoryDataManager.getRegistroAvanceById(id)
    override fun getRegistrosAvanceByUsuario(usuarioId: String): List<RegistroAvance> = MemoryDataManager.getRegistrosAvanceByUsuario(usuarioId)
    override fun getRegistrosAvanceByEjercicio(ejercicioId: String): List<RegistroAvance> = MemoryDataManager.getRegistrosAvanceByEjercicio(ejercicioId)

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
