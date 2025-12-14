package Controller

import Data.ApiDataManager
import Data.iDataManager
import Entity.Rutina
import android.content.Context
import cr.ac.utn.census.R

class RutinaController {

    private var dataManager: iDataManager = ApiDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    suspend fun addRutina(rutina: Rutina) {
        try {
            dataManager.addRutina(rutina)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    suspend fun updateRutina(rutina: Rutina) {
        try {
            dataManager.updateRutina(rutina)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    suspend fun getRutinas(): List<Rutina> {
        try {
            return dataManager.getAllRutinas()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    suspend fun getRutinaById(id: String): Rutina {
        try {
            val result = dataManager.getRutinaById(id)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }
            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    suspend fun getRutinasByUsuario(usuarioId: String): List<Rutina> {
        try {
            return dataManager.getRutinasByUsuario(usuarioId)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    suspend fun marcarRutinaCompletada(rutinaId: String) {
        try {
            val rutina = getRutinaById(rutinaId)
            rutina.Completada = true
            dataManager.updateRutina(rutina)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }
}
