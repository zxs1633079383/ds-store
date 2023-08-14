package com.zlc.springboot.controller;


import com.zlc.springboot.model.IpAddress;
import com.zlc.springboot.model.Login;
import com.zlc.springboot.model.Soft;
import com.zlc.springboot.service.InitOrderService;
import com.zlc.springboot.service.IpAddressService;
import com.zlc.springboot.service.LoginService;
import com.zlc.springboot.service.SoftService;
import com.zlc.springboot.unti.FileUntil;
import com.zlc.springboot.unti.IPUtils;
import com.zlc.springboot.unti.IpCountry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

//主页面的Controller
@Controller
@Slf4j
@Api(tags = "主页面商城接口")
public class StoreController {

    //注入SoftMapper
    @Autowired
    private SoftService softService;
    //上传文件路径
//    private static final String FileUploadPath = "/www/wwwroot/file";
    private static final String FileUploadPath = "D:\\mogu\\file";
    //上传文件内部详情的图片保存路径
//    private static String FileImageUploadPath = "/www/wwwroot/image";
    private static String FileImageUploadPath = "D:\\mogu\\image";
    @Autowired
    private InitOrderService initOrderService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private IpAddressService ipAddressService;

    //全部文件请求
    @GetMapping("/Soft-All")
    @ResponseBody
    public List<Soft> SelectSoftAll(HttpSession session) {

        //用户已登录,价格展示零元
        if (session.getAttribute("user") != null) {
            //获取所有文件信息
            List<Soft> softList = softService.selectSoftAll("allSoft");
            //限制首页文件描述信息最多为10个字,超过10个截取然后加上省略号.
            for (int i = 0; i < softList.size(); i++) {
                String softText = softList.get(i).getSofttext();
                //判断描述信息是否大于10.如果大于10则截取.
//                softList.get(i).setSoftprice(0.0);
                if (softText.length() >= 15) {
                    softText = softText.substring(0, 13) + "...";
                    softList.get(i).setSofttext(softText);
                }
            }
            return softList;
            //用户未登录,价格展示应有的价格
        } else {
            //获取所有文件信息
            List<Soft> softList = softService.selectSoftAll("allSoft");
            //限制首页文件描述信息最多为10个字,超过10个截取然后加上省略号.
            for (int i = 0; i < softList.size(); i++) {
                String softText = softList.get(i).getSofttext();
                //判断描述信息是否大于10.如果大于10则截取.
                if (softText.length() >= 15) {
                    softText = softText.substring(0, 13) + "...";
                    softList.get(i).setSofttext(softText);
                }
            }
            return softList;
        }

    }

    //精品文件请求
    @GetMapping("/Soft-Boutique")
    @ResponseBody
    @ApiOperation("精品文件请求接口")
    public List<Soft> SelectSoftOfBoutique(HttpSession session) {
        //获取所有文件信息
        List<Soft> softList = softService.selectSoftOfBoutique("Boutique");
        //限制首页文件描述信息最多为10个字,超过10个截取然后加上省略号.
        if (session.getAttribute("user") != null) {
            for (int i = 0; i < softList.size(); i++) {
                String softText = softList.get(i).getSofttext();
                //判断描述信息是否大于10.如果大于10则截取.
//                softList.get(i).setSoftprice(0.0);
                if (softText.length() >= 15) {
                    softText = softText.substring(0, 13) + "...";
                    softList.get(i).setSofttext(softText);
                }
            }
            return softList;
        } else {
            for (int i = 0; i < softList.size(); i++) {
                String softText = softList.get(i).getSofttext();
                //判断描述信息是否大于10.如果大于10则截取.
                if (softText.length() >= 15) {
                    softText = softText.substring(0, 13) + "...";
                    softList.get(i).setSofttext(softText);
                }
            }
            return softList;
        }
    }

    //销量最高文件请求
    @GetMapping("/Soft-ManyCount")
    @ResponseBody
    @ApiOperation("销量最高文件请求接口")
    public List<Soft> SelectSoftOfManyCount(HttpSession session) {
        //获取所有文件信息
        List<Soft> softList = softService.selectSoftOfManyCount("ManyCount");
        if (session.getAttribute("user") != null) {
            //限制首页文件描述信息最多为10个字,超过10个截取然后加上省略号.
            for (int i = 0; i < softList.size(); i++) {
                String softText = softList.get(i).getSofttext();
                //判断描述信息是否大于10.如果大于10则截取.
//                softList.get(i).setSoftprice(0.0);
                if (softText.length() >= 15) {
                    softText = softText.substring(0, 13) + "...";
                    softList.get(i).setSofttext(softText);
                }
            }
            return softList;
        } else {
            //限制首页文件描述信息最多为10个字,超过10个截取然后加上省略号.
            for (int i = 0; i < softList.size(); i++) {
                String softText = softList.get(i).getSofttext();
                //判断描述信息是否大于10.如果大于10则截取.
                if (softText.length() >= 15) {
                    softText = softText.substring(0, 13) + "...";
                    softList.get(i).setSofttext(softText);
                }
            }
            return softList;
        }
    }

    //免费文件请求
    @GetMapping("/Soft-Free")
    @ResponseBody
    @ApiOperation("免费文件请求接口")
    public List<Soft> SelectSoftOfFree() {
        //获取所有文件信息
        List<Soft> softList = softService.selectSoftOfFree("free");
        //限制首页文件描述信息最多为10个字,超过10个截取然后加上省略号.
        for (int i = 0; i < softList.size(); i++) {
            String softText = softList.get(i).getSofttext();
            //判断描述信息是否大于10.如果大于10则截取.
            if (softText.length() >= 15) {
                softText = softText.substring(0, 13) + "...";
                softList.get(i).setSofttext(softText);
            }
        }
        return softList;
    }

    //提交软件需求请求.


    //上传软件需求请求.
    //上传文件页面
//    @RequestMapping("/Soft-fileUpload")
//    @ResponseBody
    @PostMapping("/Soft-fileUpload")
    @ApiOperation("上传文件请求")
    public String SoftUpload(@RequestParam("softname") String softname, @RequestParam("softprice") double sofrprice,
                             @RequestParam("softtext") String softtext, @RequestParam("uploadFile") MultipartFile upfile,
                             @RequestParam("uploadFileImage") MultipartFile[] fileImages, Model model, HttpSession session) {


        log.info("进行到上传文件页面的请求---->");
//        //判断该用户是否登录
//        if (session.getAttribute("user") == null){
//            model.addAttribute("error", "请先登录该网站账号,方可进行文件上传.");
//            return "show/upload";
//        }

        //创建Soft实体类
        Soft soft = new Soft();

        //获得上传文件的用户
        String user = String.valueOf(session.getAttribute("user"));
        //获得软件名称,上传者,和当前时间,价格
        soft.setSoftname(softname);
        soft.setSoftfather(user);
        soft.setSoftcreatime(new Date());
        soft.setSoftprice(sofrprice);

        //1.判断上传的文件是否为空
        if (upfile != null) {

            //获取上传文件后缀名
            String uploadOff = upfile.getOriginalFilename();
            String uploadFIleNameOff = uploadOff.substring(uploadOff.lastIndexOf(".") + 1, uploadOff.length());
            //判断是不是php文件
            if (uploadFIleNameOff.equals("php") || uploadFIleNameOff.equals("sh")) {
                model.addAttribute("error", "该文件为系统禁止上传文件格式,请重新选择");
                return "show/upload";
            }

            //2. 判断内置展示图片是否为空
            if (fileImages != null) {
                //for循环,遍历MuliFile数据,判断后缀名是否为jpg.或png.
                log.info("用户上传了" + fileImages.length + "张软件内部描述图片");
                for (MultipartFile file : fileImages) {
                    String nameOff = file.getOriginalFilename();
                    //获取文件后缀名
                    String fileNameOff = nameOff.substring(nameOff.lastIndexOf(".") + 1, nameOff.length());
                    //判断软件内置展示图片是否符合图片格式
                    if (fileNameOff.equals("jpeg") || fileNameOff.equals("jpg") || fileNameOff.equals("png") || fileNameOff.equals("bmp")) {

                    } else {
                        model.addAttribute("error", "软件展示图片上传失败,文件格式错误");
                        return "show/upload";
                    }
                }
            } else {
                //执行到这里则说话,内置展示图片为空
                model.addAttribute("error", "软件内部展示图片为空,请重新选择.");
                return "show/upload";
            }

            //*****以上均为逻辑判断过滤

            //获取上传文件的大小.
            long fileLength = upfile.getSize();
            double length = fileLength / 1024 / 1024;
            //获取上传文件的大小(单位:MB)
            soft.setSoftlength(length);

            //softFilePath 获取上传文件的名字 + UUID随机生成不重复的字符串.
            String UUIDstr = String.valueOf(UUID.randomUUID());
            UUIDstr = UUIDstr.substring(UUIDstr.length() - 6, UUIDstr.length());
            String fileName = upfile.getOriginalFilename();
            //获取FIleName前缀
            String name = fileName.substring(0, fileName.lastIndexOf(".")) + UUIDstr;
            //获取FIlename后缀
            fileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());

            //测试环境.
//            fileName = name + fileName;
            //线上环境
            fileName = name + fileName;


            //默认上传文件为审核状态.
            soft.setSoftstate(0);
            //获取上传文件然后转换为FIle对象.
            File file = null;
            try {
                file = new File(FileUploadPath, fileName);
                //修改FIleName的值为 /file/****
                fileName = "/file/" + fileName;
                //获取文件名字,状态
                soft.setSoftfileurl(fileName);
            } catch (Exception e) {
                log.error("UploadFile为空,无法进行上传操作");
            }

            //文件流输入到指定目录
            try {
                upfile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //SoftIconPath 文件展示图标保存目录(获取上传文件对应的文件图标,保存的图标路径)
            //本地
//            String image = ImageCode.makeLocalImage(file.getAbsolutePath());
            //服务器
            log.info("服务器图片地址为: " + FileImageUploadPath);
//            String image = ImageCode.makeLocalImage(FileUploadPath+"/"+ fileName);
            //获取图片
            soft.setSofticonpath("/image/mr.jpg");

            //软件描述图片(三张,获取图片上传的图片)
            // 循环几次就创建几次图片文件
            //SoftImagePath 创建String变量,保存内置文件名
            String imagesPath = "";
            for (MultipartFile multipartFile : fileImages) {
                String ranImage = String.valueOf(System.currentTimeMillis()) + ".jpg";
                File ranFile = new File(FileImageUploadPath, ranImage);
                //数据流倒灌,输入到对应的File类里边.
                try {
                    multipartFile.transferTo(ranFile);
                    ranImage = "/image/" + ranImage + ";";
                    imagesPath += ranImage;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //去掉最后一个位置的分号
            imagesPath = imagesPath.substring(0, imagesPath.length() - 1);
            //获取软件详情图片
            soft.setSoftimagepath(imagesPath);
            //获取软件描述信息
            String fileText = softtext;
            soft.setSofttext(fileText);

            //完成上传文件信息为:
            log.info("Soft == :" + soft.toString());
            //新增上传文件信息到数据库中
            try {
                softService.insertSelective(soft);
                log.info("√ 上传文件成功");
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("error", "上传数据库报错,请选择管理员.");
                return "show/upload";
            }
            model.addAttribute("succuess", "√ 软件上传成功");
            return "show/upload";
        } else {
            //如果执行到了Else则说明,上传文件为空.
            model.addAttribute("error", "上传文件为空,请选择文件.");
            return "show/upload";
        }
    }


    //单个软件详情查询(根据SoftID进行查询)
    @GetMapping("/Soft-SelectOne/{softid}")
    public String SoftSelectOneByIdController(@PathVariable("softid") Integer softid, Model model, HttpSession session) {
        //判断是否是成功之后跳转的界面,如果是支付成功跳转则,重定向发送下载软件请求.
        if (session.getAttribute("softFilepath") != null) {
            String filepath = String.valueOf(session.getAttribute("softFilepath"));
            return "redirect:/toDown/" + filepath;
        }

        log.info("进入到软件详情页面查询,查询id为: " + softid);
        //根据主键进行软件相关信息查询.
        Soft soft = softService.selectByPrimaryKey(softid);
        log.info("查询到的数据为:" + soft.toString());

        //创建image路径的数据中,用List集合存储
        List<String> imagePath = new ArrayList<>();
        //获取内置展示图片路径
        String[] imgArr = soft.getSoftimagepath().split(";");
        //遍历图片路径,保存到list集合中
        for (String s : imgArr) {
            imagePath.add(s);
        }

        //遍历list集合
        for (String s : imagePath) {
            System.out.println("list: " + s);
        }
        System.out.println("list集合大小为:" + imagePath.size());
        model.addAttribute("list", imagePath);
        model.addAttribute("soft", soft);
        return "show/showStroe";
    }

    //支付成功之后,利用浏览器下载指定文件

    /***
     *
     * @param filepath  需要下载的文件名.
     * @param request
     * @param session
     * @return
     */
    @GetMapping("/toDown/{filepath}")
    public ResponseEntity<FileSystemResource> toDownFileOnServer(@PathVariable("filepath") String filepath, HttpServletRequest request, HttpSession session) {
        //如果状态为刚支付完成,则可以进行下载.


        log.info("浏览器正在下载文件-->" + filepath);
//        //线上测试环境
//        return null;

        //线上环境
        session.setAttribute("state",1);
        if (session.getAttribute("state") != null) {
//        if (session.getAttribute("state") == null) {
            //测试环境
            filepath = "D:\\mogu\\file\\" + filepath;
            //线上环境
//            filepath = "/www/wwwroot/file/" + filepath;
            log.info("state为: " + session.getAttribute("state"));
            File file = new File(filepath);
            log.info("下载文件大小为: " + file.length());
            log.info("客户正在下载软件,软件大小为:" + file.length());

            //测试环境---> 利用浏览器下载文件, 然后清除state的Session.
            session.removeAttribute("state");
            log.info("state的Session是否清除成功:" + session.getAttribute("state"));
            ResponseEntity<FileSystemResource> test = FileUntil.export(file);
            FileSystemResource body = test.getBody();
            try {
                System.out.println(body.contentLength());
                File testFile = body.getFile();
                System.out.println("file大小为: " + testFile.length());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return test;

        } else {

            //如果当前Session不存在则返回NUll.
            log.info("用户未购买-->");
            log.info("当前Session的State为Null");
            return null;
        }
    }






    /***
     *  跳转链接*******************************************************************
     */
    //商店首页.
    @GetMapping("/A-to-store")
    @ApiOperation("页面跳转")
    public String to_Store() {
        return "/show/store";
    }

    @GetMapping("/")
    public String goTo_store(HttpServletRequest request){
        //根目录记录访问Ip(记录访问页面用户数量)
        String ip = IPUtils.getIpAddr(request);

        //声明实体类对象,保存到数据库中
        IpAddress address = new IpAddress();
        address.setAddress(ip);
        //根据ip获取对应地区
        String country = IpCountry.ipSite(ip);
        address.setCountry(country);
        address.setIpstate("1");
        address.setIptotime(new Date());
        //把用户的ip记录到后台界面
        ipAddressService.insert(address);

        //跳转到新首页
        return "show/store.html";
    }

    //需求页面.
    @GetMapping("/A-to-dosoft")
    @ApiOperation("页面跳转")
    public String to_dosoft() {
        return "/show/dosoft";
    }

    //服务器搭建页面
    @GetMapping("/A-to-server")
    @ApiOperation("页面跳转")
    public String to_server() {
        return "/show/server";
    }

    //注册页面
    @GetMapping("/A-to-Logto")
    @ApiOperation("页面跳转")
    public String to_logto() {
        return "logto";
    }

    //登录页面
    @GetMapping("/A-to-Login")
    @ApiOperation("页面跳转")
    public String to_Login() {
        return "login";
    }

    //文件上传页面
    @GetMapping("/A-to-fileUpload")
    @ApiOperation("页面跳转")
    public String to_FileUpload() {
        return "/show/upload";
    }

    //后台首页
    @GetMapping("/A-ht-index")
    @ApiOperation("后台首页")
    public String to_ht_index(Model model) {
        //查询订单总量,查询订单数量.
        Map<Object,Object> map = initOrderService.selectNumAndMoney();
        model.addAttribute("map",map);
        //查询已注册用户数量
        Integer num = loginService.selectUserNum();
        model.addAttribute("userNum",num);
        //查询当前时间
        String time = new SimpleDateFormat("MMM dd yyyy", Locale.UK).format(new Date());
        model.addAttribute("time",time);
        return "ht/index";
    }

    //PV 与UV数据构造
    @GetMapping("/createPvAndUv")
    @ApiOperation("PV与UV数据构造")
    @ResponseBody
    public HashMap<String,Object> create_Pv_Uv(){
        //查询PV对应数据
        List<Map<Object,Object>> mapList = ipAddressService.selectPV();
        //查询UV对应数据
        List<Map<Object,Object>> uvMapList = ipAddressService.selectUv();

        //构建HashMap返回给前端.

        HashMap<String,Object> map = new HashMap();

        //Pv数值
        List<Long> pvList = new ArrayList<>();
        //Uv数值
        List<Long> uvList = new ArrayList<>();

        //Pv坐标
        List<String> pvAddr = new ArrayList<>();
        //遍历List,获取有用信息.
        for (Map<Object, Object> ObjectMap : mapList) {
            //遍历HashMap
            Long sum = (Long) ObjectMap.get("count");
            pvList.add(sum);
            String time = String.valueOf(ObjectMap.get("time"));
            time= time.substring(6,time.length());
            pvAddr.add(time);
        }


        //处理UV数据
        HashMap map2 = new HashMap();
        for (Map<Object, Object> objectObjectMap : uvMapList) {
            String time = String.valueOf(objectObjectMap.get("time"));
            if (!(map2.containsKey(time))){
                map2.put(time,1);
            }else{
                Integer num = (Integer) map2.get(time);
                map2.put(time,num+1);
            }
        }

        Collection values = map2.values();
        for (Object object:values){
            uvList.add(Long.valueOf(String.valueOf(object)));
        }


        //将数据添加到HashMap中
        map.put("pvNum",pvList);
        map.put("uvNum",uvList);
        map.put("pvAddr",pvAddr);

        return map;
    }


}
