package com.example.android.eggtimernotifications.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
private const val DEFAULT_PREFERENCES = "com.example.android.eggtimernotifications"

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(DEFAULT_PREFERENCES, Context.MODE_PRIVATE)
    }
}