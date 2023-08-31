package com.it.canguan.service;

import com.it.canguan.pojo.Food;

import java.util.List;

/**
* @author 武霄阳
* @description 针对表【food】的数据库操作Service
* @createDate 2023-08-28 19:13:53
*/
public interface FoodService {

    List<Food> getAllFood();

    List<Food> searchFoodByName(String searchKeyword);

    List<Food> getFood(String selectedFood);
}
