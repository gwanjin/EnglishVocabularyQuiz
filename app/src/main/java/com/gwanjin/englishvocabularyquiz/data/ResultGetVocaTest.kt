package com.gwanjin.englishvocabularyquiz.data

data class ResultGetVocaTest(
    val id: Int,
    val question: Question,
    val answer: List<Answer>
)

data class Question(
    val word: String,
    val question: String,
    val audio: String
)

data class Answer(
    val text: String,
    val correct: Boolean
)