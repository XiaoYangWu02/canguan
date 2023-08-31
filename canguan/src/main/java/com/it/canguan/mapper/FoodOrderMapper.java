package com.it.canguan.mapper;

import com.it.canguan.pojo.FoodOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 武霄阳
* @description 针对表【food_order】的数据库操作Mapper
* @createDate 2023-08-30 10:24:52
* @Entity com.it.canguan.pojo.FoodOrder
*/
@Mapper
public interface FoodOrderMapper {

    void insertFoodOrder(@Param("id") Integer id, @Param("foodStatus") String foodStatus, @Param("orderId") Integer orderId);

    List<FoodOrder> getAllFoodOrder();
}
