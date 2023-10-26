package com.example.templatesecond

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.example.domaingray.di.DomainGrayModule.module as DomainGrayModule
import com.example.domainwhite.di.DomainWhiteModule.module as DomainWhiteModule
import com.example.gray.di.GrayModule.module as GrayModule
import com.example.localdatagray.di.LocalGrayModule.module as LocalGrayModule
import com.example.localdatawhite.di.LocalWhiteModule.module as LocalWhiteModule
import com.example.remotedata.di.RemoteModule.module as RemoteModule
import com.example.white.di.WhiteModule.module as WhiteModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App.applicationContext)
            modules(
                GrayModule,
                LocalWhiteModule,
                LocalGrayModule,
                RemoteModule,
                WhiteModule,
                DomainWhiteModule,
                DomainGrayModule
            )
        }
    }
}