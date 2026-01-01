package com.messenger.jaber.data.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.messenger.jaber.data.datastore.appDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDataStoreModule {

    @Provides
    @Singleton
    fun provideAppDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.appDataStore
    }

}