package com.example.kotlin_mvvm

import android.app.Application
import com.example.kotlin_mvvm.database.AppDatabase
import com.example.kotlin_mvvm.ui.auth.AuthViewModelFactory
import com.example.kotlin_mvvm.network.MyApi
import com.example.kotlin_mvvm.network.NetworkConnectionInterceptor
import com.example.kotlin_mvvm.preferences.PreferenceProvider
import com.example.kotlin_mvvm.responsitories.QuoteRepository
import com.example.kotlin_mvvm.responsitories.UserRepository
import com.example.kotlin_mvvm.ui.home.profile.ProfileViewModelFactory
import com.example.kotlin_mvvm.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(),instance()) }
        bind() from singleton { QuoteRepository(instance(),instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }

    }

}