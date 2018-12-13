package top.xuqingquan.easynews.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import top.xuqingquan.easynews.model.entity.Channel

/**
 * Created by 许清泉 on 2018/11/24 19:29
 */
@Dao
interface ChannelDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(channels: List<Channel>)

    @Query("select * from channle")
    fun getAllChannel(): LiveData<List<Channel>>

    @Query("select * from channle where show = :show")
    fun getShowChannel(show: Boolean? = true): LiveData<List<Channel>>

    @Update()
    fun changeChannelState(channel:Channel)

}