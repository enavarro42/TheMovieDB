package com.example.themoviedb.core.network

sealed class AppException(message: String, cause: Throwable? = null) : Exception(message, cause) {
    class NetworkException(cause: Throwable) : AppException("Sin conexión a internet", cause)
    class HttpException(val code: Int, val errorBody: String?) : AppException("Error HTTP: $code")
    class TimeoutException(cause: Throwable) : AppException("Tiempo de espera agotado", cause)
    class UnknownException(cause: Throwable) : AppException("Error desconocido", cause)
    class UnauthorizedException : AppException("Sesión expirada")
    class NotFoundException : AppException("Recurso no encontrado")
}

// Extensión para convertir a mensaje legible
fun AppException.toUserMessage(): String = when (this) {
    is AppException.NetworkException -> "Sin conexión. Verifica tu internet."
    is AppException.HttpException -> when (code) {
        401 -> "Sesión expirada"
        403 -> "No tienes permisos"
        404 -> "No encontrado"
        429 -> "Demasiadas solicitudes. Intenta más tarde."
        500 -> "Error del servidor. Intenta más tarde."
        else -> "Error: $code"
    }
    is AppException.TimeoutException -> "La solicitud tardó demasiado. Intenta de nuevo."
    is AppException.UnauthorizedException -> "Tu sesión ha expirado"
    is AppException.NotFoundException -> "El recurso no existe"
    is AppException.UnknownException -> "Algo salió mal. Intenta de nuevo."
}