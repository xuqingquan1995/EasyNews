package top.xuqingquan.easynews.model.repository

import top.xuqingquan.easynews.model.dao.ChannelDao
import top.xuqingquan.easynews.model.entity.Channel

/**
 * Created by 许清泉 on 2018/11/24 20:16
 */
class ChannelRepository private constructor(private val dao: ChannelDao) {

    fun getAllChannel() = dao.getAllChannel()

    fun getShowChannel(show: Boolean? = true) = dao.getShowChannel(show)

    fun changeChannelState(channel: Channel)=dao.changeChannelState(channel)

    companion object {

        @Volatile
        private var instance: ChannelRepository? = null

        fun getInstance(dao: ChannelDao): ChannelRepository {
            return instance ?: synchronized(this) {
                instance ?: ChannelRepository(dao)
                    .also {
                        instance = it
                    }
            }
        }

    }

}