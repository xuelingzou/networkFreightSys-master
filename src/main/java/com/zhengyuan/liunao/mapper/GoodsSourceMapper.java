package com.zhengyuan.liunao.mapper;

import com.zhengyuan.liunao.entity.GoodSource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsSourceMapper {
    int addGoodsSource(GoodSource goodSource);
}
