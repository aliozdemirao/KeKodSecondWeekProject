package com.aliozdemir.wordmaster.presentation.worddetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.aliozdemir.wordmaster.MainActivity
import com.aliozdemir.wordmaster.databinding.FragmentWordDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WordDetailFragment : Fragment() {

    private var _binding: FragmentWordDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WordDetailViewModel by viewModels()
    private val args: WordDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWordDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.hideBottomNavigation()

        val wordId = args.id
        viewModel.getWordById(wordId).observe(viewLifecycleOwner) { word ->
            binding.tvEnglishValue.text = word.english
            binding.tvTurkishValue.text = word.turkish
            binding.tvSpanishValue.text = word.spanish
        }

        binding.imageButtonBookmark.setOnClickListener {
            viewModel.markAsLearned(wordId)
        }

        binding.imageButtonBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        (activity as? MainActivity)?.showBottomNavigation()
    }
}
