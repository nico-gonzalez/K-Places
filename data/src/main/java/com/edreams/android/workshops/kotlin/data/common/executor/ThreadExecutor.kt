package com.edreams.android.workshops.kotlin.data.common.executor

import com.edreams.android.workshops.kotlin.domain.common.Suspendable
import com.edreams.android.workshops.kotlin.domain.interactor.Executor
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class ThreadExecutor @Inject constructor() : Executor {
  override fun <T> ui(uiFun: Suspendable<T>): Job =
    GlobalScope.launch(Dispatchers.Main) {
      uiFun()
    }

  override fun <T> bg(asyncFun: Suspendable<T>): Deferred<T> =
    GlobalScope.async(Dispatchers.IO) {
      asyncFun()
    }
}