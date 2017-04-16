package com.scorpion.service;

import com.scorpion.pojo.NewsOfCompany;

import java.util.List;

/**
 * 从数据库获取相关信息
 * Created by Scorpion on 2017/4/14.
 */
public interface SearchFromData {
    /*
    搜索数据库中新闻量前二十的公司及其新闻量
     */
    public List<NewsOfCompany> queryNum();
}
