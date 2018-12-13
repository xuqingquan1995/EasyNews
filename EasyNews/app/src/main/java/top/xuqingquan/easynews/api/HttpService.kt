package top.xuqingquan.easynews.api

import io.reactivex.Observable
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import top.xuqingquan.easynews.BuildConfig
import top.xuqingquan.easynews.model.entity.ApiResult
import top.xuqingquan.easynews.model.entity.NewsItem
import top.xuqingquan.easynews.model.entity.PageBean
import top.xuqingquan.easynews.utils.BASE_URL
import java.util.concurrent.TimeUnit

/**
 * Created by 许清泉 on 2018/11/25 13:44
 */
interface HttpService {

    @FormUrlEncoded
    @POST("news/getNewsList")
    fun getNewsList(@Field("start") start: Int, @Field("limit") limit: Int, @Field("channelName") channel: String): Observable<ApiResult<PageBean<NewsItem>>>

    @GET
    fun test(@Url url:String):Observable<String>
}