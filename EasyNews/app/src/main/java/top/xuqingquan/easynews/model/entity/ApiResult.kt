package top.xuqingquan.easynews.model.entity

import org.json.JSONObject

/**
 * Created by 许清泉 on 2018/02/02 16:51
 */
data class ApiResult<out T>(val code: Int, val error: String? = null, val data: T? = null) {

    fun toJson(): String {
        val json = JSONObject()
        json.put("code", code)
        json.put("error", error)
        json.put("data", data)
        return json.toString()
    }
}