package com.zlc.springboot.controller;


import com.zlc.springboot.model.InitOrder;
import com.zlc.springboot.model.Login;
import com.zlc.springboot.model.Soft;
import com.zlc.springboot.service.InitOrderService;
import com.zlc.springboot.service.LoginService;
import com.zlc.springboot.service.SoftService;
import com.zlc.springboot.unti.Md5Util;
import com.zlc.springboot.unti.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import javax.jws.WebParam;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Slf4j
@Api(tags = "后台业务接口")
public class HtController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private InitOrderService initOrderService;
    @Autowired
    private SoftService softService;
    @Autowired
    private RedisUtil redisUtil;


    //跳转用户展示页面
    @GetMapping("/A-to-UserShow")
    @ApiOperation("跳转用户展示页面.")
    public String UserShow(@RequestParam("page") Integer page, Model model) {
        //获取起始位置和末尾
        Integer start = (page - 1) * 10;
        Integer end = start + 10;
        //分页查询用户数量
        List<Login> logins = loginService.selectAll(start, 10);
        //查询分页插件对应的数量
        Integer num = loginService.selectNumOfAll();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            list.add(i);
        }
        log.info("cjNum：" + list.size());
        model.addAttribute("userList", logins);
        model.addAttribute("cjNum", list);
        model.addAttribute("cjState", page);
        model.addAttribute("PreState", page - 1);
        model.addAttribute("NextState", page + 1);
        return "ht/user_show";


    }

    //跳转用户编辑页面
    @GetMapping("/A-to-UserUpdate")
    @ApiOperation("跳转用户更改页面")
    public String UserUpdate(@RequestParam("id") Integer id, Model model) {
        Login login = loginService.selectByPrimaryKey(id);
        model.addAttribute("login", login);
        return "ht/user_update";
    }

    //跳转用户新增页面
    @GetMapping("/A-to-userInsert")
    @ApiOperation("跳转用户新增页面")
    public String toUserInsert() {
        return "ht/user_insert";
    }

    @PostMapping("/UserUpdate-true")
    @ApiOperation("确认用户编辑完成")
    public String updateTrue(Login login, Model model) {
        Login let_login = login;
        //判断密码和确认密码是否一致
        String pwd = let_login.getPwd();
        String t_pwd = let_login.getUserkey();
        if (pwd.equals(t_pwd)) {
            //执行更新操作.
            int num = 0;
            try {
                num = loginService.updateByPrimaryKeySelective(let_login);
            } catch (Exception e) {
                model.addAttribute("error", "用户名已存在");
                return "ht/user_update";
            }
            System.out.println(num > 0 ? "更新成功" : "更新失败");
            if (num > 0) {
                return "redirect:/A-to-UserShow?page=1";
            }
        } else {
            model.addAttribute("error", "账号密码不一致");
            model.addAttribute("login", let_login);
        }
        return "ht/user_update";
    }

    //用户删除功能实现
    @GetMapping("/A-to-UserDelete")
    @ApiOperation("用户删除功能")
    public String UserDelete(@RequestParam("id") Integer id, Model model) {
        loginService.deleteByPrimaryKey(id);
        return "redirect:/A-to-UserShow?page=1";
    }

    //用户新增功能实现
    @PostMapping("/UserInsert-true")
    @ApiOperation("用户新增功能实现")
    public String UserInsert(Login login, Model model) {
        login.setCreatetime(new Date());
        int num = 0;
        //判读密码是否一致
        if (login.getPwd().equals(login.getUserkey())) {

        } else {
            model.addAttribute("error", "密码与确认密码不一致");
            return "ht/user_insert";
        }
        try {
            loginService.insertSelective(login);
        } catch (Exception e) {
            model.addAttribute("error", "用户名已存在");
            return "ht/user_insert";
        }
        log.info(num > 0 ? "新增成功" : "新增失败");
        return "redirect:/A-to-UserShow?page=1";
    }

    //跳转订单列表实现
    @GetMapping("/A-to-orderList")
    @ApiOperation("跳转订单列表")
    public String toOrderList(@RequestParam("page") Integer page, Model model) {
        //分页查询数据
        //获取起始位置和末尾
        Integer start = (page - 1) * 10;
        Integer end = start + 10;
        //获取分页插件数量
        List<InitOrder> orders = initOrderService.selectAllOfPage(start, 10);
        Integer num = initOrderService.selectPageOfAll();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            list.add(i);
        }
        log.info("orNum：" + list.size());

        model.addAttribute("ordersList", orders);
        model.addAttribute("orNum", list);
        model.addAttribute("orState", page);
        model.addAttribute("PreState", page - 1);
        model.addAttribute("NextState", page + 1);
        return "ht/order_show";
    }

    //跳转订单流水实现
    @GetMapping("/A-to-orderFlow")
    @ApiOperation("跳转订单流水")
    public String toOrderFlow(Model model) {
        //1. 获取所有的订单数量以及金额
        Long all_orderNum = 0L;
        Double all_orderMoney = 0.0;
        Map<Object, Object> map = initOrderService.selectAllNumAndMoney();
        all_orderNum = (Long) map.get("aNum");
        all_orderMoney = (Double) map.get("aMoney");
        model.addAttribute("aNum", all_orderNum);
        model.addAttribute("aMoney", all_orderMoney);
        //2. 获取已完成的订单数量以及金额
        Long t_orderNum = 0L;
        Double t_orderMoney = 0.0;
        Map<Object, Object> map2 = initOrderService.selectNumAndMoney();
        t_orderNum = (Long) map2.get("num");
        t_orderMoney = (Double) map2.get("money");
        model.addAttribute("num", t_orderNum);
        model.addAttribute("money", t_orderMoney);
        //3. 查询当前时间
        String time = new SimpleDateFormat("MMM dd yyyy", Locale.UK).format(new Date());
        model.addAttribute("time", time);
        return "ht/order_flow";
    }

    //流水ajax请求.返回已下单的文件和金额.
    @GetMapping("/Axax-orderLine")
    @ApiOperation("流水ajax请求,生成图表")
    @ResponseBody
    public Map<String, Object> createOrderLine() {
        //1. 查询所有数据
        List<Map<Object, Object>> list = initOrderService.selectFileAllOfMoney();
        //2. 最终结果的Map:X轴,文件id. Y轴:价格.
        Map<String, Object> map = new HashMap<>();
        //3. 存储文件名字(根据softid获取)
        List<String> nameList = new ArrayList<>();
        //4. 存储价格
        List<Double> priceList = new ArrayList<>();
        for (Map<Object, Object> objectMap : list) {
            //获取文件id,根据文件id获取文件名字
            Integer softid = (Integer) objectMap.get("softid");
            String softname = softService.selectNameById(softid);
            //添加过程
            nameList.add(softname);
            priceList.add((Double) objectMap.get("money"));
        }

        map.put("name", nameList);
        map.put("price", priceList);
        return map;
    }

    //跳转商品总览页面
    @GetMapping("/A-to-AllSoft")
    @ApiOperation("跳转商品总览页面")
    public String toAllSoft(@RequestParam("page") Integer page, Model model) {
        //分页查询数据
        //获取起始位置和末尾
        Integer start = (page - 1) * 10;
        Integer end = 10;
        //查询所有商品
        List<Soft> softs = softService.selectpageAllSoft(start, end);
        for (int i = 0; i < softs.size(); i++) {
            if (softs.get(i).getSofttext().length() > 25) {
                Soft soft = softs.get(i);
                soft.setSofttext(soft.getSofttext().substring(0, 25) + "...");
                softs.set(i, soft);
            }
        }
        //查询插件数量
        Integer num = softService.selectCjNum();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            list.add(i);
        }


        model.addAttribute("softList", softs);
        model.addAttribute("sfNum", list);
        model.addAttribute("sfState", page);
        model.addAttribute("PreState", page - 1);
        model.addAttribute("NextState", page + 1);

        return "ht/soft_show";
    }

    //跳转到秒杀配置界面
    @GetMapping("A-to-seckill")
    @ApiOperation("跳转到秒杀配置界面")
    public String toSeckillConfig() {
        return "ht/seckill_config";
    }

    //配置秒杀
    @PostMapping("/Skill-Config")
    @ApiOperation("秒杀配置接口")
    public String SkillConfig(@RequestParam("softid") Integer softid, Model model) {
        //添加到Redis缓存中.  定义一个Cacheing和一个CachePut
        // 查询Softid对应的文件内容,来判断当前参数是否存在于Mysql中
        Soft soft = softService.selectByPrimaryKey(softid);
        if (soft == null) {
            model.addAttribute("error", "当前秒杀ID不存在对应文件,请重新选择");
            return "ht/seckill_config";
        }
        redisUtil.sSet("skillid", softid);
        return "redirect:/Skill-Show";
    }

    //查询所有秒杀商品
    @GetMapping("/Skill-Show")
    @ApiOperation("查询所有秒杀商品")
    public String SkillShow(Model model) {
        Set<Object> skillSet = redisUtil.sGet("skillid");
        //存储缓存文件
        List<Soft> list = new ArrayList<>();
        for (Object o : skillSet) {
            Integer softid = Integer.valueOf(String.valueOf(o));
            //根据文件id查询对应文件
            Soft soft = softService.selectByPrimaryKey(softid);
            soft.setSoftimagepath(Md5Util.encodeByMD5(soft.getSoftimagepath()).substring(0, 15));
            soft.setSoftfileurl(Md5Util.encodeByMD5(soft.getSoftfileurl()).substring(0, 15));
            //限制软件描述大小
            if (soft.getSofttext().length() > 35) {
                soft.setSofttext(soft.getSofttext().substring(0,35) + "...");
            }
            soft.setSoftcreatime(new Date());
            list.add(soft);
            log.info("秒杀文件id: " + softid);
        }
        //执行到此处说明无异常发生. 执行更新缓存的操作
        redisUtil.set("skill_File", list);
        List<Soft> softList = (List<Soft>) redisUtil.get("skill_File");
        log.info("已完成缓存的更新及导入功能.");
        model.addAttribute("seckillList", softList);
        return "ht/seckill_store";
    }

    //移除指定ID,重定向到Skill-show
    @GetMapping("/Skill-delete")
    @ApiOperation("移除指定商品缓存")
    public String Skill_delete(@RequestParam("id")Integer id){
        redisUtil.setRemove("skillid",id);
        return "redirect:/Skill-Show";
    }

}
