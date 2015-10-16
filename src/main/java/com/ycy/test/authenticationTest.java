package com.ycy.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by Administrator on 2015/10/15 0015.
 * 认证测试
 */
public class authenticationTest {
    //用户登录和退出
    @Test
    public void testLoginAndLoginOut(){
        //创建一个securityManager 通过ini文件创建
        Factory<SecurityManager> securityManagerFactory=new IniSecurityManagerFactory("classpath:shrio-frist.ini");
//        XMLSecurityManager
        //创建SecurityManager
        SecurityManager securityManager=  securityManagerFactory.getInstance();
        //将SecurityManager创建到生成环境中
        SecurityUtils.setSecurityManager(securityManager);
        //从SecurityUtils 构建一个subject
        org.apache.shiro.subject.Subject subject=SecurityUtils.getSubject();
        //认证提交认证token
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken("ycy","222");
        //执行认证提交认证
        try {
            subject.login(usernamePasswordToken);
        }catch(AuthenticationException ex){
            ex.printStackTrace();
        }
        System.out.println("是否通过认证：" + subject.isAuthenticated());
        //退出
        subject.logout();
        System.out.println("是否通过认证：" + subject.isAuthenticated());
    }


    //用户登录和退出(自定义realm)
    @Test
    public void testCustomRealm(){
        //创建一个securityManager 通过ini文件创建
        Factory<SecurityManager> securityManagerFactory=new IniSecurityManagerFactory("classpath:shrio-realm.ini");
//        XMLSecurityManager
        //创建SecurityManager
        SecurityManager securityManager=  securityManagerFactory.getInstance();
        //将SecurityManager创建到生成环境中
        SecurityUtils.setSecurityManager(securityManager);
        //从SecurityUtils 构建一个subject
        org.apache.shiro.subject.Subject subject=SecurityUtils.getSubject();
        //认证提交认证token
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken("ycy","111");
        //执行认证提交认证
        try {
            subject.login(usernamePasswordToken);
        }catch(AuthenticationException ex){
            ex.printStackTrace();
        }
        System.out.println("是否通过认证：" + subject.isAuthenticated());
        //退出
        subject.logout();
        System.out.println("是否通过认证：" + subject.isAuthenticated());
    }


    //用户登录和退出(自定义realm)加密
    @Test
    public void testCustomRealmMd5(){
        //创建一个securityManager 通过ini文件创建
        Factory<SecurityManager> securityManagerFactory=new IniSecurityManagerFactory("classpath:shrio-realm-md5.ini");
//        XMLSecurityManager
        //创建SecurityManager
        SecurityManager securityManager=  securityManagerFactory.getInstance();
        //将SecurityManager创建到生成环境中
        SecurityUtils.setSecurityManager(securityManager);
        //从SecurityUtils 构建一个subject
        org.apache.shiro.subject.Subject subject=SecurityUtils.getSubject();
        //认证提交认证token
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken("ycy","111");
        //执行认证提交认证
        try {
            subject.login(usernamePasswordToken);
        }catch(AuthenticationException ex){
            ex.printStackTrace();
        }
        System.out.println("是否通过认证：" + subject.isAuthenticated());
        //退出
        subject.logout();
        System.out.println("是否通过认证：" + subject.isAuthenticated());
    }
}
