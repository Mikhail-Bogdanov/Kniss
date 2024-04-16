package com.evoteam.kniss

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.evoteam.appentrypoint.di.EntryPointModule.module as AppEntryPointModule
import com.evoteam.core.di.CoreModule.module as CoreModule
import com.evoteam.domain.di.DomainModule.module as DomainModule
import com.evoteam.localData.di.LocalDataModule.module as LocalDataModule
import com.evoteam.remoteData.di.RemoteDataModule.module as RemoteDataModule
import com.evoteam.presentation.di.PresentationModule.module as PresentationModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App.applicationContext)
            modules(
                LocalDataModule,
                RemoteDataModule,
                PresentationModule,
                DomainModule,
                AppEntryPointModule,
                CoreModule
            )
        }
    }
}