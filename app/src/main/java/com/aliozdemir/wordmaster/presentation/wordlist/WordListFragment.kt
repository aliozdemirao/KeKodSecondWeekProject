package com.aliozdemir.wordmaster.presentation.wordlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliozdemir.wordmaster.databinding.FragmentWordListBinding
import com.aliozdemir.wordmaster.domain.model.Word
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WordListFragment : Fragment() {
    private var _binding: FragmentWordListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WordListViewModel by viewModels()

    private val wordListAdapter by lazy { WordListAdapter(::onWordClick) }

    private lateinit var wordList: List<Word>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeUnlearnedWords()
        setupSwipeRefreshLayout()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewUnlearnedWords.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = wordListAdapter
        }
    }

    private fun observeUnlearnedWords() {
        viewModel.unlearnedWords.observe(viewLifecycleOwner) { words ->
            wordList = words
            wordListAdapter.submitList(wordList) {
                binding.recyclerViewUnlearnedWords.scrollToPosition(0)
            }
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            wordListAdapter.submitList(wordList.shuffled()) {
                binding.recyclerViewUnlearnedWords.scrollToPosition(0)
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun onWordClick(word: Word) {
        val action = WordListFragmentDirections.actionWordListFragmentToWordDetailFragment(word.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
