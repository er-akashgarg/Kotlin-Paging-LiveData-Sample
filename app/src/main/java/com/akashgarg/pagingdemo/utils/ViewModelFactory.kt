package com.akashgarg.pagingdemo.utils

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akashgarg.pagingdemo.restclient.Repository
import com.akashgarg.pagingdemo.viewmodel.PagingListViewModel

import javax.inject.Inject

/**
 * @author Akash Garg on 16-03-2019.
 */

open class ViewModelFactory

@Inject
constructor(private val repository: Repository) : ViewModelProvider.Factory {

    @NonNull
    override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PagingListViewModel::class.java!!)) {
            return PagingListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
