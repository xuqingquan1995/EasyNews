package top.xuqingquan.easynews.dao;

import org.springframework.stereotype.Repository;
import top.xuqingquan.easynews.entity.Channel;

import java.util.List;

@Repository
public interface ChannelDao {

    int addChannelList(List<Channel> channelList);

}
