package top.xuqingquan.easynews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xuqingquan.easynews.entity.Result;
import top.xuqingquan.easynews.service.ChannelService;

@Controller
@RequestMapping("channel")
public class ChannelController {

    private final ChannelService cs;

    @Autowired
    public ChannelController(ChannelService cs) {
        this.cs = cs;
    }

    @ResponseBody
    @RequestMapping("addChannelList")
    public Result addChannelList(String channelList){
        return cs.addChannelList(channelList);
    }
}
