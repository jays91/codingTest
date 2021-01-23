package com.pmj.codetest.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pmj.codetest.ApiUrl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Provide required dependency for network things
 * */

var networkModule = module {

    single { provideGson() }
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), get()) }
}

fun provideGson(): Gson = GsonBuilder().create()

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder().baseUrl(ApiUrl.BASE_URL).client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}