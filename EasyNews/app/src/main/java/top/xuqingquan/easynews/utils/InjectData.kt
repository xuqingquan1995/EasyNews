package top.xuqingquan.easynews.utils

import android.content.Context
import top.xuqingquan.easynews.model.databases.EasyNewsRoomDatabases
import top.xuqingquan.easynews.model.repository.ChannelRepository
import top.xuqingquan.easynews.model.repository.NewsListRepository
import top.xuqingquan.easynews.viewModel.factory.ChannleViewModelFactory
import top.xuqingquan.easynews.viewModel.factory.NewsListViewModelFactory

/**
 * Created by 许清泉 on 2018/11/24 20:14
 */
object InjectData {

    fun provideChannleViewModelFactory(context: Context): ChannleViewModelFactory =
        ChannleViewModelFactory(getChannelRepository(context))

    private fun getChannelRepository(context: Context): ChannelRepository =
        ChannelRepository.getInstance(
            EasyNewsRoomDatabases.getInstance(
                context
            ).channelDao())


    fun provideNewsListViewModelFactory(): NewsListViewModelFactory = NewsListViewModelFactory(getNewsListRepository())

    private fun getNewsListRepository(): NewsListRepository = NewsListRepository()
}