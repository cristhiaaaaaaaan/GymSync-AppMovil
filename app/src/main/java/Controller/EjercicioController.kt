package Controller

import Data.MemoryDataManager
import Data.iDataManager
import Entity.Ejercicio
import android.content.Context
import cr.ac.utn.census.R

class EjercicioController {

    private var dataManager: iDataManager = MemoryDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addEjercicio(ejercicio: Ejercicio) {
        try {
            dataManager.addEjercicio(ejercicio)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateEjercicio(ejercicio: Ejercicio) {
        try {
            dataManager.updateEjercicio(ejercicio)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getEjercicios(): List<Ejercicio> {
        try {
            return dataManager.getAllEjercicios()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getEjercicioById(id: String): Ejercicio {
        try {
            val result = dataManager.getEjercicioById(id)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }
            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    fun deleteEjercicio(id: String) {
        try {
            dataManager.removeEjercicio(id)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgRemove))
        }
    }
}
