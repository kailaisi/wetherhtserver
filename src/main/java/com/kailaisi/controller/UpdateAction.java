package com.kailaisi.controller;

import com.kailaisi.bean.UpdateBean;
import com.kailaisi.service.UpdateServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/update")
public class UpdateAction {
    @Resource
    UpdateServiceImpl updateService;

    @ResponseBody
    @RequestMapping(value = "/checkupdate.do", method = RequestMethod.GET, produces = {"application/json; charset=utf-8"})
    public UpdateBean checkUpdte(String md5Value, int versioncode, String channelid) {
        return updateService.checkUpdate(md5Value, versioncode, channelid);
    }
}
