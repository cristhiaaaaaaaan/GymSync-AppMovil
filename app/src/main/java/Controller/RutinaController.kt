package Controller

import Data.ApiDataManager
import Data.iDataManager
import Entity.Province
import android.content.Context
import cr.ac.utn.census.R

class RutinaController {

    private var dataManager: iDataManager = ApiDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addRutina(rutina: Province) {
        try {
            dataManager.addRutina(rutina)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateRutina(rutina: Province) {
        try {
            dataManager.updateRutina(rutina)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getRutinas(): List<Province> {
        try {
            return dataManager.getAllRutinas()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getRutinaById(id: String): Province {
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

    fun getRutinasByUsuario(usuarioId: String): List<Province> {
        try {
            return dataManager.getRutinasByUsuario(usuarioId)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun marcarRutinaCompletada(rutinaId: String) {
        try {
            val rutina = getRutinaById(rutinaId)
            rutina.Completada = true
            dataManager.updateRutina(rutina)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }
}
