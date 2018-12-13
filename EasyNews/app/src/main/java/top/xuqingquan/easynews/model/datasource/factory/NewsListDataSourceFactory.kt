package top.xuqingquan.easynews.model.datasource.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import top.xuqingquan.easynews.model.datasource.NewsListDataSource
import top.xuqingquan.easynews.model.entity.NewsItem


/**
 * Created by 许清泉 on 2018/11/25 17:00
 */
class NewsListDataSourceFactory(private val channel:String): DataSource.Factory<String,NewsItem>() {

    val sourceLiveData = MutableLiveData<NewsListDataSource>()

    override fun create(): DataSource<String, NewsItem> {
        val source=NewsListDataSource(channel)
        sourceLiveData.postValue(source)
        return source
    }
}