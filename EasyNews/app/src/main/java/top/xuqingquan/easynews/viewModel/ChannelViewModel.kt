package top.xuqingquan.easynews.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import top.xuqingquan.easynews.model.repository.ChannelRepository

/**
 * Created by 许清泉 on 2018/11/24 20:24
 */
class ChannelViewModel(repository: ChannelRepository) :ViewModel(){

    val allChannelList=repository.getAllChannel()

    val showChannelList=repository.getShowChannel()

    val hideChannelList=repository.getShowChannel(false)

}