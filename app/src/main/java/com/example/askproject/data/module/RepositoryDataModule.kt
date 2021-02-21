package com.example.askproject.data.module

import android.content.Context
import com.example.askproject.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.experimental.builder.factoryBy


val repositoryDataModule = module {
    single {
        androidContext().getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

//    factoryBy<SessionRepository, SessionDataRepository>()
//
//    factoryBy<PreferenceRepository, PreferenceDataRepository>()
}

