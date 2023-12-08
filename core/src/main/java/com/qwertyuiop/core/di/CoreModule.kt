package com.qwertyuiop.core.di

import com.qwertyuiop.core.remote.RemoteUtils
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object CoreModule {

    val module = module {
        singleOf(::RemoteUtils)
    }

}