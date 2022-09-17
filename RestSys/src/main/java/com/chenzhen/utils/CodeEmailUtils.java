package com.chenzhen.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

public  class CodeEmailUtils {

    //发送激活邮件
    public  Boolean sendCodeMail(String to,String username,String code){
        InputStream in = CodeEmailUtils.class.getClassLoader().getResourceAsStream("activateEmail.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            String name = properties.getProperty("name");
            String password =properties.getProperty("password");
            String host = properties.getProperty("host");
            String from = properties.getProperty("from");
            String subject = properties.getProperty("codesubject");
            String encode = properties.getProperty("encode");
            String content = properties.getProperty("codecontent");
            content = MessageFormat.format(content,username,code);
            //发送邮件
            CodeEmailUtils emailUtils = new CodeEmailUtils();
            return  emailUtils.sendMail(name,password,host,to,from,subject,content,encode);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }

    //发送邮件的方法
    public boolean sendMail(String name,String password,String host,String to,String from ,String subject,String content,String encode){
        try {
            HtmlEmail email = new HtmlEmail();
            //设置发邮件的用户名和密码(授权密码)
            email.setAuthentication(name,password);
            //设置发送邮件服务器(smtp服务器的域名)
            email.setHostName(host);
            //收件人的邮箱
            email.addTo(to);
            //发件人的邮箱
            email.setFrom(from);
            //邮件的主题
            email.setSubject(subject);
            //设置编码方式、为了防止中文乱码
            email.setCharset(encode);
            //邮件的内容
            email.setHtmlMsg(content);
            //发送邮件
            email.send();
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }


    }
}
