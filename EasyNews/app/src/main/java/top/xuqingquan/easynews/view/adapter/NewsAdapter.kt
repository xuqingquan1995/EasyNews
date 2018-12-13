package top.xuqingquan.easynews.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import top.xuqingquan.easynews.databinding.*
import top.xuqingquan.easynews.model.entity.NetworkStatus
import top.xuqingquan.easynews.model.entity.NewsItem
import top.xuqingquan.easynews.view.NewsItemActivity
import top.xuqingquan.easynews.view.adapter.callback.NewsDiffCallback
import top.xuqingquan.easynews.view.adapter.viewholder.*

/**
 * Created by 许清泉 on 2018/11/24 21:46
 */

class NewsAdapter(private val retry: () -> Unit) :
    PagedListAdapter<NewsItem, NewsViewHolder>(NewsDiffCallback()) {

    private var networkStatus: NetworkStatus? = null

    companion object {

        private const val NETWORK_STATE = -1
        private const val NO_IMAGE = 1
        private const val ONE_IMAGE = 2
        private const val MORE_IMAGE = 3

    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            NETWORK_STATE
        } else {
            val item = getItem(position)!!
            if (item.havePic && item.imageurls!!.size == 1) {
                ONE_IMAGE
            } else if (item.havePic && item.imageurls!!.size > 2) {
                MORE_IMAGE
            } else {
                NO_IMAGE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return when (viewType) {
            NETWORK_STATE -> {
                NetworkStateViewHolder(
                    NetworkStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    networkStatus
                )
            }
            ONE_IMAGE -> {
                News1ViewHolder(ItemNews1imagesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            MORE_IMAGE -> {
                News3ViewHolder(ItemNews3imagesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> {
                News0ViewHolder(ItemNews0imagesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        if (NETWORK_STATE == getItemViewType(position)) {
            holder.bind(View.OnClickListener {
                retry()
            }, null)
        } else {
            val item = getItem(position)!!
            holder.bind(View.OnClickListener {
                val intent = Intent(it.context, NewsItemActivity::class.java)
                intent.putExtra("newsId",item.newsId);
                intent.putExtra("link",item.link)
                it.context.startActivity(intent)
            }, item)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkStatus: NetworkStatus?) {
        val previousState = this.networkStatus
        val hadExtraRow = hasExtraRow()
        this.networkStatus = newNetworkStatus
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkStatus) {
            notifyItemChanged(itemCount - 1)
        }
    }

    private fun hasExtraRow() = networkStatus != null && networkStatus != NetworkStatus.SUCCESS
}