package com.zlc.springboot.unti;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sun.awt.shell.ShellFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Component
public class ImageCode {

//    @Value("${mogu.picture.upload}")
//    private static String FileImageUploadPath = "\\image";
    //本地
//    private static String FileImageUploadPath = "/www/wwwroot/image";
    private static String FileImageUploadPath = "D:\\mogu\\image";

    private static char mapTable[] = {


            '1', '2', '3', '4', '5', '6', '7', '8', '9',  '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static Map<String, Object> getImageCode(int width, int height, OutputStream os) {
        Map<String,Object> returnMap = new HashMap<String, Object>();
        //   JedisConfig jedisConfig = new JedisConfig();


        if (width <= 0) width = 80;
        if (height <= 0) height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 168; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        String strEnsure = "";
        for (int i = 0; i < 5; ++i) {
            strEnsure += mapTable[(int) (mapTable.length * Math.random())];
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            //直接生成
            String str = strEnsure.substring(i, i + 1);
            g.drawString(str, 13 * i + 6, 16);
        }
        g.dispose();

        returnMap.put("image",image);
        returnMap.put("strEnsure",strEnsure);

        return returnMap;
    }
    static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }


    //生成图片
    public static String makeLocalImage(String uploadFilePath){

        try {
            log.info("生成图片中:对应文件路径为:" +uploadFilePath);
            File file = new File(uploadFilePath);
            // 图标保存地址
            String imagePath = System.currentTimeMillis() + ".jpg";
            File imageFile = new File(FileImageUploadPath,imagePath);
            log.info("服务器端图片的绝对路径: " + imageFile);
            OutputStream inStream = new FileOutputStream(imageFile);
            // 通过awt.shellFolder获取图标 默认为32 *32

            ShellFolder shellFolder = ShellFolder.getShellFolder(file);
            log.info("shellFolder为:" + shellFolder);
            log.info("shellFolder的GetIcon为:" + shellFolder.getIcon(true));
            ImageIcon icon = new ImageIcon(shellFolder.getIcon(true));
            BufferedImage imgIcon = (BufferedImage) icon.getImage();
            // 调整icon图标大小，放大后会模糊
            imgIcon = resize(imgIcon,64,64);
            ImageIO.write(imgIcon, "png", inStream);
            inStream.flush();
            inStream.close();
            //获取图片绝对路径
//            String newPath = imageFile.getAbsolutePath();
//            //截取图片路径,从image开始
//            log.info("图片路径为:" + newPath);
//            newPath = "\\" + newPath.substring(newPath.lastIndexOf("image\\"),newPath.length());
//            System.out.println("截取之后的图片路径为:" + newPath);
            return "\\image\\" + imagePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 调整大小
     * @param img
     * @param newW
     * @param newH
     * @return
     */
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }
}
