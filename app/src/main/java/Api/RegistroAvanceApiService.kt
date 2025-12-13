package Api

import Entity.RegistroAvance
import retrofit2.http.*

interface RegistroAvanceApiService {
    @GET("registros-avance")
    suspend fun getAllRegistros(@Query("usuarioId") usuarioId: String? = null, @Query("ejercicioId") ejercicioId: String? = null): ApiResponse<List<RegistroAvance>>

    @GET("registros-avance/{id}")
    suspend fun getRegistroById(@Path("id") id: String): ApiResponse<RegistroAvance>

    @POST("registros-avance")
    suspend fun createRegistro(@Body registro: RegistroAvance): ApiResponse<RegistroAvance>

    @PUT("registros-avance/{id}")
    suspend fun updateRegistro(@Path("id") id: String, @Body registro: RegistroAvance): ApiResponse<RegistroAvance>

    @DELETE("registros-avance/{id}")
    suspend fun deleteRegistro(@Path("id") id: String): ApiResponse<RegistroAvance>
}
