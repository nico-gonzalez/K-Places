package com.edreams.android.workshops.kotlin.domain.venues

import com.edreams.android.workshops.kotlin.domain.common.Suspendable
import com.edreams.android.workshops.kotlin.domain.interactor.Executor
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Unconfined
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class BlockingExecutor : Executor {
  override fun <T> ui(uiFun: Suspendable<T>) = launch(Unconfined) {
    uiFun()
  }

  override fun <T> bg(asyncFun: Suspendable<T>): Deferred<T> =
      async(Unconfined) { asyncFun() }
}