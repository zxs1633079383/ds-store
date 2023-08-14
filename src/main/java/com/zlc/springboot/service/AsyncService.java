package com.zlc.springboot.service;

//全局任务类
public interface AsyncService {


    //定时任务,6小时发送一次邮件,统计目前系统当天人数.
    void secordPrint();
}
