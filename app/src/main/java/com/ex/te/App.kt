package com.ex.te

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.ex.domaingray.di.DomainGrayModule.module as DomainGrayModule
import com.ex.domainwhite.di.DomainWhiteModule.module as DomainWhiteModule
import com.ex.gray.di.GrayModule.module as GrayModule
import com.ex.localdatagray.di.LocalGrayModule.module as LocalGrayModule
import com.ex.localdatawhite.di.LocalWhiteModule.module as LocalWhiteModule
import com.ex.remotedata.di.RemoteModule.module as RemoteModule
import com.ex.white.di.WhiteModule.module as WhiteModule

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