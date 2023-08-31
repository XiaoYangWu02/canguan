package com.it.canguan.service.impl;

import com.it.canguan.pojo.Order;
import com.it.canguan.service.OrderService;
import com.it.canguan.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 武霄阳
* @description 针对表【order】的数据库操作Service实现
* @createDate 2023-08-30 10:23:25
*/
@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Order> searchOrderByCustomerId(Integer customerId) {
        List<Order> orderList = orderMapper.searchOrderByCustomerId(customerId);
        return orderList;
    }

    @Override
    public void insertOrder(Order order) {

        orderMapper.insertOrder(order);


    }
}
