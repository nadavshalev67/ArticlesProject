package com.work.articles.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.work.articles.adapters.ArticleAdapter
import com.work.articles.api.Resource
import com.work.articles.databinding.FragmentArticlesBinding


class ArticlesFragment :
    BaseFragment<FragmentArticlesBinding>(FragmentArticlesBinding::inflate) {
    companion object {
        fun newInstance(): ArticlesFragment {
            return ArticlesFragment()
        }
    }

    private val articleAdapter = ArticleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        setRecyclerView()
        initObservers()

        return view
    }

    private fun initObservers() {
        viewModel.articlesIndicator.observe(viewLifecycleOwner) { response ->
            response.let { response ->
                if (response is Resource.Success) {
                    response.data?.let {
                        articleAdapter.differ.submitList(it)
                    }
                }
            }
        }
    }

    private fun setRecyclerView() {
        binding.recyclerView.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}