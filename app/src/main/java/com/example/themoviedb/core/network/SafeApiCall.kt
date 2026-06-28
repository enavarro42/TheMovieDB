package com.example.themoviedb.core.network

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResult<T> {
    return try {
        ApiResult.Success(apiCall())
    } catch (e: retrofit2.HttpException) {
        val appException = when (e.code()) {
            401 -> AppException.UnauthorizedException()
            404 -> AppException.NotFoundException()
            else -> AppException.HttpException(e.code(), e.response()?.errorBody()?.string())
        }
        ApiResult.Error(appException)
    } catch (e: java.io.IOException) {
        ApiResult.Error(AppException.NetworkException(e))
    } catch (e: java.net.SocketTimeoutException) {
        ApiResult.Error(AppException.TimeoutException(e))
    } catch (e: Exception) {
        ApiResult.Error(AppException.UnknownException(e))
    }
}