package Api

data class ApiResponse<T>(
    val data: T?,
    val responseCode: Int,
    val message: String
)
