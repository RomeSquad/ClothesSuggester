package logic.exceptions

sealed class ApiException(message: String, cause: Throwable? = null) : Exception(message, cause) {

}