package Api

import Entity.Membresia
import retrofit2.http.*

interface MembresiaApiService {
    @GET("membresias")
    suspend fun getAllMembresias(): ApiResponse<List<Membresia>>

    @GET("membresias/{id}")
    suspend fun getMembresiaById(@Path("id") id: String): ApiResponse<Membresia>

    @POST("membresias")
    suspend fun createMembresia(@Body membresia: Membresia): ApiResponse<Membresia>

    @PUT("membresias/{id}")
    suspend fun updateMembresia(@Path("id") id: String, @Body membresia: Membresia): ApiResponse<Membresia>

    @DELETE("membresias/{id}")
    suspend fun deleteMembresia(@Path("id") id: String): ApiResponse<Membresia>
}
