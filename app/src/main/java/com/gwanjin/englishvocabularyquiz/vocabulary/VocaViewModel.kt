package com.gwanjin.englishvocabularyquiz.vocabulary

import android.app.Application
import android.media.MediaPlayer
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gwanjin.englishvocabularyquiz.client.VocaQuizService
import com.gwanjin.englishvocabularyquiz.data.ResultGetVocaTest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class VocaViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    var data = MutableLiveData<ResultGetVocaTest>()
    var isReset = MutableLiveData<Boolean>()
    var statusChoice1 = MutableLiveData<Int>() // 0:初期化、1:正解、2:不正解、3:非活性化
    var statusChoice2 = MutableLiveData<Int>()
    var statusChoice3 = MutableLiveData<Int>()

    init {
        getNewData()
    }

    fun getNewData() {
        VocaQuizService.getVocaQuiz().getVocaQuizInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { item ->
                    data.value=item
                    this.isReset.value = true
                    statusChoice1.value = 0
                    statusChoice2.value = 0
                    statusChoice3.value = 0
                },
                { e -> onFailure(e) }
            )
    }

    private fun onFailure(error: Throwable) {
        var message = ""
        message += when (error) {
            is HttpException -> message += "サーバエラーが発生しております。\nエラーコード：${error.code()}, "
            is SocketTimeoutException -> "エラーが発生しております。${error.message}"
            is IOException -> "エラーが発生しております。${error.message}"
            else -> "エラーが発生しております。${error.message}"
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun clickedAnswer1() {
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

    fun clickedAnswer2() {
        this.isReset.value = false

        if (this.data.value!!.answer[1].correct) {
            this.statusChoice1.value = 3
            this.statusChoice2.value = 1
            this.statusChoice3.value = 3
        } else {
            this.statusChoice1.value = if (this.data.value!!.answer[0].correct) 1 else 3
            this.statusChoice2.value = 2
            this.statusChoice3.value = if (this.data.value!!.answer[2].correct) 1 else 3
        }
    }

    fun clickedAnswer3() {
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