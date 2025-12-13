package Util

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()

    companion object {
        private const val PREF_NAME = "GymSyncSession"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
        private const val KEY_USER_ID = "userId"
        private const val KEY_USER_NAME = "userName"
        private const val KEY_USER_EMAIL = "userEmail"
        private const val KEY_AUTH_TOKEN = "authToken"
    }

    fun createLoginSession(userId: String, userName: String, userEmail: String, authToken: String? = null) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.putString(KEY_USER_ID, userId)
        editor.putString(KEY_USER_NAME, userName)
        editor.putString(KEY_USER_EMAIL, userEmail)
        authToken?.let { editor.putString(KEY_AUTH_TOKEN, it) }
        editor.apply()
    }

    /**
     * Verificar si el usuario está logueado
     */
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    /**
     * Obtener ID del usuario actual
     */
    fun getUserId(): String? {
        return prefs.getString(KEY_USER_ID, null)
    }

    /**
     * Obtener nombre del usuario actual
     */
    fun getUserName(): String? {
        return prefs.getString(KEY_USER_NAME, null)
    }

    fun getUserEmail(): String? {
        return prefs.getString(KEY_USER_EMAIL, null)
    }

    fun getAuthToken(): String? {
        return prefs.getString(KEY_AUTH_TOKEN, null)
    }

    fun logout() {
        editor.clear()
        editor.apply()
    }

    /**
     * Verificar si hay sesión activa y redirigir si es necesario
     */
    fun checkLogin(): HashMap<String, String?> {
        val user = HashMap<String, String?>()
        user[KEY_USER_ID] = prefs.getString(KEY_USER_ID, null)
        user[KEY_USER_NAME] = prefs.getString(KEY_USER_NAME, null)
        user[KEY_USER_EMAIL] = prefs.getString(KEY_USER_EMAIL, null)
        return user
    }
}
