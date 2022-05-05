package com.work.articles.activities

import android.app.Dialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.work.articles.R
import com.work.articles.api.Resource
import com.work.articles.databinding.ActivityMainBinding
import com.work.articles.fragments.ArticlesFragment
import com.work.articles.fragments.SettingsFragment
import com.work.articles.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import dmax.dialog.SpotsDialog

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModel: DataViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        loadingDialog =
            SpotsDialog.Builder().setContext(this).setMessage("Loading").build()

    }

    private fun initObservers() {
        viewModel.settingsNotifier.observe(this) { settings ->
            settings?.let {
                viewModel.fetchArticles(settings)
            } ?: run {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, SettingsFragment.newInstance(), "")
                    .commit()
            }
        }
        viewModel.articlesIndicator.observe(this) { response ->
            response.let {
                when (response) {
                    is Resource.Success -> {
                        showLoadingProgress(false)
                        response.data?.let {
                            supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.fragmentContainer, ArticlesFragment.newInstance(), "")
                                .commit()
                        }
                    }
                    is Resource.Error -> {
                        showLoadingProgress(false)
                    }
                    is Resource.Loading -> {
                        showLoadingProgress(true)
                    }
                }
            }
        }
        viewModel.retrieveSettings()
    }

    private fun showLoadingProgress(shouldShow: Boolean) {
        if (shouldShow) {
            loadingDialog.show()
        } else {
            loadingDialog.dismiss()
        }
    }
}
