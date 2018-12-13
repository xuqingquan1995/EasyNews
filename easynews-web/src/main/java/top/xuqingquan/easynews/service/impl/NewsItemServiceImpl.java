package top.xuqingquan.easynews.service.impl;

import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xuqingquan.easynews.dao.NewsImageDao;
import top.xuqingquan.easynews.dao.NewsItemDao;
import top.xuqingquan.easynews.entity.*;
import top.xuqingquan.easynews.service.NewsItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("NewsItemService")
public class NewsItemServiceImpl implements NewsItemService {

    private final NewsItemDao nd;
    private final NewsImageDao nid;
    private Logger logger = LoggerFactory.getLogger(NewsItemServiceImpl.class.getSimpleName());

    @Autowired
    public NewsItemServiceImpl(NewsItemDao nd, NewsImageDao nid) {
        this.nd = nd;
        this.nid = nid;
    }

    @Override
    public Result addNewsList(List<NewsItem> list) {
        Result result = new Result();
        if (list != null) {
            int addNewsImageList = 0;
            int addNews = 0;
            int errerData = 0;
            for (NewsItem item : list) {
                if (item.getContent() == null || item.getContent().isEmpty() || item.getHtml() == null || item.getHtml().isEmpty()) {//对于新闻消息是空的不添加
                    errerData++;
                    continue;
                }
                try {//避免因为中间某条数据插入失败导致全部失败
                    int row = nd.addNews(item);
                    addNews += row;
                    if (row > 0) {
                        List<NewsImage> newsImageList = JSONArray.toList(item.getImageurls(), NewsImage.class);
                        for (NewsImage image : newsImageList) {
                            image.setNewsId(item.getNewsId());
                            addNewsImageList += nid.addNewsImage(image);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            result.setCode(0);
            result.setMsg(String.format("成功插入%d条新闻数据，%d条图片数据,%d条数据不合规", addNews, addNewsImageList, errerData));
            result.setData(addNews);
        } else {
            result.setCode(-1);
            result.setMsg("插入列表为空");
        }
        return result;
    }

    @Override
    public Response<PageBean<NewsItem>> getNewsList(Integer start, Integer limit, String according, String direction, String channelName, String title, String pubDate, String source) {
        try {
            if (start == null || start <= 0) {
                start = 0;
            }
            if (limit == null || limit <= 0) {
                limit = 30;
            }
            if (according == null || according.isEmpty()) {
                according = "pubDate";
            }
            if (direction == null || direction.isEmpty()) {
                direction = "desc";
            }
            if (channelName == null || "推荐".equals(channelName)) {
                channelName = "最新";
            }
            PageBean<NewsItem> pageBean = new PageBean<>();
            pageBean.setBefore(start);
            Map<String, Object> map = new HashMap<>();
            map.put("start", start);
            map.put("limit", limit);
            map.put("according", according);
            map.put("direction", direction);
            map.put("channelName", channelName);
            map.put("title", title);
            map.put("pubDate", pubDate);
            map.put("source", source);
            logger.info(map.toString());
            List<NewsItem> list = nd.getNewsList(map);
            if (!list.isEmpty()) {
                pageBean.setAfter(list.get(list.size() - 1).getNewsId());
            }
            logger.warn("channel=" + channelName + "\tafter=" + pageBean.getAfter());
            for (NewsItem item : list) {
                List<NewsImage> imageList = nid.getNewsImageList(item.getNewsId());
                if (imageList != null && !imageList.isEmpty()) {
                    item.setImageurls(JSONArray.fromObject(imageList));
                    int size = imageList.size();
                    if (size == 1 || size >= 3) {
                        item.setHavePic(true);
                    }
                } else {
                    item.setImageurls(null);
                }
            }
            pageBean.setData(list);
            return new Response<>(pageBean);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Response<>(-1, "获取新闻失败");
        }
    }
}
