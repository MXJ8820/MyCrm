package com.yjxxt.service;

import com.yjxxt.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class EmailService {


    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public boolean sendMail(String toEmail, HttpServletRequest request){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        // 从哪发送
        simpleMailMessage.setFrom("504529528@qq.com");
        // 发送到哪
        simpleMailMessage.setTo(toEmail);
        // 发送的邮件标题
        simpleMailMessage.setSubject("注册验证");

        //生成随机数
        String random = randomInteger();

        request.getSession().setAttribute("code",random);

        // 设置验证码的样式
        simpleMailMessage.setText(random);
        try {
            javaMailSender.send(simpleMailMessage);
        }catch (MailException mx){
            mx.printStackTrace();
            return false;
        }

        return true;
    }

    private String randomInteger() {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        //生成6位的随机数
        for (int i = 0;i<6;i++){
            int i1 = random.nextInt(10);
            stringBuffer.append(i1);
        }
        return stringBuffer.toString();
    }
}
