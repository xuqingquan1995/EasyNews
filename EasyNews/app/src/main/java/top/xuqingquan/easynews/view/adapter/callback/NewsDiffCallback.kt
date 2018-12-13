package top.xuqingquan.easynews.view.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import top.xuqingquan.easynews.model.entity.NewsItem

/**
 * Created by 许清泉 on 2018/11/24 21:47
 */
class NewsDiffCallback : DiffUtil.ItemCallback<NewsItem>() {

    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean =
        oldItem.newsId == newItem.newsId

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean =
        oldItem == newItem

}