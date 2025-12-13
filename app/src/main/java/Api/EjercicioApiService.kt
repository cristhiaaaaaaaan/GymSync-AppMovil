package Api

import Entity.Ejercicio
import retrofit2.http.*

interface EjercicioApiService {
    @GET("ejercicios")
    suspend fun getAllEjercicios(@Query("usuarioId") usuarioId: String? = null): ApiResponse<List<Ejercicio>>

    @GET("ejercicios/{id}")
    suspend fun getEjercicioById(@Path("id") id: String): ApiResponse<Ejercicio>

    @POST("ejercicios")
    suspend fun createEjercicio(@Body ejercicio: Ejercicio): ApiResponse<Ejercicio>

    @PUT("ejercicios/{id}")
    suspend fun updateEjercicio(@Path("id") id: String, @Body ejercicio: Ejercicio): ApiResponse<Ejercicio>

    @DELETE("ejercicios/{id}")
    suspend fun deleteEjercicio(@Path("id") id: String): ApiResponse<Ejercicio>
}
