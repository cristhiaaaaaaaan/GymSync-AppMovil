package Api

import Entity.Membresia
import retrofit2.Call
import retrofit2.http.*

interface MembresiaApiService {
    @GET("membresias")
    fun getAllMembresias(): Call<ApiResponse<List<Membresia>>>

    @GET("membresias/{id}")
    fun getMembresiaById(@Path("id") id: String): Call<ApiResponse<Membresia>>

    @POST("membresias")
    fun createMembresia(@Body membresia: Membresia): Call<ApiResponse<Membresia>>

    @PUT("membresias/{id}")
    fun updateMembresia(@Path("id") id: String, @Body membresia: Membresia): Call<ApiResponse<Membresia>>

    @DELETE("membresias/{id}")
    fun deleteMembresia(@Path("id") id: String): Call<ApiResponse<Membresia>>
}
