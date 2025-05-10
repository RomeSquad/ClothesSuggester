package org.example.di

import org.example.data.api.network.HttpClient
import org.example.data.api.network.HttpClientImpl
import org.example.data.api.network.NetworkConfig
import org.example.data.api.network.UrlBuilder
import org.koin.dsl.module

val configModule = module {
    single<HttpClient> { HttpClientImpl() }
    single { NetworkConfig }
    single { UrlBuilder() }

}