package top.xuqingquan.easynews.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import top.xuqingquan.easynews.model.repository.NewsListRepository
import top.xuqingquan.easynews.viewModel.NewsListViewModel

/**
 * Created by 许清泉 on 2018/11/25 17:50
 */
class NewsListViewModelFactory(private val repository: NewsListRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = NewsListViewModel(repository) as T

}