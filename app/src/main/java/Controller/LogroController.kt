package Controller

import Data.MemoryDataManager
import Data.iDataManager
import Entity.Logro
import android.content.Context
import cr.ac.utn.census.R

class LogroController {

    private var dataManager: iDataManager = MemoryDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addLogro(logro: Logro) {
        try {
            dataManager.addLogro(logro)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateLogro(logro: Logro) {
        try {
            dataManager.updateLogro(logro)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getLogros(): List<Logro> {
        try {
            return dataManager.getAllLogros()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getLogrosByUsuario(usuarioId: String): List<Logro> {
        try {
            return dataManager.getLogrosByUsuario(usuarioId)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }
}
