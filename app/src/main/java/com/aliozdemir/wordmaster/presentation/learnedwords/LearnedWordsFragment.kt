package com.aliozdemir.wordmaster.presentation.learnedwords

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliozdemir.wordmaster.R
import com.aliozdemir.wordmaster.databinding.FragmentLearnedWordsBinding
import com.aliozdemir.wordmaster.domain.model.Word
import com.aliozdemir.wordmaster.presentation.wordlist.WordListAdapter
import com.aliozdemir.wordmaster.presentation.wordlist.WordListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearnedWordsFragment : Fragment() {

    private var _binding: FragmentLearnedWordsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LearnedWordsViewModel by viewModels()

    private val learnedWordsAdapter by lazy { LearnedWordsAdapter(::onWordClick) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLearnedWordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeLearnedWords()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewLearnedWords.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = learnedWordsAdapter
        }
    }

    private fun observeLearnedWords() {
        viewModel.learnedWords.observe(viewLifecycleOwner) { words ->
            learnedWordsAdapter.submitList(words)
        }
    }

    private fun onWordClick(word: Word) {
        val action = LearnedWordsFragmentDirections.actionLearnedWordsFragmentToLearnedWordDetailFragment(word.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
