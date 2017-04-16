package com.scorpion.service.impl;

import com.scorpion.dao.NewMapper;
import com.scorpion.pojo.NewsOfCompany;
import com.scorpion.service.SearchFromData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Scorpion on 2017/4/14.
 */
@Service
public class SearchFromDataImpl implements SearchFromData {
    @Autowired
    private NewMapper newMapper;

    @Override
    public List<NewsOfCompany> queryNum() {
        return newMapper.selectNewsNum();
    }
}
