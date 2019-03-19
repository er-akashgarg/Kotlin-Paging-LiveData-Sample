package com.akashgarg.pagingdemo.datasource

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.akashgarg.pagingdemo.model.NewsModelClass
import com.akashgarg.pagingdemo.restclient.Repository
import com.akashgarg.pagingdemo.utils.AppConstants
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import io.reactivex.disposables.CompositeDisposable
import org.json.JSONObject
import java.util.*

/**
 * @author Akash Garg on 18-03-2019.
 */
class NewsDataSourceClass(
    var repository: Repository,
    private var compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, NewsModelClass>() {
    private var gson: Gson
    private var sourceIndex: Int = 0
    private var progressLiveStatus: MutableLiveData<String> = MutableLiveData()

    init {
        val builder = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        gson = builder.setLenient().create()
    }

    fun getProgressLiveStatus(): MutableLiveData<String> {
        return progressLiveStatus
    }

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, NewsModelClass>) {
        repository.let {
            it.executeNewsApi(sourceIndex)
                .doOnSubscribe { disposable ->
                    compositeDisposable.add(disposable)
                    progressLiveStatus.postValue(AppConstants.LOADING)
                }
                .subscribe(
                    { result: JsonElement ->
                        progressLiveStatus.postValue(AppConstants.LOADED)

                        val jsonObj = JSONObject(gson.toJson(result))
                        val array = jsonObj.getJSONArray("articles")

                        val arrayList = ArrayList<NewsModelClass>()

                        for (i in 0 until array.length()) {
                            arrayList.add(
                                NewsModelClass(
                                    array.getJSONObject(i).optString("title"),
                                    array.getJSONObject(i).optString("urlToImage")
                                )
                            )
                        }

                        sourceIndex++
                        callback.onResult(arrayList, null, sourceIndex)
                    },
                    { throwable -> progressLiveStatus.postValue(AppConstants.LOADED) }

                )
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NewsModelClass>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewsModelClass>) {
        repository.let {
            it.executeNewsApi(params.key)
                .doOnSubscribe { disposable ->
                    //                    compositeDisposable.add(disposable)
                    progressLiveStatus.postValue(AppConstants.LOADING)
                }
                .subscribe(
                    { result: JsonElement ->
                        progressLiveStatus.postValue(AppConstants.LOADED)

                        val jsonData = JSONObject(gson.toJson(result))
                        val array = jsonData.getJSONArray("articles")

                        val arrayList = ArrayList<NewsModelClass>()

                        for (i in 0 until array.length()) {
                            arrayList.add(
                                NewsModelClass(
                                    array.getJSONObject(i).optString("title"),
                                    array.getJSONObject(i).optString("urlToImage")
                                )
                            )
                        }
                        callback.onResult(arrayList, if (params.key === 3) null else params.key + 1)
                    },
                    { throwable ->
                        Log.e("##:", "throwable : " + throwable.message)
                        progressLiveStatus.postValue(AppConstants.LOADED)
                    }
                )
        }
    }
}