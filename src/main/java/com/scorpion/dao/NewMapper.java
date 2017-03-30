package com.scorpion.dao;

import com.scorpion.pojo.New;
import com.scorpion.pojo.NewWithBLOBs;

public interface NewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewWithBLOBs record);

    int insertSelective(NewWithBLOBs record);

    New selectByName(String name);

    NewWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(NewWithBLOBs record);

    int updateByPrimaryKey(New record);
}