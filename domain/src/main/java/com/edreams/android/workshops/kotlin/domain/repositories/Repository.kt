package com.edreams.android.workshops.kotlin.domain.repositories

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.channels.ProducerScope
import kotlinx.coroutines.experimental.channels.produce

interface Repository {

  fun <T> produce(producer: suspend ProducerScope<T>.() -> Unit) = produce(CommonPool) {
    producer()
  }
}