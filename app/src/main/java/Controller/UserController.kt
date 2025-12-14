package Controller

import Data.ApiDataManager
import Data.iDataManager
import Entity.User
import android.content.Context
import cr.ac.utn.census.R

class UserController {

    private var dataManager: iDataManager = ApiDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    suspend fun addUsuario(usuario: User) {
        try {
            dataManager.addUsuario(usuario)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    suspend fun updateUsuario(usuario: User) {
        try {
            dataManager.updateUsuario(usuario)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    suspend fun getUsuarios(): List<User> {
        try {
            return dataManager.getAllUsuarios()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    suspend fun getUsuarioById(id: String): User {
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
