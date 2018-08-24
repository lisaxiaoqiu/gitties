package com.itheima.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


//创建SqlSessionFactory工具类
public class SqlSessionFactoryUtil {
    private static SqlSessionFactory sqlSessionFactory;
    //使用静态代码块加载
    static {
        try {
        //加载获取配置文件内容
            InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            //创建SqlSessionFactory对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            sqlSessionFactory = builder.build(inputStream);
        } catch (IOException e) {
            System.out.println("初始化异常"+e.getMessage());
        }
    }
    //提供一个公共的静态方法返回sqlSessionFactory
    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }
}
