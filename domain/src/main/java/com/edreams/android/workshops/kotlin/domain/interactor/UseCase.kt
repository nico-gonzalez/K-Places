package com.edreams.android.workshops.kotlin.domain.interactor

import com.edreams.android.workshops.kotlin.domain.common.Suspendable

data class Result<out T>(
    val value: T? = null,
    val error: Throwable? = null
) {
    val isError = error != null

    val isSuccess = !isError
}

abstract class UseCase(private val executor: Executor) {

    fun <T> postExecute(uiFun: Suspendable<T>) =
        executor.ui(uiFun)

    fun <T> asyncExecute(asyncFun: Suspendable<T>) =
        executor.bg(asyncFun)
}
