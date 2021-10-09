package com.gwanjin.englishvocabularyquiz.data

import com.google.gson.annotations.SerializedName

data class ResultGetVocaTest(
    @SerializedName("id") val id: Int,
    @SerializedName("question") val question: Question,
    @SerializedName("answer") val answer: List<Answer>
)

data class Question(
    @SerializedName("word") val word: String,
    @SerializedName("question") val question: String,
    @SerializedName("audio") val audio: String
)

data class Answer(
    @SerializedName("text") val text: String,
    @SerializedName("correct") val correct: Boolean
)

//
//data class ResultGetVocaTest(
//    var id : Integer,
//    var question: Question,
//    var answer: Answer
//)
//
//data class Question(
//    val word:String,
//    val question: String,
//    val audio: String
//)
//
//data class Answer(
//    val test:String,
//    val correct: Boolean
//)
