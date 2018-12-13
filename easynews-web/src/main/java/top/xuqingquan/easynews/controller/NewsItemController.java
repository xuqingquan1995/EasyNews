package top.xuqingquan.easynews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xuqingquan.easynews.entity.NewsItem;
import top.xuqingquan.easynews.entity.PageBean;
import top.xuqingquan.easynews.entity.Response;
import top.xuqingquan.easynews.service.NewsItemService;


@Controller
@RequestMapping("news")
public class NewsItemController {

    private final NewsItemService ns;

    @Autowired
    public NewsItemController(NewsItemService ns) {
        this.ns = ns;
    }

    /**
     * @param start     开始id
     * @param limit     每页记录数
     * @param according 排序依据
     * @param direction 排序方向
     */
    @ResponseBody
    @RequestMapping("getNewsList")
    public Response<PageBean<NewsItem>> getNewsList(Integer start, Integer limit, String according, String direction,
                                String channelName, String title, String pubDate, String source) {
        return ns.getNewsList(start, limit, according, direction, channelName, title, pubDate, source);
    }

}
