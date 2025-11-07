package Controller

import Data.MemoryDataManager
import Data.iDataManager
import Entity.FotoProgreso
import android.content.Context
import cr.ac.utn.census.R

class FotoProgresoController {

    private var dataManager: iDataManager = MemoryDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addFotoProgreso(foto: FotoProgreso) {
        try {
            dataManager.addFotoProgreso(foto)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateFotoProgreso(foto: FotoProgreso) {
        try {
            dataManager.updateFotoProgreso(foto)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getFotosProgreso(): List<FotoProgreso> {
        try {
            return dataManager.getAllFotosProgreso()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getFotosProgresoByUsuario(usuarioId: String): List<FotoProgreso> {
        try {
            return dataManager.getFotosProgresoByUsuario(usuarioId)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }
}
