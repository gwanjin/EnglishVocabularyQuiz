package com.gwanjin.englishvocabularyquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.gwanjin.englishvocabularyquiz.vocabulary.VocaFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, VocaFragment()).addToBackStack(null)
        fragmentTransaction.commit()
    }
}