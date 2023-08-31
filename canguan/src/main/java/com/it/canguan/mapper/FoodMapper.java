package com.it.canguan.mapper;

import com.it.canguan.pojo.Food;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 武霄阳
* @description 针对表【food】的数据库操作Mapper
* @createDate 2023-08-28 19:13:53
* @Entity com.it.canguan.pojo.Food
*/
@Mapper
public interface FoodMapper {
    List<Food> getAllFood();

    List<Food> searchFoodByName(String searchKeyword);

    List<Food> getFood(String selectedFood);
}
