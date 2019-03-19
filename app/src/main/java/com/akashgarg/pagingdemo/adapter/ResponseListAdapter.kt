package com.akashgarg.pagingdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akashgarg.pagingdemo.databinding.RowLayoutBinding
import com.akashgarg.pagingdemo.model.NewsModelClass


/**
 * Created by Akash Garg on 16-03-2019.
 */

class ResponseListAdapter
internal constructor() :
    PagedListAdapter<NewsModelClass, ResponseListAdapter.MyViewHolder>(NewsModelClass.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<RowLayoutBinding>(
            LayoutInflater.from(parent.context), com.akashgarg.pagingdemo.R.layout.row_layout, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.model = getItem(position)
    }

    inner class MyViewHolder(var binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    /*@BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, imageUrl: String) {
        val context = imageView.context
        Picasso.with(context)
            .load(imageUrl)
            .into(imageView)
    }*/

}
