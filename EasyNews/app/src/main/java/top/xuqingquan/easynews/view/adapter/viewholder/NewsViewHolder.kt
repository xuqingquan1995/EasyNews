package top.xuqingquan.easynews.view.adapter.viewholder

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import top.xuqingquan.easynews.databinding.*
import top.xuqingquan.easynews.model.entity.NetworkStatus
import top.xuqingquan.easynews.model.entity.NewsItem
import top.xuqingquan.easynews.utils.dateFormat
import java.util.Date

/**
 * Created by 许清泉 on 2018/11/24 22:53
 */
abstract class NewsViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(listener: View.OnClickListener, item: NewsItem?)

    internal fun getTimeSub(time: String): String {
        val pubDate = dateFormat.parse(time)
        val now = Date()
        val subTime = (now.time - pubDate.time) / 1000 //两个时间相差秒数
        return if (subTime < 60) {//一分钟内
            "刚刚"
        } else if (subTime > 60 && subTime < 60 * 60) {//一小时内
            "${subTime / 60}分钟前"
        } else if (subTime > 60 * 60 && subTime < 24 * 60 * 60) {
            "${subTime / 60 / 60}小时前"
        } else {
            time
        }
    }

}

class News0ViewHolder(val binding: ItemNews0imagesBinding) : NewsViewHolder(binding) {

    override fun bind(listener: View.OnClickListener, item: NewsItem?) {
        binding.apply {
            clickListener = listener
            news = item!!
            time = getTimeSub(item.pubDate)
            executePendingBindings()
        }
    }
}

class News1ViewHolder(val binding: ItemNews1imagesBinding) : NewsViewHolder(binding) {

    override fun bind(listener: View.OnClickListener, item: NewsItem?) {
        binding.apply {
            clickListener = listener
            news = item!!
            time = getTimeSub(item.pubDate)
            image1 = item.imageurls!![0].url
            executePendingBindings()
        }
    }
}

class News3ViewHolder(val binding: ItemNews3imagesBinding) : NewsViewHolder(binding) {

    override fun bind(listener: View.OnClickListener, item: NewsItem?) {
        binding.apply {
            clickListener = listener
            news = item!!
            time = getTimeSub(item.pubDate)
            image1 = item.imageurls!![0].url
            image2 = item.imageurls[1].url
            image3 = item.imageurls[2].url
            executePendingBindings()
        }
    }
}

class NetworkStateViewHolder(val binding: NetworkStateBinding, private val networkStatus: NetworkStatus?) :
    NewsViewHolder(binding) {

    override fun bind(listener: View.OnClickListener, item: NewsItem?) {
        binding.apply {
            clickListener = listener
            loadingVisible=networkStatus==NetworkStatus.RUNNING
            errorVisible=networkStatus==NetworkStatus.FAILED
            executePendingBindings()
        }
    }

}