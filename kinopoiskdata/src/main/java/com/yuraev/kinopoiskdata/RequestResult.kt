package com.yuraev.kinopoiskdata

import kotlin.getOrThrow
import kotlin.let

sealed class RequestResult<out E>( open val data: E? = null) {
    class InProgress<E>(data: E? = null) : RequestResult<E>(data)
    class Success<E>(override val data: E) : RequestResult<E>(data)
    class Error<E>(
        data: E? = null,
        val error: Throwable? = null
    ) : RequestResult<E>(data)
}

internal fun <T> Result<T>.toRequestResult(): RequestResult<T> {
    return if (isSuccess) {
        RequestResult.Success(this.getOrThrow())
    } else {
        RequestResult.Error()
    }
}


fun <I, O> RequestResult<I>.map(mapper: (I) -> O): RequestResult<O> {

    return when (this) {
        is RequestResult.Success -> RequestResult.Success(mapper(data))
        is RequestResult.Error -> RequestResult.Error(data?.let(mapper))
        is RequestResult.InProgress -> RequestResult.InProgress(data?.let(mapper))

    }
}
