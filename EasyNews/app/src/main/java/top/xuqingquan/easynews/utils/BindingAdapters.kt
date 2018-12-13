package top.xuqingquan.easynews.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import top.xuqingquan.easynews.R

/**
 * Created by 许清泉 on 2018/11/24 23:15
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String) {
    GlideApp.with(view.context)
        .load(imageUrl)
        .placeholder(R.drawable.loading)
        .override(200,200)
        .fitCenter()
        .into(view)
}

@BindingAdapter("isShow")
fun bindisShow(view:View,visibility:Boolean){
    view.visibility=if (visibility){
        View.VISIBLE
    }else{
        View.GONE
    }
}