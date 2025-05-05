package logic.exceptions

sealed class ApiException(message: String, cause: Throwable? = null) : Exception(message, cause) {
    class NetworkError(message: String, cause: Throwable? = null) : ApiException(message, cause)
    class DataError(message: String, cause: Throwable? = null) : ApiException(message, cause)
}