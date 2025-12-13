package Controller

import Data.ApiDataManager
import Data.iDataManager
import Entity.Membresia
import android.content.Context
import cr.ac.utn.census.R

class MembresiaController {

    private var dataManager: iDataManager = ApiDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    suspend fun addMembresia(membresia: Membresia) {
        try {
            dataManager.addMembresia(membresia)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    suspend fun updateMembresia(membresia: Membresia) {
        try {
            dataManager.updateMembresia(membresia)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    suspend fun getMembresias(): List<Membresia> {
        try {
            return dataManager.getAllMembresias()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    suspend fun getMembresiaByUsuario(usuarioId: String): Membresia? {
        try {
            return dataManager.getMembresiaByUsuario(usuarioId)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    suspend fun verificarMembresiaActiva(usuarioId: String): Boolean {
        try {
            val membresia = dataManager.getMembresiaByUsuario(usuarioId)
            return membresia?.Activa ?: false
        } catch (e: Exception) {
            return false
        }
    }
}
