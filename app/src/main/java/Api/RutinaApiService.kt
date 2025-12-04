package Api

import Entity.Province
import retrofit2.Call
import retrofit2.http.*

interface RutinaApiService {
    @GET("rutinas")
    fun getAllRutinas(): Call<ApiResponse<List<Province>>>

    @GET("rutinas/{id}")
    fun getRutinaById(@Path("id") id: String): Call<ApiResponse<Province>>

    @POST("rutinas")
    fun createRutina(@Body rutina: Province): Call<ApiResponse<Province>>

    @PUT("rutinas/{id}")
    fun updateRutina(@Path("id") id: String, @Body rutina: Province): Call<ApiResponse<Province>>

    @DELETE("rutinas/{id}")
    fun deleteRutina(@Path("id") id: String): Call<ApiResponse<Province>>
}
