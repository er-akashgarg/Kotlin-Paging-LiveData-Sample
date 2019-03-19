package com.akashgarg.pagingdemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * @author Akash Garg on 16-03-2019.
 */
class AppConstants {

    companion object {
        val LOADING = "Loading"
        val LOADED = "Loaded"
        val CHECK_NETWORK_ERROR = "Check Your Network Connection!!"
        val API_KEY = "1cac2cb48b744f06ac45b88cb85b4d88"//put your api_key generate it from "https://newsapi.org/docs"
        val sources = arrayOf("bbc-news", "abc-news-au", "bloomberg", "cnbc")

        fun checkInternetConnection(context: Context): Boolean {
            val connectivity = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = connectivity.allNetworkInfo
            if (info != null) {
                for (anInfo in info) {
                    if (anInfo.state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
            return false
        }
    }
}