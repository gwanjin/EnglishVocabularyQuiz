package com.gwanjin.englishvocabularyquiz.vocabulary

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gwanjin.englishvocabularyquiz.R

class VocaFragment : Fragment() {

    companion object {
        fun newInstance() = VocaFragment()
    }

    private lateinit var viewModel: VocaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.voca_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VocaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}