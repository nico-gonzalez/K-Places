package com.edreams.android.workshops.kotlin.domain.interactor

import com.edreams.android.workshops.kotlin.domain.common.Suspendable

data class Result<out T>(val value: T,
    val error: Throwable = Throwable())

abstract class BaseInteractor(private val executor: Executor) {

  fun <T> postExecute(uiFun: Suspendable<T>) =
      executor.ui(uiFun)

  fun <T> asyncExecute(asyncFun: Suspendable<T>) =
      executor.bg(asyncFun)
}