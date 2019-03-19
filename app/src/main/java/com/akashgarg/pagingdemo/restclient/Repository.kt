package com.akashgarg.pagingdemo.restclient

import com.akashgarg.pagingdemo.utils.AppConstants
import com.google.gson.JsonElement
import io.reactivex.Observable

/**
 * @author Akash Garg on 16-03-2019.
 */
class Repository(private var apiCallInterface: ApiCallInterface) {
    fun executeNewsApi(index: Int): Observable<JsonElement> {
        return apiCallInterface.fetchListNews(AppConstants.sources[index], AppConstants.API_KEY)
    }
}