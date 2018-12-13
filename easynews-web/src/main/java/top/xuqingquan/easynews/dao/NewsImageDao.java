package top.xuqingquan.easynews.dao;

import org.springframework.stereotype.Repository;
import top.xuqingquan.easynews.entity.NewsImage;

import java.util.List;


@Repository
public interface NewsImageDao {

    int addNewsImage(NewsImage list);

    List<NewsImage> getNewsImageList(int newsId);


}
