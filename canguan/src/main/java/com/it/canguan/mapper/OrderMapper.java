package com.it.canguan.mapper;

import com.it.canguan.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 武霄阳
* @description 针对表【order】的数据库操作Mapper
* @createDate 2023-08-30 10:23:25
* @Entity com.it.canguan.pojo.Order
*/
@Mapper
public interface OrderMapper{


    void insertOrder(Order order);

    List<Order> searchOrderByCustomerId(Integer customerId);
}
