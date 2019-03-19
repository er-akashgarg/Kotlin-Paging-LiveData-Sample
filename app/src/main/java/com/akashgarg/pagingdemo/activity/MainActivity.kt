package com.akashgarg.pagingdemo.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.akashgarg.pagingdemo.R
import com.akashgarg.pagingdemo.adapter.ResponseListAdapter
import com.akashgarg.pagingdemo.app.MyApplication
import com.akashgarg.pagingdemo.databinding.PagingListLayoutBinding
import com.akashgarg.pagingdemo.utils.AppConstants
import com.akashgarg.pagingdemo.utils.ViewModelFactory
import com.akashgarg.pagingdemo.viewmodel.PagingListViewModel
import javax.inject.Inject


/**
 * Created by Akash Garg on 16-03-2019.
 */

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var pagingLayoutBinding: PagingListLayoutBinding
    private lateinit var viewModel: PagingListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pagingLayoutBinding = DataBindingUtil.setContentView(this, R.layout.paging_list_layout)
        (application as MyApplication).getAppComponent().doInjectInApp(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PagingListViewModel::class.java)
        init()
    }

    private fun init() {
        val adapter = ResponseListAdapter()
        pagingLayoutBinding.rvList.adapter = adapter
        if (!AppConstants.checkInternetConnection(this)) {
            Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
        }

        viewModel.getListLiveData().observe(this, Observer { response ->
            adapter.submitList(response)
        })

        viewModel.getProgressLoadStatus().observe(this, Observer { status ->
            if (status == AppConstants.LOADING) {
                pagingLayoutBinding.progressBar.visibility = View.VISIBLE
            } else
                pagingLayoutBinding.progressBar.visibility = View.GONE
        })
    }
}
