package Util

import android.content.Context
import android.content.Intent
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class Util {
    companion object {
        // Abrir una nueva actividad
        fun openActivity(context: Context, objClass: Class<*>) {
            val intent = Intent(context, objClass)
            context.startActivity(intent)
        }

        // Generar un ID único para las entidades
        fun generateId(): String {
            return UUID.randomUUID().toString()
        }

        // Formatear fecha a String legible
        fun formatDate(date: Date): String {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return formatter.format(date)
        }

        // Formatear fecha con hora a String legible
        fun formatDateTime(date: Date): String {
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            return formatter.format(date)
        }

        // Parsear String a Date
        fun parseDate(dateString: String): Date? {
            return try {
                val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                formatter.parse(dateString)
            } catch (e: Exception) {
                null
            }
        }

        // Calcular días restantes hasta una fecha
        fun diasRestantes(fechaVencimiento: Date): Long {
            val hoy = Date()
            val diff = fechaVencimiento.time - hoy.time
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
        }

        // Verificar si una membresía está vencida
        fun isMembresiaVencida(fechaVencimiento: Date): Boolean {
            val hoy = Date()
            return fechaVencimiento.before(hoy)
        }

        // Calcular progreso de peso (porcentaje de incremento)
        fun calcularProgresoEnPeso(pesoInicial: Double, pesoActual: Double): Double {
            if (pesoInicial == 0.0) return 0.0
            return ((pesoActual - pesoInicial) / pesoInicial) * 100
        }

        // Validar email
        fun isEmailValid(email: String): Boolean {
            val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
            return emailRegex.matches(email)
        }

        // Validar que un string no esté vacío
        fun isNotEmpty(text: String): Boolean {
            return text.trim().isNotEmpty()
        }

        // Obtener fecha actual
        fun getCurrentDate(): Date {
            return Date()
        }

        // Convertir Date a String en formato ISO para la API
        fun dateToISOString(date: Date): String {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            return formatter.format(date)
        }

        // Obtener fecha actual como String en formato ISO para la API
        fun getCurrentDateString(): String {
            return dateToISOString(Date())
        }

        // Calcular IMC (Índice de Masa Corporal) - útil para tracking de progreso
        fun calcularIMC(peso: Double, altura: Double): Double {
            if (altura == 0.0) return 0.0
            return peso / (altura * altura)
        }

        // Formatear peso para mostrar con unidad
        fun formatPeso(peso: Double): String {
            return String.format("%.1f kg", peso)
        }

        // Validar que un número sea positivo
        fun isPositive(numero: Double): Boolean {
            return numero > 0.0
        }

        // Validar que un número sea positivo
        fun isPositive(numero: Int): Boolean {
            return numero > 0
        }
    }
}