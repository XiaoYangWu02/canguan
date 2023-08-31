package com.it.canguan.service;

import com.it.canguan.pojo.Location;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
* @author 武霄阳
* @description 针对表【location】的数据库操作Service
* @createDate 2023-08-29 09:04:50
*/
public interface LocationService {

    List<Location> getAllLocation();

    List<Location> searchLocationByName(String confirmLocation);
}
