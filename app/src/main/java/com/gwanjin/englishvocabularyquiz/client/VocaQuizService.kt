package com.gwanjin.englishvocabularyquiz.client

import com.gwanjin.englishvocabularyquiz.api.VocaQuizAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class VocaQuizService {
    companion object {
        private const val BASE_URL = ""

        fun getVocaQuiz() :VocaQuizAPI {
            // 通信Log出力
            val logIntercepter = HttpLoggingInterceptor()
            logIntercepter.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(logIntercepter).build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(VocaQuizAPI::class.java)
        }
    }
}