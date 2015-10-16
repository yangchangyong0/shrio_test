package com.ycy.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Administrator on 2015/10/16 0016.
 * 权限授权
 */
public class authorizationTest {
    @Test
    public void testAuthorization(){
        //创建securityManager工厂
        Factory<SecurityManager> securityManagerFactory=new IniSecurityManagerFactory("classpath:shiro-permission.ini");

        //获取security
        SecurityManager securityManager=securityManagerFactory.getInstance();

        //蒋securityManager加入运行坏境
        SecurityUtils.setSecurityManager(securityManager);

        //建立subject
        Subject subject=SecurityUtils.getSubject();

        //创建用户令牌
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken("ycy","111");

        //先认证
        subject.login(usernamePasswordToken);
        System.out.println("用户时候通过认证：" + subject.isAuthenticated());

        //再权限授权验证(单个)
         Boolean isPermittedOne=  subject.isPermitted("user:create");
        System.out.println("单个权限认证："+isPermittedOne);
        //授权验证权限（多个）
        Boolean isPermittedAll=subject.isPermittedAll("user:create", "user:update");
        System.out.println("多个权限认证：" + isPermittedOne);


        //另外也可以角色验证权限（不建议）
        Boolean ishasRole=   subject.hasRole("role1");
        Boolean ishasRoleAll=  subject.hasAllRoles(Arrays.asList("role1","role2"));
        System.out.println("单个角色验证："+ishasRole+"|多个角色验证"+ishasRoleAll);
    }

        //自定义权限realm信息
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
            //授权。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
            //单个权限授权
            boolean ispermitted=subject.isPermitted("user:create");
            //多个权限授权
            boolean ispermittedAll=subject.isPermittedAll("user:create","items:add");

            System.out.println("单个授权："+ispermitted+"|多个授权"+ispermittedAll);
            //退出
            subject.logout();
        }

}
