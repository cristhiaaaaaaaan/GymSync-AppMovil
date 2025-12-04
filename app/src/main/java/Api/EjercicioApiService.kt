package Api

import Entity.Ejercicio
import retrofit2.Call
import retrofit2.http.*

interface EjercicioApiService {
    @GET("ejercicios")
    fun getAllEjercicios(): Call<ApiResponse<List<Ejercicio>>>

    @GET("ejercicios/{id}")
    fun getEjercicioById(@Path("id") id: String): Call<ApiResponse<Ejercicio>>

    @POST("ejercicios")
    fun createEjercicio(@Body ejercicio: Ejercicio): Call<ApiResponse<Ejercicio>>

    @PUT("ejercicios/{id}")
    fun updateEjercicio(@Path("id") id: String, @Body ejercicio: Ejercicio): Call<ApiResponse<Ejercicio>>

    @DELETE("ejercicios/{id}")
    fun deleteEjercicio(@Path("id") id: String): Call<ApiResponse<Ejercicio>>
}
