    package id.smkcoding.githubuserapp.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun httpClient(): OkHttpClient {
    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val builder = OkHttpClient.Builder()

    builder.readTimeout(60, TimeUnit.SECONDS)
    builder.connectTimeout(60, TimeUnit.SECONDS)
    builder.addInterceptor(logInterceptor)

    return builder.build()
}

inline fun <reified T> retrofitRequest(okHttpClient: OkHttpClient): T {
    val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://konser.azurewebsites.net/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    return retrofit.create(T::class.java)
}