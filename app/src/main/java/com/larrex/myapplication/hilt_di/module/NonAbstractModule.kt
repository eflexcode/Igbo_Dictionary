package com.larrex.myapplication.hilt_di.module

import com.larrex.myapplication.Util
import com.larrex.myapplication.network.IgboApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NonAbstractModule {

    @Singleton
    @Provides
    fun doGetMeaning(): IgboApiInterface {

        return Retrofit.Builder()
            .baseUrl(Util.IGBO_API_LINK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IgboApiInterface::class.java)
    }

}