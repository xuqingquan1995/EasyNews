package top.xuqingquan.easynews.api

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import top.xuqingquan.easynews.BuildConfig
import top.xuqingquan.easynews.model.entity.ApiResult
import top.xuqingquan.easynews.model.entity.NewsItem
import top.xuqingquan.easynews.model.entity.PageBean
import top.xuqingquan.easynews.utils.BASE_URL
import java.util.concurrent.TimeUnit

/**
 * Created by 许清泉 on 2018/11/25 13:54
 */

private val TAG by lazy { RetrofitService::class.java.simpleName }


class RetrofitService private constructor() {

    fun getNewsList(start: Int, limit: Int? = 20, channel: String? = ""): Observable<ApiResult<PageBean<NewsItem>>> =
        api.getNewsList(start, limit!!, channel!!)

    companion object {

        val service = getInstance()

        private lateinit var api: HttpService

        @Volatile
        private var instance: RetrofitService? = null

        private fun getInstance(): RetrofitService {
            return instance ?: synchronized(this) {
                instance ?: RetrofitService().also {
                    api = create(HttpUrl.parse(BASE_URL)!!)
                    instance = it
                }
            }
        }

        private fun create(url: HttpUrl): HttpService {
            val interceptor: Interceptor?
            if (BuildConfig.DEBUG) {
                interceptor = LoggingInterceptor()
            } else {
                interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build()
            return retrofit.create(HttpService::class.java)
        }

    }
}