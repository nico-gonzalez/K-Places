package com.edreams.android.workshops.kotlin.common.injection.scopes

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

@Scope
@Retention(RUNTIME)
annotation class PerApplication