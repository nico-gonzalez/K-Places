package com.edreams.android.workshops.kotlin.data.executor

import com.edreams.android.workshops.kotlin.domain.common.Suspendable
import com.edreams.android.workshops.kotlin.domain.interactor.Executor
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class ThreadExecutor @Inject constructor() : Executor {
  override fun <T> ui(uiFun: Suspendable<T>): Job =
      launch(UI) {
        uiFun()
      }

  override fun <T> bg(asyncFun: Suspendable<T>): Deferred<T> =
      async(CommonPool) {
        asyncFun()
      }
}