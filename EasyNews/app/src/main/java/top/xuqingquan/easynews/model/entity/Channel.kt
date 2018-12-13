package top.xuqingquan.easynews.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by 许清泉 on 2018/11/24 19:17
 */
@Entity(tableName = "channle")
data class Channel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val show: Boolean? = true
)