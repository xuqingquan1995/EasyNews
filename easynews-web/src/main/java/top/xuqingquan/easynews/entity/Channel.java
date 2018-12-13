package top.xuqingquan.easynews.entity;

/**
 * 新闻频道
 */
public class Channel {

    //数据库id
    private int id;
    //频道id
    private String channelId;
    //频道名称
    private String name;

    public Channel() {
    }

    public Channel(String channelId, String name) {
        this.channelId = channelId;
        this.name = name;
    }

    public Channel(int id, String channelId, String name) {
        this.id = id;
        this.channelId = channelId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", channelId='" + channelId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
