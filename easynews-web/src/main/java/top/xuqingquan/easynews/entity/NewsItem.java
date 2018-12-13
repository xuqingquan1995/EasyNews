package top.xuqingquan.easynews.entity;

import net.sf.json.JSONArray;

/**
 * 新闻数据
 */
public class NewsItem {

    //用于存储数据库的id
    private int newsId;
    //新闻id
    private String id;
    //新闻标题
    private String title;
    //新闻详情链接
    private String link;
    //发布时间
    private String pubDate;
    //来源网站
    private String source;
    //新闻简要描述
    private String desc;
    //频道id
    private String channelId;
    //channelName
    private String channelName;
    //新闻对应的外网id
    private String nid;
    //图片列表
    private JSONArray imageurls;
    //新闻正文,txt格式
    private String content;
    //新闻正文,html格式
    private String html;
    //是否有图片
    private boolean havePic;


    public NewsItem() {
    }

    public NewsItem(String id, String title, String link, String pubDate, String source, String desc, String channelId, String channelName, String nid, JSONArray imageurls, String content, String html, boolean havePic) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.source = source;
        this.desc = desc;
        this.channelId = channelId;
        this.channelName = channelName;
        this.nid = nid;
        this.imageurls = imageurls;
        this.content = content;
        this.html = html;
        this.havePic = havePic;
    }

    public NewsItem(int newsId, String id, String title, String link, String pubDate, String source, String desc, String channelId, String channelName, String nid, JSONArray imageurls, String content, String html, boolean havePic) {
        this.newsId = newsId;
        this.id = id;
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.source = source;
        this.desc = desc;
        this.channelId = channelId;
        this.channelName = channelName;
        this.nid = nid;
        this.imageurls = imageurls;
        this.content = content;
        this.html = html;
        this.havePic = havePic;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public JSONArray getImageurls() {
        return imageurls;
    }

    public void setImageurls(JSONArray imageurls) {
        this.imageurls = imageurls;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public boolean isHavePic() {
        return havePic;
    }

    public void setHavePic(boolean havePic) {
        this.havePic = havePic;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "newsId=" + newsId +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", source='" + source + '\'' +
                ", desc='" + desc + '\'' +
                ", channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                ", nid='" + nid + '\'' +
                ", imageurls=" + imageurls +
                ", content='" + content + '\'' +
                ", html='" + html + '\'' +
                ", havePic=" + havePic +
                '}';
    }
}
