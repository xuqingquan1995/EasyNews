package top.xuqingquan.easynews.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import top.xuqingquan.easynews.entity.NewsItem;
import top.xuqingquan.easynews.entity.Result;
import top.xuqingquan.easynews.service.NewsItemService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

@Configuration
@EnableScheduling
@EnableAsync
@Component
public class ScheduleTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);
    private final NewsItemService ns;

    @Autowired
    public ScheduleTask(NewsItemService ns) {
        this.ns = ns;
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(200);
        executor.setQueueCapacity(10);
        executor.initialize();
        return executor;
    }

    @Async
    @Scheduled(fixedRate = 10 * 60 * 1000)//10分钟一次
    public void scheduled() {
        logger.info(new Date().toString() + "\t" + (ns == null));
        try {
            getNews(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getNews(int page) throws Exception {
        logger.warn("page=="+page);
        Result result = GetNews.getNews(page, 50);
        if (result.getCode() == 0) {
            List<NewsItem> newsItemList = (List<NewsItem>) result.getData();
            if (ns != null && newsItemList != null) {
                Result result1 = ns.addNewsList(newsItemList);
                logger.error(result1.toString());
                if (result1.getCode() == 0) {
                    int row = Integer.parseInt(result1.getData().toString());
                    if (row > 0) {
                        page++;
                        getNews(page);
                    }
                }
            } else {
                logger.error("ns == null");
            }
        }
    }
}
