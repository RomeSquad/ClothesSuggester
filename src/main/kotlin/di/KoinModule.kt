package org.example.di

import org.koin.dsl.module

val koinModule= module {
        includes(configModule, dataModule, presentationModule, useCaseModule)
}