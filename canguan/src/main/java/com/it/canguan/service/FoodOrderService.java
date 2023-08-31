package com.it.canguan.service;

import com.it.canguan.pojo.FoodOrder;

import java.util.List;

/**
* @author 武霄阳
* @description 针对表【food_order】的数据库操作Service
* @createDate 2023-08-30 10:24:52
*/
public interface FoodOrderService{

    void insertFoodOrder(Integer id, String foodStatus, Integer orderId);

    List<FoodOrder> getAllFoodOrder();
}
