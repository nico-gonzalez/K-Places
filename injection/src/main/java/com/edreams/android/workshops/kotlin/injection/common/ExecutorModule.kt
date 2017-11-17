package com.edreams.android.workshops.kotlin.injection.common

import com.edreams.android.workshops.kotlin.data.executor.ThreadExecutor
import com.edreams.android.workshops.kotlin.domain.interactor.Executor
import com.edreams.android.workshops.kotlin.injection.scopes.PerApplication
import dagger.Binds
import dagger.Module

@Module
abstract class ExecutorModule {

  @PerApplication
  @Binds
  abstract fun bindExecutor(threadExecutor: ThreadExecutor): Executor
}