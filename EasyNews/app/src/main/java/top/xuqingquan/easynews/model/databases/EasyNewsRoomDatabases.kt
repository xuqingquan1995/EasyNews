package top.xuqingquan.easynews.model.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import top.xuqingquan.easynews.model.dao.ChannelDao
import top.xuqingquan.easynews.model.entity.Channel
import top.xuqingquan.easynews.utils.DATABASE_NAME
import top.xuqingquan.easynews.worker.InitChannelWorker

/**
 * Created by 许清泉 on 2018/11/24 17:44
 */
@Database(entities = [Channel::class], version = 1, exportSchema = false)
abstract class EasyNewsRoomDatabases : RoomDatabase() {

    abstract fun channelDao(): ChannelDao

    companion object {

        @Volatile
        private var instance: EasyNewsRoomDatabases? = null

        fun getInstance(context: Context): EasyNewsRoomDatabases {
            return instance ?: synchronized(this) {
                instance ?: buildDatabases(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabases(context: Context): EasyNewsRoomDatabases {
            return Room
                .databaseBuilder(context,EasyNewsRoomDatabases::class.java, DATABASE_NAME)
                .addCallback(object :RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<InitChannelWorker>().build()
                        WorkManager.getInstance().enqueue(request)
                    }
                })
                .build()
        }
    }

}