package com.itheima.test;

import com.itheima.dao.UserMapper;
import com.itheima.po.QueryVo;
import com.itheima.po.User;
import com.itheima.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserMapperTest {

    //根据用户ID查询用户
    @Test
    public void findUserByIdTest(){
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
        //加载读取主配置文件内容
           inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
           //创建SqlSessionFactory对象,通过SqlSessionFactoryBuilder获得
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
            // 创建SqlSession对象,通过sqlSessionFactory对象获取
             sqlSession = sqlSessionFactory.openSession();
            //获取mapper接口代理对象,使用sqlSession对象获得
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //执行数据库语句
            User user = mapper.findUserById(24);
            System.out.println(user);
            //释放资源
            sqlSession.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 使用工具类查询数据
    @Test
    public void findUserByIdTest2(){
        //使用工具类获得SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        //创建sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建mapper代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //执行数据库语句
        User user = mapper.findUserById(24);
        System.out.println(user);
        //释放资源
        sqlSession.close();
    }
    //模糊查询用户
    @Test
    public void findUserByName(){
        //使用工具类创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        //创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建mapper代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //连接数据库
        List<User> username = mapper.findUserByName("小");
        for (User user : username) {
            System.out.println(user);
        }
        //释放资源
        sqlSession.close();
    }

    //新增用户
    @Test
    public void insertUser(){
        //使用工具类获得SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        //创建sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建mapper代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //执行数据库语句
        //封装User对象
        User user = new User();
        //user.setId(4);
        user.setUsername("琼姐");
        user.setBirthday(new Date());
        user.setSex("2");
        user.setAddress("无人区");
        System.out.println("执行前"+user);
        mapper.insertUser(user);
        System.out.println("执行后"+user);
        //手动提交事务 , 数据库的增,删,改,查,操作都需要事务的提交才能将数据提交到数据库
        // 事务的提交有两种,手动提交和自动提交,一个测试类只做一个数据库操作,那么可以选择自动提交事务,如果是
        // 进行多个操作,那么就选择手动提交事务
        //事务的四大特性: ACID  原子性,一致性,隔离性,持久性
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //根据用户ID修改用户
    @Test
    public void updateUser(){
        //根据工具类获得SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        //创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建mapper代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //执行数据库语句
        //封装User对象
        User user = new User();
        user.setId(3);
        user.setUsername("老钟");
        user.setSex("2");
        mapper.updateUser(user);
        sqlSession.commit();
        System.out.println(user);
        //释放资源
        sqlSession.close();
    }

    //根据用户ID删除用户
    @Test
    public void deleteUserById(){
        //使用工具类获得SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        //创建sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建mapper代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //执行数据库
        mapper.deleteUser(4);
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //使用pojo包装类,根据用户名称模糊查询用户
    @Test
    public void findUserByPojoTest(){
        /** pojo包装类就是在pojo中包含了其他的pojo.通常用于接收综合查询条件
         *
          */
        //使用工具类获得SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        //创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建mapper代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //执行数据库操作
        //创建QueryVo对象
        QueryVo queryVo = new QueryVo();
        //创建User对象
        User user = new User();
        user.setUsername("小");
        queryVo.setUser(user);
        List<User> userByUserNameForPojo = mapper.findUserByUserNameForPojo(queryVo);
        for (User u:userByUserNameForPojo) {
            System.out.println(u);
        }
        //释放资源
        sqlSession.close();
    }

    //统计用户表中的用户数量, resultType
    @Test
    public void countUserTest(){
        //使用工具类获得SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        //创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建mapper代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //执行数据库操作
        Integer integer = mapper.countUser();
        System.out.println("当前用户表中的用户数量是:"+integer);
        //释放资源
        sqlSession.close();
    }
}
