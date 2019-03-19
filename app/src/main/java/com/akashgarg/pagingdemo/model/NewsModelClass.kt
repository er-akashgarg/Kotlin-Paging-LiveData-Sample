package com.akashgarg.pagingdemo.model


import androidx.recyclerview.widget.DiffUtil


/**
 * @author Akash Garg on 16-03-2019.
 */

open class NewsModelClass(val newsTitle: String, val newsImg: String) {

    override fun equals(obj: Any?): Boolean {
        if (obj === this)
            return true

        val article = obj as NewsModelClass?
        return article!!.newsTitle == this.newsTitle
    }

    override fun hashCode(): Int {
        var result = newsTitle.hashCode()
        result = 31 * result + newsImg.hashCode()
        return result
    }

    companion object {

        var DIFF_CALLBACK: DiffUtil.ItemCallback<NewsModelClass> = object : DiffUtil.ItemCallback<NewsModelClass>() {
            override fun areItemsTheSame(oldItem: NewsModelClass, newItem: NewsModelClass): Boolean {
                return oldItem.newsTitle == newItem.newsTitle
            }

            override fun areContentsTheSame(oldItem: NewsModelClass, newItem: NewsModelClass): Boolean {
                return oldItem == newItem
            }
        }
    }
}
