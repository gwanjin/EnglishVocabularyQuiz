package com.gwanjin.englishvocabularyquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.gwanjin.englishvocabularyquiz.client.VocaQuizService
import com.gwanjin.englishvocabularyquiz.vocabulary.VocaFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        VocaQuizService.getVocaQuiz().getVocaQuizInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        item -> Log.d("AAA", "" + item)
                }
                ,{ e -> Log.d("AAA", e.toString())
                }
            )

        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, VocaFragment()).addToBackStack(null)
        fragmentTransaction.commit()
    }
}