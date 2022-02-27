package com.corneliudascalu.bakerjourney

import android.app.Application
import com.corneliudascalu.bakerjourney.log.LogEntriesViewModel
import com.corneliudascalu.bakerjourney.log.LogRepository
import com.corneliudascalu.bakerjourney.log.ShortLogRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BakerLifeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                module {
                    single { LogRepository() }
                    single { ShortLogRepository(repository = get()) }
                    viewModel { LogEntriesViewModel(repository = get()) }
                }
            )
        }
    }
}