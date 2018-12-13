package top.xuqingquan.easynews.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import top.xuqingquan.easynews.R

class NewsItemActivity : AppCompatActivity() {

    private val TAG by lazy { NewsItemActivity::class.java.simpleName }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_item)
        val newsId = intent.getIntExtra("newsId", -1)
        val link = intent.getStringExtra("link")
        Log.d(TAG,"newsId=$newsId,link=$link")
    }

}
