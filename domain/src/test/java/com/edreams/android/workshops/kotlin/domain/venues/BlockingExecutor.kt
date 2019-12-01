package com.edreams.android.workshops.kotlin.domain.venues

import com.edreams.android.workshops.kotlin.domain.common.Suspendable
import com.edreams.android.workshops.kotlin.domain.interactor.Executor
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class BlockingExecutor : Executor {
  override fun <T> ui(uiFun: Suspendable<T>) = GlobalScope.launch(Unconfined) {
    uiFun()
  }

  override fun <T> bg(asyncFun: Suspendable<T>): Deferred<T> =
    GlobalScope.async(Unconfined) { asyncFun() }
}