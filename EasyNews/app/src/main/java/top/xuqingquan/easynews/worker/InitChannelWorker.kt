package top.xuqingquan.easynews.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import top.xuqingquan.easynews.model.databases.EasyNewsRoomDatabases
import top.xuqingquan.easynews.model.entity.Channel
import top.xuqingquan.easynews.utils.channels

/**
 * Created by 许清泉 on 2018/11/24 19:41
 */
class InitChannelWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val databases = EasyNewsRoomDatabases.getInstance(applicationContext)
        val channelList = ArrayList<Channel>()
        for ((index, values) in channels.withIndex()) {
            channelList.add(Channel(index + 1, values))
        }
        databases.channelDao().insertAll(channelList)
        return ListenableWorker.Result.SUCCESS
    }
}