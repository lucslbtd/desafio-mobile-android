package com.example.desafiomarvel.di

import com.example.desafiomarvel.BuildConfig
import com.example.desafiomarvel.data.repository.MarvelRepository
import com.example.desafiomarvel.domain.usecases.GetCharactersUseCase
import com.example.desafiomarvel.network.MarvelInterceptor
import com.example.desafiomarvel.network.MarvelService
import com.example.desafiomarvel.presentation.home.HomePresenter
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(MarvelInterceptor())
    .build()

val appModule = module {
    single {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(MarvelService::class.java) }
    single { MarvelRepository(get()) }
    factory { GetCharactersUseCase(get()) }
    factory { HomePresenter(get(), get()) }
}
