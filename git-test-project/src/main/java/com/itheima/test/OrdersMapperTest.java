package com.itheima.test;

import com.itheima.dao.OrdersMapper;
import com.itheima.po.Orders;
import com.itheima.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

public class OrdersMapperTest {
    //查询全部订单数据,使用resultType
    @Test
    public void findAllOrdersTest(){
        //使用工具类获得SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        //创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建mapper代理对象
        OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);
        //执行数据库操作
        List<Orders> allOrders = mapper.findAllOrders();
        for (Orders o :allOrders) {
            System.out.println(o);
        }
        //释放资源
        sqlSession.close();
        /** 控制台结果出现null,说明不能完成映射.使用resultType完成映射,要求sql语句中的字段名称与java实体类中的属性名称
         * 一致,否则不能完成映射.
         *
          */
    }

    //使用resultMap实现查询全部订单数据
    /** resultMap用于配置sql语句中字段的名称与java实体类型属性名称的对应关系.本质上还是要把结果把j执行结果映射到java
     *  对象中.
     */
    @Test
    public void findAllOrdersRMTest(){
        //使用工具类获得SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        //创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建mapper代理对象
        OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);
        //执行数据库操作
        List<Orders> allOrdersRM = mapper.findAllOrdersRM();
        for (Orders o :allOrdersRM) {
            System.out.println(o);
        }
        //释放资源
        sqlSession.close();
    }
}
