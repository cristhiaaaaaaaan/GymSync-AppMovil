package Controller

import Data.MemoryDataManager
import Data.iDataManager
import Entity.Membresia
import android.content.Context
import cr.ac.utn.census.R

class MembresiaController {

    private var dataManager: iDataManager = MemoryDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addMembresia(membresia: Membresia) {
        try {
            dataManager.addMembresia(membresia)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateMembresia(membresia: Membresia) {
        try {
            dataManager.updateMembresia(membresia)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getMembresias(): List<Membresia> {
        try {
            return dataManager.getAllMembresias()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getMembresiaByUsuario(usuarioId: String): Membresia? {
        try {
            return dataManager.getMembresiaByUsuario(usuarioId)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun verificarMembresiaActiva(usuarioId: String): Boolean {
        try {
            val membresia = dataManager.getMembresiaByUsuario(usuarioId)
            return membresia?.Activa ?: false
        } catch (e: Exception) {
            return false
        }
    }
}
