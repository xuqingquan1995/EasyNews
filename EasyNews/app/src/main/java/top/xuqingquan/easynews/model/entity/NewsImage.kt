package top.xuqingquan.easynews.model.entity

/**
 * 新闻对应的图片
 */
data class NewsImage(
    val newsId: Int?=0,
    val url: String,
    val width: Int?=0,
    val height: Int?=0
)