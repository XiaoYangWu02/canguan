package com.it.canguan.service;

import com.it.canguan.pojo.Order;

import java.util.List;

/**
* @author 武霄阳
* @description 针对表【order】的数据库操作Service
* @createDate 2023-08-30 10:23:25
*/
public interface OrderService {

    List<Order> searchOrderByCustomerId(Integer customerId);

    void insertOrder(Order order);
}
