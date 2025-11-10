package cz.vican.scratchapp.library.networking.di

import cz.vican.scratchapp.library.networking.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val networkModule = module {
    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
    single { Retrofit.createOkHttpClient(get()) }
    single { Retrofit.createMoshi() }
}
