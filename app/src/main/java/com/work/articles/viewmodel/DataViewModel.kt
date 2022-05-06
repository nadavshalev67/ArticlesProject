package com.work.articles.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.articles.api.Resource
import com.work.articles.logic.Utils.safeLet
import com.work.articles.logic.Utils.toSettings
import com.work.articles.logic.filters.FactoryFilter
import com.work.articles.logic.filters.base.FilterType
import com.work.articles.model.FilterSettings
import com.work.articles.model.ServerArticle
import com.work.articles.repository.ArticlesRepository
import com.work.articles.settings.customkeyvalue.DateKeyValue
import com.work.articles.settings.customkeyvalue.DateWrapper
import com.work.articles.settings.customkeyvalue.StringKeyValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(private val articlesRepository: ArticlesRepository) :
    ViewModel() {


    val settingsNotifier: MutableLiveData<FilterSettings?> = MutableLiveData()

    val articlesIndicator: MutableLiveData<Resource<List<ServerArticle>>> =
        MutableLiveData()


    fun retrieveSettings() = viewModelScope.launch(Dispatchers.IO) {
        articlesRepository.retrieveSettings().collect { response ->
            response?.let { json ->
                settingsNotifier.postValue(JSONObject(json).toSettings())
            } ?: run {
                settingsNotifier.postValue(null)
            }
        }
    }

    fun fetchArticles(settings: FilterSettings) = viewModelScope.launch(Dispatchers.IO) {
        articlesIndicator.postValue(Resource.Loading())
        try {
            val articlesResponse = articlesRepository.fetchData()
            if (articlesResponse.isSuccessful) {
                articlesResponse.body()?.let { res ->
                    val list = res.articles.filter { article ->
                        settings.applyFilters(article)
                    }.toList()
                    articlesIndicator.postValue(Resource.Success(list))
                } ?: run {
                    articlesIndicator.postValue(Resource.Error(articlesResponse.message()))
                }
            } else {
                articlesIndicator.postValue(Resource.Error(articlesResponse.message()))
            }
        } catch (ex: Exception) {
            articlesIndicator.postValue(Resource.Error(ex.localizedMessage))
        }


    }


    fun saveAndApplySettings(
        dateWrapper: DateWrapper,
        topic: String,
        title: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val dateFilter = FactoryFilter.createFilter(FilterType.DateFilterType, DateKeyValue(FilterType.DateFilterType, dateWrapper))
        val titleFilter = FactoryFilter.createFilter(FilterType.TitleFilterType,StringKeyValue(FilterType.TitleFilterType,title))
        val topicFilter = FactoryFilter.createFilter(FilterType.TitleFilterType,StringKeyValue(FilterType.TopicFilterType,topic))
        safeLet(dateFilter, topicFilter, titleFilter) { dateFilter, topicFilter, titleFitler -> val settings =
            FilterSettings.Builder().putFilterType(dateFilter).putFilterType(topicFilter)
                    .putFilterType(titleFitler).build()
            articlesRepository.saveSettings(settings)
            fetchArticles(settings)
        }
    }
}

