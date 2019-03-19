package com.akashgarg.pagingdemo.restclient

import com.google.gson.JsonElement
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Akash Garg on 16-03-2019.
 */

interface ApiCallInterface {
    @GET(Urls.FetchNewsList)
    fun fetchListNews(@Query("source") source: String, @Query("apiKey") apiKey: String): Observable<JsonElement>
}