package com.gen.userdataapp

import android.app.Application
import com.gen.userdataapp.di.domainModule
import com.gen.userdataapp.di.localModule
import com.gen.userdataapp.di.mappersModule
import com.gen.userdataapp.di.networkModule
import com.gen.userdataapp.di.repositoryModule
import com.gen.userdataapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                domainModule,
                localModule,
                networkModule,
                repositoryModule,
                viewModelModule,
                mappersModule
            )
        }
    }
}