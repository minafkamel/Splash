package com.hello.splash.photolist.data.di

import PhotoDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hello.splash.photolist.data.Photo
import com.hello.splash.photolist.data.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideBaseUrl(): String = "https://api.unsplash.com/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(
            HttpLoggingInterceptor().apply {
                HttpLoggingInterceptor.Level.BODY
            }
        )
        addInterceptor(ClientIdInterceptor)
    }.build()


    @Provides
    @Singleton
    fun providesGsonAdapter() = GsonBuilder()
        .registerTypeAdapter(Photo::class.java,  PhotoDeserializer())  // Register the custom deserializer
        .create();

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): RemoteDataSource =
        retrofit.create(RemoteDataSource::class.java)
}
