package com.larrex.myapplication.hilt_di.module

import com.larrex.myapplication.Util
import com.larrex.myapplication.network.IgboApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NonAbstractModule {

    @Singleton
    @Provides
    fun doGetMeaning(): IgboApiInterface {

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1,TimeUnit.MINUTES)
            .readTimeout(40,TimeUnit.SECONDS)
            .writeTimeout(40,TimeUnit.SECONDS).build()

        return Retrofit.Builder()
            .baseUrl(Util.IGBO_API_LINK_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IgboApiInterface::class.java)
    }

}