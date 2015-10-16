package com.ycy.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/15 0015.
 * 自定义数据源
 */
public class CustomRealm extends AuthorizingRealm{

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       //authenticationToken为用户输入信息
        String userCode=(String)authenticationToken.getPrincipal();
        //模拟数据库操作查询用户
        if(!userCode.equals("ycy")){
            return null;
        }
        //查询密码为111
        String password="111";

        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(userCode,password,"customRealm");

        return info;
    }
    //用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
         //获取身份信息Principal
        //这里的值跟我们用户认证的时候SimpleAuthenticationInfo一样
        String usercode=(String)principalCollection.getPrimaryPrincipal();
        //查询数据库。。。。
        //模拟查询到数据
        List<String> permission=new ArrayList<String>();
        permission.add("user:create");
        permission.add("items:add");
        //..................
        //查询全选数据，返回授权信息
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        //增加权限信息
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }


}
