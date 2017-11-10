package com.edreams.android.workshops.kotlin.domain.interactor

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

import com.edreams.android.workshops.kotlin.domain.common.Suspendable

interface BaseInteractor {

  fun <T> postExecute(uiFun: Suspendable<T>) =
      launch(UI) {
        uiFun()
      }

  fun <T> asyncExecute(asyncFun: Suspendable<T>) =
      async(CommonPool) {
        asyncFun()
      }
}