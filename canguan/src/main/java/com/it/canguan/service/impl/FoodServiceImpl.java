package com.it.canguan.service.impl;

import com.it.canguan.pojo.Food;
import com.it.canguan.service.FoodService;
import com.it.canguan.mapper.FoodMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 武霄阳
* @description 针对表【food】的数据库操作Service实现
* @createDate 2023-08-28 19:13:53
*/
@Service
public class FoodServiceImpl implements FoodService{

    @Resource
    private FoodMapper foodMapper;

    @Override
    public List<Food> getAllFood() {
        List<Food> foodList = foodMapper.getAllFood();
        return foodList;
    }

    @Override
    public List<Food> searchFoodByName(String searchKeyword) {
        List<Food> foodList = foodMapper.searchFoodByName(searchKeyword);
        return foodList;
    }

    @Override
    public List<Food> getFood(String selectedFood) {
        List<Food> foodList = foodMapper.getFood(selectedFood);
        return foodList;
    }
}
