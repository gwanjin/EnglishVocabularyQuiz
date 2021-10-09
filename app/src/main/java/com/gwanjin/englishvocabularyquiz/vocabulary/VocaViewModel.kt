package com.gwanjin.englishvocabularyquiz.vocabulary

import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gwanjin.englishvocabularyquiz.client.VocaQuizService
import com.gwanjin.englishvocabularyquiz.data.Answer
import com.gwanjin.englishvocabularyquiz.data.Question
import com.gwanjin.englishvocabularyquiz.data.ResultGetVocaTest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class VocaViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var data = MutableLiveData<ResultGetVocaTest>()
    var isReset = MutableLiveData<Boolean>()
    var statusChoice1 = MutableLiveData<Int>() // 0:初期化、1:正解、2:不正解、3:非活性化
    var statusChoice2 = MutableLiveData<Int>()
    var statusChoice3 = MutableLiveData<Int>()

    fun getNewData() {

        VocaQuizService.getVocaQuiz().getVocaQuizInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    item -> data.value = item
                },
                {
                    it.message?.let { it1 -> Log.d("AAA", it1) }
                }
            )


        this.isReset.value = true
        statusChoice1.value = 0
        statusChoice2.value = 0
        statusChoice3.value = 0
    }

    fun clicedChoice1() {
        this.isReset.value = false

        if (this.data.value!!.answer[0].correct) {
            statusChoice1.value = 1
            statusChoice2.value = 3
            statusChoice3.value = 3
        } else {
            statusChoice1.value = 2
            statusChoice2.value = if (this.data.value!!.answer[1].correct) 1 else 3
            statusChoice3.value = if (this.data.value!!.answer[2].correct) 1 else 3
        }
    }

    fun clicedChoice2() {
        this.isReset.value = false

        if (this.data.value!!.answer[1].correct) {
            statusChoice1.value = 3
            statusChoice2.value = 1
            statusChoice3.value = 3
        } else {
            statusChoice1.value = if (this.data.value!!.answer[0].correct) 1 else 3
            statusChoice2.value = 2
            statusChoice3.value = if (this.data.value!!.answer[2].correct) 1 else 3
        }
    }

    fun clicedChoice3() {
        this.isReset.value = false

        if (this.data.value!!.answer[2].correct) {
            statusChoice1.value = 3
            statusChoice2.value = 3
            statusChoice3.value = 1
        } else {
            statusChoice1.value = if (this.data.value!!.answer[0].correct) 1 else 3
            statusChoice2.value = if (this.data.value!!.answer[1].correct) 1 else 3
            statusChoice3.value = 2
        }
    }

    fun playAudio() {
        var mediaplayer = MediaPlayer()
        mediaplayer.setDataSource(this.data.value?.question?.audio)
        mediaplayer.prepare()
        mediaplayer.start()
    }
}