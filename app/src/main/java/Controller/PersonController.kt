package Controller

import Data.MemoryDataManager
import Data.iDataManager
import Entity.Person
import android.content.Context
import cr.ac.utn.census.R

class PersonController {

    private var dataManager: iDataManager = MemoryDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addUsuario(usuario: Person) {
        try {
            dataManager.addUsuario(usuario)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateUsuario(usuario: Person) {
        try {
            dataManager.updateUsuario(usuario)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getUsuarios(): List<Person> {
        try {
            return dataManager.getAllUsuarios()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getUsuarioById(id: String): Person {
        try {
            val result = dataManager.getUsuarioById(id)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }
            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }
}