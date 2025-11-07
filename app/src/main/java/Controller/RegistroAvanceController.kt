package Controller

import Data.MemoryDataManager
import Data.iDataManager
import Entity.RegistroAvance
import android.content.Context
import cr.ac.utn.census.R

class RegistroAvanceController {

    private var dataManager: iDataManager = MemoryDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addRegistroAvance(registro: RegistroAvance) {
        try {
            dataManager.addRegistroAvance(registro)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateRegistroAvance(registro: RegistroAvance) {
        try {
            dataManager.updateRegistroAvance(registro)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getRegistrosAvance(): List<RegistroAvance> {
        try {
            return dataManager.getAllRegistrosAvance()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getRegistrosAvanceByUsuario(usuarioId: String): List<RegistroAvance> {
        try {
            return dataManager.getRegistrosAvanceByUsuario(usuarioId)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getRegistrosAvanceByEjercicio(ejercicioId: String): List<RegistroAvance> {
        try {
            return dataManager.getRegistrosAvanceByEjercicio(ejercicioId)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }
}
