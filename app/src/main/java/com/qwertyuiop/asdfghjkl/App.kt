package com.qwertyuiop.asdfghjkl

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.qwertyuiop.appentrypoint.di.EntryPointModule.module as AppEntryPointModule
import com.qwertyuiop.core.di.CoreModule.module as CoreModule
import com.qwertyuiop.di.EntryPointModule.module as EntryPointModule
import com.qwertyuiop.domaingray.di.DomainGrayModule.module as DomainGrayModule
import com.qwertyuiop.domainwhite.di.DomainWhiteModule.module as DomainWhiteModule
import com.qwertyuiop.gray.di.GrayModule.module as GrayModule
import com.qwertyuiop.localdatagray.di.LocalGrayModule.module as LocalGrayModule
import com.qwertyuiop.localdatawhite.di.LocalWhiteModule.module as LocalWhiteModule
import com.qwertyuiop.remotedata.di.RemoteModule.module as RemoteModule
import com.qwertyuiop.white.di.WhiteModule.module as WhiteModule

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
                EntryPointModule,
                DomainWhiteModule,
                DomainGrayModule,
                AppEntryPointModule,
                CoreModule
            )
        }
    }
}