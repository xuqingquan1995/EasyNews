package top.xuqingquan.easynews.entity;

/**
 * 新闻对应的图片
 */
public class NewsImage {

    //存储数据库用的id
    private int id;
    //关联新闻数据的id
    private int newsId;
    //图片的url
    private String url;
    //图片的尺寸
    private int width;
    private int height;

    public NewsImage() {
    }

    public NewsImage(String url, int width, int height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public NewsImage(int id, int newsId, String url, int width, int height) {
        this.id = id;
        this.newsId = newsId;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "NewsImage{" +
                "id=" + id +
                ", newsId='" + newsId + '\'' +
                ", url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
