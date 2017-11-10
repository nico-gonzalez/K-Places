package com.edreams.android.workshops.kotlin.domain.interactor

import com.edreams.android.workshops.kotlin.domain.common.Suspendable

abstract class BaseInteractor(private val executor: Executor) {

  fun <T> postExecute(uiFun: Suspendable<T>) =
      executor.ui(uiFun)

  fun <T> asyncExecute(asyncFun: Suspendable<T>) =
      executor.bg(asyncFun)
}