package top.xuqingquan.easynews.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import top.xuqingquan.easynews.model.repository.ChannelRepository
import top.xuqingquan.easynews.viewModel.ChannelViewModel

/**
 * Created by 许清泉 on 2018/11/24 20:30
 */
class ChannleViewModelFactory(private val repository: ChannelRepository):ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = ChannelViewModel(repository) as T
}