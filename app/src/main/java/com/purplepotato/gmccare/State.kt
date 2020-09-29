package com.purplepotato.gmccare

sealed class State<T>
    (
    val data: T? = null,
    val message: String? = null
) {
    class OnLoading<T> : State<T>()
    class OnSuccess<T>(data: T?) : State<T>(data)
    class OnError<T>(message: String?, data: T? = null) : State<T>(data, message)
}