package top.xuqingquan.easynews.dao;

import org.springframework.stereotype.Repository;
import top.xuqingquan.easynews.entity.NewsItem;

import java.util.List;
import java.util.Map;

@Repository
public interface NewsItemDao {

    int addNews(NewsItem item);

    List<NewsItem> getNewsList(Map<String,Object> map);

}
