package top.xuqingquan.easynews.model.entity

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * 数据更新作用
 */
data class Listing<T>(
        val pagedList: LiveData<PagedList<T>>,
        val networkState: LiveData<NetworkStatus>,
        val refreshState: LiveData<NetworkStatus>,
        val refresh: () -> Unit,
        val retry: () -> Unit)