package com.samar.pulsesolutionpreviewinterview.di


import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.samar.pulsesolutionpreviewinterview.R
import com.samar.pulsesolutionpreviewinterview.data.remote.PreviewApi
import com.samar.pulsesolutionpreviewinterview.data.repository.PreviewRepositoryImp
import com.samar.pulsesolutionpreviewinterview.domain.repository.PreviewRepository
import com.samar.pulsesolutionpreviewinterview.util.ConnectionLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Singleton
    @Provides
    fun getOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val httpClient =
            OkHttpClient.Builder().callTimeout(5, TimeUnit.MINUTES).readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES).readTimeout(5, TimeUnit.MINUTES).writeTimeout(
                    5, TimeUnit.MINUTES
                )
        httpClient.addInterceptor(httpLoggingInterceptor)
        return httpClient.build()
    }


    @Singleton
    @Provides
    fun getPreviewApi(httpClient: OkHttpClient, @ApplicationContext context: Context): PreviewApi {
        val gson =
            GsonBuilder().setLenient().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
        val retrofit: Retrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(context.resources.getString(R.string.base_url)).client(httpClient).build()
        return retrofit.create(PreviewApi::class.java)
    }


    @Singleton
    @Provides
    fun getNetworkStatus(@ApplicationContext context: Context): ConnectionLiveData = ConnectionLiveData(context)


    @Singleton
    @Provides
    fun getRepositories(api: PreviewApi): PreviewRepository = PreviewRepositoryImp(api)

}