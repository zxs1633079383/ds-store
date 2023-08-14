package com.zlc.springboot.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.zlc.springboot.model.InitOrder;
import com.zlc.springboot.model.PlaceOrder;
import com.zlc.springboot.model.Sell;
import com.zlc.springboot.model.Soft;
import com.zlc.springboot.service.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Slf4j
@Api(tags = "下单成功Controller")
public class IndexController {

    //注入PlaceOrder的service层
    @Autowired
    private PlaceOrderService placeOrderService;
    //注入Soft的Service层
    @Autowired
    private SoftService softService;
    //注入Sell的Service层
    @Autowired
    private SellService sellService;
    //注入LoginService
    @Autowired
    private LoginService loginService;
    //注入InitOrder的Service层
    @Autowired
    private InitOrderService initOrderService;
    //测试账号 sfrqhp8010@sandbox.com
    //测试密码 111111


    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    private static String app_id = "2021000118638281";
    //线上环境
//    private static String app_id = "2021002189692850";
    //配置文件
//    @Value("${alipay.app_id}")
//    private static String app_id;

    // 商户私钥，您的PKCS8格式RSA2私钥    （自己生成的私钥，记得去除空格，换行符）
    private static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIynafS7nX1vmGxAmAbH6n6BfWG24d5GLsqhWe8NwyWFMo+nYf7uEoZhKAsKQgUV6ZTzSwda12gQcfjibhT2G/KDvfM21Ah7MbHoqH0W3jMi9T6VIlNcQi9NbMqfy4qdOhA4aAsWz/uXAWqSXW3fCy/J1ipD1Or9sBUR3juNBnUanY28omZ0r8qbU3qiRcUsmPVE1/l0F+sGeifAv8U28yNKz5nxAviE6PW+tIfj75boHtRBGoRxv715CO+mmEZ1Aa4djhmPowoG4lvz+AbIwnGnIxjcn2gL4RZLctQYxuf1GHBmoUnCTyXfGh/jtXPlxQd5uLAFzlj4q9oHSq5HKdAgMBAAECggEAUqZyUgGBQqbDkef1DcGALZeQf2+Hc0xBcVm7QpOpmEnBxWq/4L+HD8/MsjEImfZQQDWZUl1n98gyM6DCYTSDjOqUcmIySuEzGNm1PicOTE5QRlUdqxn3bl+dQYAcEL+b/ReSN0P85e53iVmeYVssShlURvuRh2+91efbNXK/iQzuaEpl6KgqRfwh3NuX71fXS0wONIJdmYq5Ywwf+/fBkxa2XDIHTLWVGlSLKy9lUSBkJZV4Fdr+mSKBeSt3rHIGYPcbLyIxOknLRULbDK3humZWpYeBWrb0fSnuQJ5uYTMKPwDA3DEP4TifW77UxLfD/2E3ZfmH1BMkNfb+xJypCQKBgQDAT5+/eQ6vGbssXf195dtxHBc6GROcymHnDRTPrmCAXNiZ9qFxd9PpaLm7zBmkO2+s2dakkPVCPxIfkzffngvGQRSElDhM5CIwe225TwhhGd9qhQ+hnoF/N7Wfi8TCtOqC444MOafBPCwIQKCPN4OBO9SbC9XBoA8a9HyFsJLg6wKBgQC2F8TgxvxZBlTTCTILGniYeM17CxHpLpHzqV7JH7ngRNOuCS5/mnbgKr9fIm+KOi0w3aDgZEwyfNn1DVANLZqAgkunZsat0Bm2oY0XJ1AV2EGUJUU7+FA+bEFO00AZ7oh9EDEPEShQ0bg2FBHIfsL1Tt2H4OXb+bnYAcC3oJNYlwKBgFc0RAY5SNVR37RQsqAiWQ3DZSztLh2NEDQLlIJQWmYatAWs9qxTX/6ZhI+KD56PbSZ+aXyaVvzBSOCUi2OVcVVnMXylEZqcYHyHm8vJ8MzHRK4yxFHPZH1hr7Q3VvPLwgO0WDdm9ZEEThs34xbOX418qf3csgNpJzBb6RNebFwdAoGASM0z/5JO7eDkJQO2KE5LDNqvfJQucwYVgE+k/9BZZlreLuSo3IeLI5Cztnt0ENW1n6XcXhp7hGv6UEkkSkCDVwHA8YrbwD5FKYlpeWpUUIeHoI1OIu3iVzK84aEALd36X9YLZzS646UVezmG6pwTojQHyf2WqN4s+TmqLUBb6L0CgYEAqz/IFYHE4Pm4M4mBwYvf50pXTbYf3LOR9IbJ1OxIZavHWm8GhalK5JRAIIW8RhMWVCjj7rQKgBfFoVw0dCHytb/0kFHN9KZ/pEppceEFsZyuywIMv6ydRu2UDjq//4/OniIiORYpk4iHP8SffJID0Np8x6MV4+ADsH65sQLuPIc=";
    //线上环境
//    private static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCR5UE4rVH5bQv2GwHtwBHUeQ6HvSxXmHLHB6ugHTlHV89B9O2li9SnN6U75AtED6TtAIjgIuvHDBpVNH1iq0DWAgtLUG80vnKRcP1Ep7qm2W2qTQ71AvWJYgaDoUYp7qmb9wf43HBND8d7XueP724fjphXmYw2Ux5+90f5YfN3ZwRvpV0Zc3N+Rp0k5iFiX/nJvPCK7y/8Qf1lXTsw2eyBZ2o8ihBf/F7QSGPMOyiAkAjVBlv32co9D8PI2NMVCvANNEvB7DyPBGItpIeTZ+22FeWljCNx2RbDsmd7yfSzk/HRXNoy0vJrAjbKcDnmY29JYlcici0i0ixrBxRjsIWjAgMBAAECggEAHQiUbCUBtTNgN/+z6I5afgABRj39PwlVfgomu1xVc78jvapySImFLinCtiJxo3/BgHCkzIXmidv8uYwSj7oo0u3hNYGyP7rOrebgX/NJD78hNczT9yo5lrdtOHC1t86I7kLA0WN87bkAJ6sBRgGN1rXX10b1QjJUVUv8hMJbYx1MVUfZn9wxA5ogWGz0doARTzwuB5a0KPaaPAk1Ty/JgYSYiaCd3+GubmETET2NkobSsLVpr4S4XhRuQJvTNY0HWsD4kQ9mER2csGwaGAd/+fyjQn1iHsYoYrbrTWK6knjcCffJ1Dw3r4HDlRYipy37tGj8WoDIcl+Ow3+hoVW00QKBgQDxBBmTt3wKUEmiu9Mg0pTGOOSrSJzzbyO3aHizXxPokTuTfu57RyqzIdWMkZyPEK3iskqamy7nkwo2p8blxzbcC3NPejGYUa25e1ZKs9oTidBTgQMccBXIrhjZK18OLQOk/X6pQKsaAwYvxnKAAxjhO2kewf48KdJd9/fo4vebrwKBgQCa90LJ5hKPYasWY/H4Da7cbV2kxx2s39HGlXWZ1LxOHBlE0KoUC/2OAFmb3XLZqUVAQFTuc23Xf83MeIjrvvb15oCnwfBxHanttgUDC0GI9WqlHxDY6kgDR/swj4EU+7hVQHVWb8eWORUNb+qSzPDpem3hHeN+RylRoNN516buTQKBgHEC5SNYo/cKEp0VyTfYxh30S3TtRXr4IB0+BWrGKEWi4/aTTCvOAP2h3cpd9LU4svBbfZPlNCct0VozbZYMxXKEg/s+ZsbvdbBFXv8EOjtzIZYmJllNTUmeKNSb+6RgAxd4Lc4bHp/Bz0LToJjZ2u6xtubPO/DmA1hAj6RJ2KTNAoGAbY8uuwtcR96nVNcodVVHMTRpJlxYEKbU6u80C6Qp1ZdsDgjwlee8Ra7dq6Ku1lAkU76nzUXyfI8w8sStGO8ULtneT7x/v5lXUGaFuJc2qBvKYB9Mi+9nJlq3rqdmfW1R74P48TlqBD0rMyrSzyLxmEAkeynkcpzhC4MGwHfE32kCgYEAs8g0k7qIAOiciFIiMcW8IXg5Cc8ydBahqOtxWPFKxP2BWQx5PxyfLywaEp8f81bxJoHSmHb9tKGbQ7XZ4NrYi6EYpzpAgMi30ory6EWLA4q7pvOTHb1H3QoE1uYOmRCg25l1ixlm6/VH0Sfcz6fCb+HztLrZxNyyXxZRQQLGlSI=";
//    配置文件
//    @Value("${alipay.merchant_private_key}")
//    private static String merchant_private_key;

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs3BRcotuONglGUSemm8bwvdsDgZn62gKMsnD1fVa672zrFCRMd2CSZ9C7X0+puefKE2v4WglmCJnPjP9N2gUHPTB+DJF6NvNuBFJwkNFXcADKgVkR/dpZnz+iLS0hok44Zz0Lm7QgMfpz69ELDbZuJ6DJiMm+dhRBlGYe1BC2JrftvXeIzXKv/WtO7DdD+H6/4KZ5654ILAlrlIRn7NINmWi+3HxtcTEQbxKEBPflNBQ3fUELUXBHvSwVE8tmt7FrPwXrURnTvPrL8+N+EbvoHJxq8wlpubzRX+p9+NDKndLW/WK02s0zhTMMpVAwWs4Lx+uqqoQdEs5QFLNB/dSHQIDAQAB";
    //线上环境
//    private static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1klDxca+jItg/tdjzZEwxDicj9vmhH0jz3wwcYLO9vYjagQF6cTyAyUdDHzrszELyv5bkrBsO3RRRyV0iUCXUq4xt7PdoVGjeIQjcgz4Z1rY/G9uUu4yQWIQGPX6dNlxT6QxZ1jLyVV63AGuIm4iBQ+uWYYckEC1b5AdWudR1rNRkbzLDuhKeOrChF/bNlVaSfjc3m5hlt0CFaEF3Yt0TG0lYgNq4DmOuuX8mIIWmtrAqGNaUvNNr0sOcHLHzYPi1Vv7D5QF3RxqyWLAjNVpkGne0slORcn6p+RFZCdf05rsV0G1orSGiN9MxOlg6HKbVrsXt8VByyvkbnHdPdqrrQIDAQAB";
    //配置文件
//    @Value("${alipay.alipay_public_key}")
//    private static String alipay_public_key;

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private static String notify_url = "http://localhost:8080/alipay/notify_url";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private static String return_url = "http://localhost:8080/alipay/return_url";
    //线上环境
//    private static String return_url = "http://purpleairs.com/alipay/return_url";
    //配置文件
//    @Value("${alipay.return_url}")
//    private static String return_url;

    // 签名方式
    private static String sign_type = "RSA2";
    // 字符编码格式
    private static String charset = "utf-8";

    // 支付宝网关
    private static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    //线上
//    private static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
    //配置文件
//    @Value("${alipay.gatewayUrl}")
//    private static String gatewayUrl;

    // 支付宝网关
//    public static String log_path = "D:/";




    //回调验证.验证成功后可以返回自己想要跳转的页面
//    /alipay/return_url
    @RequestMapping("/alipay/return_url")
//    @ResponseBody
    public String returnUrl(String charset, String out_trade_no, String method, BigDecimal total_amount,
                            String sign, String trade_no, String auth_app, String version, String app_id, String sign_type,
                            String seller_id, String timestamp, HttpServletRequest request, HttpSession session, Model model,
                            HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException {
        log.info("用户支付成功--> 订单号为: " + out_trade_no);
        log.info("用户购买成功");
        System.out.println("支付成功---");
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        System.out.println("params:" + params);
        //异常捕获,支付环境.如果发生异常则提示错误信息
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, alipay_public_key, charset, sign_type); //调用SDK验证签名
        } catch (Exception e) {
            e.printStackTrace();
            //生成报错信息到500页面.
            model.addAttribute("errorMsg", "支付参数格式有误,请联系管理员");
            return "error/500";
        }

        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {

            //业务逻辑代码.
//            log.info("订单号为:" + out_trade_no);
//            log.info("APPID为:" + app_id);
//            log.info("tradeNo:" + trade_no);
//            log.info("卖家iD: " + seller_id);
//            log.info("下单时间为: " + timestamp);
            //创建下单详情实体类
            PlaceOrder placeOrder = new PlaceOrder();
            placeOrder.setOutTradeNo(out_trade_no);
            placeOrder.setAppId(app_id);
            placeOrder.setTradeNo(trade_no);
            placeOrder.setSellerId(seller_id);
            //格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = sdf.parse(timestamp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //赋值下单时间
            placeOrder.setTimestamp(date);

            //插入下单详情信息
            try {
                placeOrderService.insert(placeOrder);
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("errorMsg", "请勿重复发起相同支付订单");
                return "error/500";
            }
            //根据订单号id去修改初始订单中的支付状态.
            initOrderService.updateStateById(out_trade_no);
            //根据订单号id去查询订单状态.

            //执行到这里代表支付成功,根据订单编号,文件编号, 返回对应的文件地址.;
//            FileUntil.export(new File("D:\\MyRuanJian\\jar\\017-springboot-mybatis-plus-1.0.0.jar"));

            //根据订单号获取对应的SoftID
//            Integer softid = initOrderService.SelectIdByOrderID(out_trade_no);
//            String filePath = initOrderService.FilePathBySoftID(out_trade_no);
            //线上环境
            String filePath = initOrderService.FilePathBySoftID(out_trade_no);
            filePath = filePath.substring(6, filePath.length());

            String newFile = URLEncoder.encode(filePath, "utf-8");
            //创建Session.
            session.setAttribute("state", 1);

            //根据订单号去查询id.
            Integer softid = initOrderService.SelectIdByOrderID(out_trade_no);
            //根据Softid去查询软件详情啥的.
            Soft soft = softService.selectByPrimaryKey(softid);
            //查询当前Sell表中是否存在当前softid.
            int sum = sellService.selectCountBySoftid(softid);

            // ****用户下单后,下单统计表统计汇总数据.
            //数量等于0代表当前软件首次下载.需要进行插入信息
            //大于0则则代表软件已下载过,更新Sell中的数据.
            if (sum == 0) {
                Sell sell = new Sell();
                //给Sell赋值
                sell.setSellid(null);
                sell.setSellnumcount(1);
                sell.setSellsummoney(soft.getSoftprice());
                sell.setSoftid(softid);
                //插入数据
                sellService.insert(sell);
            } else {
                //更新数据
                Sell sell = new Sell();
                //给Sell赋值
                sell.setSellid(null);
                sell.setSellsummoney(soft.getSoftprice());
                sellService.updateDownBySoftid(softid);
            }

            //生成Sell实体类
            //判断下单总计表种,有没有该id如果没有则插入,有则更新
            response.setContentType("text/html;charset=utf-8");
            //
            return "redirect:/toDown/" + newFile;

        } else {
            model.addAttribute("error", "支付验签失败,请联系管理员");
            return "error/500";
        }
    }

    /***
     *
     * @param subject 商品购买信息
     * @param total_amount 商品价格
     * @param body 商品描述信息
     * @param request
     * @param response
     * @param session
     * @throws Exception
     */
    @RequestMapping("/toPay/{subject}/{total_amount}/{body}")
    public void toPay(@PathVariable("subject") String subject, @PathVariable("total_amount") double total_amount,
                      @PathVariable("body") String body, HttpServletRequest request, HttpServletResponse response,
                      HttpSession session) throws Exception {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, "json", charset, alipay_public_key, sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = UUID.randomUUID().toString();
        System.out.println("out_trade_no=" + out_trade_no);
        //定制body内容
        body = "您正在购买编号为" + body + "的软件订单...";

        //根据softid查询对应的价格.防止前端恶意篡改参数值.恶意下载.
        String regEx = "\\D";
        int softid = Integer.parseInt(body.replaceAll(regEx, ""));
        Soft soft = softService.selectByPrimaryKey(softid);
        total_amount = soft.getSoftprice();

        //订单号,付款金额,商品信息,商品详情描述
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"10m\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = "";
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //生成实体类创建当前订单,初始的时候只有订单id,订单描述,商品描述信息
        InitOrder initOrder = new InitOrder();
        initOrder.setOutTradeNo(out_trade_no);
        initOrder.setSubject(subject);
        initOrder.setTotalAmount(total_amount);
        initOrder.setBody(body);
        //判断用户Session是否存在.存在的话下单人就为用户.不存在的话下单人则为游客.
        String user = "游客";
        if (session.getAttribute("user") != null) {
            user = String.valueOf(session.getAttribute("user"));
        }
        //根据用户名查询用户id.
        Integer userid = loginService.findUserid(user);
        //userid赋值
        initOrder.setUserid(userid);
        //提取body中的软件编号.

        //softid 赋值
        initOrder.setSoftid(softid);
        //订单状态为 未支付. 0则代表未支付
        initOrder.setOrderstate(0);

        log.info("初始订单信息为:" + initOrder);

        //insert!!!调用Service层插入初始订单信息
        initOrderService.insert(initOrder);

        //输出
        response.setContentType("text/html;charset=" + charset);
        response.getWriter().write(result);// 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }
}
