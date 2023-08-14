package com.zlc.springboot.unti;

public class IpCountry {
    private static final String IP_SITE = "http://whois.pconline.com.cn/?ip=IPSITE";

    public static String ipSite(String ip) {
        //发送get请求
        String str = HttpClient.doGet(IP_SITE.replaceAll("IPSITE", ip));
//        System.out.println("最终数据: "+str);
        //正则去掉返回html格式字符串所有空白字符
        str = str.replaceAll("\\s", "");
        //通过匹配关键字进行拆分
        String[] str1 = str.split("<inputtype=\"submit\"></p><p>位置：");

        String[] str2 = str1[1].split("</p>");
        //返回结果
        return str2[0];
    }
}