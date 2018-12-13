package top.xuqingquan.easynews.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Transformations
import top.xuqingquan.easynews.model.repository.NewsListRepository

/**
 * Created by 许清泉 on 2018/11/25 16:51
 */
class NewsListViewModel(private val repository: NewsListRepository) : ViewModel() {

    private val channelName = MutableLiveData<String>()

    private val repoResult = Transformations.map(channelName) {
        repository.getNewsList(it, 20)
    }

    val newsList = Transformations.switchMap(repoResult) {
        it.pagedList
    }!!

    val networkState = Transformations.switchMap(repoResult) { it.networkState }!!

    val refreshState = Transformations.switchMap(repoResult) { it.refreshState }!!

    fun setChannel(channel: String): Boolean {
        if (channelName.value == channel) {
            return false
        }
        channelName.value = channel
        return true
    }

    fun retry() {
        val listing = repoResult?.value
        listing?.retry?.invoke()
    }

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

}