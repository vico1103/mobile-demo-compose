package cz.vican.scratchapp.library.networking

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {

    fun createMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    fun createOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(UrlConst.CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(UrlConst.READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()
    }

    inline fun <reified T> createWebService(
        okHttpClient: OkHttpClient, moshi: Moshi, url: String
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create(T::class.java)
    }
}