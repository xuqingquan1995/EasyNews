package top.xuqingquan.easynews.service.impl;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xuqingquan.easynews.dao.ChannelDao;
import top.xuqingquan.easynews.entity.Channel;
import top.xuqingquan.easynews.entity.Result;
import top.xuqingquan.easynews.service.ChannelService;

import java.util.List;

@Service("ChannelService")
public class ChannelServiceImpl implements ChannelService {

    private final ChannelDao cd;

    @Autowired
    public ChannelServiceImpl(ChannelDao cd) {
        this.cd = cd;
    }

    @Override
    public Result addChannelList(String channelList) {
        Result result = new Result();
        try {
            JSONArray array = JSONArray.fromObject(channelList);
            List<Channel> list = JSONArray.toList(array, Channel.class);
            int row = cd.addChannelList(list);
            result.setCode(0);
            result.setMsg("成功插入" + row + "条数据");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(-1);
            result.setMsg("插入数据出错");
        }
        return result;
    }
}
