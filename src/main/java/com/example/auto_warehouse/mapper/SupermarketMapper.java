package com.example.auto_warehouse.mapper;

import com.example.auto_warehouse.bean.ShopStorage;
import com.example.auto_warehouse.bean.Supermarket;
import com.example.auto_warehouse.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SupermarketMapper {
    Supermarket findById(String suid); //根据id查询超市，返回supermarket实体类
    List<ShopStorage> getStorage(String suid);
}
