package com.edreams.android.workshops.kotlin.domain.interactor

import com.edreams.android.workshops.kotlin.domain.common.Suspendable
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job

interface Executor {

  fun <T> ui(uiFun: Suspendable<T>): Job

  fun <T> bg(asyncFun: Suspendable<T>): Deferred<T>
}