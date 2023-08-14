package com.zlc.springboot.service.impl;

import com.zlc.springboot.service.AsyncService;
import com.zlc.springboot.service.InitOrderService;
import com.zlc.springboot.service.IpAddressService;
import com.zlc.springboot.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 测试异步任务,定时任务,(邮件任务已测试完毕)
 */
@Component
public class AsyncServiceImpl implements AsyncService {

    //注入IpAddressService
    @Autowired
    private IpAddressService ipAddressService;
    //注入initOrder的Service
    @Autowired
    private InitOrderService initOrderService;
    //注入sell的Service
    @Autowired
    private SellService sellService;
    //注入邮件工具类
    @Autowired
//    private SendEmailUtil sendEmailUtil;

    //定时任务
    @Override
//    @Scheduled(fixedDelay = 21600000)
//    @Scheduled(fixedDelay = 5000)
    public void secordPrint() {
        System.out.println("定时任务每六小时执行一次--->");


        //格式化日期获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        //
        //获取当前总人数
        int peopleNum = ipAddressService.selectCountByDay(date);
        //获取监控时间
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdfNow.format(new Date());

        //声明邮件主题
        String title = date + "监控人数";
        //获取下单数量和成功交易订单数量
        Integer orderNum = initOrderService.ZeroByOrderState();
        Integer trueOrderNum = initOrderService.OneByOrderState();
        //获取当前交易总额
        Integer money = sellService.moneyAll();
        //声明邮件内容
        String text = date + "监控人数: " + peopleNum + "人" + "\n" +
                             "监控时间为: " + nowTime + "\n" +
                             "下单数量为: " + orderNum + "  成功交易订单数量为: "+trueOrderNum + "\n" +
                             "当前交易总额为:  " + money ;
        //声明邮件发送者

        String[] userArr = {"1633079383@qq.com"};
//        sendEmailUtil.PuTongSend(title, text, userArr);

    }
}
