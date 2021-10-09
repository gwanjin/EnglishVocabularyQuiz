package com.gwanjin.englishvocabularyquiz.vocabulary

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gwanjin.englishvocabularyquiz.R
import com.gwanjin.englishvocabularyquiz.client.VocaQuizService
import com.gwanjin.englishvocabularyquiz.data.Answer
import com.gwanjin.englishvocabularyquiz.data.Question
import com.gwanjin.englishvocabularyquiz.data.ResultGetVocaTest
import com.gwanjin.englishvocabularyquiz.databinding.FragmentWordBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class VocaFragment : Fragment() {

    private lateinit var callback: OnBackPressedCallback
    private lateinit var viewModel: VocaViewModel
    private var _binding: FragmentWordBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_word, container, false)
        return mBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel

        viewModel = ViewModelProvider(this).get(VocaViewModel::class.java)
        mBinding.lifecycleOwner = activity
        mBinding.vocaViewModel = viewModel
        viewModel.getNewData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}