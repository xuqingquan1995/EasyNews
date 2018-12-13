package top.xuqingquan.easynews.model.datasource

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import top.xuqingquan.easynews.api.RetrofitService
import top.xuqingquan.easynews.model.entity.NetworkStatus
import top.xuqingquan.easynews.model.entity.NewsItem
import top.xuqingquan.easynews.utils.NETWORK_IO

/**
 * Created by 许清泉 on 2018/11/25 16:59
 */
@SuppressLint("CheckResult")
class NewsListDataSource(private val channel: String) : ItemKeyedDataSource<String, NewsItem>() {

    val networkState = MutableLiveData<NetworkStatus>()

    val initialLoad = MutableLiveData<NetworkStatus>()

    private var retry: (() -> Any)? = null

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            NETWORK_IO.execute {
                it.invoke()
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<NewsItem>) {
        networkState.postValue(NetworkStatus.RUNNING)
        initialLoad.postValue(NetworkStatus.RUNNING)
        RetrofitService
            .service
            .getNewsList(0, params.requestedLoadSize, channel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.apply {
                    if (code == 0) {
                        networkState.postValue(NetworkStatus.SUCCESS)
                        initialLoad.postValue(NetworkStatus.SUCCESS)
                        retry = null
                        callback.onResult(data!!.data)
                    }else{
                        retry = {
                            loadInitial(params, callback)
                        }
                        networkState.postValue(NetworkStatus.FAILED)
                        initialLoad.postValue(NetworkStatus.FAILED)
                    }
                }
            }, {
                retry = {
                    loadInitial(params, callback)
                }
                networkState.postValue(NetworkStatus.FAILED)
                initialLoad.postValue(NetworkStatus.FAILED)
                it.printStackTrace()
            })
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<NewsItem>) {
        networkState.postValue(NetworkStatus.RUNNING)
        RetrofitService
            .service
            .getNewsList(params.key.toInt(), params.requestedLoadSize, channel)
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.apply {
                    if (code == 0) {
                        callback.onResult(data!!.data)
                        networkState.postValue(NetworkStatus.SUCCESS)
                    }else{
                        retry = {
                            loadAfter(params, callback)
                        }
                        networkState.postValue(NetworkStatus.FAILED)
                    }
                }
            }, {
                retry = {
                    loadAfter(params, callback)
                }
                networkState.postValue(NetworkStatus.FAILED)
                it.printStackTrace()
            })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<NewsItem>) {
    }

    override fun getKey(item: NewsItem): String = item.newsId.toString()
}