package com.akashgarg.pagingdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.akashgarg.pagingdemo.datasource.NewsDataSourceClass
import com.akashgarg.pagingdemo.datasource.NewsDataSourceFactory
import com.akashgarg.pagingdemo.model.NewsModelClass
import com.akashgarg.pagingdemo.restclient.Repository
import io.reactivex.disposables.CompositeDisposable

class PagingListViewModel(var repository: Repository) : ViewModel() {

    private var newsDataSourceFactory: NewsDataSourceFactory
    private lateinit var listLiveData: LiveData<PagedList<NewsModelClass>>
    private var progressLoadStatus: LiveData<String> = MutableLiveData()
    private var compositeDisposable = CompositeDisposable()

    init {
        newsDataSourceFactory = NewsDataSourceFactory(repository, compositeDisposable)
        initializePaging()
    }

    private fun initializePaging() {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()

        listLiveData = LivePagedListBuilder(newsDataSourceFactory, pagedListConfig).build()

        progressLoadStatus = Transformations.switchMap(
            newsDataSourceFactory.getMutableLiveData(),
            NewsDataSourceClass::getProgressLiveStatus
        )
    }

    fun getListLiveData(): LiveData<PagedList<NewsModelClass>> {
        return listLiveData
    }

    fun getProgressLoadStatus(): LiveData<String> {
        return progressLoadStatus
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}