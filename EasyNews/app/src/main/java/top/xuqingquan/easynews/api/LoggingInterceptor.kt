package top.xuqingquan.easynews.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import top.xuqingquan.easynews.model.entity.ApiResult
import top.xuqingquan.easynews.utils.dateFormat
import java.nio.charset.Charset

/**
 * Created by 许清泉 on 2018/11/25 14:03
 */
class LoggingInterceptor : Interceptor {

    private val TAG by lazy { LoggingInterceptor::class.java.simpleName }
    private val utf8 = Charset.forName("UTF-8")

    @Synchronized
    private fun getTime(): String {
        return dateFormat.format(System.currentTimeMillis())
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBody = request.body()
        var body: String? = null
        requestBody?.let {
            val buffer = Buffer()
            it.writeTo(buffer)
            var charset = utf8
            val contentType = it.contentType()
            contentType?.let { type ->
                charset = type.charset(utf8)
                type
            }
            body = buffer.readString(charset)
            it
        }
        Log.i(
            TAG,
            "发送请求\n\r请求时间：${getTime()}\n\rmethod：${request.method()}\n\r请求url：${request.url()}\n\rheaders: ${request.headers()}\n\rbody：$body"
        )
        var response = chain.proceed(request)
        if (!response.isSuccessful) {
            val msg = when (response.code()) {
                404 -> "连接服务器失败，找不到页面"
                500 -> "服务器错误，请联系管理员"
                else -> "请求失败，请重试"
            }
            val resp = response
                .newBuilder()
                .body(
                    ResponseBody.create(
                        MediaType.parse("text/*"),
                        ApiResult<Any>(response.code(), msg, null).toJson()
                    )
                )
                .build()
            response = resp
        }
        val responseBody = response.body()
        var rBody: String? = null
        responseBody?.let {
            val source = responseBody.source()
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer()
            var charset = utf8
            val contentType = responseBody.contentType()
            contentType?.let {type->
                charset = type.charset(utf8)
                type
            }
            rBody = buffer.clone().readString(charset)
            it
        }
        Log.i(
            TAG,
            "收到响应\n\r响应时间：${getTime()}\n\rcode:${response.code()}\n\rmessage:${response.message()}\n\r请求url：${response.request().url()}\n\r请求的参数：$body\n\r响应结果：$rBody"
        )
        return response
    }
}