package com.zlc.springboot.unti;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class SendEmailUtil {

    //注入Email
    @Autowired
    private JavaMailSenderImpl mailSender;

    //普通发送邮件
    public void PuTongSend(String subObject, String text, String[] toArr) {
        System.out.println("一共有" + toArr.length + "需要发送邮件---------------------");
        for (int i = 0; i < toArr.length; i++) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            //设置邮件主题
            mailMessage.setSubject(subObject);
            //设置邮件详情内容
            mailMessage.setText(text);
            //设置邮件发送者邮箱
            mailMessage.setFrom("1633079383@qq.com");
            //给数组中每个用户发送邮件(相同内容)
            mailMessage.setTo(toArr[i]);
            //开启发送功能.
            mailSender.send(mailMessage);

            //for循环发送五次邮件,为防止频繁发送,每次循环睡三秒
            System.out.println("发送邮件成功:" + (i + 1));

        }

        System.out.println("成功给" + toArr.length + "人发送邮件.");
    }

    //携带附件发送
    public void FileSend(String subObject, String text, String[] toArr, File[] file) throws MessagingException {

        //使用for循环给所有用户发送邮件(携带附件)
        for (int i = 0; i < toArr.length; i++) {
            //一个复杂的邮件
            MimeMessage mimeMailMessage = mailSender.createMimeMessage();
            //组装
            MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, true);
            //标题
            helper.setSubject("张先生你好");
            helper.setText("张先生早上好");

            //多个附件添加到邮件中
            for (int j = 0; j < file.length; j++) {
                File emailFile = file[i];
                //获取文件名
                String name = emailFile.getName();
                helper.addAttachment(name, file[i]);

            }


            helper.setFrom("1633079383@qq.com");
            //给多人发送邮件
            helper.setTo(toArr[i]);

            mailSender.send(mimeMailMessage);
        }
        System.out.println("成功给" + toArr.length + "人发送邮件.");
    }

}
