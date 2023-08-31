package com.it.canguan.service.impl;

import com.it.canguan.pojo.FoodOrder;
import com.it.canguan.service.FoodOrderService;
import com.it.canguan.mapper.FoodOrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 武霄阳
* @description 针对表【food_order】的数据库操作Service实现
* @createDate 2023-08-30 10:24:52
*/
@Service
public class FoodOrderServiceImpl implements FoodOrderService{

    @Resource
    private FoodOrderMapper foodOrderMapper;

    @Override
    public void insertFoodOrder(Integer id, String foodStatus, Integer orderId) {
        foodOrderMapper.insertFoodOrder(id, foodStatus, orderId);
    }

    @Override
    public List<FoodOrder> getAllFoodOrder() {
        List<FoodOrder> foodOrderList = foodOrderMapper.getAllFoodOrder();
        return foodOrderList;
    }
}
