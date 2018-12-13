package top.xuqingquan.easynews.model.entity

/**
 * Created by 许清泉 on 2018/11/25 14:31
 */
data class PageBean<out T>(val before:Int,val after:Int,val data:List<T>)