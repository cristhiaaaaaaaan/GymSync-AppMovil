package Api

import Entity.Rutina
import retrofit2.http.*

interface RutinaApiService {
    @GET("rutinas")
    suspend fun getAllRutinas(): ApiResponse<List<Rutina>>

    @GET("rutinas/{id}")
    suspend fun getRutinaById(@Path("id") id: String): ApiResponse<Rutina>

    @POST("rutinas")
    suspend fun createRutina(@Body rutina: Rutina): ApiResponse<Rutina>

    @PUT("rutinas/{id}")
    suspend fun updateRutina(@Path("id") id: String, @Body rutina: Rutina): ApiResponse<Rutina>

    @DELETE("rutinas/{id}")
    suspend fun deleteRutina(@Path("id") id: String): ApiResponse<Rutina>
}
