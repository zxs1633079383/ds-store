package com.zlc.springboot.controller;


import com.zlc.springboot.model.Login;
import com.zlc.springboot.service.LoginService;
import com.zlc.springboot.unti.ImageCode;
import com.zlc.springboot.unti.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//登录与注册Controller
@Controller
@Slf4j
@Api(tags = "登录与注册相关业务接口")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisUtil redisUtil; //Redis工具类

    //登录请求
    @PostMapping("/isUser")
    @ApiOperation("登录请求")
    public String isUserTrue(@RequestParam("user") String user, @RequestParam("pwd") String pwd,
                             Model model, @RequestParam("tpCode") String tpCode, HttpSession session) {
        log.info("进入系统的用户名和密码为:" + user + "  " + pwd);
        Login login = loginService.isUser(user, pwd);

        String tpSession = String.valueOf(session.getAttribute("imageCode")).toUpperCase();
        log.info("当前图片验证码Session为:" + tpSession);
        log.info("当前图片用户输入为: " + tpCode);
        tpCode = tpCode.toUpperCase();
        //如果login为null,则代表该账号或密码不正确
        if (login == null) {
            //保存用户登录信息
            model.addAttribute("usertext", user);
            model.addAttribute("pwd", pwd);
            //提示用户名错误信息
            model.addAttribute("error", "账号或密码不正确");
            return "login";
        } else {
            //都不符合条件的话,判断验证码是否正确.
            if (tpSession.equals(tpCode)) {
                //验证码输入成功不提示
            } else {
                //验证码输入错误提示错误信息.
                model.addAttribute("error", "验证码错误,请重新输入");
                model.addAttribute("usertext", user);
                model.addAttribute("pwd", pwd);

                return "login";
            }
            //保存session作为本次会话对象.全局拦截,如果没有登录的话,就跳转到登录界面提示用户请登录
            session.setAttribute("user", user);
            //因为下载软件的时候需要验证session
            session.setAttribute("state", 1);
            String usersession = String.valueOf(session.getAttribute("user"));
            log.info("user的Session为:" + usersession);
            //执行到此处说明用户所有信息均填写正确,可正常登录
            //查询用户Session状态是否已存在与Redis中
            String redis_User = String.valueOf(redisUtil.get("user:" + user));
            System.out.println(redis_User);
            boolean flag = false;
            if (redis_User == null || redis_User.equals("") || redis_User.equals("null")) {
                flag = redisUtil.set("user:" + user, 1, 60000 * 30);
            }
            System.out.println(flag ? "success" : "shibai");


            System.out.println("user的值为:" + user);
            model.addAttribute("user-btn", user);

            return "show/store";

//            model.addAttribute("user",login.getUser());
        }
    }

    /***
     *
     * @param login 需要注册的用户信息
     * @param model
     * @return
     */
    @PostMapping("/user/register")
    @ApiOperation("注册请求")
    public String toRegister(Login login, Model model) {
        log.info("进入到注册功能中");
//        log.info("註冊信息為:" + login.toString());

        //判断用户名是否包含中英文和数字
        String str = login.getUser();
        String regEx = "^[A-z0-9\\u4e00-\\u9fa5]*$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if (!m.matches()) {
            System.out.println("含有特殊字符");
            log.info("用户名只能以中英文以及数字组成");
            model.addAttribute("error", "用户名只能以中英文以及数字组成");

            //数据重新展示到页面中
            String user = login.getUser();
            String pwd = login.getPwd();
            String qq = login.getQqname();
            String key = login.getUserkey();
            model.addAttribute("logtouser", user);
            model.addAttribute("logtopwd", pwd);
            model.addAttribute("logtoqq", qq);
            model.addAttribute("logtokeymi", key);
            return "logto";
        } else {
            System.out.println("没有特殊字符");
        }

        //需要注册的账号信息
        try {
            //設置創建時間
            login.setCreatetime(new Date());
//            int num = loginService.insert(login);
            int num = loginService.insertSelective(login);
            //注册成功需要更改当前使用的卡密状态
//            int updateKeyMi = keyMiService.updateFroKeyState(login.getKey());
            //如果成功註冊,就重定向到登录界面
            return "redirect:/login.html";
        } catch (Exception e) {
            //發生異常則提示報錯信息
            e.printStackTrace();
            log.error("用户名已存在,无法进行注册");
            model.addAttribute("error", "用户名已存在,无法进行注册");
            return "logto";
        }
    }

    //用户注销登录
    @GetMapping("/sign-out/delete-Session")
    @ApiOperation("注销当前用户请求")
    public String delete_Session(HttpSession session) {
        String user = (String) session.getAttribute("user");
        session.removeAttribute("user");
        log.info("注销当前用户:" + user);
        //清除Redis对应的UserSession缓存
        redisUtil.del("user:" + user);
        return "redirect:/A-to-Login";
    }

    //生成图片验证码(数字加字母)并保存值到Session中.
    @GetMapping(value = "/getImage")
    @ApiOperation("图片验证码生成接口.")
    public String imagecode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        //生成随机字符串.
        OutputStream os = response.getOutputStream();
        //这里修改传入的数值，可以修改产生的验证码的大小
        Map<String, Object> map = ImageCode.getImageCode(100, 20, os);
        //获得随机生成的图片验证码..
        String strEnsure = (String) map.get("strEnsure");
        try {
            String str = "";
            session.setAttribute("imageCode", strEnsure);
            ImageIO.write((BufferedImage) map.get("image"), "JPEG", os);
        } catch (IOException e) {
            return "";
        }
        return null;
    }
}
