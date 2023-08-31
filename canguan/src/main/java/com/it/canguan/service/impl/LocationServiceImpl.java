package com.it.canguan.service.impl;

import com.it.canguan.pojo.Location;
import com.it.canguan.service.LocationService;
import com.it.canguan.mapper.LocationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 武霄阳
* @description 针对表【location】的数据库操作Service实现
* @createDate 2023-08-29 09:04:50
*/
@Service
public class LocationServiceImpl implements LocationService{

    @Resource
    private LocationMapper locationMapper;

    @Override
    public List<Location> getAllLocation() {
        List<Location> locationList = locationMapper.getAllLocation();
        return locationList;
    }

    @Override
    public List<Location> searchLocationByName(String confirmLocation) {
        List<Location> locationList = locationMapper.searchLocationByName(confirmLocation);
        return locationList;
    }
}
