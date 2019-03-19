package com.akashgarg.pagingdemo.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.akashgarg.pagingdemo.model.NewsModelClass
import com.akashgarg.pagingdemo.restclient.Repository
import io.reactivex.disposables.CompositeDisposable
/**
 * @author Akash Garg on 18-03-2019.
 */
class NewsDataSourceFactory(
    var repository: Repository,
    private var compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, NewsModelClass>() {

    private val liveData: MutableLiveData<NewsDataSourceClass> = MutableLiveData()


    override fun create(): DataSource<Int, NewsModelClass> {
        val dataSourceClass = NewsDataSourceClass(repository, compositeDisposable)
        liveData.postValue(dataSourceClass)
        return dataSourceClass
    }

    fun getMutableLiveData(): MutableLiveData<NewsDataSourceClass> {
        return liveData
    }


}