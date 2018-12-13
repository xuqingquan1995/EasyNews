package top.xuqingquan.easynews.model.entity

/**
 * Created by 许清泉 on 2018/11/24 17:49
 */
data class NewsItem(
    val newsId: Int,
    val id: String? = null,
    val title: String,
    val link: String,
    val pubDate: String,
    val source: String,
    val desc: String? = null,
    val channelId: String? = null,
    val channelName: String? = null,
    val nid: String? = null,
    val imageurls: List<NewsImage>? = null,
    val content: String? = null,
    val html: String?=null,
    val havePic: Boolean
)
/*
{
	"title": "瞻博网络：坚守“市场走向”和“生态伙伴”两条战线",
	"link": "http://baijiahao.baidu.com/s?id=1609947210925419893&wfr=newsapp",
	"pubDate": "2018-08-27 18:39:17",
	"source": "IT168",
	"desc": "对此，IT168记者专门采访了瞻博网络副总裁及大中国区总经理林小平先生和瞻博网络大中国区CTO井有浩先生，请他们就这三份报告对中国区所带来的价值与影响谈谈感想。瞻博网络副总裁及大中国区总经理林小平先生　　随着移动互联网、云计算技术的普及，不单单是企业，甚至个人的衣食住行等，都通过一些新的移动互联网技术、物联网技术发生了改变。",
	"imageurls": [{
		"height": 0,
		"id": 0,
		"newsId": 0,
		"url": "http://t10.baidu.com/it/u=3875635755,2306663949&fm=173&app=25&f=JPEG?w=640&h=426&s=1600974B56C24F5B10003C5D03009091",
		"width": 0
	}, {
		"height": 0,
		"id": 0,
		"newsId": 0,
		"url": "http://t11.baidu.com/it/u=600650374,1371803590&fm=173&app=25&f=JPEG?w=640&h=426&s=F790C921C01B47DA57B475CF0300E0E1",
		"width": 0
	}],
	"havePic": false
}
 */