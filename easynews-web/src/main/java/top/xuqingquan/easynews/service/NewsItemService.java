package top.xuqingquan.easynews.service;


import top.xuqingquan.easynews.entity.NewsItem;
import top.xuqingquan.easynews.entity.PageBean;
import top.xuqingquan.easynews.entity.Response;
import top.xuqingquan.easynews.entity.Result;

import java.util.List;

public interface NewsItemService {

    Result addNewsList(List<NewsItem> list);

    Response<PageBean<NewsItem>> getNewsList(Integer start, Integer limit, String according, String direction,
                                             String channelName, String title, String pubDate, String source);

}
