package Api

import Entity.User
import retrofit2.Call
import retrofit2.http.*

interface UserApiService {
    @GET("users")
    fun getAllUsers(): Call<ApiResponse<List<User>>>

    @GET("users/{id}")
    fun getUserById(@Path("id") id: String): Call<ApiResponse<User>>

    @POST("users")
    fun createUser(@Body user: User): Call<ApiResponse<User>>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id: String, @Body user: User): Call<ApiResponse<User>>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: String): Call<ApiResponse<User>>
}
