package com.example.hookset_mobile.modules

import com.example.hookset_mobile.AuthService
import org.koin.dsl.module

val authModule = module{
    single{ AuthService(get(), get()) }
}
