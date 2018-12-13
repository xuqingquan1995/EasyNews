package top.xuqingquan.easynews.utils;

import com.show.api.ShowApiRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import top.xuqingquan.easynews.entity.*;

import java.util.List;

public class GetNews {

    private static final String appid="xxx";
    private static final String appSecret="xxx";

    public static void main(String... args) throws Exception {
        GetNews getNews = new GetNews();
        getNews.getNews(1, 20);
    }

    public static Result getNews(int page, int maxResult) throws Exception {
        String res = new ShowApiRequest("http://route.showapi.com/109-35", appid, appSecret)
                .addTextPara("channelId", "")
                .addTextPara("channelName", "")
                .addTextPara("title", "")
                .addTextPara("page", String.valueOf(page))
                .addTextPara("needContent", "1")
                .addTextPara("needHtml", "1")
                .addTextPara("needAllList", "0")
                .addTextPara("maxResult", String.valueOf(maxResult))
                .addTextPara("id", "")
                .post();
        return parseNews(res);
    }

    private static Result parseNews(String news) {
        try {
            ShowApiResponse baseResponse = (ShowApiResponse) JSONObject.toBean(JSONObject.fromObject(news), ShowApiResponse.class);
            if (baseResponse.getShowapi_res_code() == 0) {
                ShowApiAppResponse newsResponse = baseResponse.getShowapi_res_body();
                if (newsResponse.getRet_code().equals("0")) {
                    ShowApiPageBean pageBean = newsResponse.getPagebean();
                    List<NewsItem> newsItemList = JSONArray.toList(pageBean.getContentlist(), NewsItem.class);
                    pageBean.setContentlist(null);
                    return new Result(0, JSONObject.fromObject(pageBean).toString(), newsItemList);
                }
            }else{
                baseResponse.setShowapi_res_body(null);
                LoggerFactory.getLogger(GetNews.class).info("baseResponse=" + baseResponse.toString());
            }
            return new Result(-1, baseResponse.getShowapi_res_error());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(-1,e.getMessage(),e);
        }
    }

}
