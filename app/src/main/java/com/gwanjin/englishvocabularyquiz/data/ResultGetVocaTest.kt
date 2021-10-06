package com.gwanjin.englishvocabularyquiz.data

data class ResultGetVocaTest(
    var id : Integer,
    var question: Question,
    var answer: Answer
)

data class Question(
    val word:String,
    val question: String,
    val audio: String
)

data class Answer(
    val test:String,
    val correct: Boolean
)
