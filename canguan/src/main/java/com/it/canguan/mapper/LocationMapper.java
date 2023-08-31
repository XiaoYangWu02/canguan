package com.it.canguan.mapper;

import com.it.canguan.pojo.Location;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
* @author 武霄阳
* @description 针对表【location】的数据库操作Mapper
* @createDate 2023-08-29 09:04:50
* @Entity com.it.canguan.pojo.Location
*/
@Mapper
public interface LocationMapper {


    List<Location> getAllLocation();

    List<Location> searchLocationByName(String confirmLocation);
}
