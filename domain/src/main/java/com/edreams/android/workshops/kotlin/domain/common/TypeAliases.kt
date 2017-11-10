package com.edreams.android.workshops.kotlin.domain.common

typealias Callback<T> = (T) -> Unit

typealias Suspendable<T> = suspend () -> T