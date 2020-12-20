package com.aru_oi.cleanarchitecture.functional

sealed class Result<out TSuccess, out TFailure> {
    data class Success<out TSuccess>(val data: TSuccess) : Result<TSuccess, Nothing>()
    data class Failure<out TFailure>(val failure: TFailure) : Result<Nothing, TFailure>()

    fun fold(s: (TSuccess) -> Any, f: (TFailure) -> Any): Any =
        when (this) {
            is Success -> s(data)
            is Failure -> f(failure)
        }
}