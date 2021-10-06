package com.gwanjin.englishvocabularyquiz.api

import com.gwanjin.englishvocabularyquiz.data.ResultGetVocaTest
import io.reactivex.Single
import retrofit2.http.GET

interface VocaQuizAPI {
    @GET("/default/program-detail-for-test")
    fun getVocaQuizInfo() : Single<ResultGetVocaTest>
}