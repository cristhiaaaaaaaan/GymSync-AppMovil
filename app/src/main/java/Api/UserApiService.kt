package Api

import Entity.User
import retrofit2.http.*

interface UserApiService {
    @GET("users")
    suspend fun getAllUsers(): ApiResponse<List<User>>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: String): ApiResponse<User>

    @GET("users/email/{email}")
    suspend fun getUserByEmail(@Path("email") email: String): ApiResponse<User>

    @POST("users")
    suspend fun createUser(@Body user: User): ApiResponse<User>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: User): ApiResponse<User>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: String): ApiResponse<User>
}
