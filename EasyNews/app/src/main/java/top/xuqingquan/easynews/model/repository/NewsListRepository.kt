package top.xuqingquan.easynews.model.repository

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import top.xuqingquan.easynews.model.datasource.factory.NewsListDataSourceFactory
import top.xuqingquan.easynews.model.entity.Listing
import top.xuqingquan.easynews.model.entity.NewsItem

/**
 * Created by 许清泉 on 2018/11/25 16:59
 */
class NewsListRepository {

    @MainThread
    fun getNewsList(channel: String, pageSize: Int): Listing<NewsItem> {
        val factory = NewsListDataSourceFactory(channel)
        return Listing(
            pagedList = factory.toLiveData(Config(pageSize, pageSize, false, pageSize * 2)),
            networkState = Transformations.switchMap(factory.sourceLiveData) {
                it.networkState
            },
            retry = {
                factory.sourceLiveData.value?.retryAllFailed()
            },
            refresh = {
                factory.sourceLiveData.value?.invalidate()
            },
            refreshState = Transformations.switchMap(factory.sourceLiveData) {
                it.initialLoad
            }
        )
    }

}