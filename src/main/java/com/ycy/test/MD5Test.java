package com.ycy.test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by Administrator on 2015/10/15 0015.
 */
public class MD5Test {
    public static void main(String[] args) {
        //原始密码
        String source="111";
        //盐
        String salt="ycy";
        //散列次数
        int hashInterration=1;//MD5（****）一次
        Md5Hash md5Hash=new Md5Hash(source,salt,hashInterration);
        String password_md5=md5Hash.toString();
        System.out.println("111加盐MD5加密后为："+password_md5);
        //散列加密2
        SimpleHash simpleHash=new SimpleHash("md5",source,salt,hashInterration);
        System.out.println("111加盐MD5加密后为："+simpleHash.toString());
        //96c26c8872da9d2534bf7c31f499425d
    }
}
