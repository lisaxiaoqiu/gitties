package com.itheima.dao;

import com.itheima.po.Orders;

import java.util.List;

public interface OrdersMapper {
    //查询全部订单数据,使用resultType
    List<Orders> findAllOrders();

    //查询全部订单数据,使用resultMap
    List<Orders> findAllOrdersRM();
}
