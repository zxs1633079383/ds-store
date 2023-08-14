package com.zlc.springboot.controller;

import com.zlc.springboot.model.Need;
import com.zlc.springboot.service.NeedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

//软件定制页面控制器
@Controller
@Slf4j
@Api(tags = "软件定制页面")
public class NeedController {

    @Autowired
    private NeedService needService;

    //post请求提交定制软件详情信息

    /***
     *
     * @param need  商品需求信息.
     * @param model
     * @return
     */
    @RequestMapping("/need-submit")
    @ApiOperation("用户提交软件需求接口")
    public String toNeedSubmit(Need need, Model model) {
        log.info("提交需求成功---->");
        log.info("前置: mess:attr: " + model.getAttribute("mess"));

//      log.info("需求信息为: " + need.toString());

        try {
            //新增需求到数据库中,进行异常捕获
            need.setNeeddate(new Date());
            int num = needService.insertSelective(need);
            if (num > 0) {
                model.addAttribute("mess", "√ 提交需求成功");
                return "show/dosoft";
            } else {
                model.addAttribute("error", "× 请检查需求信息");
                return "show/dosoft";
            }
        } catch (Exception e) {
            //如果发生异常就返回错误信息
            model.addAttribute("error", "× 请检查需求信息");
            return "show/dosoft";
        }
    }
}
