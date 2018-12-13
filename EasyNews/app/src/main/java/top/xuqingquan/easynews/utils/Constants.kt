package top.xuqingquan.easynews.utils

import com.google.gson.GsonBuilder
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

/**
 * Created by 许清泉 on 2018/11/24 19:35
 */

const val DATABASE_NAME = "easynews"

val channels = arrayListOf(
    "推荐", "国内", "国际", "军事", "财经", "互联网", "房产", "汽车", "体育", "娱乐", "游戏", "教育", "女人", "科技", "社会", "台湾",
    "港澳", "理财", "经济", "足球", "CBA", "电影", "电视", "美容护肤", "情感两性", "健康养生", "数码", "电脑", "科普", "电商"
)

val dateFormat=SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)

const val BASE_URL="http://192.168.0.106/"

val gson=GsonBuilder().create()

val NETWORK_IO = Executors.newFixedThreadPool(5)