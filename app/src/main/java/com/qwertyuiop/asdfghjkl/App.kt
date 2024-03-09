package com.qwertyuiop.asdfghjkl

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.qwertyuiop.appentrypoint.di.EntryPointModule.module as AppEntryPointModule
import com.qwertyuiop.core.di.CoreModule.module as CoreModule
import com.qwertyuiop.domain.di.DomainModule.module as DomainModule
import com.qwertyuiop.localData.di.LocalDataModule.module as LocalDataModule
import com.qwertyuiop.remoteData.di.RemoteDataModule.module as RemoteDataModule
import com.qwertyuiop.presentation.di.PresentationModule.module as PresentationModule

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